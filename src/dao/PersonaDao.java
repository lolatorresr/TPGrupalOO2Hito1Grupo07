package dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cajero;
import datos.Persona;


public class PersonaDao {
	private static Session session;
	private Transaction tx;
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public int agregar(Persona persona) {
		int id=0;
		try {
			iniciaOperacion();
			id= Integer.parseInt(session.save(persona).toString());
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}
	
	public Persona traer(int idPersona) {
		Persona p = null;
		try {
			iniciaOperacion();
			p= (Persona) session.get(Persona.class, idPersona);
		} finally {
			session.close();
		}
		return p;
	}
	
	public Persona traer(long dni) {
		Persona p = null;
		try {
			iniciaOperacion();
			p= (Persona) session.createQuery("from Persona p where p.dni = :dni").setParameter("dni", dni)
					.uniqueResult();
		} finally {
			session.close();
		}
		return p;
	}
	
	public List<Persona> traerPersonas(){
		List<Persona> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Persona", Persona.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public void actualizar(Persona p) {
		try {
			iniciaOperacion();
			session.update(p);
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}
	
	public void eliminar(Persona p) {
		try {
			iniciaOperacion();
			session.delete(p);
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally { 
			session.close();
		}
	}
	
	
	
	
	//traer lista de cajeros
	public List<Cajero> traerCajeros(){
		List<Cajero> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Cajero", Cajero.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	//traer cajero x id
	public Cajero traerCajero(int idPersona) {
		Cajero c = null;
		try {
			iniciaOperacion();
			c = (Cajero) session.get(Cajero.class, idPersona);
		} finally {
			session.close();
		}
		return c;
	}
	
	//traer cajero de mayor recaudacion
	public Cajero traerCajeroMayorRecaudacion() {
		Cajero c = null;
		try {
			iniciaOperacion();
			c= (Cajero) session.createQuery("from Cajero c order by c.recaudacionTotal desc")
					.setMaxResults(1).uniqueResult();
		}finally {
			session.close();
		}
		return c;
	}

}
