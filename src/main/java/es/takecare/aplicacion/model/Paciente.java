package es.takecare.aplicacion.model;

import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false, updatable = false)
    private int codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechanacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;

    @Column(name = "contacto", nullable = false)
    private String contacto;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "codigoubicacion", nullable = false)
    private Ubicacion ubicacion;

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

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    
}
