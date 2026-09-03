package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.PedidoDao;
import datos.Festival;
import datos.ItemPlato;
import datos.Pedido;
import datos.UnidadDeVenta;

public class PedidoABM {
	PedidoDao dao = new PedidoDao();
	
	public int agregarPedido (LocalDate fechaTransaccion, Festival festival, UnidadDeVenta unidadVenta){
		Pedido p= new Pedido(fechaTransaccion, festival, unidadVenta);
		return dao.agregarPedido(p);
	}
	
	public Pedido traerPedido(int idPedido) {
		return dao.traerPedido(idPedido);
	}
	
	public List<Pedido> traerPedidos(){
		return dao.traerPedidos();
	}
	
	public void actualizarPedido(Pedido p) throws Exception{
		if(p == null) {
			throw new Exception("ERROR: El pedido a modificar no existe.");
		}
		dao.actualizarPedido(p);
	}
	
	public void eliminarPedido(int idPedido) throws Exception{
		Pedido p = dao.traerPedido(idPedido);
		if(p == null) {
			throw new Exception("ERROR: No existe el pedido con ID " + idPedido);
		}
		dao.eliminarPedido(p);
	}
	
	//----CONSULTAS----
	public List<Pedido> traerPedidosEntreFechas(LocalDate desde, LocalDate hasta) throws Exception{
		if(desde == null || hasta == null) {
			throw new Exception("ERROR: Las fechas no pueden ser nulas");
		}
		if(desde.isAfter(hasta)) {
			throw new Exception("ERROR: La fecha 'desde' no puede ser posterior a la fecha 'hasta'.");
		}
		return dao.traerPedidosEntreFechas(desde, hasta);
	}
	
	public Pedido traerPedidoConDetalle(int idPedido) throws Exception{
		Pedido p = dao.traerPedidoConDetalle(idPedido);
		if(p == null) {
			throw new Exception("ERROR: No existe el Pedido con ID: " + idPedido);
		}
		return p;
	}
	
	public List<Pedido> traerPedidosPorUnidadDeVenta(int idUnidadDeVenta) throws Exception{
		if(UnidadDeVentaABM.getInstancia().traerUnidadVenta(idUnidadDeVenta) == null) {
			throw new Exception("ERROR: No existe la Unidad de Venta con ID: " + idUnidadDeVenta);
		}
		List<Pedido> lista = dao.traerPedidosPorUnidadDeVenta(idUnidadDeVenta);
		
		if(lista == null || lista.isEmpty()) {
			throw new Exception("La Unidad de Venta con ID: " + idUnidadDeVenta + " no registra pedidos.");
		}
		return lista;
	}

}
