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

		try {
			System.out.println("---- CONSULTAS UNIDAD DE VENTA----\n");
			
			System.out.println("\n---- Traer Unidad de Venta por ID ----\n");
			System.out.println(uvAbm.traerUnidadVenta(1));
			
			System.out.println("\n---- Traer Unidad de Venta por nombre ----\n");
			System.out.println(uvAbm.traerUnidadVenta("Burger Movil"));
			
			System.out.println("\n---- Traer con Personal y Platos asociados ----\n");
			System.out.println(uvAbm.traerUnidadDeVentaYPlatos(2));
			
			
			
			
		} catch (Exception e) {
			System.out.println("\nExcepcion capturada correctamente: " + e.getMessage());
		}
	}

}