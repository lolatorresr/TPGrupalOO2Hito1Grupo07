package test;

import java.util.List;
import dao.PersonaDao;
import datos.Pedido;
import datos.Persona;
import datos.Plato;
import datos.UnidadDeVenta;
import negocio.PedidoABM;
import negocio.PlatoABM;
import negocio.UnidadDeVentaABM;

public class TestUnidadDeVenta {

	public static void main(String[] args) {
		UnidadDeVentaABM uvAbm = UnidadDeVentaABM.getInstancia();
		PersonaDao personaDao = new PersonaDao();
		PlatoABM platoAbm = new PlatoABM();
		PedidoABM pedidoAbm = new PedidoABM();

		int idUv = 0;

		try {
			Persona responsable = personaDao.traerPersona(1);
			Persona empleado = personaDao.traerPersona(2);
			Plato plato1 = platoAbm.traerPlato(1);
			Pedido pedido1 = pedidoAbm.traerPedido(1);

			System.out.println("--- 1. ALTA DE UNIDAD DE VENTA ---\n");
			UnidadDeVenta nuevaUv = new UnidadDeVenta("Tio pancho", responsable, 25.0, "PC-001");
			idUv = uvAbm.agregarUnidadVenta(nuevaUv);
			System.out.println("Unidad agregada con ID: " + idUv);

			System.out.println("\n--- 2. TRAER POR ID Y POR NOMBRE COMERCIAL ---\n");
			System.out.println(uvAbm.traerUnidadVenta(idUv));
			System.out.println(uvAbm.traerUnidadVenta("Tio pancho"));

			System.out.println("\n--- 3. ASIGNAR PERSONAL, PLATO Y PEDIDO ---\n");
			uvAbm.agregarPersonal(idUv, empleado);
			uvAbm.agregarPlato(idUv, plato1);
			uvAbm.agregarPedido(idUv, pedido1);

			System.out.println("\n--- 4. TRAER CON ASOCIACIONES ---\n");
			System.out.println("Personal asociado: " + uvAbm.traerUnidadDeVentaYPersonal(idUv));
			System.out.println("Platos asociados: " + uvAbm.traerUnidadDeVentaYPlatos(idUv));
			System.out.println("Pedidos asociados: " + uvAbm.traerUnidadDeVentaYPedido(idUv));

			System.out.println("Recaudacion total: $" + uvAbm.traerRecaudacionTotal(idUv));
			System.out.println("Plato mas pedido: " + uvAbm.traerPlatoMasPedido(idUv));
			System.out.println("Unidad con mas pedidos: " + uvAbm.traerUnidadVentaMayorPedidos());

			System.out.println("\n--- 5. REPORTES ---\n");
			System.out.println("Unidades con superficie > 20:");
			List<UnidadDeVenta> unidadesGrandes = uvAbm.traerUnidadVentaMayorSuperficie(20.0);
			unidadesGrandes.forEach(System.out::println);

			System.out.println("\nPersonal total:");
			List<Persona> personal = uvAbm.traerPersonal();
			personal.forEach(System.out::println);

			System.out.println("\n--- 6. ELIMINAR UNIDAD DE VENTA ---\n");
			uvAbm.eliminarUnidadVenta(idUv);
			System.out.println("Unidad con ID " + idUv + " eliminada.");

			System.out.println("\n--- 7. PRUEBA DE EXCEPCION (DEBE LANZAR ERROR) ---\n");
			uvAbm.traerUnidadVenta(idUv);

		} catch (Exception e) {
			System.out.println("\nExcepcion capturada correctamente: " + e.getMessage());
		}
	}

}