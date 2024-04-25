package es.takecare.aplicacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.takecare.aplicacion.model.Paciente;
import es.takecare.aplicacion.model.Ubicacion;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

     List<Paciente> findByUbicacion(Ubicacion ubicacion);

    Optional<Paciente> findByNombre(String principal);

    Optional<Paciente> findByNombreAndPassword(String user, String password);

}
