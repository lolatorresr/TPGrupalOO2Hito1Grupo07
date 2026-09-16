package test;

import datos.ItemPlato;
import datos.Pedido;
import negocio.ItemPlatoABM;
import negocio.PedidoABM;
import negocio.UnidadDeVentaABM;

public class TestItemPlato {

	public static void main(String[] args) {
		ItemPlatoABM abm = new ItemPlatoABM();
		PedidoABM pedidoAbm = new PedidoABM();
		UnidadDeVentaABM uvAbm = UnidadDeVentaABM.getInstancia();
		
		//IDs segun el orden de alta de TestCargaDatos
		int idPedido1 = 1;
		int idPedido2 = 2;
		int idHamburguesa = 1;
		int idLomito = 2;
		int idSuperPancho = 4;
		int idRedVelvet = 5;
		int idBurgerMovil = 1;

		try {
			System.out.println("---- CONSULTAS DE ITEMPLATO ---\n");
			
			System.out.println("\n---- Traer item por ID (1) ----\n");
			System.out.println(abm.traerItemPlato(1));
			
			System.out.println("\n---- Lista completa de items ----\n");
			abm.traerItemPlatos().forEach(System.out::println);
			
			System.out.println("\n---- Items del pedido 1 ----\n");
			abm.traerItemPlatosPorPedido(idPedido1).forEach(i ->
					System.out.println("-> " + i.getCantidad() + " x " + i.getPlato().getNombre()
							+ " | SubTotal: $" + i.getSubTotal()));
			
			System.out.println("\n---- Items del pedido 2 ----\n");
			abm.traerItemPlatosPorPedido(idPedido2).forEach(i ->
					System.out.println("-> " + i.getCantidad() + " x " + i.getPlato().getNombre()
							+ " | SubTotal: $" + i.getSubTotal()));
			
			System.out.println("\n---- Items que incluyen la Hamburguesa completa ----\n");
			abm.traerItemPlatosPorPlato(idHamburguesa).forEach(System.out::println);
			
			System.out.println("\n---- Cantidad total vendida por plato ----\n");
			System.out.println("Hamburguesa completa: " + abm.traerCantidadVendidaPorPlato(idHamburguesa) + " unidades");
			System.out.println("Lomito Clasico:       " + abm.traerCantidadVendidaPorPlato(idLomito) + " unidades");
			System.out.println("Super pancho:         " + abm.traerCantidadVendidaPorPlato(idSuperPancho) + " unidades");
			System.out.println("Red Velvet:           " + abm.traerCantidadVendidaPorPlato(idRedVelvet) + " unidades");
			
			System.out.println("\n---- Pedido con detalle (items + platos) ----\n");
			Pedido conDetalle = pedidoAbm.traerPedidoConDetalle(idPedido1);
			System.out.println("Pedido ID " + conDetalle.getIdPedido() + " del " + conDetalle.getFechaTransaccion());
			double total = 0.0;
			for (ItemPlato i : conDetalle.getItemPlatos()) {
				System.out.println("   " + i.getCantidad() + " x " + i.getPlato().getNombre()
						+ " = $" + i.getSubTotal());
				total += i.getSubTotal();
			}
			System.out.println("Total del pedido: $" + total);
			
			System.out.println("\n---- Recaudacion de la unidad BurgerMovil ----\n");
			System.out.println("Recaudacion total: $" + uvAbm.traerRecaudacionTotal(idBurgerMovil));
			
			System.out.println("\n---- Prueba Excepcion: pedido sin items ----");
			try {
				abm.traerItemPlatosPorPedido(9999);
			} catch (Exception e) {
				System.out.println("Excepcion capturada correctamente: " + e.getMessage());
			}
			
			System.out.println("\n---- Prueba Excepcion: plato inexistente ----");
			try {
				abm.traerCantidadVendidaPorPlato(9999);
			} catch (Exception e) {
				System.out.println("Excepcion capturada correctamente: " + e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
