package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Festival;

public class FestivalDao {
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
	
	public int agregarFestival(Festival festival) {
		int id=0;
		try {
			iniciaOperacion();
			id= Integer.parseInt(session.save(festival).toString());
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}
	
	public Festival traerFestival(int idFestival) {
		Festival f = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			f= (Festival) session.get(Festival.class, idFestival);
		} finally {
			session.close();
		}
		return f;
	}
	
	public List<Festival> traerFestivales(){
		List<Festival> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from Festival", Festival.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public void actualizarFestival(Festival f) {
		try {
			iniciaOperacion();
			session.update(f);
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}
	
	public void eliminarFestival(Festival f) {
		try {
			iniciaOperacion();
			session.delete(f);
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally { 
			session.close();
		}
	}
	
	public Festival traerFestivalYUnidades(int idFestival) {
		Festival f =null;
		try {
			iniciaOperacion();
			f= session.createQuery("from Festival f left join fetch f.unidades "
					+ "where f.idFestival = :idFestival", Festival.class)
					.setParameter("idFestival", idFestival)
					.uniqueResult();
		} finally {
			session.close();
		}
		return f;
	}
	

}
