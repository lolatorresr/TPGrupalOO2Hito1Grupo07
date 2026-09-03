package negocio;

import java.util.HashSet;
import java.util.List;
import dao.UnidadDeVentaDao;
import datos.UnidadDeVenta;
import datos.Persona;
import datos.Plato;
import datos.PuestoDesarmable;
import datos.FoodTruck;
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
    
  //--------ABM UNIDAD DE VENTA--------
    
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

    public void eliminarUnidadVenta(int idUnidadDeVenta) throws Exception {
        UnidadDeVenta u = dao.traerUnidadVenta(idUnidadDeVenta);
        if (u == null) {
            throw new Exception("Error: No se puede eliminar. La Unidad de Venta no existe.");
        }
        dao.eliminarUnidadVenta(u);
    }
    
    //----METODOS FOODTRUCK----
    
    public int agregarFoodTruck(FoodTruck ft) throws Exception {
        if (dao.traerUnidadVenta(ft.getNombreComercial()) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + ft.getNombreComercial());
        }
        if (dao.traerFoodTruckPorPatente(ft.getPatente()) != null) {
            throw new Exception("Error: Ya existe un Food Truck registrado con la patente " + ft.getPatente());
        }
        return dao.agregarUnidadVenta(ft);
    }

    public void eliminarFoodTruck(int idUnidadDeVenta) throws Exception {
        FoodTruck ft = dao.traerFoodTruck(idUnidadDeVenta);
        if (ft == null) {
            throw new Exception("Error: No se puede eliminar. El Food Truck no existe.");
        }
        dao.eliminarUnidadVenta(ft);
    }
    
    public FoodTruck traerFoodTruck(int idUnidadDeVenta) throws Exception {
        FoodTruck f = dao.traerFoodTruck(idUnidadDeVenta);
        if (f == null) {
            throw new Exception("Error: No existe el Food Truck con ID " + idUnidadDeVenta);
        }
        return f;
    }

    public List<FoodTruck> traerFoodTrucks() {
        return dao.traerFoodTrucks();
    }
    
    //----METODOS PUESTO DESARMABLE----
    
    public int agregarPuestoDesarmable(PuestoDesarmable pd) throws Exception {
        if (dao.traerUnidadVenta(pd.getNombreComercial()) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + pd.getNombreComercial());
        }
        return dao.agregarUnidadVenta(pd);
    }


    public void eliminarPuestoDesarmable(int idUnidadDeVenta) throws Exception {
        PuestoDesarmable pd = dao.traerPuestoDesarmable(idUnidadDeVenta);
        if (pd == null) {
            throw new Exception("Error: No se puede eliminar. El Puesto Desarmable no existe.");
        }
        dao.eliminarUnidadVenta(pd);
    }
    
    public PuestoDesarmable traerPuestoDesarmable(int idUnidadDeVenta) throws Exception {
        PuestoDesarmable p = dao.traerPuestoDesarmable(idUnidadDeVenta);
        if (p == null) {
            throw new Exception("Error: No existe el Puesto Desarmable con ID " + idUnidadDeVenta);
        }
        return p;
    }

    public List<PuestoDesarmable> traerPuestosDesarmables() {
        return dao.traerPuestosDesarmables();
    }
    
    //---------------------------
    
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
    
    
    //----CONSULTAS----


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
    
    
    //-----------------------
    
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
        Pedido p = dao.traerPedido(idPedido);
        if (p == null) {
            throw new Exception("Error: No existe el Pedido con ID: " + idPedido);
        }
        return p;
    }
}