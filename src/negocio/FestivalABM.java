package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.FestivalDao;
import datos.Festival;

public class FestivalABM {
	FestivalDao dao = new FestivalDao();
	
	public int agregarFestival (String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin){
		Festival f= new Festival(nombre, temporada, fechaInicio, fechaFin);
		return dao.agregarFestival(f);
	}
	
	public Festival traerFestival(int idFestival) {
		return dao.traerFestival(idFestival);
	}
	
	public List<Festival> traerFestival(){
		return dao.traerFestivales();
	}
	
	public void actualizarFestival(Festival f) throws Exception{
		if(f == null) {
			throw new Exception("ERROR: El Festival a modificar no existe.");
		}
		dao.actualizarFestival(f);
	}
	
	public void eliminarFestival(int idFestival) throws Exception{
		Festival f = dao.traerFestival(idFestival);
		if(f == null) {
			throw new Exception("ERROR: No existe el Festival con ID " + idFestival);
		}
		dao.eliminarFestival(f);
	}

	
	public Festival traerFestivalYUnidades(int idFestival) {
        return dao.traerFestivalYUnidades(idFestival);
    }
}
