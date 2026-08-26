package com.vetSystem.Service;

import com.vetSystem.DTO.TurnoRequesDTO;
import com.vetSystem.DTO.TurnoResponseDTO;
import com.vetSystem.Entity.Estado;
import com.vetSystem.Entity.Mascota;
import com.vetSystem.Entity.Turno;
import com.vetSystem.Entity.Veterinario;
import com.vetSystem.Exception.ResourceNotFoundException;
import com.vetSystem.Mapper.TurnoMapper;
import com.vetSystem.Repository.MascotaRepository;
import com.vetSystem.Repository.TurnoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private final MascotaRepository mascotaRepository;
    //private final VeterinarioRepository veterinarioRepository;
    @Autowired
    private final TurnoMapper turnoMapper;

    @Transactional
    public TurnoResponseDTO crearTurno(TurnoRequesDTO turnoRequesDTO) {
        Mascota mascota = mascotaRepository.findById(turnoRequesDTO.getIdMascota())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Mascota", turnoRequesDTO.getIdMascota()));
        //busco veterinario
        // veterinario ya esta comprometido en dia y horario?
        Turno turno= new Turno();
        turno.setFecha(turnoRequesDTO.getFecha());
        turno.setHora(turnoRequesDTO.getHora());
        turno.setMotivo(turnoRequesDTO.getMotivo());
        turno.setMascota(mascota);
        turno.setTurnoEstado(Estado.Programado);
        //aca faltan valores
        return turnoMapper.toDto(turnoRepository.save(turno));

    }
}
