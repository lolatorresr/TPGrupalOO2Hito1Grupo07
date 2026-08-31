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
    
  //--------METODOS UNIDAD DE VENTA--------
    
    public int agregarUnidadVenta(UnidadDeVenta uv) throws Exception {
        if (dao.traerUnidadVenta(uv.getNombreComercial()) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + uv.getNombreComercial());
        }
        return dao.agregarUnidadVenta(uv);
    }

    public void modificarUnidadVenta(UnidadDeVenta uv) throws Exception {
        UnidadDeVenta existente = dao.traerUnidadVenta(uv.getIdUnidadDeVenta());
        if (existente == null) {
            throw new Exception("Error: No se puede modificar. La Unidad de Venta no existe.");
        }
        dao.actualizarUnidadVenta(uv);
    }

    public void eliminar(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traerUnidadVenta(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No se puede eliminar. La Unidad de Venta no existe.");
        }
        dao.eliminarUnidadVenta(u);
    }
    public UnidadDeVenta traerUnidadVenta(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traerUnidadVenta(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        return u;
    }

    public UnidadDeVenta traerUnidadVenta(String nombreComercial) throws Exception {
        UnidadDeVenta u = dao.traerUnidadVenta(nombreComercial);
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

}