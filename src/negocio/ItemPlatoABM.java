package negocio;

import java.util.List;

import dao.ItemPlatoDao;
import datos.ItemPlato;
import datos.Pedido;
import datos.Plato;

public class ItemPlatoABM {
	ItemPlatoDao dao = new ItemPlatoDao();
	PlatoABM platoABM = new PlatoABM();
	PedidoABM pedidoABM = new PedidoABM();
	
	//--------METODOS ITEMPLATO--------
	//Un ItemPlato es el detalle de un Pedido, por eso siempre se opera sobre el pedido al que pertenece.
	
	public int agregarItemPlatoAPedido(int idPedido, int idPlato, int cantidad) throws Exception {
		Pedido pedido = pedidoABM.traerPedido(idPedido);
		if(pedido == null) {
			throw new Exception("ERROR: No existe el pedido con ID " + idPedido);
		}
		Plato plato = platoABM.traerPlato(idPlato);
		if(plato == null) {
			throw new Exception("ERROR: No existe el plato con ID " + idPlato);
		}
		if(cantidad <= 0) {
			throw new Exception("ERROR: La cantidad debe ser mayor a 0.");
		}
		ItemPlato item = new ItemPlato(plato, cantidad, calcularSubTotal(plato, cantidad));
		return dao.agregarItemPlatoAPedido(idPedido, item);
	}
	
	public void modificarCantidadItemPlato(int idItemPlato, int cantidad) throws Exception {
		ItemPlato item = dao.traerItemPlato(idItemPlato);
		if(item == null) {
			throw new Exception("ERROR: No existe el item con ID " + idItemPlato);
		}
		if(cantidad <= 0) {
			throw new Exception("ERROR: La cantidad debe ser mayor a 0.");
		}
		item.setCantidad(cantidad);
		item.setSubTotal(calcularSubTotal(item.getPlato(), cantidad));
		dao.actualizarItemPlato(item);
	}
	
	public void quitarItemPlatoDePedido(int idPedido, int idItemPlato) throws Exception {
		if(pedidoABM.traerPedido(idPedido) == null) {
			throw new Exception("ERROR: No existe el pedido con ID " + idPedido);
		}
		if(dao.traerItemPlato(idItemPlato) == null) {
			throw new Exception("ERROR: No existe el item con ID " + idItemPlato);
		}
		dao.eliminarItemPlatoDePedido(idPedido, idItemPlato);
	}
	
	public ItemPlato traerItemPlato(int idItemPlato) {
		return dao.traerItemPlato(idItemPlato);
	}
	
	public List<ItemPlato> traerItemPlatos(){
		return dao.traerItemPlatos();
	}
	
	//----CONSULTAS----
	
	public List<ItemPlato> traerItemPlatosPorPedido(int idPedido) throws Exception{
		if(pedidoABM.traerPedido(idPedido) == null) {
			throw new Exception("ERROR: No existe el pedido con ID " + idPedido);
		}
		List<ItemPlato> lista = dao.traerItemPlatosPorPedido(idPedido);
		if(lista == null || lista.isEmpty()) {
			throw new Exception("El pedido con ID: " + idPedido + " no registra items.");
		}
		return lista;
	}
	
	public List<ItemPlato> traerItemPlatosPorPlato(int idPlato) throws Exception{
		if(platoABM.traerPlato(idPlato) == null) {
			throw new Exception("ERROR: No existe el plato con ID " + idPlato);
		}
		return dao.traerItemPlatosPorPlato(idPlato);
	}
	
	public int traerCantidadVendidaPorPlato(int idPlato) throws Exception{
		if(platoABM.traerPlato(idPlato) == null) {
			throw new Exception("ERROR: No existe el plato con ID " + idPlato);
		}
		return dao.traerCantidadVendidaPorPlato(idPlato);
	}
	
	//----AUXILIARES----
	
	private double calcularSubTotal(Plato plato, int cantidad) {
		return plato.getPrecio() * cantidad;
	}
	
}
