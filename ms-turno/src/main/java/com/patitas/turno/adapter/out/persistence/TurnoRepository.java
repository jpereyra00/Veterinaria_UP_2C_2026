package com.patitas.turno.adapter.out.persistence;

import com.patitas.turno.domain.model.Turno;
import com.patitas.turno.domain.port.out.TurnoRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>, TurnoRepositoryPort {

    List<Turno> findByVeterinarioId(Long veterinarioId);
    List<Turno> findByMascotaId(Long mascotaId);

    @Override
    default List<Turno> buscarTodos() {
        return findAll();
    }

    @Override
    default Turno buscarPorId(Long id) {
        return findById(id).orElse(null);
    }

    @Override
    default List<Turno> buscarPorVeterinarioId(Long veterinarioId) {
        return findByVeterinarioId(veterinarioId);
    }

    @Override
    default List<Turno> buscarPorMascotaId(Long mascotaId) {
        return findByMascotaId(mascotaId);
    }

    @Override
    default Turno guardar(Turno turno) {
        return save(turno);
    }
}
