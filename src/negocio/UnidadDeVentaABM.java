package negocio;

import java.util.List;
import dao.UnidadDeVentaDao;
import datos.UnidadDeVenta;
import datos.Persona;
import datos.Plato;
import datos.Pedido;

public class UnidadDeVentaABM {
    private static UnidadDeVentaABM instancia;
    private UnidadDeVentaDao dao;

    private UnidadDeVentaABM() {
        this.dao = new UnidadDeVentaDao();
    }

    public static UnidadDeVentaABM getInstancia() {
        if (instancia == null) {
            instancia = new UnidadDeVentaABM();
        }
        return instancia;
    }



    public UnidadDeVenta traer(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traer(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        return u;
    }

    public UnidadDeVenta traer(String nombreComercial) throws Exception {
        UnidadDeVenta u = dao.traer(nombreComercial);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con nombre comercial: " + nombreComercial);
        }
        return u;
    }

    public UnidadDeVenta traerUnidadDeVentaYPersonal(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traerUnidadDeVentaYPersonal(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        return u;
    }

    public UnidadDeVenta traerUnidadDeVentaYPedido(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traerUnidadDeVentaYPedido(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        return u;
    }

    public UnidadDeVenta traerUnidadDeVentaYPlatos(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traerUnidadDeVentaYPlatos(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        return u;
    }

    public List<Persona> traerPersonal() {
        return dao.traerPersonal();
    }

  
    public int agregar(UnidadDeVenta uv) throws Exception {
        if (dao.traer(uv.getNombreComercial()) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + uv.getNombreComercial());
        }
        return dao.agregar(uv);
    }

    public void modificar(UnidadDeVenta uv) throws Exception {
        UnidadDeVenta existente = dao.traer(uv.getIdUnidadVenta());
        if (existente == null) {
            throw new Exception("Error: No se puede modificar. La Unidad de Venta no existe.");
        }
        dao.actualizar(uv);
    }

    public void eliminar(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traer(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No se puede eliminar. La Unidad de Venta no existe.");
        }
        dao.eliminar(u);
    }


    public void agregarPersonal(int idUnidadDeVenta, Persona persona) throws Exception {
        UnidadDeVenta u = dao.traerUnidadDeVentaYPersonal(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        if (persona == null) {
            throw new Exception("Error: La Persona no puede ser nula.");
        }
        if (u.getPersonal().contains(persona)) {
            throw new Exception("Error: La persona ya está asignada a esta Unidad de Venta.");
        }
        dao.agregar(u, persona);
    }

    public void agregarPlato(int idUnidadDeVenta, Plato plato) throws Exception {
        UnidadDeVenta u = dao.traerUnidadDeVentaYPlatos(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        if (plato == null) {
            throw new Exception("Error: El Plato no puede ser nulo.");
        }
        if (u.getPlatos().contains(plato)) {
            throw new Exception("Error: El plato ya existe en el menú de esta Unidad de Venta.");
        }
        dao.agregar(u, plato);
    }

    public void agregarPedido(int idUnidadDeVenta, Pedido pedido) throws Exception {
        UnidadDeVenta u = dao.traerUnidadDeVentaYPedido(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        if (pedido == null) {
            throw new Exception("Error: El Pedido no puede ser nulo.");
        }
        dao.agregar(u, pedido);
    }


    public Persona traerPersona(int idPersona) throws Exception {
        Persona p = dao.traerPersona(idPersona);
        if (p == null) {
            throw new Exception("Error: No existe la Persona con id: " + idPersona);
        }
        return p;
    }

    public Plato traerPlato(int idPlato) throws Exception {
        Plato p = dao.traerPlato(idPlato);
        if (p == null) {
            throw new Exception("Error: No existe el Plato con ID: " + idPlato);
        }
        return p;
    }

    public Pedido traerPedido(int idPedido) throws Exception {
        Pedido p = dao.traer(idPedido);
        if (p == null) {
            throw new Exception("Error: No existe el Pedido con ID: " + idPedido);
        }
        return p;
    }
}