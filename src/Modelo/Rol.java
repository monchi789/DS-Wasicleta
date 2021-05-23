package Modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol")
@NamedQueries({
        @NamedQuery(name = "rol.listar", query = "select u from Rol u")
})
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private Long idRol;
    @Column(name = "tipoRol")
    private String tipoRol;
    //RELACION ENTRE ROL Y TRABAJADOR
    @OneToMany(mappedBy = "tieneRol")
    private List<Trabajador> agrupaTrabajador;

    public Rol() {
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public List<Trabajador> getAgrupaTrabajador() {
        if (agrupaTrabajador == null){
            agrupaTrabajador = new ArrayList<>();
        }
        return agrupaTrabajador;
    }

    public void setAgrupaTrabajador(List<Trabajador> agrupaTrabajador) {
        this.agrupaTrabajador = agrupaTrabajador;
    }
}
