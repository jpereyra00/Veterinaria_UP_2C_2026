package domain.exception;

public class DuenioNotFoundException extends RuntimeException {
    public DuenioNotFoundException(Long id) {
        super("No se encontro el duenio con el id " + id);
    }
}
