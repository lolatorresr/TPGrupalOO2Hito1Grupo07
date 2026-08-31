package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Persona;
import datos.UnidadDeVenta;

public class UnidadDeVentaDao {
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
	
	//----METODOS UNIDAD DE VENTA----

    public int agregarUnidadVenta(UnidadDeVenta Uv) {
		int id=0;
		try {
			iniciaOperacion();
			id= Integer.parseInt(session.save(Uv).toString());
			tx.commit();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}
	
	public UnidadDeVenta traerUnidadVenta(int idUnidadDeVenta) {
		UnidadDeVenta p = null;
		try {
			iniciaOperacion();
			p= (UnidadDeVenta) session.get(UnidadDeVenta.class, idUnidadDeVenta);
		} finally {
			session.close();
		}
		return p;
	}
	
	public UnidadDeVenta traerUnidadVenta(String nombreComercial) {
		UnidadDeVenta p = null;
		try {
			iniciaOperacion();
			p= (UnidadDeVenta) session.createQuery("from UnidadDeVenta u where u.nombreComercial = :nombreComercial").setParameter("nombreComercial", nombreComercial).uniqueResult();
		} finally {
			session.close();
		}
		return p;
	}
	
	public List<Persona> traerPersonal(){
		List<Persona> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Persona", Persona.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public void actualizarUnidadVenta(UnidadDeVenta p) {
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
	
	public void eliminarUnidadVenta(UnidadDeVenta p) {
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
	
    public UnidadDeVenta traerUnidadDeVentaYPersonal(int idUnidadDeVenta) {
        UnidadDeVenta objeto = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from UnidadDeVenta u left join fetch u.personal where u.idUnidadDeVenta = :id";
            objeto = (UnidadDeVenta) session.createQuery(hql)
                                           .setParameter("id", idUnidadDeVenta)
                                           .uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }
    
    public UnidadDeVenta traerUnidadDeVentaYPedido(int idUnidadDeVenta) {
        UnidadDeVenta objeto = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from UnidadDeVenta u left join fetch u.pedido where u.idUnidadDeVenta = :id";
            objeto = (UnidadDeVenta) session.createQuery(hql)
                                           .setParameter("id", idUnidadDeVenta)
                                           .uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }
    
    public UnidadDeVenta traerUnidadDeVentaYPlatos(int idUnidadDeVenta) {
        UnidadDeVenta objeto = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from UnidadDeVenta u left join fetch u.platos where u.idUnidadDeVenta = :id";
            objeto = (UnidadDeVenta) session.createQuery(hql)
                                           .setParameter("id", idUnidadDeVenta)
                                           .uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }
   
    //----METODOS PUESTO DESARMABLE----
    
    
    
    
    
    
    //----METODOS FOODTRUCK----
    
}