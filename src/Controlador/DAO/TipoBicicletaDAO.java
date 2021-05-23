package Controlador.DAO;

import Modelo.Repositorio.Persistencia;
import Modelo.TipoBicicleta;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TipoBicicletaDAO {
    private  static EntityManager em = Persistencia.getInstancia().getEm();

    public static List<TipoBicicleta> listarTipoBicicletas(){
        Query consulta = em.createNamedQuery("tipoBicicleta.listar");
        return  consulta.getResultList();
    }

    public static void crear(TipoBicicleta nuevotipoBicicleta) {
        em.getTransaction().begin();
        em.persist(nuevotipoBicicleta);
        em.getTransaction().commit();
    }

    public static void editar(TipoBicicleta editartipoBicicleta) {
        em.getTransaction().begin();
        em.merge(editartipoBicicleta);
        em.getTransaction().commit();
    }

    public static void eliminar(TipoBicicleta eliminartipoBicicleta) {
        em.getTransaction().begin();
        em.remove(eliminartipoBicicleta);
        em.getTransaction().commit();
    }
}
