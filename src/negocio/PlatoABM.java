package negocio;
import java.util.List;
import dao.PlatoDao;
import datos.Plato;

public class PlatoABM {
	PlatoDao dao = new PlatoDao();
	
	//--------METODOS PLATO--------
	
	public int agregarPlato (String nombre, double precio, double costo) throws Exception {
		if(dao.traerPlato(nombre) != null) {
			throw new Exception("ERROR: Ya existe un plato registrado con el nombre: " +  nombre);
		}
		Plato p= new Plato(nombre, precio, costo);
		return dao.agregarPlato(p);
	}
	
	public Plato traerPlato(int idPlato) {
		return dao.traerPlato(idPlato);
	}
	
	public Plato traerPlato(String nombre) {
		return dao.traerPlato(nombre);
	}
	
	public List<Plato> traerPlatos(){
		return dao.traerPlatos();
	}
	
	public void actualizarPlato(Plato p) throws Exception{
		if(p == null) {
			throw new Exception("ERROR: El plato a modificar no existe.");
		}
		dao.actualizarPlato(p);
	}
	
	public void eliminarPlato(int idPlato) throws Exception{
		Plato p = dao.traerPlato(idPlato);
		if(p == null) {
			throw new Exception("ERROR: No existe el plato con ID " + idPlato);
		}
		dao.eliminarPlato(p);
	}

}