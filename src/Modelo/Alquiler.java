package Modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alquiler")
@NamedQueries({
        @NamedQuery(name = "alquiler.listar", query = "select a from Alquiler a"),
        @NamedQuery(name = "alquiler.busqueda", query = "select a from Alquiler a where a.nombreAlquiler like :nombre")
})
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlquiler")
    private Long idAlquiler;
    @Column(name = "fechaAlquiler")
    private String fechaAlquiler;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombreAlquiler")
    private String nombreAlquiler;
    @Column(name = "apellidoAlquiler")
    private String apellidoAlquiler;
    @Column(name = "numeroDocumentoAlquiler")
    private String numeroDocumentoAlquiler;
    @Column(name = "celularAlquiler")
    private String celularAlquiler;
    @Column(name = "correoAlquiler")
    private String correoAlquiler;
    @Column(name = "montoAlquiler")
    private String montoAlquiler;
    //RELACION ENTRE ALQUILER Y TRABAJADOR
    @ManyToOne
    @JoinColumn(name = "perteneceTrabajador", referencedColumnName = "idTrabajador")
    private Trabajador perteneceTrabajador;
    //RELACION ENTRE ALQUILER Y CLIENTE
    @ManyToOne
    @JoinColumn(name = "perteneceCliente", referencedColumnName = "idCliente")
    private Cliente perteneceCliente;
    //RELACION ENTRE ALQUILER Y BICICLETA
    @ManyToOne
    @JoinColumn(name = "tieneBicicleta", referencedColumnName = "idBicicleta")
    private Bicicleta tieneBicicleta;

    public Alquiler() {
    }

    public Long getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Long idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Trabajador getPerteneceTrabajador() {
        return perteneceTrabajador;
    }

    public void setPerteneceTrabajador(Trabajador perteneceTrabajador) {
        this.perteneceTrabajador = perteneceTrabajador;
    }

    public Cliente getPerteneceCliente() {
        return perteneceCliente;
    }

    public void setPerteneceCliente(Cliente perteneceCliente) {
        this.perteneceCliente = perteneceCliente;
    }

    public Bicicleta getTieneBicicleta() {
        return tieneBicicleta;
    }

    public void setTieneBicicleta(Bicicleta tieneBicicleta) {
        this.tieneBicicleta = tieneBicicleta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlquiler() {
        return nombreAlquiler;
    }

    public void setNombreAlquiler(String nombreAlquiler) {
        this.nombreAlquiler = nombreAlquiler;
    }

    public String getApellidoAlquiler() {
        return apellidoAlquiler;
    }

    public void setApellidoAlquiler(String apellidoAlquiler) {
        this.apellidoAlquiler = apellidoAlquiler;
    }

    public String getNumeroDocumentoAlquiler() {
        return numeroDocumentoAlquiler;
    }

    public void setNumeroDocumentoAlquiler(String numeroDocumentoAlquiler) {
        this.numeroDocumentoAlquiler = numeroDocumentoAlquiler;
    }

    public String getCelularAlquiler() {
        return celularAlquiler;
    }

    public void setCelularAlquiler(String celularAlquiler) {
        this.celularAlquiler = celularAlquiler;
    }

    public String getCorreoAlquiler() {
        return correoAlquiler;
    }

    public void setCorreoAlquiler(String correoAlquiler) {
        this.correoAlquiler = correoAlquiler;
    }

    public String getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(String montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public String getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(String fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }
}
