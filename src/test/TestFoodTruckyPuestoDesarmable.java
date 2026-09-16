package test;

import java.time.LocalDate;
import java.util.List;

import datos.Cajero;
import datos.Cocinero;
import datos.Festival;
import datos.FoodTruck;
import datos.PuestoDesarmable;
import negocio.PersonaABM;
import negocio.UnidadDeVentaABM;
import negocio.FestivalABM;

public class TestFoodTruckyPuestoDesarmable {

	public static void main(String[] args) {
		UnidadDeVentaABM uvAbm = UnidadDeVentaABM.getInstancia();
		PersonaABM personaAbm = new PersonaABM();
		FestivalABM festivalAbm = new FestivalABM(); 

		try {
			System.out.println("--- 1. PREPARACIÓN: ALTA DE FESTIVAL ---");
			
			LocalDate fechaInicio = LocalDate.of(2026, 10, 10);
			LocalDate fechaFin = LocalDate.of(2026, 10, 12);
			
			festivalAbm.agregarFestival("Festival Unla", "Primavera", fechaInicio, fechaFin);
			
			// Usando el método explícito que definiste
			Festival festUnla = festivalAbm.traerFestivalPorNombre("Festival Unla");
			System.out.println("Festival creado exitosamente: " + festUnla.getNombre() + "\n");

			System.out.println("--- 2. PREPARACIÓN: ALTA DE EMPLEADOS ---");
			LocalDate fechaNac = LocalDate.of(1995, 5, 20);
			LocalDate fechaIng = LocalDate.now();

			// Alta de 4 Cajeros
			int idCaj1 = personaAbm.agregarCajero("Ana", "López", 99999999, fechaNac, fechaIng, 500000.0, "Mañana", 0.0);
			int idCaj2 = personaAbm.agregarCajero("Beto", "García", 12345234, fechaNac, fechaIng, 500000.0, "Tarde", 0.0);
			int idCaj3 = personaAbm.agregarCajero("Carlos", "Ruiz", 33333333, fechaNac, fechaIng, 500000.0, "Noche", 0.0);
			int idCaj4 = personaAbm.agregarCajero("Diana", "Paz", 44444444, fechaNac, fechaIng, 500000.0, "Mañana", 0.0);

			Cajero caj1 = (Cajero) personaAbm.traerPersona(idCaj1);
			Cajero caj2 = (Cajero) personaAbm.traerPersona(idCaj2);
			Cajero caj3 = (Cajero) personaAbm.traerPersona(idCaj3);
			Cajero caj4 = (Cajero) personaAbm.traerPersona(idCaj4);

			// Alta de 4 Cocineros
			int idCoc1 = personaAbm.agregarCocinero("Elena", "Ríos", 55555555, fechaNac, fechaIng, 600000.0, "Parrilla", "Chef");
			int idCoc2 = personaAbm.agregarCocinero("Fabio", "Mena", 66666666, fechaNac, fechaIng, 600000.0, "Pizzero", "Ayudante");
			int idCoc3 = personaAbm.agregarCocinero("Gaby", "Soto", 77777777, fechaNac, fechaIng, 600000.0, "Comida Rápida", "Chef");
			int idCoc4 = personaAbm.agregarCocinero("Hugo", "Vera", 88888888, fechaNac, fechaIng, 600000.0, "Panadero", "Chef");

			Cocinero coc1 = (Cocinero) personaAbm.traerPersona(idCoc1);
			Cocinero coc2 = (Cocinero) personaAbm.traerPersona(idCoc2);
			Cocinero coc3 = (Cocinero) personaAbm.traerPersona(idCoc3);
			Cocinero coc4 = (Cocinero) personaAbm.traerPersona(idCoc4);
			System.out.println("Los 8 empleados (4 cajeros y 4 cocineros) fueron cargados exitosamente.\n");

			System.out.println("--- 3. ALTA DE UNIDADES DE VENTA ---");
			// Food Trucks (El primero CON luz, el segundo SIN luz)
			int idFt1 = uvAbm.agregarFoodTruck("Patys Unla", "BU-01", 15.0, "PAT-001", true);
			int idFt2 = uvAbm.agregarFoodTruck("Pizza Unla", "PU-01", 12.0, "PAT-002", false);

			// Puestos Desarmables (5 carpas y 2 carpas)
			int idPd1 = uvAbm.agregarPuestoDesarmable("Cerveza Unla", 25.0, "CU-01", 5, 120);
			int idPd2 = uvAbm.agregarPuestoDesarmable("Pancheria Unla", 10.0, "PA-01", 2, 45);

			System.out.println("Las 4 unidades de venta fueron cargadas exitosamente.\n");

			System.out.println("--- 4. ASIGNACIONES (FESTIVAL, RESPONSABLES Y PERSONAL) ---");
			
			// FT1: Burguer Unla
			uvAbm.asignarFestival(idFt1, festUnla);
			uvAbm.asignarResponsable(idFt1, caj1); // Cajero es el responsable
			uvAbm.agregarPersonal(idFt1, caj1);
			uvAbm.agregarPersonal(idFt1, coc1);

			// FT2: Pizza Unla
			uvAbm.asignarFestival(idFt2, festUnla);
			uvAbm.asignarResponsable(idFt2, caj2);
			uvAbm.agregarPersonal(idFt2, caj2);
			uvAbm.agregarPersonal(idFt2, coc2);

			// PD1: Cerveza Unla
			uvAbm.asignarFestival(idPd1, festUnla);
			uvAbm.asignarResponsable(idPd1, caj3);
			uvAbm.agregarPersonal(idPd1, caj3);
			uvAbm.agregarPersonal(idPd1, coc3);

			// PD2: Pancheria Unla
			uvAbm.asignarFestival(idPd2, festUnla);
			uvAbm.asignarResponsable(idPd2, caj4);
			uvAbm.agregarPersonal(idPd2, caj4);
			uvAbm.agregarPersonal(idPd2, coc4);

			System.out.println("Se asignaron los responsables, el personal y el festival a cada unidad.\n");

			System.out.println("--- 5. PRUEBA DE CONSULTAS COMPLEJAS ---");
			
			System.out.println("A) Buscando Food Trucks del 'Festival Unla' CON electricidad:");
			List<FoodTruck> fts = uvAbm.traerFoodTruckPorFestivalYElectricidad("Festival Unla", true);
			for (FoodTruck ft : fts) {
				System.out.println(" -> ÉXITO: Encontrado " + ft.getNombreComercial() + " (Patente: " + ft.getPatente() + ")");
			}
			
			System.out.println("\nB) Buscando Puestos Desarmables del 'Festival Unla' con MÁS de 4 carpas:");
			List<PuestoDesarmable> pds = uvAbm.traerPuestoPorFestivalYCarpas("Festival Unla", 4);
			for (PuestoDesarmable pd : pds) {
				System.out.println(" -> ÉXITO: Encontrado " + pd.getNombreComercial() + " (Carpas: " + pd.getCantidadCarpas() + ")");
			}

		} catch (Exception e) {
			System.out.println("\nERROR EN LA EJECUCIÓN: " + e.getMessage());
			e.printStackTrace();
		}
	}
}