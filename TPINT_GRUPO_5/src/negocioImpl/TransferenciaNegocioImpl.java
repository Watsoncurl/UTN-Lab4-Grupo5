package negocioImpl;

import datos.TransferenciaDao;
import datosImpl.TransferenciaDaoImpl;
import entidades.Transferencia;
import negocio.TransferenciaNegocio;
import negocio.CuentasNegocio;

public class TransferenciaNegocioImpl implements TransferenciaNegocio {

    private TransferenciaDao transferenciaDao;
    private CuentasNegocio cuentasNegocio;

    public TransferenciaNegocioImpl() {
        this.transferenciaDao = new TransferenciaDaoImpl(); 
    }
    public TransferenciaNegocioImpl(TransferenciaDao transferenciaDao, CuentasNegocio cuentasNegocio) {
        this.transferenciaDao = transferenciaDao;
        this.cuentasNegocio = cuentasNegocio;
    }

    // Setter para inyectar la lógica de cuentas desde fuera si es necesario
    public void setCuentasNegocio(CuentasNegocio cuentasNegocio) {
        this.cuentasNegocio = cuentasNegocio;
    }

    @Override
    public boolean realizarTransferencia(Transferencia t) {
        // Podés agregar aquí lógica para validar saldo, verificar cuentas, etc.
        return transferenciaDao.registrarTransferencia(t);
    }
}


