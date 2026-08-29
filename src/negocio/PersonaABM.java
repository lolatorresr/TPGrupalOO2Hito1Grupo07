package negocio;
import java.time.LocalDate;
import java.util.List;
import dao.PersonaDao;
import datos.Cajero;
import datos.Persona;
public class PersonaABM {
	PersonaDao dao = new PersonaDao();
	
	//--------METODOS PERSONA--------
	
	public int agregar (String nombre, String apellido, long dni, LocalDate fechaNacimiento, 
			LocalDate fechaIngreso, double sueldoBase) throws Exception {
		if(dao.traer(dni) != null) {
			throw new Exception("ERROR: Ya existe una persona registrada con el DNI " +  dni);
		}
		Persona c= new Persona(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		return dao.agregar(c);
	}
	
	public Persona traer(int idPersona) {
		return dao.traer(idPersona);
	}
	
	public Persona traer(long dni) {
		return dao.traer(dni);
	}
	
	public List<Persona> traerPersonas(){
		return dao.traerPersonas();
	}
	
	public void modificar(Persona p) throws Exception{
		dao.actualizar(p);
	}
	
	public void eliminar(int idPersona) throws Exception{
		Persona p = dao.traer(idPersona);
		if(p == null) {
			throw new Exception("ERROR: No existe la persona con ID " + idPersona);
		}
		dao.eliminar(p);
	}
	
	//--------METODOS CAJERO--------
	
	public int agregarCajero(String nombre, String apellido, long dni, LocalDate fechaNacimiento, 
			LocalDate fechaIngreso, double sueldoBase, String turnoTrabajo, 
			double recaudacionTotal) throws Exception{
		if(dao.traer(dni) != null) {
			throw new Exception("ERROR: Ya existe una persona registrada con el DNI " + dni);
		}
		
		Cajero c = new Cajero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, turnoTrabajo, recaudacionTotal);
		return dao.agregar(c);
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
	
}