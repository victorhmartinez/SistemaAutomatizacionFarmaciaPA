package DAT.SISTEMA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import ENT.Sistema.Medicamentos;
import java.awt.HeadlessException;
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
        JOptionPane.showMessageDialog(null, "Agregado con EXITO");
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

//    public boolean eliminarMedicamento(int idMedicamento) {
//
//        String Sentencia = "DELETE FROM medicamento WHERE idMedicamento = ?";
//        try {
//            ps = conecta.conectarBD().prepareStatement(Sentencia);
//            ps.setInt(0, idMedicamento);
//            ps.execute();
//            return true;
//        } catch (Exception e) {
//            System.err.println("Hola ERROR" + e);
//            return false;
//            
//        }
//    }
    
    
//    public int eliminarMedicamento(int idMedicamento) throws ClassNotFoundException, SQLException {
//        Statement st = conecta.conectarBD().createStatement();
//        int resultado = 0;
//        int confirmar = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar?");
//        if (confirmar == JOptionPane.OK_OPTION) {
//
//            try {
//                String Sentencia = "DELETE FROM medicamento WHERE  idMedicamento = ?" ;
//                ResultSet rs = st.executeQuery(Sentencia);
//                ps.setInt(1, idMedicamento);
//                if (ps.executeUpdate() > 0) {
//                    JOptionPane.showMessageDialog(null, "Se ha eliminado al Empleado");
//                } else {
//                    JOptionPane.showMessageDialog(null, "No se ha podido eliminar al elemento.\n "
//                            + "Intente denuevo");
//
//                }
//            } catch (HeadlessException | SQLException e) {
//                JOptionPane.showMessageDialog(null, "No se ha podido eliminar al elemento.\n " + " Intente denuevo.\n" + e);
//            } finally {
//                if (conecta != null) {
//                    try {
//                        conecta.desconectarBD();
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, "Se ha producido un error a la hora de cerrar la conexion.\n " + " Con la BD.\n" + e);
//
//                    }
//                }
//            }
//
//        }
//        return resultado;
//    }
    
    public int eliminarMedicamento(int idMedicamento) throws SQLException,ClassNotFoundException{
         Statement consulta = conecta.conectarBD().createStatement();
         
          int resultado = 0;
          String sql = "DELETE FROM medicamento WHERE idMedicamento = "+idMedicamento;
          resultado = consulta.executeUpdate(sql);
          consulta.close();
          return resultado;
                  
    }
}
