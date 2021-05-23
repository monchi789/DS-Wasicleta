package Modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipoBicicleta")
@NamedQueries({
        @NamedQuery(name = "tipoBicicleta.listar", query = "select tb from TipoBicicleta tb")
})
public class TipoBicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoBicicleta")
    private Long idTipoBicicleta;
    @Column(name = "nombreTipoBicicleta")
    private String nombreTipoBicicleta;
    @Column(name = "precioTipoBicicleta")
    private Double precioTipoBicicleta;
    //RELACION ENTRE TIPOBICICLETA Y BICICLETA
    @OneToMany(mappedBy = "tieneTipoBicicleta")
    private List<Bicicleta> agrupaBicicleta;

    public TipoBicicleta() {
    }

    public Long getIdTipoBicicleta() {
        return idTipoBicicleta;
    }

    public void setIdTipoBicicleta(Long idTipoBicicleta) {
        this.idTipoBicicleta = idTipoBicicleta;
    }

    public String getNombreTipoBicicleta() {
        return nombreTipoBicicleta;
    }

    public void setNombreTipoBicicleta(String nombreTipoBicicleta) {
        this.nombreTipoBicicleta = nombreTipoBicicleta;
    }

    public double getPrecioTipoBicicleta() {
        return precioTipoBicicleta;
    }

    public void setPrecioTipoBicicleta(Double precioTipoBicicleta) {
        this.precioTipoBicicleta = precioTipoBicicleta;
    }

    public List<Bicicleta> getAgrupaBicicleta() {
        if (agrupaBicicleta == null){
            agrupaBicicleta = new ArrayList<>();
        }
        return agrupaBicicleta;
    }

    public void setAgrupaBicicleta(List<Bicicleta> agrupaBicicleta) {
        this.agrupaBicicleta = agrupaBicicleta;
    }
}
