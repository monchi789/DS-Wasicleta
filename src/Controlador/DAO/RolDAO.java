package Controlador.DAO;

import Modelo.Repositorio.Persistencia;
import Modelo.Rol;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RolDAO {
    private static EntityManager em = Persistencia.getInstancia().getEm();

    public static List<Rol> listarRoles(){
        Query conslta = em.createNamedQuery("rol.listar");
        return conslta.getResultList();
    }

    public static void crear(Rol nuevoRol) {
        em.getTransaction().begin();
        em.persist(nuevoRol);
        em.getTransaction().commit();
    }

    public static void editar(Rol editarRol){
        em.getTransaction().begin();
        em.merge(editarRol);
        em.getTransaction().commit();
    }

    public static void eliminar(Rol eliminarRol){
        em.getTransaction().begin();
        em.remove(eliminarRol);
        em.getTransaction().commit();
    }
}
