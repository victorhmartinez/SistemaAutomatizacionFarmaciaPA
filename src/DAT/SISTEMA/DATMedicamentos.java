
package DAT.SISTEMA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class DATMedicamentos {
    ConexionBD conecta = new ConexionBD();
      public ResultSet getAllMedicamentos() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM productos";
        return consulta.executeQuery(sql);
    }
         public ResultSet getOneMedicamento(int idProducto) throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM productos="+idProducto;
        return consulta.executeQuery(sql);
    }
}
