package datos;

import java.util.List;
import entidades.Provincia;

public interface ProvinciaDao {
    List<Provincia> obtenerTodas();
    int obtenerIdPorNombre(String nombre);
    String obtenerNombrePorId(int idProvincia); 
}
