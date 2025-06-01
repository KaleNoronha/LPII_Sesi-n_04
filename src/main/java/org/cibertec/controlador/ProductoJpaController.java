package org.cibertec.controlador;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.cibertec.model.Producto;

public class ProductoJpaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa_sesion02");
	
	public ProductoJpaController(EntityManagerFactory emf) {
		this.emf=emf;
	}	
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public ProductoJpaController() {		
	}
	
	public void registrar(Producto data) throws Exception {		
		 EntityManager em = getEntityManager();
		    try {
		        em.getTransaction().begin();
		        em.persist(data);
		        em.getTransaction().commit();
		    } catch (Exception e) {
		        if (em.getTransaction().isActive()) {
		            em.getTransaction().rollback();
		        }
		        throw e;
		    } finally {
		        em.close();
		    }
	}

	public List<Producto> findAll() {
		EntityManager em = getEntityManager();
	    try {
	        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}
	
	public Producto buscarById(int codigo) {		
		EntityManager em = getEntityManager();
	    try {
	        return em.find(Producto.class, codigo);
	    } finally {
	        em.close();
	    }
	}
}
