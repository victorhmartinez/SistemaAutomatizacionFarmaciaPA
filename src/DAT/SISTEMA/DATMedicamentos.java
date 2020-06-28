package DAT.SISTEMA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import ENT.Sistema.Medicamentos;

/**
 *
 * @author Usuario
 */
public class DATMedicamentos {

    
    ConexionBD conecta  = new ConexionBD();

    public ResultSet getAllMedicamentos() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM productos";
        return consulta.executeQuery(sql);
    }

    public ResultSet getOneMedicamento(int idProducto) throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM productos=" + idProducto;
        return consulta.executeQuery(sql);
    }

    public int insertarMedicamento(Medicamentos objMedicamentos) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String Sentencia = "insert into medicamento(nombreMedic,precioMedic,existenciTot,fechaElab,fechaExpira,lote)"
                + "VALUES ('"
                + objMedicamentos.getNombreMedic() + "',"
                + objMedicamentos.getPrecioMedic() + ","
                + objMedicamentos.getExistenciTot() + ",'"
                + objMedicamentos.getFechaElab() + "','"
                + objMedicamentos.getFecha_Expira() + "','"
                + objMedicamentos.getLote()
                + "')";
        JOptionPane.showMessageDialog(null, "Agregado con EXITO");
        intRetorno = st.executeUpdate(Sentencia); // envia la sentencia a la bd
        return intRetorno;
    }

    public int insertarMedicMonoDro(Medicamentos objMedicamentos) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String Sentencia = "insert into monodroga(MonoDrogaNombre)"
                + "VALUES ('"
                + objMedicamentos.getNombreMedic() + "',"
                + objMedicamentos.getPrecioMedic() + ","
                + objMedicamentos.getExistenciTot() + ",'"
                + objMedicamentos.getFechaElab() + "','"
                + objMedicamentos.getFecha_Expira() + "','"
                + objMedicamentos.getLote()
                + "')";
        JOptionPane.showMessageDialog(null, "Agregado con EXITO");
        intRetorno = st.executeUpdate(Sentencia); // envia la sentencia a la bd
        return intRetorno;
    }

}
