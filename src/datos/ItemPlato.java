package datos;

public class ItemPlato {
	private int idItemPlato;
	private Plato plato;
	private int cantidad;
	private double subTotal;
	
	public ItemPlato() {
	}

	public ItemPlato(Plato plato, int cantidad, double subTotal) {
		this.plato = plato;
		this.cantidad = cantidad;
		this.subTotal = subTotal;
	}

	public int getIdItemPlato() {
		return idItemPlato;
	}

	protected void setIdItemPlato(int idItemPlato) {
		this.idItemPlato = idItemPlato;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "ItemPlato [idItemPlato=" + idItemPlato + ", plato=" + plato + ", cantidad=" + cantidad + ", subTotal="
				+ subTotal + "]";
	}
	
	

}
