import com.brauliovaz.modelos.Entidad;

public class CarpetaLibro implements Entidad {
	public int IdCarpeta;
	public int IdLibro;
	
	@Override
	public String toString() {
		return "Id de carpeta: " + IdCarpeta + ", Id del libro: " + IdLibro;
	}
}
