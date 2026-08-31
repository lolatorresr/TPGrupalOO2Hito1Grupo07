package datos;

import java.time.LocalDate;

public class Pedido {
	private int idPedido;
	private LocalDate fechaTransaccion;
	private Festival festival;
	private UnidadDeVenta unidadVenta;
	private ItemPlato itemPlatos;
	
	public Pedido() {
	}

	public Pedido(LocalDate fechaTransaccion, Festival festival, UnidadDeVenta unidadVenta,
			ItemPlato itemPlatos) {
		this.fechaTransaccion = fechaTransaccion;
		this.festival = festival;
		this.unidadVenta = unidadVenta;
		this.itemPlatos = itemPlatos;
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

	public ItemPlato getItemPlatos() {
		return itemPlatos;
	}

	public void setItemPlatos(ItemPlato itemPlatos) {
		this.itemPlatos = itemPlatos;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", fechaTransaccion=" + fechaTransaccion + ", festival=" + festival
				+ ", unidadVenta=" + unidadVenta + ", itemPlatos=" + itemPlatos + "]";
	}
	
	

}
