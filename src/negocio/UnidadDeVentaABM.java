package negocio;

import java.util.List;
import dao.UnidadDeVentaDao;
import datos.UnidadDeVenta;
import datos.Persona;
import datos.Plato;
import datos.PuestoDesarmable;
import datos.FoodTruck;
import datos.Pedido;
import datos.Festival;

public class UnidadDeVentaABM {
	
    private static UnidadDeVentaABM instancia;
    private UnidadDeVentaDao dao;

    public UnidadDeVentaABM() {
        this.dao = new UnidadDeVentaDao();
    }

    public static UnidadDeVentaABM getInstancia() {
        if (instancia == null) {
            instancia = new UnidadDeVentaABM();
        }
        return instancia;
    }
    
    //--------ABM UNIDAD DE VENTA--------
    
    // Se quitó idResponsable
    public int agregarUnidadVenta(String nombreComercial, String codigoUnico, double superficie) throws Exception {
        if (dao.traerUnidadVenta(nombreComercial) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + nombreComercial);
        }
        // Se instancia sin responsable
        UnidadDeVenta uv = new UnidadDeVenta(nombreComercial, superficie, codigoUnico);
        return dao.agregarUnidadVenta(uv);
    }

    // Sobrecarga por si le pasas el objeto armado (útil para el test)
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
    
    // Se quitó idResponsable
    public int agregarFoodTruck(String nombreComercial, String codigoUnico, double superficie, 
            String patente, boolean conexionElectrica) throws Exception {
        if (dao.traerUnidadVenta(nombreComercial) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + nombreComercial);
        }
        if (dao.traerFoodTruckPorPatente(patente) != null) {
            throw new Exception("Error: Ya existe un Food Truck registrado con la patente " + patente);
        }
        
        FoodTruck ft = new FoodTruck(nombreComercial, superficie, codigoUnico, patente, conexionElectrica);
        return dao.agregarUnidadVenta(ft);
    }

    // Sobrecarga por si le pasas el objeto armado
    public int agregarFoodTruck(FoodTruck ft) throws Exception {
        if (dao.traerUnidadVenta(ft.getNombreComercial()) != null) {
            throw new Exception("Error: Ya existe una Unidad de Venta con el nombre " + ft.getNombreComercial());
        }
        if (dao.traerFoodTruckPorPatente(ft.getPatente()) != null) {
            throw new Exception("Error: Ya existe un Food Truck registrado con la patente " + ft.getPatente());
        }
        return dao.agregarUnidadVenta(ft);
    }
    
    public FoodTruck traerFoodTruckPorPatente(String patente) throws Exception {
        FoodTruck f = dao.traerFoodTruckPorPatente(patente);
        if (f == null) {
            throw new Exception("Error: No se encontró ningún Food Truck registrado con la patente " + patente);
        }
        return f;
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
    
    // Se quitó idResponsable
    public int agregarPuestoDesarmable(String nombreComercial, double superficie, 
            String codigoUnico, int cantidadCarpas, int tiempoMontaje) throws Exception {
    	if (dao.traerUnidadVenta(nombreComercial) != null) {
            throw new Exception("ERROR: Ya existe una Unidad de Venta con el nombre " + nombreComercial);
        }
    	
    	PuestoDesarmable pd = new PuestoDesarmable(nombreComercial, superficie, codigoUnico, cantidadCarpas, tiempoMontaje);
        return dao.agregarUnidadVenta(pd);
    }

    // Sobrecarga por si le pasas el objeto armado
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
    
    //---- ASIGNACIONES POSTERIORES ----
    
    public void asignarResponsable(int idUnidadDeVenta, Persona responsable) throws Exception {
        UnidadDeVenta uv = dao.traerUnidadVenta(idUnidadDeVenta);
        if (uv == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        if (responsable == null) {
            throw new Exception("Error: La persona a asignar como responsable no puede ser nula.");
        }
        
        uv.setResponsable(responsable);
        dao.actualizarUnidadVenta(uv);
    }

    public void asignarFestival(int idUnidadDeVenta, Festival festival) throws Exception {
        UnidadDeVenta uv = dao.traerUnidadVenta(idUnidadDeVenta);
        if (uv == null) {
            throw new Exception("Error: No existe la Unidad de Venta con ID " + idUnidadDeVenta);
        }
        if (festival == null) {
            throw new Exception("Error: El festival a asignar no puede ser nulo.");
        }
        
        uv.setFestival(festival);
        dao.actualizarUnidadVenta(uv);
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
    
    //----CONSULTAS COMPLEJAS HQL (NUEVAS)----

    public List<FoodTruck> traerFoodTruckPorFestivalYElectricidad(String nombreFestival, boolean electricidad) throws Exception {
        List<FoodTruck> lista = dao.traerFoodTruckPorFestivalYElectricidad(nombreFestival, electricidad);
        if (lista == null || lista.isEmpty()) {
            throw new Exception("No se encontraron Food Trucks para el festival '" + nombreFestival + "' con electricidad = " + electricidad);
        }
        return lista;
    }

    public List<PuestoDesarmable> traerPuestoPorFestivalYCarpas(String nombreFestival, int cantidadMinimaCarpas) throws Exception {
        List<PuestoDesarmable> lista = dao.traerPuestoPorFestivalYCarpas(nombreFestival, cantidadMinimaCarpas);
        if (lista == null || lista.isEmpty()) {
            throw new Exception("No se encontraron Puestos Desarmables en el festival '" + nombreFestival + "' con más de " + cantidadMinimaCarpas + " carpas.");
        }
        return lista;
    }
    
    //----CONSULTAS BÁSICAS----

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