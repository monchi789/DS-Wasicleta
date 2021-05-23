package Modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bicicleta")
@NamedQueries({
        @NamedQuery(name = "bicicletas.listar", query = "select b from Bicicleta b"),
        @NamedQuery(name = "bicicletas.buscartipo", query = "select b from Bicicleta b where b.tieneTipoBicicleta = :tipo")
})
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBicicleta")
    private Long idBicicleta;
    @Column(name = "codigoBicicleta")
    private String codigoBicicleta;
    @Column(name = "statusBicicleta")
    private String statusBicicleta;
    //RELACION ENTRE BICICLETA Y TIPO BICICLETA
    @ManyToOne
    @JoinColumn(name = "tieneTipoBicicleta",referencedColumnName = "idTipoBicicleta")
    private TipoBicicleta tieneTipoBicicleta;
    //RELACION ENTRE BICICLETA Y ALQUILER
    @OneToMany(mappedBy = "tieneBicicleta")
    private List<Alquiler> perteneceAlquiler;

    public Bicicleta() {
    }

    public Long getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(Long idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public String getCodigoBicicleta() {
        return codigoBicicleta;
    }

    public void setCodigoBicicleta(String codigoBicicleta) {
        this.codigoBicicleta = codigoBicicleta;
    }

    public String getStatusBicicleta() {
        return statusBicicleta;
    }

    public void setStatusBicicleta(String statusBicicleta) {
        this.statusBicicleta = statusBicicleta;
    }

    public TipoBicicleta getTieneTipoBicicleta() {
        return tieneTipoBicicleta;
    }

    public void setTieneTipoBicicleta(TipoBicicleta tieneTipoBicicleta) {
        this.tieneTipoBicicleta = tieneTipoBicicleta;
    }

    public List<Alquiler> getPerteneceAlquiler() {
        if (perteneceAlquiler == null){
            perteneceAlquiler = new ArrayList<>();
        }
        return perteneceAlquiler;
    }

    public void setPerteneceAlquiler(List<Alquiler> perteneceAlquiler) {
        this.perteneceAlquiler = perteneceAlquiler;
    }
}
