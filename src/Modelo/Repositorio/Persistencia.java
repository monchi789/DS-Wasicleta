package Modelo.Repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Persistencia {
    private static Persistencia instancia;
    private EntityManager em;

    private Persistencia() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_PU");
        em = emf.createEntityManager();
    }

    public static Persistencia getInstancia() {
        if (instancia == null){
            instancia = new Persistencia();
        }
        return instancia;
    }

    public EntityManager getEm() {
        return em;
    }
}
