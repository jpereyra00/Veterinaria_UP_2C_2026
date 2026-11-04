package com.patitas.ms_auth.application;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Corazón de la seguridad: crea y verifica tokens JWT.
 *
 * Un JWT tiene 3 partes separadas por puntos -> Header.Payload.Signature
 *   - Header   : el algoritmo de firma (acá HS256).
 *   - Payload  : los "claims" (datos): sub=username, rol, iat=emitido, exp=expira.
 *   - Signature: HMAC-SHA256 del header+payload usando la CLAVE SECRETA.
 *
 * Como la firma depende de la clave secreta, cualquiera que tenga esa clave puede
 * VERIFICAR que el token no fue modificado, SIN consultar la base de datos. Si
 * cambian una coma del payload, la firma deja de coincidir y el token se rechaza.
 *
 * Esta MISMA lógica de validación la repite el API Gateway (con la MISMA clave).
 */
@Service
public class JwtService {

    /** Milisegundos de validez del token: 86_400_000 ms = 24 horas. */
    private static final long EXPIRACION_MS = 86_400_000L;

    /**
     * La clave secreta se inyecta desde la config (jwt.secret). Debe tener al menos
     * 256 bits (= 32 caracteres ASCII): JJWT 0.12.x lo exige para firmar con HS256.
     * Es la MISMA clave que usa el api-gateway para verificar (ver regla de oro).
     */
    private final SecretKey signingKey;

    public JwtService(@Value("${jwt.secret}") String secret) {
        // Convierte el String de config en una SecretKey apta para HMAC-SHA.
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Genera un token firmado para un usuario y su rol.
     * subject = username; agregamos "rol" como claim propio; fechas iat y exp.
     */
    public String generateToken(String username, String rol) {
        Date ahora = new Date();
        Date expira = new Date(ahora.getTime() + EXPIRACION_MS);

        return Jwts.builder()
                .subject(username)              // claim "sub"
                .claim("rol", rol)              // claim propio
                .issuedAt(ahora)                // claim "iat"
                .expiration(expira)             // claim "exp" (24hs)
                .signWith(signingKey)           // firma con la clave secreta (HS256)
                .compact();                     // arma el String Header.Payload.Signature
    }

    /**
     * Valida un token: comprueba que la firma sea correcta y que no esté expirado.
     * @return true si es válido; false si la firma no coincide, está vencido o malformado.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(signingKey)     // verifica la firma con la clave
                    .build()
                    .parseSignedClaims(token);  // si algo está mal -> lanza JwtException
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Firma inválida, token expirado, formato incorrecto, token vacío...
            return false;
        }
    }

    /** Extrae el username (claim "sub") de un token ya validado. */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /** Parsea el token y devuelve sus claims (datos del payload). */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
