package adapter.in.web.dto;


import domain.model.Duenio;

public final class DuenioMapper {
    private DuenioMapper(){

            }
    //tomar el Dto y pasarlo al dominio
    public static Duenio toEntity(DuenioRequest duenioRequest) {
        Duenio duenio = new Duenio();
        duenio.setNombre(duenioRequest.getNombre());
        duenio.setApellido(duenioRequest.getApellido());
        duenio.setCedula(duenioRequest.getCedula());
        duenio.setTelefono(duenio.getTelefono());
        duenio.setEmail(duenioRequest.getEmail());
        return duenio;
    }
    public static DuenioResponse toResponse(Duenio duenio) {
        return new DuenioResponse(
                duenio.getId(),
                duenio.getNombre(),
                duenio.getApellido(),
                duenio.getCedula(),
                duenio.getTelefono(),
                duenio.getEmail());
            }

}
