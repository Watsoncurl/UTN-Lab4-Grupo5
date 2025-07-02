package negocio;

import java.util.List;
import entidades.Provincia;

public interface ProvinciaNegocio {
    List<Provincia> obtenerTodas();
    int obtenerIdPorNombre(String nombre) throws Exception;
    String obtenerNombrePorId(int idProvincia) throws Exception;
}
