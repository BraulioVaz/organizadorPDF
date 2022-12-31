import com.brauliovaz.modelos.Entidad;

public class Subcarpeta implements Entidad {
	public int IdSubcarpeta;
	public int Carpeta;
	public int Subcarpeta;
	
	@Override
	public String toString() {
		return "Id de carpeta: " + Carpeta + ", Id de la subcarpeta: " + Subcarpeta;
	}
}
