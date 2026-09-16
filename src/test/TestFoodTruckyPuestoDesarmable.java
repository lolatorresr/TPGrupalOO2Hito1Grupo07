package test;

import java.util.List;

import datos.FoodTruck;
import datos.PuestoDesarmable;
import negocio.UnidadDeVentaABM;

public class TestFoodTruckyPuestoDesarmable {

	public static void main(String[] args) {
		UnidadDeVentaABM unidadABM = new UnidadDeVentaABM();

		try {
			System.out.println("\n--------------------------------------\n");
			System.out.println("------- TEST DE CONSULTAS COMPLEJAS -------");
			System.out.println("\n--------------------------------------\n");

			String nombreFestival = "Festival Gastronomico Primavera";

			// -------------------------------------------------------------------
			// CONSULTA 1: Food Trucks con electricidad
			// -------------------------------------------------------------------
			System.out.println("1) Buscando Food Trucks CON electricidad en '" + nombreFestival + "':");
			
			List<FoodTruck> foodTrucks = unidadABM.traerFoodTruckPorFestivalYElectricidad(nombreFestival, true);
			
			for (FoodTruck ft : foodTrucks) {
				System.out.println("   -> ÉXITO: Encontrado " + ft.getNombreComercial() + " (Patente: " + ft.getPatente() + ")");
			}

			System.out.println("\n--------------------------------------\n");

			// CONSULTA 2: Puestos Desarmables por cantidad de carpas
			int minCarpas = 2; // Buscar los que tengan MAS de 2 carpas
			System.out.println("2) Buscando Puestos Desarmables con MÁS de " + minCarpas + " carpas en '" + nombreFestival + "':");
			
			List<PuestoDesarmable> puestos = unidadABM.traerPuestoPorFestivalYCarpas(nombreFestival, minCarpas);
			
			for (PuestoDesarmable pd : puestos) {
				System.out.println("   -> ÉXITO: Encontrado " + pd.getNombreComercial() + " (Carpas: " + pd.getCantidadCarpas() + ")");
			}

			System.out.println("\n--------------------------------------\n");
			System.out.println("------- FIN DE LAS CONSULTAS -------");

		} catch (Exception e) {
			System.out.println("\nERROR EN LA EJECUCIÓN: " + e.getMessage());
			e.printStackTrace();
		}
	}
}