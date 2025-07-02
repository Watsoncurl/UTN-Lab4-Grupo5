package datos;

import java.util.List;
import entidades.Localidad;

public interface LocalidadDao {
    List<Localidad> obtenerPorProvincia(int idProvincia);
    Localidad obtenerPorId(int idLocalidad);

}
