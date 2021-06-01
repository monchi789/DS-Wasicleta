package Controlador.DAO;

import Modelo.Reporte.ReporteTrabajador;
import Modelo.Repositorio.Persistencia;
import Modelo.Rol;
import Modelo.Trabajador;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class TrabajadoresDAO {
    private  static EntityManager em = Persistencia.getInstancia().getEm();

    public static List<Trabajador> listarTrabajadores(){
        Query consulta = em.createNamedQuery("trabajador.listar");
        return  consulta.getResultList();
    }

    public static List<ReporteTrabajador> listarReporteTrabajador(){

        Query consulta = em.createNamedQuery("trabajador.listaTipoTrabajador");
        List<ReporteTrabajador> salida = consulta.getResultList();
        return salida;

    }

    public static Trabajador buscarUsuario(String usuarioTrabajador){
        Query consulta = em.createNamedQuery("trabajador.buscar");
        consulta.setParameter("usuarioTrabajador", usuarioTrabajador);
        Trabajador salida = null;
        try {
            salida = (Trabajador) consulta.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return salida;
    }

    public static void crear(Trabajador nuevoTrabajor) {
        em.getTransaction().begin();
        em.persist(nuevoTrabajor);
        em.getTransaction().commit();
        Rol rol = nuevoTrabajor.getTieneRol();
        rol.getAgrupaTrabajador().add(nuevoTrabajor);
    }

    public static void editar(Trabajador editarTrabajor) {
        em.getTransaction().begin();
        em.merge(editarTrabajor);
        em.getTransaction().commit();
    }

    public static void eliminar(Trabajador eliminarTrabajor) {
        em.getTransaction().begin();
        em.remove(eliminarTrabajor);
        em.getTransaction().commit();
    }
}
