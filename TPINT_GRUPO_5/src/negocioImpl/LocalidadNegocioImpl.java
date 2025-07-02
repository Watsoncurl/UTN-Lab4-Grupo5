package negocioImpl;

import datos.LocalidadDao;
import datosImpl.LocalidadDaoImpl;
import entidades.Localidad;
import negocio.LocalidadNegocio;
import java.util.List;

public class LocalidadNegocioImpl implements LocalidadNegocio {
    private LocalidadDao localidadDao = new LocalidadDaoImpl();

    @Override
    public List<Localidad> obtenerPorProvincia(int idProvincia) {
        return localidadDao.obtenerPorProvincia(idProvincia);
    }

    @Override
    public Localidad obtenerPorId(int idLocalidad) {
        return localidadDao.obtenerPorId(idLocalidad);
    }
}
