package datos;

import java.util.HashSet;
import java.util.Set;

public class UnidadDeVenta {
	protected int idUnidadDeVenta;
	protected String nombreComercial;
	protected Persona responsable;
	protected double superficie;
	protected String codigoUnico;
	protected Set<Persona> personal;
	
	
	public UnidadDeVenta () {
	}
	
	public UnidadDeVenta(String nombreComercial, Persona responsable, double superficie,
			String codigoUnico) {
		this.nombreComercial=nombreComercial;
		this.responsable=responsable;
		this.superficie=superficie;
		this.codigoUnico=codigoUnico;
		this.personal= new HashSet<Persona>();
	}

	public int getIdUnidadDeVenta() {
		return idUnidadDeVenta;
	}

	protected void setIdUnidadDeVenta(int idUnidadDeVenta) {
		this.idUnidadDeVenta = idUnidadDeVenta;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public String getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public Set<Persona> getPersonal() {
		return personal;
	}

	public void setPersonal(Set<Persona> personal) {
		this.personal = personal;
	}

	@Override
	public String toString() {
		return "UnidadDeVenta [idUnidadDeVenta=" + idUnidadDeVenta + ", nombreComercial=" + nombreComercial
				+ ", responsable=" + responsable + ", superficie=" + superficie + ", codigoUnico=" + codigoUnico
				+ ", personal=" + personal + "]";
	}
	
	
	
}
