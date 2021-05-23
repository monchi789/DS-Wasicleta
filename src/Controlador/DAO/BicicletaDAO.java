package Controlador.DAO;

import Modelo.Bicicleta;
import Modelo.Repositorio.Persistencia;
import Modelo.TipoBicicleta;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BicicletaDAO {
    private  static EntityManager em = Persistencia.getInstancia().getEm();

    public static List<Bicicleta> listarBicicletas(){
        Query consulta = em.createNamedQuery("bicicletas.listar");
        return  consulta.getResultList();
    }

    public static List<Bicicleta> listarTipoBicicleta(TipoBicicleta tipo){
        Query consulta = em.createNamedQuery("bicicletas.buscartipo");
        consulta.setParameter("tipo", tipo);
        return consulta.getResultList();
    }

    public static void crear(Bicicleta nuevaBicicleta) {
        em.getTransaction().begin();
        em.persist(nuevaBicicleta);
        em.getTransaction().commit();
        TipoBicicleta tipoBicicleta = nuevaBicicleta.getTieneTipoBicicleta();
        tipoBicicleta.getAgrupaBicicleta().add(nuevaBicicleta);
    }

    public static void editar(Bicicleta editarBicicleta) {
        em.getTransaction().begin();
        em.merge(editarBicicleta);
        em.getTransaction().commit();
    }

    public static void eliminar(Bicicleta eliminarBicicleta) {
        em.getTransaction().begin();
        em.remove(eliminarBicicleta);
        em.getTransaction().commit();
    }
}
