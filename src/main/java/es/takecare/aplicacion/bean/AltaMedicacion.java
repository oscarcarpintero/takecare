package es.takecare.aplicacion.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import es.takecare.aplicacion.model.Medicamento;
import es.takecare.aplicacion.model.Paciente;

public class AltaMedicacion {

    private Medicamento medicamento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inicio;
    private List<String> frecuencia;
    private Integer duracion;
    private Paciente paciente;
    private Integer dosis;

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public List<String> getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(List<String> frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

}
