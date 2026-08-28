package negocio;
import java.time.LocalDate;
import java.util.List;
import dao.PersonaDao;
import datos.Persona;
public class PersonaABM {
PersonaDao dao = new PersonaDao();
/*

public Persona traer(long idPersona) {
return dao.traer(idPersona);
}

public Persona traer(int dni) {
return dao.traer(dni);
}

protected String nombre;
protected String apellido;
protected long dni;

protected LocalDate fechaNacimiento;
protected LocalDate fechaIngreso;
protected double sueldoBase;
protected int idPersona;


public int agregar(String apellido, String nombre, long dni, LocalDate fechaNacimiento,LocalDate fechaIngreso,double sueldoBase)throws Exception {
	
	Persona pe = dao.traer(dni);
	if (pe != null) {
		throw new Exception("ya existe este cliente en la BD");
	}
Persona p = new Persona( nombre, apellido, dni,fechaNacimiento, fechaIngreso, sueldoBase);
return dao.agregar(p);
}


/*public void modificar(Persona p) {
/*
* En caso de editar el dni, antes de actualizar, validar que no exista un cliente con el mismo
dni y si eso pasa lanzar la Exception

dao.actualizar(c);
}

public void eliminar(long idPersona)throws Exception {

Persona p = dao.traer(idPersona);
if (p != null) {
	throw new Exception("Esta persona no existe");
}
dao.eliminar(p);
}

public List<Persona> traer() {
return dao.traer();
}*/
}