package DAT.SISTEMA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ENT.Sistema.Medicamentos;
import ENT.Sistema.Monodroga;
import java.sql.PreparedStatement;

/**
 *
 * @author Usuario
 */
public class DATMedicamentos {

    PreparedStatement ps = null;
    public ResultSet rs;

    ConexionBD conecta = new ConexionBD();
//Senetencia para tener todos los medicamentos

    public ResultSet getAllMedicamentos() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM medicamento";
        return consulta.executeQuery(sql);
    }

    public ResultSet getOneMedicamento(int idProducto) throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM medicamenti WHERE idMedicamento =" + idProducto;
        return consulta.executeQuery(sql);
    }

    //Senetencia para modificar medicamentos
    public int modificarMediacamento(Medicamentos medicamentos) throws SQLException, ClassNotFoundException {
        Statement consulta = conecta.conectarBD().createStatement();

        int resultado = 0;
        String sql = "UPDATE medicamento SET 	nombreMedic = '" + medicamentos.getNombreMedic()
                + "',precioMedic = " + medicamentos.getPrecioMedic() + ",existenciTot = " + medicamentos.getExistenciTot()
                + ",fechaElab = '" + medicamentos.getFechaElab() + "',fechaExpira = '" + medicamentos.getFecha_Expira()
                + "', lote = '" + medicamentos.getLote() + "'WHERE  idMedicamento = " + medicamentos.getIdMedicamento();
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;

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
        intRetorno = st.executeUpdate(Sentencia); // envia la sentencia a la bd
        return intRetorno;
    }


    public ResultSet getAllMonodroga() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM monodroga";
        return consulta.executeQuery(sql);
    }

    public int ingresarMonodroga(Monodroga objMono) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String query = "INSERT INTO monodroga(idMonoDroga,MonoDrogaNombre)"
                + "VALUES ("
                + objMono.getIdMonoDroga() + ",'"
                + objMono.getMonoDrogaNombre()
                + "')";
        intRetorno = st.executeUpdate(query); // envia la sentencia a la bd
        return intRetorno;
    }
    public int modificarMonodroga(Monodroga objMono) throws SQLException{
         Statement consulta = conecta.conectarBD().createStatement();

        int resultado = 0;
        String sql = "UPDATE monodroga SET MonoDrogaNombre = '" + objMono.getMonoDrogaNombre()
                + "'WHERE  idMonoDroga = " + objMono.getIdMonoDroga();
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;
    }
    public int eliminarMonodroga(int idMonodroga) throws SQLException, ClassNotFoundException {
        Statement consulta = conecta.conectarBD().createStatement();

        int resultado = 0;
        String sql = "DELETE FROM monodroga WHERE idMonoDroga = " + idMonodroga;
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;

    }
    public int eliminarMedicamento(int idMedicamento) throws SQLException, ClassNotFoundException {
        Statement consulta = conecta.conectarBD().createStatement();

        int resultado = 0;
        String sql = "DELETE FROM medicamento WHERE idMedicamento = " + idMedicamento;
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;

    }
}
