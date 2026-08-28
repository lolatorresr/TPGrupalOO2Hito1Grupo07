package dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cajero;


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
	
	//metodos persona
	
	
	
	
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
	
	//traer cajero x dni
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
