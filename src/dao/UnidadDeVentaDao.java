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

    public int agregar(UnidadDeVenta Uv) {
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
	
	public UnidadDeVenta traer(int idUnidadDeVenta) {
		UnidadDeVenta p = null;
		try {
			iniciaOperacion();
			p= (UnidadDeVenta) session.get(UnidadDeVenta.class, idUnidadDeVenta);
		} finally {
			session.close();
		}
		return p;
	}
	
	public UnidadDeVenta traer(String nombreComercial) {
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
	
	public void actualizar(UnidadDeVenta p) {
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
	
	public void eliminar(UnidadDeVenta p) {
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
	public void eliminar(Plato p) {
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
	public void eliminar(Pedido p) {
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
	public Pedido traer(int idPedido) {
		Pedido p = null;
		try {
			iniciaOperacion();
			p= (Pedido) session.get(Pedido.class, idPedido);
		} finally {
			session.close();
		}
		return p;
	}
	public Plato traer(int idPlato) {
		Plats p = null;
		try {
			iniciaOperacion();
			p= (Plato) session.get(Plato.class, idPlato);
		} finally {
			session.close();
		}
		return p;
	}
	public Persona traer(String nombre) {
	    Persona p = null;
	    try {
	        iniciaOperacion();
	        String hql = "from Persona p where p.nombre = :nombre";
	        p = (Persona) session.createQuery(hql)
	                             .setParameter("nombre", nombre)
	                             .uniqueResult();
	    } finally {
	        if (session != null) session.close();
	    }
	    return p;
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
    
    
    public void agregar(UnidadDeVenta unidad, Persona persona) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            UnidadDeVenta u = (UnidadDeVenta) session.get(UnidadDeVenta.class, unidad.getIdUnidadDeVenta());
            Hibernate.initialize(u.getPersonal());
            u.getPersonal().add(persona);
            session.update(u);

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }
    }
    public void agregar(UnidadDeVenta unidad, Pedido pedido) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            UnidadDeVenta u = (UnidadDeVenta) session.get(UnidadDeVenta.class, unidad.getIdUnidadDeVenta());
            Hibernate.initialize(u.getPedido());
            u.getPedido().add(pedido);
            session.update(u);

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }
    }
        public void agregar(UnidadDeVenta unidad, Plato plato) throws HibernateException {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                UnidadDeVenta u = (UnidadDeVenta) session.get(UnidadDeVenta.class, unidad.getIdUnidadDeVenta());
                Hibernate.initialize(u.getPlato());
                u.getPlato().add(plato);
                session.update(u);

                tx.commit();
            } catch (HibernateException he) {
                if (tx != null) tx.rollback();
                throw he;
            } finally {
                session.close();
            }
    }
    
}