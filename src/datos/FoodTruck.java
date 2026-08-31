package datos;

public class FoodTruck extends UnidadDeVenta{
	private String patente;
	private boolean conexionElectrica;
	
	public FoodTruck() {
	}

	public FoodTruck(String nombreComercial, Persona responsable, double superficie, String codigoUnico,
			String patente, boolean conexionElectrica) {
		super(nombreComercial, responsable, superficie, codigoUnico);
		this.patente=patente;
		this.conexionElectrica=conexionElectrica;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public boolean isConexionElectrica() {
		return conexionElectrica;
	}

	public void setConexionElectrica(boolean conexionElectrica) {
		this.conexionElectrica = conexionElectrica;
	}

	@Override
	public String toString() {
		return super.toString() + " | FoodTruck [patente=" + patente + ", conexionElectrica=" + conexionElectrica + "]";
	}
	
	
	
	

}
