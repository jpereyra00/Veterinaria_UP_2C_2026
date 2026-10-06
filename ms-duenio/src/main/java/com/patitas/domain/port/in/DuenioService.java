package domain.port.in;

import domain.model.Duenio;

import java.util.List;
//es una puerta de entrada. Define que puede hacer con los duenios. No deberia decir COMO.
public interface DuenioService {

    List<Duenio> listarTodos();
    Duenio registrar(Duenio duenio);
    Duenio actualizar(Long id, Duenio duenio);
    void eliminarPorId(Long id);
    Duenio buscarPorId(Long id);
}
