package dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Plato;


public class PlatoDao {
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
	
	public int agregarPlato(Plato plato) {
		int id=0;
		try {
			iniciaOperacion();
			id= Integer.parseInt(session.save(plato).toString());
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}
	
	public Plato traerPlato(int idPlato) {
		Plato p = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			p= (Plato) session.get(Plato.class, idPlato);
		} finally {
			session.close();
		}
		return p;
	}
	
	public Plato traerPlato(String nombre) {
		Plato p = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			p = session.createQuery("from Plato p where p.nombre = :nombre", Plato.class)
					.setParameter("nombre", nombre).uniqueResult();
		}finally {
			session.close();
		}
		return p;
	}
	
	public List<Plato> traerPlatos(){
		List<Plato> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from Plato", Plato.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public void actualizarPlato(Plato p) {
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
	
	public void eliminarPlato(Plato p) {
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
}