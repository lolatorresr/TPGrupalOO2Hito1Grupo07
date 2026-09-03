package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Pedido {
	private int idPedido;
	private LocalDate fechaTransaccion;
	private Festival festival;
	private UnidadDeVenta unidadVenta;
	private Set<ItemPlato> itemPlatos;
	
	public Pedido() {
	}

	public Pedido(LocalDate fechaTransaccion, Festival festival, UnidadDeVenta unidadVenta) {
		this.fechaTransaccion = fechaTransaccion;
		this.festival = festival;
		this.unidadVenta = unidadVenta;
		this.itemPlatos = new HashSet<ItemPlato>();
	}

	public int getIdPedido() {
		return idPedido;
	}

	protected void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(LocalDate fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public UnidadDeVenta getUnidadVenta() {
		return unidadVenta;
	}

	public void setUnidadVenta(UnidadDeVenta unidadVenta) {
		this.unidadVenta = unidadVenta;
	}

	public Set<ItemPlato> getItemPlatos() {
		return itemPlatos;
	}

	public void setItemPlatos(Set<ItemPlato> itemPlatos) {
		this.itemPlatos = itemPlatos;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", fechaTransaccion=" + fechaTransaccion + ", festival=" + festival
				+ ", unidadVenta=" + unidadVenta + ", itemPlatos=" + itemPlatos + "]";
	}
	
	
	

}
