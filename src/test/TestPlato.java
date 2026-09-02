package test;

import java.time.LocalDate;

import negocio.PlatoABM;

public class TestPlato {

	public static void main(String[] args) {
		PlatoABM abm = new PlatoABM();
		
		try {
			System.out.println("---ALTA DE PLATOS---\n");
			int id1 = abm.agregarPlato("Pizza individual", 9000.0, 4000.0);
			
			int id2 = abm.agregarPlato("Hamburguesa completa", 12000.0, 5000.0);
			
			System.out.println("Platos agregados con ID: " + id1 + " y " + id2);
			
			System.out.println("--- TRAER PLATO POR ID ---\n");
			System.out.println(abm.traerPlato(id1));
			
			System.out.println("\n--- LISTA DE PLATOS ---\n");
			abm.traerPlatos().forEach(System.out::println);
			
			System.out.println("\n--- PRUEBA EXCEPCION ---");
			abm.agregarPlato("Hamburguesa completa", 14000.0, 6000.0);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
