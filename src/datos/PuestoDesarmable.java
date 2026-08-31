package datos;

public class PuestoDesarmable extends UnidadDeVenta {
	private int cantidadCarpas;
	private int tiempoMontaje;
	
	public PuestoDesarmable() {
	}

	public PuestoDesarmable(String nombreComercial, Persona responsable, double superficie, String codigoUnico, 
			int cantidadCarpas, int tiempoMontaje) {
		super(nombreComercial, responsable, superficie, codigoUnico);
		this.cantidadCarpas=cantidadCarpas;
		this.tiempoMontaje=tiempoMontaje;
	}

	public int getCantidadCarpas() {
		return cantidadCarpas;
	}

	public void setCantidadCarpas(int cantidadCarpas) {
		this.cantidadCarpas = cantidadCarpas;
	}

	public int getTiempoMontaje() {
		return tiempoMontaje;
	}

	public void setTiempoMontaje(int tiempoMontaje) {
		this.tiempoMontaje = tiempoMontaje;
	}

	@Override
	public String toString() {
		return super.toString() + " | PuestoDesarmable [cantidadCarpas=" + cantidadCarpas + ", tiempoMontaje=" + tiempoMontaje + "]";
	}
	
	
	

}
