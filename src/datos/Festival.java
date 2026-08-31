package datos;

import java.time.LocalDate;

public class Festival {
	private int idFestival;
	private String nombre;
	private String temporada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private UnidadDeVenta unidades;
	
	public Festival() {
	}

	public Festival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,
			UnidadDeVenta unidades) {
		this.nombre = nombre;
		this.temporada = temporada;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.unidades = unidades;
	}

	public int getIdFestival() {
		return idFestival;
	}

	protected void setIdFestival(int idFestival) {
		this.idFestival = idFestival;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public UnidadDeVenta getUnidades() {
		return unidades;
	}

	public void setUnidades(UnidadDeVenta unidades) {
		this.unidades = unidades;
	}

	@Override
	public String toString() {
		return "Festival [idFestival=" + idFestival + ", nombre=" + nombre + ", temporada=" + temporada
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", unidades=" + unidades + "]";
	}
	
	

}
