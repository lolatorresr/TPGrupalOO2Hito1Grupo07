package datos;

import java.time.LocalDate;
import java.util.Objects;

import datos.Persona;

public class Persona {
	
	protected String nombre;
	protected String apellido;
	protected long dni;
	protected LocalDate fechaNacimiento;
	protected LocalDate fechaIngreso;
	protected double sueldoBase;
	protected int idPersona;
	
	
	public Persona() {
		
	}
	
	public Persona(String nombre, String apellido, long dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			double sueldoBase) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.sueldoBase = sueldoBase;
	}

	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", fechaNacimiento="
				+ fechaNacimiento + ", fechaIngreso=" + fechaIngreso + ", sueldoBase=" + sueldoBase + ", idPersona="
				+ idPersona + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, fechaIngreso, fechaNacimiento, idPersona, nombre, sueldoBase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellido, other.apellido) && dni == other.dni
				&& Objects.equals(fechaIngreso, other.fechaIngreso)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && idPersona == other.idPersona
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(sueldoBase) == Double.doubleToLongBits(other.sueldoBase);
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	

}
