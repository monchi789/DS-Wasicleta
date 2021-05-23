package Controlador.DAO;

import Modelo.Repositorio.Persistencia;
import Modelo.Cliente;
import Modelo.Trabajador;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class ClienteDAO {
    private  static EntityManager em = Persistencia.getInstancia().getEm();

    public static List<Cliente> listarClientes(){
        Query consulta = em.createNamedQuery("cliente.listar");
        return  consulta.getResultList();
    }

    public static Cliente buscarCliente(String nombreCliente){
        Query consulta = em.createNamedQuery("cliente.buscar");
        consulta.setParameter("nombreCliente", nombreCliente);
        Cliente salida = null;
        try {
            salida = (Cliente) consulta.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return salida;
    }

    public static void crear(Cliente nuevoCliente) {
        em.getTransaction().begin();
        em.persist(nuevoCliente);
        em.getTransaction().commit();
    }

    public static void editar(Cliente editarCliente) {
        em.getTransaction().begin();
        em.merge(editarCliente);
        em.getTransaction().commit();
    }

    public static void eliminar(Cliente eliminarCliente) {
        em.getTransaction().begin();
        em.remove(eliminarCliente);
        em.getTransaction().commit();
    }

    public static List<Cliente> listaclientes(String nombreCliente){
        Query consulta = em.createNamedQuery("cliente.buscar");
        return consulta.getResultList();
    }
}
