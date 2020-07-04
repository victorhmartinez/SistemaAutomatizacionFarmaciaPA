package DAT.SISTEMA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DATClientes {

    PreparedStatement ps = null;
    public ResultSet rs;

    ConexionBD conecta = new ConexionBD();
//Senetencia para tener todos los medicamentos

    public ResultSet getAllCliente() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT nombreCli,apellidoCli,identificacion FROM cliente";
        return consulta.executeQuery(sql);
    }

    public ResultSet getOneCliente(int idCliente) throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT nombreCli,apellidoCli,identificacion FROM cliente WHERE idCliente =" + idCliente;
        return consulta.executeQuery(sql);
    }
}
