package datos;

import java.util.HashSet;
import java.util.Set;
import datos.ItemPlato;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

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
	
	public double calcularRecaudacionTotal() {
	    double total = 0.0;
	    if (this.pedidos != null) {
	        for (Pedido p : this.pedidos) {
	            if (p.getItemPlatos() != null) {
	            	for(ItemPlato item : p.getItemPlatos()) {
	            	total += item.getSubTotal();
	            }
	           }
	        }
	    }
	    return total;
	}
	
	public int calcularCantidadDePlato(Plato plato) {
	    int total = 0;
	    if (this.pedidos != null && plato != null) {
	        for (Pedido p : this.pedidos) {
	            if (p.getItemPlatos() != null) {
	            	for(ItemPlato item : p.getItemPlatos()) {
	            		if(item.getPlato() != null && plato.equals(item.getPlato())) {
	            			total += item.getCantidad();
	            		}
	            	}
	            }
	        }
	    }
	    return total;
	}
	
	public Plato traerPlatoMasPedido() {
	    Plato masPedido = null;
	    int maxCantidad = 0;

	    if (this.pedidos != null) {
	        for (Pedido p : this.pedidos) {
	        	for(ItemPlato item : p.getItemPlatos()) {
	        		 if (p.getItemPlatos() != null) {
	 	                Plato platoActual = item.getPlato();
	 	                int cantidadActual = this.calcularCantidadDePlato(platoActual);

	 	                if (cantidadActual > maxCantidad) {
	 	                    maxCantidad = cantidadActual;
	 	                    masPedido = platoActual;
	        	}
	                }
	            }
	        }
	    }

	    return masPedido;
	}
	public int getCantidadPedidos() {
	    return this.pedidos.size();
	}
	
	@Override
	public String toString() {
		return "UnidadDeVenta [idUnidadDeVenta=" + idUnidadDeVenta + ", nombreComercial=" + nombreComercial
				+ ", responsable=" + responsable + ", superficie=" + superficie + ", codigoUnico=" + codigoUnico
				+ ", personal=" + personal + "]";
	}
	
	
	
}
