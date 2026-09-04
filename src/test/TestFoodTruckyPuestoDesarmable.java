package test;

import java.time.LocalDate;
import java.util.List;

import datos.FoodTruck;
import datos.Persona;
import datos.PuestoDesarmable;
import negocio.PersonaABM;
import negocio.UnidadDeVentaABM;

public class TestFoodTruckyPuestoDesarmable {

	public static void main(String[] args) {
		UnidadDeVentaABM uvAbm = UnidadDeVentaABM.getInstancia();
		PersonaABM personaAbm = new PersonaABM();

		try {
			System.out.println("--- 1 ALTA DE PERSONAL RESPONSABLE");
			int idResp1 = personaAbm.agregarPersona("Juan", "Perez", 12345678L, LocalDate.of(1980, 1, 1), LocalDate.now(), 500000.0);
			int idResp2 = personaAbm.agregarPersona("Maria", "Gomez", 87654321L, LocalDate.of(1985, 5, 5), LocalDate.now(), 500000.0);
			Persona resp1 = personaAbm.traerPersona(idResp1);
			Persona resp2 = personaAbm.traerPersona(idResp2);
			System.out.println("Responsables creados ok.\n");

			System.out.println("--- 2. ALTA FOODTRUCKS ---");
			FoodTruck ft1 = new FoodTruck("Burguer UNLA", resp1, 15.0, "FT-001", "AA111AA", true);
			FoodTruck ft2 = new FoodTruck("Pancheria UNLA", resp2, 12.0, "FT-002", "BB222BB", false);
			
			int idFt1 = uvAbm.agregarFoodTruck(ft1);
			int idFt2 = uvAbm.agregarFoodTruck(ft2);
			System.out.println("Food Trucks agregados ok con ID: " + idFt1 + " y " + idFt2 + "\n");

			System.out.println("--- 3. ALTA DE PUESTOS DESARMABLES ---");
			PuestoDesarmable pd1 = new PuestoDesarmable("Pizzeria UNLA", resp1, 20.0, "PD-001", 2, 45);
			PuestoDesarmable pd2 = new PuestoDesarmable("Cerveceria Artesanal UNLA", resp2, 25.0, "PD-002", 3, 60);
			
			int idPd1 = uvAbm.agregarPuestoDesarmable(pd1);
			int idPd2 = uvAbm.agregarPuestoDesarmable(pd2);
			System.out.println("Puestos Desarmables agregados ok con ID: " + idPd1 + " y " + idPd2 + "\n");

			System.out.println("--- 4. TRAER FOODTRUCK POR PATENTE ---");
			String patenteBuscada = "AA111AA";
			FoodTruck ftBuscado = uvAbm.traerFoodTruckPorPatente(patenteBuscada);
			System.out.println("FOODTRUCK encontrado: " + ftBuscado.getNombreComercial() + " - " + ftBuscado.getPatente() + "\n");

			System.out.println("--- 5. TRAER LISTA DE PUESTOS DESARMABLES ---");
			List<PuestoDesarmable> listaPD = uvAbm.traerPuestosDesarmables();
			for (PuestoDesarmable pd : listaPD) {
				System.out.println("ID: " + pd.getIdUnidadDeVenta() + " | Nombre: " + pd.getNombreComercial() + " | Carpas: " + pd.getCantidadCarpas());
			}
			System.out.println();

			System.out.println("--- 6. INTENTO DE CARGAR DUPLICADO PARA PROBAR EXCEPCION ---");
			try {
				System.out.println("Intentando cargar un Food Truck con una patente ya registrada (" + patenteBuscada + ")...");
				FoodTruck ftDuplicado = new FoodTruck("Otro Nombre", resp1, 15.0, "FT-003", patenteBuscada, true);
				uvAbm.agregarFoodTruck(ftDuplicado);
			} catch (Exception e) {
				System.out.println("Excepcion capturada ok, " + e.getMessage());
			}

		} catch (Exception e) {
			System.out.println("\nERROR GENERAL INESPERADO: " + e.getMessage());
			e.printStackTrace();
		}
	}
}