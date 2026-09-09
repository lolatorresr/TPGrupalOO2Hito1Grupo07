package test;

import java.time.LocalDate;

import datos.Cocinero;
import datos.ItemPlato;
import datos.Pedido;
import datos.UnidadDeVenta;
import negocio.ItemPlatoABM;
import negocio.PedidoABM;
import negocio.PersonaABM;
import negocio.PlatoABM;
import negocio.UnidadDeVentaABM;

public class TestItemPlato {

	public static void main(String[] args) {
		ItemPlatoABM abm = new ItemPlatoABM();
		PlatoABM platoAbm = new PlatoABM();
		PedidoABM pedidoAbm = new PedidoABM();
		PersonaABM personaAbm = new PersonaABM();
		UnidadDeVentaABM uvAbm = UnidadDeVentaABM.getInstancia();

		try {
			System.out.println("\n--- 1. DATOS DE PRUEBA: PLATOS ---\n");
			int idMilanesa = platoAbm.agregarPlato("Milanesa napolitana", 15000.0, 6000.0);
			int idEmpanada = platoAbm.agregarPlato("Empanada de humita", 3000.0, 1000.0);
			int idFlan = platoAbm.agregarPlato("Flan casero", 5000.0, 1800.0);
			System.out.println("Platos agregados con ID: " + idMilanesa + ", " + idEmpanada + " y " + idFlan);

			System.out.println("\n--- 2. DATOS DE PRUEBA: UNIDAD DE VENTA Y PEDIDOS ---\n");
			Cocinero responsable = personaAbm.traerCocinero(personaAbm.agregarCocinero("Nicolas", "Aguirre",
					33444555L, LocalDate.of(1991, 3, 12), LocalDate.of(2022, 7, 1), 900000.0, "Cocina criolla", "A"));
			UnidadDeVenta uv = new UnidadDeVenta("La Esquina Gourmet", responsable, 25.0, "UV-ITE001");
			int idUv = uvAbm.agregarUnidadVenta(uv);

			int idPedido1 = pedidoAbm.agregarPedido(LocalDate.now(), null, uvAbm.traerUnidadVenta(idUv));
			int idPedido2 = pedidoAbm.agregarPedido(LocalDate.now(), null, uvAbm.traerUnidadVenta(idUv));
			System.out.println("Unidad de venta ID: " + idUv + " | Pedidos ID: " + idPedido1 + " y " + idPedido2);

			System.out.println("\n--- 3. ALTA DE ITEMS DENTRO DE CADA PEDIDO ---\n");
			int idItem1 = abm.agregarItemPlatoAPedido(idPedido1, idMilanesa, 2);
			int idItem2 = abm.agregarItemPlatoAPedido(idPedido1, idEmpanada, 6);
			int idItem3 = abm.agregarItemPlatoAPedido(idPedido2, idMilanesa, 1);
			int idItem4 = abm.agregarItemPlatoAPedido(idPedido2, idFlan, 3);
			System.out.println("Items agregados con ID: " + idItem1 + ", " + idItem2 + ", " + idItem3
					+ " y " + idItem4);

			System.out.println("\n--- 4. TRAER ITEM POR ID ---\n");
			System.out.println(abm.traerItemPlato(idItem1));

			System.out.println("\n--- 5. LISTA COMPLETA DE ITEMS ---\n");
			abm.traerItemPlatos().forEach(System.out::println);

			System.out.println("\n--- 6. ITEMS DEL PEDIDO " + idPedido1 + " ---\n");
			abm.traerItemPlatosPorPedido(idPedido1).forEach(i ->
					System.out.println("-> " + i.getPlato().getNombre() + " | Cantidad: " + i.getCantidad()
							+ " | SubTotal: $" + i.getSubTotal()));

			System.out.println("\n--- 7. MODIFICAR CANTIDAD DE UN ITEM ---\n");
			System.out.println("Antes:  " + abm.traerItemPlato(idItem2));
			abm.modificarCantidadItemPlato(idItem2, 10);
			System.out.println("Despues: " + abm.traerItemPlato(idItem2));

			System.out.println("\n--- 8. CONSULTA: ITEMS QUE INCLUYEN LA MILANESA ---\n");
			abm.traerItemPlatosPorPlato(idMilanesa).forEach(System.out::println);

			System.out.println("\n--- 9. CONSULTA: CANTIDAD TOTAL VENDIDA POR PLATO ---\n");
			System.out.println("Milanesa napolitana: " + abm.traerCantidadVendidaPorPlato(idMilanesa) + " unidades");
			System.out.println("Empanada de humita:  " + abm.traerCantidadVendidaPorPlato(idEmpanada) + " unidades");
			System.out.println("Flan casero:         " + abm.traerCantidadVendidaPorPlato(idFlan) + " unidades");

			System.out.println("\n--- 10. PEDIDO CON DETALLE (items + platos) ---\n");
			Pedido conDetalle = pedidoAbm.traerPedidoConDetalle(idPedido1);
			System.out.println("Pedido ID " + conDetalle.getIdPedido() + " del " + conDetalle.getFechaTransaccion());
			double total = 0.0;
			for (ItemPlato i : conDetalle.getItemPlatos()) {
				System.out.println("   " + i.getCantidad() + " x " + i.getPlato().getNombre()
						+ " = $" + i.getSubTotal());
				total += i.getSubTotal();
			}
			System.out.println("Total del pedido: $" + total);

			System.out.println("\n--- 11. RECAUDACION DE LA UNIDAD DE VENTA ---\n");
			System.out.println("Recaudacion total: $"
					+ uvAbm.traerUnidadVenta(idUv).calcularRecaudacionTotal());

			System.out.println("\n--- 12. BAJA DE UN ITEM DEL PEDIDO ---\n");
			abm.quitarItemPlatoDePedido(idPedido1, idItem2);
			System.out.println("Item ID " + idItem2 + " quitado. Items restantes en el pedido " + idPedido1 + ":");
			abm.traerItemPlatosPorPedido(idPedido1).forEach(System.out::println);

			System.out.println("\n--- 13. PRUEBA EXCEPCION: CANTIDAD INVALIDA ---");
			try {
				abm.agregarItemPlatoAPedido(idPedido1, idMilanesa, 0);
			} catch (Exception e) {
				System.out.println("Excepcion capturada correctamente: " + e.getMessage());
			}

			System.out.println("\n--- 14. PRUEBA EXCEPCION: PLATO INEXISTENTE ---");
			try {
				abm.agregarItemPlatoAPedido(idPedido1, 9999, 1);
			} catch (Exception e) {
				System.out.println("Excepcion capturada correctamente: " + e.getMessage());
			}

			System.out.println("\n--- 15. PRUEBA EXCEPCION: PEDIDO INEXISTENTE ---");
			abm.agregarItemPlatoAPedido(9999, idMilanesa, 1);

		} catch (Exception e) {
			System.out.println("Excepcion capturada correctamente: " + e.getMessage());
		}
	}

}
