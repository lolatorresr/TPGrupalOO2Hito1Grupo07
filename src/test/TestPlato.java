package test;

import java.time.LocalDate;
import java.util.List;

import datos.Plato;
import negocio.PlatoABM;

public class TestPlato {

	public static void main(String[] args) {
		PlatoABM abm = new PlatoABM();
		
		try {
			System.out.println("---- CONSULTAS DE PLATOS ---\n");
			
			System.out.println("\n---- Traer plato por ID (1) ----\n");
			System.out.println(abm.traerPlato(1));
			
			System.out.println("\n---- Lista de platos ----\n");
			abm.traerPlatos().forEach(System.out::println);
			
			System.out.println("\n---- Platos hasta $12000 ----\n");
			List<Plato> economicos = abm.traerPlatosPrecioMenorA(12000.0);
			economicos.forEach(p-> 
					System.out.println("-> " + p.getNombre() + " | Precio: $"+ p.getPrecio() +
							" | Costo: $" + p.getCosto())
							);
			
			System.out.println("\n---- Prueba Excepcion nombre duplicado ----");
			try {
				abm.agregarPlato("Hamburguesa completa", 14000.0, 6000.0);
			}catch(Exception e) {
				e.printStackTrace();
			}
				
			System.out.println("\n---- Prueba Excepcion precio invalido ----");
			try {
				abm.traerPlatosPrecioMenorA(-500.0);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
