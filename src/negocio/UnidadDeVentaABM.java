package negocio;

import java.util.HashSet;
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
<<<<<<< Updated upstream

=======
    
  //--------METODOS UNIDAD DE VENTA--------
    
    public int agregarUnidadVenta(String nombreComercial, Persona responsable, double superficie,
			String codigoUnico) throws Exception {
        if (dao.traerUnidadVenta(nombreComercial) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + nombreComercial);
        }
        UnidadDeVenta uv = new UnidadDeVenta();
        return dao.agregarUnidadVenta(uv);
    }
    public void agregarPersonal(int idUnidadDeVenta, Persona persona) throws Exception {
        UnidadDeVenta uv = dao.traerUnidadDeVentaYPersonal(idUnidadDeVenta);
        if (uv == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        
        if (persona == null) {
            throw new Exception("Error: La persona a agregar no puede ser nula.");
        }
        
        if (uv.getPersonal().contains(persona)) {
            throw new Exception("Error: La persona con DNI " + persona.getDni() + " ya está asignada a esta Unidad de Venta.");
        }
        
        dao.agregarPersonal(uv, persona);
    }
    public void agregarPlato(int idUnidadDeVenta, Plato plato) throws Exception {
        UnidadDeVenta uv = dao.traerUnidadDeVentaYPlatos(idUnidadDeVenta);
        if (uv == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        if (plato == null) {
            throw new Exception("Error: El plato a agregar no puede ser nulo.");
        }
        if (uv.getPlatos().contains(plato)) {
            throw new Exception("Error: El plato ya se encuentra asignado a esta Unidad de Venta.");
        }
        dao.agregarPlato(uv, plato);
    }

    public void agregarPedido(int idUnidadDeVenta, Pedido pedido) throws Exception {
        UnidadDeVenta uv = dao.traerUnidadDeVentaYPedido(idUnidadDeVenta);
        if (uv == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        if (pedido == null) {
            throw new Exception("Error: El pedido a agregar no puede ser nulo.");
        }
        if (uv.getPedidos().contains(pedido)) {
            throw new Exception("Error: El pedido ya está registrado en esta Unidad de Venta.");
        }
        dao.agregarPedido(uv, pedido);
    }
    public void modificarUnidadVenta(UnidadDeVenta uv) throws Exception {
        UnidadDeVenta existente = dao.traerUnidadVenta(uv.getIdUnidadDeVenta());
        if (existente == null) {
            throw new Exception("Error: No se puede modificar. La Unidad de Venta no existe.");
        }
        dao.actualizarUnidadVenta(uv);
    }
>>>>>>> Stashed changes


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

    public List<UnidadDeVenta> traerUnidadVentaMayorSuperficie(double superficie) {
        return dao.traerUnidadVentaMayorSuperficie(superficie);
    }
    public List<Persona> traerPersonal() {
        return dao.traerPersonal();
    }
    public UnidadDeVenta traerUnidadVentaMayorPedidos() throws Exception {
        UnidadDeVenta uv = dao.traerUnidadDeVentaMayorPedidos();
        if (uv == null) {
            throw new Exception("Error: No se encontraron unidades de venta registradas.");
        }
        return uv;
    }

<<<<<<< Updated upstream
  
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
=======
    public double traerRecaudacionTotal(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta uv = dao.traerUnidadDeVentaYPedido(idUnidadDeVenta);
        if (uv == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        return uv.calcularRecaudacionTotal();
    }
    public Plato traerPlatoMasPedido(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta uv = dao.traerUnidadVentaCompleta(idUnidadDeVenta);
        if (uv == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        Plato p = uv.traerPlatoMasPedido();
        if (p == null) {
            throw new Exception("Error: La Unidad de Venta no registra pedidos con platos.");
        }
        return p;
    }
    
>>>>>>> Stashed changes
}