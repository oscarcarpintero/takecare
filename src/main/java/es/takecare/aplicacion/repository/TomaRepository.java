package es.takecare.aplicacion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.takecare.aplicacion.model.Paciente;
import es.takecare.aplicacion.model.Toma;

public interface TomaRepository extends JpaRepository<Toma, Integer> {

    List<Toma> findByPaciente(Paciente paciente);

    List<Toma> findByPacienteOrderByFecha(Paciente paciente);

    List<Toma> findByPacienteAndFecha(Paciente paciente, Date date);

    void deleteByPaciente(Paciente paciente);

}
