package es.takecare.aplicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.takecare.aplicacion.model.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
}