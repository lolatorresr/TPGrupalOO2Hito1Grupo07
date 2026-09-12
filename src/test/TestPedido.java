package test;

import java.time.LocalDate;
import java.util.List;

import datos.ItemPlato;
import datos.Pedido;
import negocio.PedidoABM;

public class TestPedido {

	public static void main(String[] args) {
		PedidoABM pedidoABM = new PedidoABM();
		
		try {
			
			System.out.println("---- CONSULTAS DE PEDIDOS ----\n");
			
			System.out.println("\n---- Traer Pedido por ID (1) ----\n");
			System.out.println(pedidoABM.traerPedido(1));
			
			System.out.println("\n---- Traer Pedido con Items ----\n");
			Pedido pConItems = pedidoABM.traerPedidoConDetalle(1);
			double total = 0;
			System.out.println("Detalle de platos pedidos:");
			for (ItemPlato item : pConItems.getItemPlatos()) {
				System.out.println("-> Plato: " + item.getPlato().getNombre() + 
				                   " | Cantidad: " + item.getCantidad() + 
				                   " | Subtotal: $" + item.getSubTotal());
				total += item.getSubTotal();
			}
			System.out.println("TOTAL DEL PEDIDO: $" + total);
			
			System.out.println("\n---- Traer Pedidos entre fechas ----\n");
			List<Pedido> pedidosFechas = pedidoABM.traerPedidosEntreFechas(
					LocalDate.of(2026, 9, 9), 
					LocalDate.of(2026, 9, 11)
			);
			pedidosFechas.forEach(System.out::println);
			
			System.out.println("\n---- Traer Pedidos por Unidad de Venta (ID = 2) ----\n");
			List<Pedido> pedidosUV = pedidoABM.traerPedidosPorUnidadDeVenta(2);

			for (Pedido p : pedidosUV) {
			    Pedido pDetalle = pedidoABM.traerPedidoConDetalle(p.getIdPedido());
			    System.out.println(p);
			    pDetalle.getItemPlatos().forEach(System.out::println);
			}
			
			System.out.println("\n---- Traer pedido por Plato (lomito) ----\n");
			pedidoABM.traerPedidosPorPlato(2).forEach(System.out::println);
			
			System.out.println("\n---- Prueba excepcion (id no existente) ----\n");
			pedidoABM.traerPedido(999);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
