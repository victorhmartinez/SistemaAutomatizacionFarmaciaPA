/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAT.SISTEMA;

import ENT.Sistema.DetalleFactura;
import ENT.Sistema.DetalleMedicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class DATDetalles {
    PreparedStatement ps = null;
    public ResultSet rs;

    ConexionBD conecta = new ConexionBD();
       public ResultSet obtenerUltimaFactura() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT max (idDetalleFactura) NumeroDet from detallefactura";
        return consulta.executeQuery(sql);
    }
      public int insertDetallefac(DetalleFactura detalle) throws SQLException{
           int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia="INSERT INTO detallefactura(idDetalle,fecha,subTotal,total,idFarmacia,idCliente)"
                +"VALUES("+detalle.getIdDetalle()+",'"
                +detalle.getFecha()+"',"
                +detalle.getSubtotal()+","
                +detalle.getTotal()+","
                +detalle.getIdFarmacia()+","
                +detalle.getIdCliente()+")";
        intRetorno=st.executeUpdate(sentencia);
          JOptionPane.showMessageDialog(null, "Detalle Afregado");
          return intRetorno;
      }
       public int insertDetalleMedic(DetalleMedicamento detalle) throws SQLException{
           int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia="INSERT INTO detallefactura(idMedicamento,idDetalleFactura,cantProduct)"
                +"VALUES("+detalle.getIdDetalle()+","+detalle.getIdMedicamento()+","+detalle.getCant()+")";
        intRetorno=st.executeUpdate(sentencia);
          JOptionPane.showMessageDialog(null, "Detalle Agregado");
          return intRetorno;
      }
      
}
