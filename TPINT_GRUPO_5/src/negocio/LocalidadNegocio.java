package negocio;

import java.util.List;
import entidades.Localidad;

public interface LocalidadNegocio {
    List<Localidad> obtenerPorProvincia(int idProvincia);
    Localidad obtenerPorId(int idLocalidad);
}
