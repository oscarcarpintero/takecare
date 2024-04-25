package es.takecare.aplicacion.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "toma")
public class Toma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false, updatable = false)
    private int codigo;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "dosis", nullable = false)
    private int dosis;

    @Column(name = "horario", nullable = false)
    private String horario;

    @Column(name = "tomada", nullable = false)
    private boolean tomada;

    @ManyToOne
    @JoinColumn(name = "codigopaciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "codigomedicamento", nullable = false)
    private Medicamento medicamento;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isTomada() {
        return tomada;
    }

    public void setTomada(boolean tomada) {
        this.tomada = tomada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    
}
