package datosImpl;

import datos.TransferenciaDao;
import entidades.Transferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferenciaDaoImpl implements TransferenciaDao {

    private static final int ID_TIPO_MOVIMIENTO_TRANSFERENCIA = 3;

    public TransferenciaDaoImpl() {
        // No necesitas inyectar nada aquí por ahora
    }

    @Override
    public boolean registrarTransferencia(Transferencia transferencia) {
        boolean exito = false;
        Connection conexion = null;
        PreparedStatement psTransferencia = null;
        PreparedStatement psMovimientoOrigen = null;
        PreparedStatement psMovimientoDestino = null;
        PreparedStatement psUpdateOrigen = null;
        PreparedStatement psUpdateDestino = null;

        try {
            conexion = Conexion.getConexion().getSQLConexion();
            conexion.setAutoCommit(false);

            // 1. Insertar en Transferencias
            String insertTransferencia = "INSERT INTO Transferencias (id_cuenta_origen, id_cuenta_destino, fecha, importe, concepto) VALUES (?, ?, NOW(), ?, ?)";
            psTransferencia = conexion.prepareStatement(insertTransferencia);
            psTransferencia.setInt(1, transferencia.getIdCuentaOrigen());
            psTransferencia.setInt(2, transferencia.getIdCuentaDestino());
            psTransferencia.setDouble(3, transferencia.getImporte());
            psTransferencia.setString(4, transferencia.getConcepto());
            psTransferencia.executeUpdate();

            // 2. Insertar Movimiento en cuenta origen y destino (usando método auxiliar)
            registrarMovimiento(conexion, transferencia.getIdCuentaOrigen(), ID_TIPO_MOVIMIENTO_TRANSFERENCIA,
                    "Transferencia enviada a Cta ID: " + transferencia.getIdCuentaDestino(), -transferencia.getImporte());
            registrarMovimiento(conexion, transferencia.getIdCuentaDestino(), ID_TIPO_MOVIMIENTO_TRANSFERENCIA,
                    "Transferencia recibida de Cta ID: " + transferencia.getIdCuentaOrigen(), transferencia.getImporte());

            // 3. Actualizar saldos
            String updateSaldoOrigen = "UPDATE Cuentas SET saldo = saldo - ? WHERE id_cuenta = ?";
            psUpdateOrigen = conexion.prepareStatement(updateSaldoOrigen);
            psUpdateOrigen.setDouble(1, transferencia.getImporte());
            psUpdateOrigen.setInt(2, transferencia.getIdCuentaOrigen());
            psUpdateOrigen.executeUpdate();

            String updateSaldoDestino = "UPDATE Cuentas SET saldo = saldo + ? WHERE id_cuenta = ?";
            psUpdateDestino = conexion.prepareStatement(updateSaldoDestino);
            psUpdateDestino.setDouble(1, transferencia.getImporte());
            psUpdateDestino.setInt(2, transferencia.getIdCuentaDestino());
            psUpdateDestino.executeUpdate();

            conexion.commit();
            exito = true;

        } catch (SQLException e) {
            // Reemplaza logger.log() con System.err.println()
            System.err.println("Error al registrar transferencia: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para depuración
            try {
                if (conexion != null) conexion.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
                ex.printStackTrace();
            }
        } finally {
            //Cerrar los recursos en bloques try-catch individuales
            if (psTransferencia != null) {
                try {
                    psTransferencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psMovimientoOrigen != null) {
                try {
                    psMovimientoOrigen.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psMovimientoDestino != null) {
                try {
                    psMovimientoDestino.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psUpdateOrigen != null) {
                try {
                    psUpdateOrigen.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psUpdateDestino != null) {
                try {
                    psUpdateDestino.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return exito;
    }

    //Método auxiliar para registrar movimientos y evitar duplicación de código
    private void registrarMovimiento(Connection conexion, int idCuenta, int idTipoMovimiento, String concepto, double importe) throws SQLException {
        String insertMovimiento = "INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, fecha, concepto, importe) VALUES (?, ?, NOW(), ?, ?)";
        try (PreparedStatement psMovimiento = conexion.prepareStatement(insertMovimiento)) {
            psMovimiento.setInt(1, idCuenta);
            psMovimiento.setInt(2, idTipoMovimiento);
            psMovimiento.setString(3, concepto);
            psMovimiento.setDouble(4, importe);
            psMovimiento.executeUpdate();
        } catch (SQLException e) {
            // Reemplaza logger.log() con System.err.println()
            System.err.println("Error al registrar movimiento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}