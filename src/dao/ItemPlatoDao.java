package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.ItemPlato;
import datos.Pedido;

public class ItemPlatoDao {
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
	
	//El ItemPlato siempre pertenece a un Pedido, por eso se persiste a traves de el
	//(el mapeo de Pedido usa cascade="all-delete-orphan").
	
	public int agregarItemPlatoAPedido(int idPedido, ItemPlato item) {
		int id=0;
		try {
			iniciaOperacion();
			Pedido pedido = (Pedido) session.get(Pedido.class, idPedido);
			pedido.getItemPlatos().add(item);
			session.update(pedido);
			tx.commit();
			id = item.getIdItemPlato();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}
	
	public void eliminarItemPlatoDePedido(int idPedido, int idItemPlato) {
		try {
			iniciaOperacion();
			Pedido pedido = (Pedido) session.get(Pedido.class, idPedido);
			pedido.getItemPlatos().removeIf(i -> i.getIdItemPlato() == idItemPlato);
			session.update(pedido);
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}
	
	public void actualizarItemPlato(ItemPlato item) {
		try {
			iniciaOperacion();
			session.update(item);
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}
	
	public ItemPlato traerItemPlato(int idItemPlato) {
		ItemPlato item = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			item= (ItemPlato) session.get(ItemPlato.class, idItemPlato);
		} finally {
			session.close();
		}
		return item;
	}
	
	public List<ItemPlato> traerItemPlatos(){
		List<ItemPlato> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from ItemPlato", ItemPlato.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	//----CONSULTAS----
	
	//traer los items de un pedido
	public List<ItemPlato> traerItemPlatosPorPedido(int idPedido){
		List<ItemPlato> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("select ip from Pedido p "
					+ "join p.itemPlatos ip "
					+ "join fetch ip.plato "
					+ "where p.idPedido = :idPedido", ItemPlato.class)
					.setParameter("idPedido", idPedido)
					.getResultList();
		} finally {
			if(session != null) session.close();
		}
		return lista;
	}
	
	//traer los items que corresponden a un plato
	public List<ItemPlato> traerItemPlatosPorPlato(int idPlato){
		List<ItemPlato> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from ItemPlato ip "
					+ "where ip.plato.idPlato = :idPlato", ItemPlato.class)
					.setParameter("idPlato", idPlato)
					.getResultList();
		} finally {
			if(session != null) session.close();
		}
		return lista;
	}
	
	//cantidad total vendida de un plato
	public int traerCantidadVendidaPorPlato(int idPlato) {
		Long cantidad = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			cantidad = session.createQuery("select coalesce(sum(ip.cantidad), 0) from ItemPlato ip "
					+ "where ip.plato.idPlato = :idPlato", Long.class)
					.setParameter("idPlato", idPlato)
					.uniqueResult();
		} finally {
			if(session != null) session.close();
		}
		return cantidad == null ? 0 : cantidad.intValue();
	}
	
}
