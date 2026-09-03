package dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cajero;
import datos.Cocinero;
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
	
	public int agregarPersona(Persona persona) {
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
	
	public Persona traerPersona(int idPersona) {
		Persona p = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			p= (Persona) session.get(Persona.class, idPersona);
		} finally {
			session.close();
		}
		return p;
	}
	
	public Persona traerPersona(long dni) {
		Persona p = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
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
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from Persona", Persona.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public void actualizarPersona(Persona p) {
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
	
	public void eliminarPersona(Persona p) {
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
	
	
	//CONSULTAS DE CAJERO
	//traer lista de cajeros
	public List<Cajero> traerCajeros(){
		List<Cajero> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
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
			session = HibernateUtil.getSessionFactory().openSession();
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
			session = HibernateUtil.getSessionFactory().openSession();
			c= (Cajero) session.createQuery("from Cajero c order by c.recaudacionTotal desc")
					.setMaxResults(1).uniqueResult();
		}finally {
			session.close();
		}
		return c;
	}

	// CONSULTAS DE COCINERO 
	//trae cocinero por id
	public Cocinero traerCocinero(int idPersona) {
		Cocinero c = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			c = (Cocinero) session.get(Cocinero.class, idPersona);
		} finally {
			session.close();
		}
		return c;
	}

	//trae lista de cocineros
	@SuppressWarnings("unchecked")
	public List<Cocinero> traerCocineros(){
		List<Cocinero> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from Cocinero").getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

	//trae los cocineros que forman parte del personal de una unidad de venta
	@SuppressWarnings("unchecked")
	public List<Cocinero> traerCocinerosDeUnidadDeVenta(int idUnidadDeVenta){
		List<Cocinero> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select c from UnidadDeVenta u "
					+ "join u.personal c "
					+ "where u.idUnidadDeVenta = :id "
					+ "and type(c) = Cocinero";
			lista = session.createQuery(hql).setParameter("id", idUnidadDeVenta).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

	//trae el cocinero con mayor antiguedad de una unidad de venta
	public Cocinero traerCocineroMasAntiguo(int idUnidadDeVenta) {
		Cocinero c = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select c from UnidadDeVenta u "
					+ "join u.personal c "
					+ "where u.idUnidadDeVenta = :id "
					+ "and type(c) = Cocinero "
					+ "order by c.fechaIngreso asc";
			c = (Cocinero) session.createQuery(hql).setParameter("id", idUnidadDeVenta)
					.setMaxResults(1).uniqueResult();
		} finally {
			session.close();
		}
		return c;
	}

	//trae los cocineros de una unidad de venta filtrados por especialidad
	@SuppressWarnings("unchecked")
	public List<Cocinero> traerCocinerosPorEspecialidad(int idUnidadDeVenta, String especialidad){
		List<Cocinero> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select c from UnidadDeVenta u "
					+ "join u.personal c "
					+ "where u.idUnidadDeVenta = :id "
					+ "and type(c) = Cocinero "
					+ "and c.especialidad = :especialidad";
			lista = session.createQuery(hql)
					.setParameter("id", idUnidadDeVenta)
					.setParameter("especialidad", especialidad)
					.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

}
