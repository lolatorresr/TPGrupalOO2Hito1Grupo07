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
	protected Set<Plato> platos;
	protected Set<Pedido> pedidos;
	
	
	public UnidadDeVenta () {
	}
	
	public UnidadDeVenta(String nombreComercial, Persona responsable, double superficie,
			String codigoUnico) {
		this.nombreComercial=nombreComercial;
		this.responsable=responsable;
		this.superficie=superficie;
		this.codigoUnico=codigoUnico;
		this.personal= new HashSet<Persona>();
		this.platos= new HashSet<Plato>();
		this.pedidos= new HashSet<Pedido>();
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
	

	public Set<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(Set<Plato> platos) {
		this.platos = platos;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "UnidadDeVenta [idUnidadDeVenta=" + idUnidadDeVenta + ", nombreComercial=" + nombreComercial
				+ ", responsable=" + responsable + ", superficie=" + superficie + ", codigoUnico=" + codigoUnico
				+ ", personal=" + personal + ", platos=" + platos + ", pedidos=" + pedidos + "]";
	}

	
	
	
	
}
