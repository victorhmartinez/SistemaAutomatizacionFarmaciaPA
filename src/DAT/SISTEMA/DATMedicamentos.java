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
       public int modificarMediacamento(Medicamentos medicamentos) throws SQLException,ClassNotFoundException{
         Statement consulta = conecta.conectarBD().createStatement();
         
          int resultado = 0;
          String sql = "UPDATE medicamento SET 	nombreMedic = '"+medicamentos.getNombreMedic()+
                  "',precioMedic = "+medicamentos.getPrecioMedic()+",existenciTot = "+medicamentos.getExistenciTot()+
                  ",fechaElab = '"+medicamentos.getFechaElab()+"',fechaExpira = '"+medicamentos.getFecha_Expira()+
                  "', lote = '"+medicamentos.getLote()+"'WHERE  idMedicamento = "+medicamentos.getIdMedicamento();
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
