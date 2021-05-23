package Modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trabajador")
@NamedQueries({
        @NamedQuery(name = "trabajador.listar", query = "select t from Trabajador t"),
        @NamedQuery(name = "trabajador.buscar", query = "select t from Trabajador t where t.usuarioTrabajador = :usuarioTrabajador")
})
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrabajador")
    private Long idTrabajador;
    @Column(name = "nombreTrabajador")
    private String nombreTrabajador;
    @Column(name = "apellidosTrabajador")
    private String apellidosTrabajador;
    @Column(name = "nroDocumentoTrabajador")
    private String nroDocumentoTrabajador;
    @Column(name = "celularTrabajador")
    private String celularTrabajador;
    @Column(name = "correoTrabajador")
    private String correoTrabajador;
    @Column(name = "usuarioTrabajador")
    private String usuarioTrabajador;
    @Column(name = "contraseñaTrabajador")
    private String contraseñaTrabajador;
    //FORMA DE DEFINIR UNA FOREING KEY //RELACION ENTRE TRABAJADOR Y ROL
    @ManyToOne
    @JoinColumn(name = "tieneRol",referencedColumnName = "idRol")
    private Rol tieneRol;
    //RELACION ENTRE TRABAJADOR Y ALQUILER
    @OneToMany(mappedBy = "perteneceTrabajador")
    private List<Alquiler> realizaAlquiler;

    public Trabajador() {
    }

    public Long getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getApellidosTrabajador() {
        return apellidosTrabajador;
    }

    public void setApellidosTrabajador(String apellidosTrabajador) {
        this.apellidosTrabajador = apellidosTrabajador;
    }

    public String getNroDocumentoTrabajador() {
        return nroDocumentoTrabajador;
    }

    public void setNroDocumentoTrabajador(String nroDocumentoTrabajador) {
        this.nroDocumentoTrabajador = nroDocumentoTrabajador;
    }

    public String getCelularTrabajador() {
        return celularTrabajador;
    }

    public void setCelularTrabajador(String celularTrabajador) {
        this.celularTrabajador = celularTrabajador;
    }

    public String getCorreoTrabajador() {
        return correoTrabajador;
    }

    public void setCorreoTrabajador(String correoTrabajador) {
        this.correoTrabajador = correoTrabajador;
    }

    public String getUsuarioTrabajador() {
        return usuarioTrabajador;
    }

    public void setUsuarioTrabajador(String usuarioTrabajador) {
        this.usuarioTrabajador = usuarioTrabajador;
    }

    public String getContraseñaTrabajador() {
        return contraseñaTrabajador;
    }

    public void setContraseñaTrabajador(String contraseñaTrabajador) {
        this.contraseñaTrabajador = contraseñaTrabajador;
    }

    public Rol getTieneRol() {
        return tieneRol;
    }

    public void setTieneRol(Rol tieneRol) {
        this.tieneRol = tieneRol;
    }

    public List<Alquiler> getRealizaAlquiler() {
        if (realizaAlquiler == null){
            realizaAlquiler = new ArrayList<>();
        }
        return realizaAlquiler;
    }

    public void setRealizaAlquiler(List<Alquiler> realizaAlquiler) {
        this.realizaAlquiler = realizaAlquiler;
    }
}
