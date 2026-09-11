package com.vetSystem.Service;

import java.util.List;
import java.util.Optional;

public interface InterfaceService<T> {

    T registrarEntidad(T entidad);

    Optional<T> buscarPorId(Long id);

    void eliminarEntidad(Long id);

    T modificarEntidad(T entidad);

    List<T> listarEntidades();

    Optional<T> buscarPorString(String nombre);
}
