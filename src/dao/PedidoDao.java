package dao;

import java.time.LocalDate;
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
	
	//traer pedidos en rango de fechas
	public List<Pedido> traerPedidosEntreFechas(LocalDate desde, LocalDate hasta){
		List<Pedido> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Pedido p where p.fecha between :desde and :hasta"
					+ "order by p.fecha desc", Pedido.class)
					.setParameter("desde", desde)
					.setParameter("hasta", hasta)
					.getResultList();
		} finally {
			if(session != null) session.close();
		}
		return lista;
	}
	
	//traer pedido join items y platos
	public Pedido traerPedidoConDetalle(int idPedido) {
		Pedido p=null;
		try {
			iniciaOperacion();
			p = (Pedido) session.createQuery("select distinct p from Pedido p"
					+ "left join fetch p.itemPlato ip"
					+ "left join fetch ip.plato"
					+ "where p.idPedido = :idPedido")
					.setParameter("idPedido", idPedido)
					.uniqueResult();
		}finally {
			if(session != null) session.close();
		}
		return p;
	}
	
	
	//traer pedidos x unidad de venta
	public List<Pedido> traerPedidosPorUnidadDeVenta(int idUnidadDeVenta){
		List<Pedido> lista = null;
		try {
			iniciaOperacion();
			lista= session.createQuery("select p from UnidadDeVenta uv"
					+ "join uv.pedidos p"
					+ "where uv.idUnidadDeVenta = :id", Pedido.class)
					.setParameter("id", idUnidadDeVenta)
					.getResultList();
		} finally {
			if(session != null) session.close();
		}
		return lista;
	}
	
}
