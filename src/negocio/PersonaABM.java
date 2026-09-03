package negocio;
import java.time.LocalDate;
import java.util.List;
import dao.PersonaDao;
import datos.Cajero;
import datos.Cocinero;
import datos.Persona;
public class PersonaABM {
	PersonaDao dao = new PersonaDao();
	
	//--------METODOS PERSONA--------
	
	public int agregarPersona (String nombre, String apellido, long dni, LocalDate fechaNacimiento, 
			LocalDate fechaIngreso, double sueldoBase) throws Exception {
		if(dao.traerPersona(dni) != null) {
			throw new Exception("ERROR: Ya existe una persona registrada con el DNI " +  dni);
		}
		Persona c= new Persona(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		return dao.agregarPersona(c);
	}
	
	public Persona traerPersona(int idPersona) {
		return dao.traerPersona(idPersona);
	}
	
	public Persona traerPersona(long dni) {
		return dao.traerPersona(dni);
	}
	
	public List<Persona> traerPersonas(){
		return dao.traerPersonas();
	}
	
	public void modificarPersona(Persona p) throws Exception{
		dao.actualizarPersona(p);
	}
	
	public void eliminarPersona(int idPersona) throws Exception{
		Persona p = dao.traerPersona(idPersona);
		if(p == null) {
			throw new Exception("ERROR: No existe la persona con ID " + idPersona);
		}
		dao.eliminarPersona(p);
	}
	
	//--------METODOS CAJERO--------
	
	public int agregarCajero(String nombre, String apellido, long dni, LocalDate fechaNacimiento, 
			LocalDate fechaIngreso, double sueldoBase, String turnoTrabajo, 
			double recaudacionTotal) throws Exception{
		if(dao.traerPersona(dni) != null) {
			throw new Exception("ERROR: Ya existe una persona registrada con el DNI " + dni);
		}
		if(fechaNacimiento == null || fechaNacimiento.plusYears(18).isAfter(LocalDate.now())) {
			throw new Exception("ERROR: El personal del predio debe ser mayor de edad.");
		}
		
		Cajero c = new Cajero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, turnoTrabajo, recaudacionTotal);
		return dao.agregarPersona(c);
	}
	
	public Cajero traerCajero(int idPersona) {
		return dao.traerCajero(idPersona);
	}
	
	public List<Cajero> traerCajeros(){
		return dao.traerCajeros();
	}
	
	public Cajero traerCajeroMayorRecaudacion() {
		return dao.traerCajeroMayorRecaudacion();
	}
	

	//--------METODOS COCINERO--------

	public int agregarCocinero(String nombre, String apellido, long dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String especialidad,
			String categoria) throws Exception{
		if(dao.traerPersona(dni) != null) {
			throw new Exception("ERROR: Ya existe una persona registrada con el DNI " + dni);
		}
		if(fechaNacimiento == null || fechaNacimiento.plusYears(18).isAfter(LocalDate.now())) {
			throw new Exception("ERROR: El personal del predio debe ser mayor de edad.");
		}

		Cocinero c = new Cocinero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, especialidad, categoria);
		return dao.agregarPersona(c);
	}

	public Cocinero traerCocinero(int idPersona) {
		return dao.traerCocinero(idPersona);
	}

	public List<Cocinero> traerCocineros(){
		return dao.traerCocineros();
	}

	//--------CASO DE USO: cocineros por unidad de venta--------

	public List<Cocinero> traerCocinerosDeUnidadDeVenta(int idUnidadDeVenta) throws Exception{
		List<Cocinero> lista = dao.traerCocinerosDeUnidadDeVenta(idUnidadDeVenta);
		if(lista == null || lista.isEmpty()) {
			throw new Exception("ERROR: La unidad de venta con ID " + idUnidadDeVenta + " no tiene cocineros asignados.");
		}
		return lista;
	}

	public Cocinero traerCocineroMasAntiguo(int idUnidadDeVenta) throws Exception{
		Cocinero c = dao.traerCocineroMasAntiguo(idUnidadDeVenta);
		if(c == null) {
			throw new Exception("ERROR: La unidad de venta con ID " + idUnidadDeVenta + " no tiene cocineros asignados.");
		}
		return c;
	}

	public List<Cocinero> traerCocinerosPorEspecialidad(int idUnidadDeVenta, String especialidad) throws Exception{
		List<Cocinero> lista = dao.traerCocinerosPorEspecialidad(idUnidadDeVenta, especialidad);
		if(lista == null || lista.isEmpty()) {
			throw new Exception("ERROR: No hay cocineros con especialidad '" + especialidad
					+ "' en la unidad de venta con ID " + idUnidadDeVenta + ".");
		}
		return lista;
	}

}
