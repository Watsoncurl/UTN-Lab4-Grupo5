package negocioImpl;

import datos.ProvinciaDao;
import datosImpl.ProvinciaDaoImpl;
import entidades.Provincia;
import negocio.ProvinciaNegocio;
import java.util.List;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {
    private ProvinciaDao provinciaDao = new ProvinciaDaoImpl();

    @Override
    public List<Provincia> obtenerTodas()  {
        return provinciaDao.obtenerTodas();
    }

    @Override
    public int obtenerIdPorNombre(String nombre) throws Exception {
        return provinciaDao.obtenerIdPorNombre(nombre);
    }
    
    @Override
    public String obtenerNombrePorId(int idProvincia) throws Exception {
        return provinciaDao.obtenerNombrePorId(idProvincia);
    }

}
