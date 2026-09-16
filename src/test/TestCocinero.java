package test;

import java.util.List;

import datos.Cocinero;
import negocio.PersonaABM;

public class TestCocinero {

	public static void main(String[] args) {
		PersonaABM abm = new PersonaABM();
		
		//IDs segun el orden de alta de TestCargaDatos
		int idBurgerMovil = 1;
		int idPastaRossa = 3;
		int idPasteleria = 4;

		try {
			System.out.println("---- CONSULTAS DE COCINEROS ---\n");
			
			System.out.println("\n---- Traer cocinero por ID (1) ----\n");
			System.out.println(abm.traerCocinero(1));
			
			System.out.println("\n---- Lista completa de cocineros ----\n");
			abm.traerCocineros().forEach(System.out::println);
			
			System.out.println("\n---- CU: Cocineros de la unidad BurgerMovil ----\n");
			abm.traerCocinerosDeUnidadDeVenta(idBurgerMovil).forEach(c ->
					System.out.println("-> " + c.getNombre() + " " + c.getApellido()
							+ " | Especialidad: " + c.getEspecialidad()
							+ " | Ingreso: " + c.getFechaIngreso()));
			
			System.out.println("\n---- CU: Cocineros de la unidad Pasteleria 212 ----\n");
			abm.traerCocinerosDeUnidadDeVenta(idPasteleria).forEach(c ->
					System.out.println("-> " + c.getNombre() + " " + c.getApellido()
							+ " | Especialidad: " + c.getEspecialidad()
							+ " | Ingreso: " + c.getFechaIngreso()));
			
			System.out.println("\n---- CU: Cocinero de mayor antiguedad en BurgerMovil ----\n");
			System.out.println(abm.traerCocineroMasAntiguo(idBurgerMovil));
			
			System.out.println("\n---- CU: Cocinero de mayor antiguedad en Pasteleria 212 ----\n");
			System.out.println(abm.traerCocineroMasAntiguo(idPasteleria));
			
			System.out.println("\n---- CU: Cocineros por especialidad (Pasteleria) en Pasteleria 212 ----\n");
			List<Cocinero> pasteleros = abm.traerCocinerosPorEspecialidad(idPasteleria, "Pasteleria");
			pasteleros.forEach(c -> System.out.println("-> " + c.getNombre() + " " + c.getApellido()));
			
			System.out.println("\n---- CU: Cocineros por especialidad (Asados y Frituras) en BurgerMovil ----\n");
			abm.traerCocinerosPorEspecialidad(idBurgerMovil, "Asados y Frituras")
					.forEach(c -> System.out.println("-> " + c.getNombre() + " " + c.getApellido()));
			
			System.out.println("\n---- Prueba Excepcion: especialidad inexistente en la unidad ----");
			try {
				abm.traerCocinerosPorEspecialidad(idPastaRossa, "Sushi");
			} catch (Exception e) {
				System.out.println("Excepcion capturada correctamente: " + e.getMessage());
			}
			
			System.out.println("\n---- Prueba Excepcion: unidad sin cocineros asignados ----");
			try {
				abm.traerCocinerosDeUnidadDeVenta(2);
			} catch (Exception e) {
				System.out.println("Excepcion capturada correctamente: " + e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
