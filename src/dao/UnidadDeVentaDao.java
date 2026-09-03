package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import datos.Persona;
import datos.UnidadDeVenta;
import datos.FoodTruck;
import datos.Pedido;
import datos.Plato;
import datos.PuestoDesarmable;

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

	//----ABM----
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
	
	//----CONSULTAS BASICAS
	
	public UnidadDeVenta traerUnidadVenta(int idUnidadDeVenta) {
        UnidadDeVenta u = null;
        try {
            iniciaOperacion();
            u = (UnidadDeVenta) session.get(UnidadDeVenta.class, idUnidadDeVenta);
        } finally {
            if (session != null) session.close();
        }
        return u;
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
	
	
	//----METODOS FOODTRUCK----
	public FoodTruck traerFoodTruck(int idUnidadDeVenta) {
        FoodTruck f = null;
        try {
            iniciaOperacion();
            f = session.get(FoodTruck.class, idUnidadDeVenta);
        } finally {
            if (session != null) session.close();
        }
        return f;
    }

    public List<FoodTruck> traerFoodTrucks() {
        List<FoodTruck> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from FoodTruck", FoodTruck.class).getResultList();
        } finally {
            if (session != null) session.close();
        }
        return lista;
    }

    public FoodTruck traerFoodTruckPorPatente(String patente) {
        FoodTruck f = null;
        try {
            iniciaOperacion();
            f = session.createQuery("from FoodTruck f where f.patente = :patente", FoodTruck.class)
                       .setParameter("patente", patente)
                       .uniqueResult();
        } finally {
            if (session != null) session.close();
        }
        return f;
    }
    
    //----METODOS PUESTO DESARMABLE----
    
    public PuestoDesarmable traerPuestoDesarmable(int idUnidadDeVenta) {
        PuestoDesarmable p = null;
        try {
            iniciaOperacion();
            p = session.get(PuestoDesarmable.class, idUnidadDeVenta);
        } finally {
            if (session != null) session.close();
        }
        return p;
    }

    public List<PuestoDesarmable> traerPuestosDesarmables() {
        List<PuestoDesarmable> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from PuestoDesarmable", PuestoDesarmable.class).getResultList();
        } finally {
            if (session != null) session.close();
        }
        return lista;
    }
    
    //----ASOCIACIONES----
	
    public void agregarPersonal(UnidadDeVenta unidad, Persona persona) throws HibernateException {
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
    
    public void agregarPlato(UnidadDeVenta unidad, Plato plato) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UnidadDeVenta u = (UnidadDeVenta) session.get(UnidadDeVenta.class, unidad.getIdUnidadDeVenta());
            Hibernate.initialize(u.getPlatos());
            u.getPlatos().add(plato);
            session.update(u);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }
    }

    public void agregarPedido(UnidadDeVenta unidad, Pedido pedido) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UnidadDeVenta u = (UnidadDeVenta) session.get(UnidadDeVenta.class, unidad.getIdUnidadDeVenta());
            Hibernate.initialize(u.getPedidos());
            u.getPedidos().add(pedido);
            session.update(u);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }
    }
	
	//------------------------------------
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
	public Pedido traerPedido(int idPedido) {
		Pedido p = null;
		try {
			iniciaOperacion();
			p= (Pedido) session.get(Pedido.class, idPedido);
		} finally {
			session.close();
		}
		return p;
	}
	public Persona traerPersona(int idPersona) {
		Persona p = null;
		try {
			iniciaOperacion();
			p= (Persona) session.get(Persona.class, idPersona);
		} finally {
			session.close();
		}
		return p;
	}
	public Plato traerPlato(int idPlato) {
		Plato p = null;
		try {
			iniciaOperacion();
			p= (Plato) session.get(Plato.class, idPlato);
		} finally {
			session.close();
		}
		return p;
	}
	
	//----CONSULTAS CON JOIN----

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
            String hql = "from UnidadDeVenta u left join fetch u.pedidos where u.idUnidadDeVenta = :id";
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

    public UnidadDeVenta traerUnidadVentaCompleta(int idUnidadDeVenta) throws HibernateException {
        UnidadDeVenta uv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select distinct u from UnidadDeVenta u "
                       + "left join fetch u.pedidos p "
                       + "left join fetch p.itemPlato ip "
                       + "left join fetch ip.plato "
                       + "where u.idUnidadDeVenta = :id";
            uv = (UnidadDeVenta) session.createQuery(hql)
                                       .setParameter("id", idUnidadDeVenta)
                                       .uniqueResult();
        } catch (HibernateException he) {
            throw he;
        } finally {
            session.close();
        }
        return uv;
    }
    //trae la unidad de venta con mayor superficie

    public List<UnidadDeVenta> traerUnidadVentaMayorSuperficie(double superficie) throws HibernateException {
        List<UnidadDeVenta> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from UnidadDeVenta u where u.superficie > :superficie";
            lista = session.createQuery(hql, UnidadDeVenta.class)
                           .setParameter("superficie", superficie)
                           .getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    //trae la unidad de venta con mayor cantidad de pedidos 
    public UnidadDeVenta traerUnidadDeVentaMayorPedidos() throws HibernateException {
        UnidadDeVenta resultado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select distinct u from UnidadDeVenta u "
                       + "left join fetch u.pedidos p "
                       + "left join fetch p.itemPlato";
            List<UnidadDeVenta> lista = session.createQuery(hql, UnidadDeVenta.class).getResultList();

            int maxPedidos = -1;
            for (UnidadDeVenta uv : lista) {
                int cantidad = uv.getCantidadPedidos();
                if (cantidad > maxPedidos) {
                    maxPedidos = cantidad;
                    resultado = uv;
                }
            }
        } finally {
            session.close();
        }
        return resultado;
    }

    
}