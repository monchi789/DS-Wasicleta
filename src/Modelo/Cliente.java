package Modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@NamedQueries({
        @NamedQuery(name = "cliente.listar", query = "select c from Cliente c"),
        @NamedQuery(name = "cliente.buscar", query = "select c from Cliente c where c.nombreCliente like :nombreCliente")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Long idCliente;
    @Column(name = "nombreCliente")
    private String nombreCliente;
    @Column(name = "apellidosCliente")
    private String apellidosCliente;
    @Column(name = "nroDocumentoCliente")
    private String nroDocumentoCliente;
    @Column(name = "celularCliente")
    private String celularCliente;
    @Column(name = "correoCliente")
    private String correoCliente;
    //RELACION ENTRE CLIENTE Y ALQUILER
    @OneToMany(mappedBy = "perteneceCliente")
    private List<Alquiler> tieneAlquiler;

    public Cliente() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public String getNroDocumentoCliente() {
        return nroDocumentoCliente;
    }

    public void setNroDocumentoCliente(String nroDocumentoCliente) {
        this.nroDocumentoCliente = nroDocumentoCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public List<Alquiler> getTieneAlquiler() {
        if (tieneAlquiler == null){
            tieneAlquiler = new ArrayList<>();
        }
        return tieneAlquiler;
    }

    public void setTieneAlquiler(List<Alquiler> tieneAlquiler) {
        this.tieneAlquiler = tieneAlquiler;
    }
}
