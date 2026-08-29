package test;

import java.time.LocalDate;
import negocio.PersonaABM;

public class TestCajero {

	public static void main(String[] args) {
		PersonaABM abm = new PersonaABM();
		
		try {
			System.out.println("\n--- ALTA DE CAJEROS ---\n");
			int id1 = abm.agregarCajero("Lola", "Torres", 44507859L, LocalDate.of(2002, 12,5), 
					LocalDate.of(2022, 3,1), 900000.0, "Tarde", 480000.0);
			
			int id2 = abm.agregarCajero("Marco", "Pappalardo", 45876654L, LocalDate.of(2003, 7 ,20), 
					LocalDate.of(2024, 6,10), 827000.0, "Noche", 520000.0);
			
			System.out.println("Cajeros agregados con ID: " + id1 + " y " + id2);
			
			System.out.println("--- TRAER CAJERO POR ID ---\n");
			System.out.println(abm.traerCajero(id1));
			
			System.out.println("\n--- LISTA DE CAJEROS ---\n");
			abm.traerCajeros().forEach(System.out::println);
			
			System.out.println("\n--- CAJERO CON MAYOR RECAUDACION ---\n");
			System.out.println(abm.traerCajeroMayorRecaudacion());
			
			System.out.println("\n--- PRUEBA EXCEPCION ---");
			abm.agregarCajero("Lopez", "Lucas", 44507859L , LocalDate.of(2000, 12, 12),
					LocalDate.of(2024, 11, 23), 600000.0, "Noche", 420000.0);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
