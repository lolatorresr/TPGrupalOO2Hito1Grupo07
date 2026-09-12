package test;

import java.time.LocalDate;

import negocio.FestivalABM;
import negocio.ItemPlatoABM;
import negocio.PedidoABM;
import negocio.PersonaABM;
import negocio.PlatoABM;
import negocio.UnidadDeVentaABM;

public class TestCargaDatos {

	public static void main(String[] args) {
		PersonaABM personaABM = new PersonaABM();
		PlatoABM platoABM = new PlatoABM();
		UnidadDeVentaABM unidadABM = new UnidadDeVentaABM();
		PedidoABM pedidoABM = new PedidoABM();
		ItemPlatoABM itemPlatoABM = new ItemPlatoABM();
		FestivalABM festivalABM = new FestivalABM();
		
		try {
			System.out.println("\n--------------------------------------\n");
			System.out.println("------- CARGA DE DATOS -------");
			System.out.println("\n--------------------------------------\n");
			
			System.out.println("\n---- Alta de Festival----\n");
			
			int idFestival = festivalABM.agregarFestival("Festival Gastronomico Primavera",
							"Primavera",
							LocalDate.of(2026, 9, 1),
							LocalDate.of(2026, 9, 30));
			System.out.println("\nFestival creado con ID: " + idFestival);
			
			System.out.println("\n---- Alta de Cocineros----\n");
			
			int idCocinero1 = personaABM.agregarCocinero("Roberto" , "Sanchez", 
					34111222L, LocalDate.of(1945, 8, 19), LocalDate.of(2023, 3, 1),
					600000.0, "Pastas y masas" , "Jefe de cocina");
			System.out.println("\nCocinero 1 agregado con ID: " + idCocinero1);
			
			int idCocinero2 = personaABM.agregarCocinero("Ruben", "Rada", 
					32445998L, LocalDate.of(1943, 7, 16), LocalDate.of(2024, 3, 1),
					580000.0, "Asados y Frituras" , "Cocinero principal");
			System.out.println("\nCocinero 2 agregado con ID: " + idCocinero2);
			
			System.out.println("\n---- Alta de Cajeros----\n");
			
			int idCajero1 = personaABM.agregarCajero("Willy", "Quiroga", 
					30897654L, LocalDate.of(1940, 5, 17), LocalDate.of(2022, 3, 4), 
					420000.0, "Noche", 340000.0);
			System.out.println("\nCajero 1 agregado con ID: " + idCajero1);
			
			int idCajero2 = personaABM.agregarCajero("Oscar", "Moro", 
					35087543L, LocalDate.of(1948, 1, 24), LocalDate.of(2024, 11, 17), 
					420000.0, "Tarde", 280000.0);
			System.out.println("\nCajero 2 agregado con ID: " + idCajero2);
			
			int idCajero3 = personaABM.agregarCajero("Edelmiro", "Molinari", 
					33466766L, LocalDate.of(1947, 7, 8), LocalDate.of(2024, 3, 17), 
					420000.0, "Tarde", 270000.0);
			System.out.println("\nCajero 3 agregado con ID: " + idCajero3);
			
			int idCajero4 = personaABM.agregarCajero("Ricardo", "Soule", 
					34588788L, LocalDate.of(1950, 3, 15), LocalDate.of(2022, 7, 21), 
					420000.0, "Noche", 400000.0);
			System.out.println("\nCajero 4 agregado con ID: " + idCajero4);
			
			System.out.println("\n---- Alta de Unidades de Venta----\n");
			
			//foodtruck
			int idUv1 = unidadABM.agregarFoodTruck("BurgerMovil", "FT-001", 22.5, idCocinero1,
					"AF123JK", true);
			System.out.println("\nFoodTruck 1 agregado con ID: " + idUv1);
			
			int idUv2 = unidadABM.agregarFoodTruck("Panchos pepe", "FT-002", 23.9, idCocinero2,
					"PE333PE", true);
			System.out.println("\nFoodTruck 2 agregado con ID: " + idUv2);
			
			
			//puesto desarmable
			//@lu agregar cocineros (1 pastelero)
			//@juampi asignar cocineros a c/puestodesarm
			int idUv3 = unidadABM.agregarPuestoDesarmable("Pasta Rossa" , 18.0, idCocinero2, 
					"PD-001", 3, 60);
			System.out.println("\nPuesto Desarmable 1 agregado con ID: " + idUv3);

			int idUv4 = unidadABM.agregarPuestoDesarmable("Pasteleria 212" , 13.0, idCocinero2, 
					"PD-002", 2, 40);
			System.out.println("\nPuesto Desarmable 2 agregado con ID: " + idUv4);
			
			unidadABM.agregarPersonal(idUv1, unidadABM.traerPersona(idCajero1));
			unidadABM.agregarPersonal(idUv2, unidadABM.traerPersona(idCajero2));
			unidadABM.agregarPersonal(idUv3, unidadABM.traerPersona(idCajero3));
			unidadABM.agregarPersonal(idUv4, unidadABM.traerPersona(idCajero4));
			
			System.out.println("\n---- Alta de Platos----\n");
			
			int idPlato1 = platoABM.agregarPlato("Hamburguesa completa", 11000.0, idCocinero2);
			int idPlato2 = platoABM.agregarPlato("Lomito Clásico", 12000.0, idCocinero2);
			System.out.println("\nPlatos Cocinero 1 creados con IDs: "+ idPlato1 + " y " + idPlato2);
			
			/*
			 * falta agregar 3 cocineros y ponerlos aca
			int idPlato2 = platoABM.agregarPlato("Ñoquis con salsa blanca", 13000.0, idCocinero);
			int idPlato3 = platoABM.agregarPlato("Super pancho", 4000.0, idCocinero);
			int idPlato4 = platoABM.agregarPlato("Red Velvet", 9000.0, idCocinero);
			*/
			
			unidadABM.agregarPlato(idUv1, unidadABM.traerPlato(idPlato1));
			unidadABM.agregarPlato(idUv1, unidadABM.traerPlato(idPlato2));
			
			System.out.println("\n---- Alta de Pedidos----\n");
			
			LocalDate fecha1 = LocalDate.of(2026, 9, 10);
			LocalDate fecha2 = LocalDate.of(2026, 9, 13);
			int idPedido1 = pedidoABM.agregarPedido(fecha1, festivalABM.traerFestival(idFestival), unidadABM.traerUnidadVenta(idUv1));
			System.out.println("\nPedido 1 agregado con ID: " + idPedido1);
			
			int idPedido2 = pedidoABM.agregarPedido(fecha2, festivalABM.traerFestival(idFestival), unidadABM.traerUnidadVenta(idUv2));
			System.out.println("\nPedido 2 agregado con ID: " + idPedido2);
			
			
			itemPlatoABM.agregarItemPlatoAPedido(idPedido1, idPlato1, 2);
			itemPlatoABM.agregarItemPlatoAPedido(idPedido1, idPlato2, 1);
			
			itemPlatoABM.agregarItemPlatoAPedido(idPedido2, idPlato1, 3);
			
			System.out.println("\n--------------------------------------\n");
			System.out.println("------- FIN CARGA DE DATOS -------");
			System.out.println("\n--------------------------------------\n");
			
		} catch(Exception e) {
			System.out.println("\nERROR DURANTE LA CARGA: ");
			e.printStackTrace();
			
		}
		

	}

}
