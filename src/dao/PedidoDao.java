package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Pedido;

public class PedidoDao {
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
	
	public int agregarPedido(Pedido pedido) {
		int id=0;
		try {
			iniciaOperacion();
			id= Integer.parseInt(session.save(pedido).toString());
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}
	
	public Pedido traerPedido(int idPedido) {
		Pedido p = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			p= (Pedido) session.get(Pedido.class, idPedido);
		} finally {
			session.close();
		}
		return p;
	}
	
	public List<Pedido> traerPedidos(){
		List<Pedido> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from Pedido", Pedido.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public void actualizarPedido(Pedido p) {
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
	
	public void eliminarPedido(Pedido p) {
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
