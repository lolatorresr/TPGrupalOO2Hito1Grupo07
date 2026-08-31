package datos;

import java.time.LocalDate;

public class Cocinero extends Persona {
	private String especialidad;
	private String categoria;
	
	public Cocinero() {
	}

	public Cocinero(String nombre, String apellido, long dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			double sueldoBase, String especialidad, String categoria) {
		super(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.especialidad=especialidad;
		this.categoria=categoria;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return super.toString() + " | Cocinero [especialidad=" + especialidad + ", categoria=" + categoria + "]";
	}
	
	

}
