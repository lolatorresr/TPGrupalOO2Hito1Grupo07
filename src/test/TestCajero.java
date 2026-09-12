package test;

import java.time.LocalDate;
import java.util.List;

import datos.Cajero;
import negocio.PersonaABM;

public class TestCajero {

	public static void main(String[] args) {
		PersonaABM abm = new PersonaABM();
		
		try {
			System.out.println("\n--- CONSULTAS DE CAJEROS ---\n");
			
			System.out.println("--- Traer cajero por ID (3) ---\n");
			System.out.println(abm.traerCajero(3));
			
			System.out.println("\n--- Lista de cajeros ---\n");
			abm.traerCajeros().forEach(System.out::println);
			
			System.out.println("\n--- Cajero con mayor recaudación ---\n");
			System.out.println(abm.traerCajeroMayorRecaudacion());
			
			System.out.println("\n--- Cajero turno noche en UV ID 1 ---\n");
			List<Cajero> cajeros = abm.traerCajerosPorTurnoYUnidadDeVenta("Noche", 1);
		    cajeros.forEach(System.out::println);
			
			try {
				System.out.println("\n--- Prueba excepción (DNI duplicado) ---");
				abm.agregarCajero("Lopez", "Lucas", 44507859L , LocalDate.of(2000, 12, 12),
					LocalDate.of(2024, 11, 23), 600000.0, "Noche", 420000.0);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
