package es.takecare.aplicacion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false, updatable = false)
    private int codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "cantidadamaxima", nullable = false)
    private int cantidadamaxima;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadamaxima() {
        return cantidadamaxima;
    }

    public void setCantidadamaxima(int cantidadamaxima) {
        this.cantidadamaxima = cantidadamaxima;
    }

   
}
