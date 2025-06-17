package NegocioImpl;

import java.util.List;
import Negocio.ITipoSeguroNegocio;
import daoImpl.TipoSeguroDaoImpl;
import entidad.TipoSeguro;

public class TipoSeguroNegocioImpl implements ITipoSeguroNegocio {
    private TipoSeguroDaoImpl dao = new TipoSeguroDaoImpl();

    @Override
    public List<TipoSeguro> obtenerTipos() {
        return dao.obtenerTipos();
    }
}
