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
	
	public int agregarPedido (LocalDate fechaTransaccion, Festival festival, UnidadDeVenta unidadVenta, ItemPlato itemPlatos){
		Pedido p= new Pedido(fechaTransaccion, festival, unidadVenta, itemPlatos);
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

}
