package es.takecare.aplicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.takecare.aplicacion.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {


}
