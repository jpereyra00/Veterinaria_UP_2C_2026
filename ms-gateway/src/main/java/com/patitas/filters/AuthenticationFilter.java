package com.patitas.filters;

import com.patitas.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. Ruta de la petición entrante.
        String path = exchange.getRequest().getPath().value();

        // 2. Rutas públicas: login y registro pasan SIN token (si no, no habría
        //    forma de obtener el primer token). Es la excepción de la regla.
        if (path.startsWith("/auth/")) {
            return chain.filter(exchange);
        }

        // 3. Header Authorization. Debe venir como:  Authorization: Bearer <token>
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // 4. Sin header o sin prefijo "Bearer " -> 401 (no autenticado).
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return rechazar(exchange);
        }

        // 5. Quitar el prefijo "Bearer " (7 caracteres) para quedarnos con el token.
        String token = authHeader.substring(7);

        // 6. Token inválido (firma incorrecta / expirado / malformado) -> 401.
        if (!jwtService.validateToken(token)) {
            return rechazar(exchange);
        }

        // 7. Token válido: reenviamos la petición al microservicio agregando el
        //    username como header X-Auth-User. Así los ms de negocio pueden saber
        //    QUIÉN hace la petición (útil para auditoría) sin volver a validar el token.
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("X-Auth-User", jwtService.extractUsername(token))
                .header("X-Auth-Rol", jwtService.extractRol(token))
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }

    /** Respuesta 401 Unauthorized reactiva: setea el status y cierra el intercambio. */
    private Mono<Void> rechazar(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    /**
     * getOrder() = 0. El LoggingFilter usa -1 (corre primero, así loguea TODO,
     * incluidas las peticiones que este filtro rechaza con 401). Menor número =
     * mayor prioridad; con 0 la autenticación corre justo después del logging.
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
