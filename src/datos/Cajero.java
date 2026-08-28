package datos;

import java.time.LocalDate;

public class Cajero extends Persona {
	private String turnoTrabajo;
	private double recaudacionTotal;
	
	public Cajero() {
	}
	
	public Cajero(String nombre, String apellido, long dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String turnoTrabajo, double recaudacionTotal) {
		//super(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.turnoTrabajo=turnoTrabajo;
		this.recaudacionTotal=recaudacionTotal;
	}

	public String getTurnoTrabajo() {
		return turnoTrabajo;
	}

	public void setTurnoTrabajo(String turnoTrabajo) {
		this.turnoTrabajo = turnoTrabajo;
	}

	public double getRecaudacionTotal() {
		return recaudacionTotal;
	}

	public void setRecaudacionTotal(double recaudacionTotal) {
		this.recaudacionTotal = recaudacionTotal;
	}

	@Override
	public String toString() {
		return "Cajero [turnoTrabajo=" + turnoTrabajo + ", recaudacionTotal=" + recaudacionTotal + "]";
	}
	
	

}
