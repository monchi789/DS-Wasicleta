package Controlador.DAO;

import Modelo.*;
import Modelo.Reporte.ReporteAlquiler;
import Modelo.Reporte.ReporteTrabajador;
import Modelo.Repositorio.Persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AlquilerDAO {
    private  static EntityManager em = Persistencia.getInstancia().getEm();

    public static List<Alquiler> listarAlquiler(){
        Query consulta = em.createNamedQuery("alquiler.listar");
        return  consulta.getResultList();
    }

    public static List<Alquiler> buscarXNombre(String nombre) {
        Query consulta = em.createNamedQuery("alquiler.busqueda");
        consulta.setParameter("nombre", nombre);
        return consulta.getResultList();
    }

    public static List<ReporteAlquiler> listarReporteAlquiler(){

        Query consulta = em.createNamedQuery("alquiler.listarReporteAlquiler");
        List<ReporteAlquiler> salida = consulta.getResultList();
        return salida;

    }


    public static void crear(Alquiler nuevoAlquiler) {
        em.getTransaction().begin();
        em.persist(nuevoAlquiler);
        em.getTransaction().commit();

        Cliente cliente = nuevoAlquiler.getPerteneceCliente();
        cliente.getTieneAlquiler().add(nuevoAlquiler);

        Bicicleta bicicleta = nuevoAlquiler.getTieneBicicleta();
        bicicleta.getPerteneceAlquiler().add(nuevoAlquiler);

        Trabajador trabajador = nuevoAlquiler.getPerteneceTrabajador();
        trabajador.getRealizaAlquiler().add(nuevoAlquiler);
    }
}
