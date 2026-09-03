package test;

import java.time.LocalDate;

import datos.Cocinero;
import datos.UnidadDeVenta;
import negocio.PersonaABM;
import negocio.UnidadDeVentaABM;


public class TestCocinero {

	public static void main(String[] args) {
		PersonaABM abm = new PersonaABM();
		UnidadDeVentaABM uvAbm = UnidadDeVentaABM.getInstancia();

		try {
			System.out.println("\n--- 1. ALTA DE COCINEROS ---\n");
			int id1 = abm.agregarCocinero("Martina", "Gomez", 30111222L, LocalDate.of(1988, 4, 18),
					LocalDate.of(2021, 2, 15), 950000.0, "Pasteleria", "A");

			int id2 = abm.agregarCocinero("Rodrigo", "Fernandez", 31222333L, LocalDate.of(1990, 9, 3),
					LocalDate.of(2023, 8, 1), 870000.0, "Parrilla", "B");

			int id3 = abm.agregarCocinero("Valentina", "Ibarra", 32333444L, LocalDate.of(1992, 6, 27),
					LocalDate.of(2024, 5, 20), 820000.0, "Pasteleria", "C");

			System.out.println("Cocineros agregados con ID: " + id1 + ", " + id2 + " y " + id3);

			System.out.println("\n--- 2. TRAER COCINERO POR ID ---\n");
			System.out.println(abm.traerCocinero(id1));

			System.out.println("\n--- 3. LISTA COMPLETA DE COCINEROS ---\n");
			abm.traerCocineros().forEach(System.out::println);

			System.out.println("\n--- 4. ALTA DE UNIDAD DE VENTA Y ASIGNACION DE PERSONAL ---\n");
			Cocinero responsable = abm.traerCocinero(id1);
			UnidadDeVenta uv = new UnidadDeVenta("El Buen Sabor", responsable, 30.0, "UV-COC001");
			int idUv = uvAbm.agregarUnidadVenta(uv);
			System.out.println("Unidad de venta agregada con ID: " + idUv);

			uvAbm.agregarPersonal(idUv, abm.traerCocinero(id1));
			uvAbm.agregarPersonal(idUv, abm.traerCocinero(id2));
			uvAbm.agregarPersonal(idUv, abm.traerCocinero(id3));
			System.out.println("3 cocineros asignados al personal de la unidad.");

			System.out.println("\n--- 5. CU: COCINEROS DE LA UNIDAD DE VENTA ---\n");
			abm.traerCocinerosDeUnidadDeVenta(idUv).forEach(System.out::println);

			System.out.println("\n--- 6. CU: COCINERO DE MAYOR ANTIGUEDAD ---\n");
			System.out.println(abm.traerCocineroMasAntiguo(idUv));

			System.out.println("\n--- 7. CU: COCINEROS POR ESPECIALIDAD (Pasteleria) ---\n");
			abm.traerCocinerosPorEspecialidad(idUv, "Pasteleria").forEach(System.out::println);

			System.out.println("\n--- 8. PRUEBA DE EXCEPCION: especialidad inexistente ---\n");
			abm.traerCocinerosPorEspecialidad(idUv, "Sushi");

		} catch (Exception e) {
			System.out.println("Excepcion capturada correctamente: " + e.getMessage());
		}
	}

}
