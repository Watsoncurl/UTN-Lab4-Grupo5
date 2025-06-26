package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datosImpl.Conexion;
import entidades.Cuentas; // Importa la clase Cuentas

@WebServlet("/ListarCuentasServlet")
public class ServletCuenta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cuentas> listaCuentas = new ArrayList<>();
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultados = null;
        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();
            System.out.println("Conexi�n a la base de datos: " + (conexion != null ? "Exitosa" : "Fallida"));

            String sql = "SELECT " +
                    "c.nro_cuenta, " +
                    "CONCAT(cl.nombre, ' ', cl.apellido) AS cliente, " +
                    "t.descripcion AS tipo_cuenta, " +
                    "c.saldo, " +
                    "c.estado " +
                    "FROM Cuentas c " +
                    "INNER JOIN Clientes cl ON cl.id_cliente = c.id_cliente " +
                    "INNER JOIN Tipos_Cuenta t ON t.id_tipo_cuenta = c.id_tipo_cuenta";

            consulta = conexion.createStatement();
            resultados = consulta.executeQuery(sql);
            System.out.println("Consulta SQL ejecutada: " + sql);

            int contador = 0;
            while (resultados.next()) {
                Cuentas cuenta = new Cuentas();
                cuenta.setNro_cuenta(resultados.getString("nro_cuenta"));
                cuenta.setCliente(resultados.getString("cliente")); 
                cuenta.setTipo_cuenta(resultados.getString("tipo_cuenta"));  
                cuenta.setSaldo(resultados.getDouble("saldo")); 
                cuenta.setEstado(resultados.getBoolean("estado"));

                listaCuentas.add(cuenta);
                contador++;

                System.out.println("Cuenta creada: " + cuenta); 
            }
            System.out.println("N�mero de cuentas encontradas: " + contador);
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("AdminCuentas.jsp").forward(request, response);

        } catch (SQLException e) {
            System.err.println("Error al listar cuentas: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            request.setAttribute("error", "Error al listar cuentas: " + e.getMessage());
            request.getRequestDispatcher("AdminCuentas.jsp").forward(request, response);
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (consulta != null) consulta.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
            if (conexion != null) {
                Conexion.getConexion().cerrarConexion();
            }
        }
    }
}