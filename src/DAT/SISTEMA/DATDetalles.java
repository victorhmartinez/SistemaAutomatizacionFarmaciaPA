/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAT.SISTEMA;

import ENT.Sistema.DetalleFactura;
import ENT.Sistema.DetalleMedicamento;
import java.sql.Date;
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
        String sql = "SELECT MAX(idDetalleFactura) NumeroDet from detallefactura";
        return consulta.executeQuery(sql);
    }
      public int insertDetallefac(DetalleFactura detalle) throws SQLException{
           int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia="INSERT INTO detallefactura(idDetalleFactura,fecha,subTotal,total,idFarmacia,idCliente)"
                +"VALUES("+detalle.getIdDetalle()+",'"
                +detalle.getFecha()+"',"
                +detalle.getSubtotal()+","
                +detalle.getTotal()+","
                +detalle.getIdFarmacia()+","
                +detalle.getCliente().getIdCliente()+")";
        intRetorno=st.executeUpdate(sentencia);
        //  JOptionPane.showMessageDialog(null, "Detalle Agregado");
          return intRetorno;
      }
       public int insertDetalleMedic(DetalleMedicamento detalle) throws SQLException{
           int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia="INSERT INTO medicamentodetallefactura(idMedicamento,idDetalleFactura,cantProduct)"
                +"VALUES("+detalle.getIdMedicamento()+","+detalle.getIdDetalle()+","+detalle.getCant()+")";
        intRetorno=st.executeUpdate(sentencia);
         // JOptionPane.showMessageDialog(null, "Detalle Agregado");
          return intRetorno;
      }
          public ResultSet getReportes(Date fechaInicio, Date fechafin) throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT df.idDetalleFactura,df.fecha,df.subTotal,df.total,cl.idCliente,cl.nombreCli,cl.apellidoCli "
                + "FROM detallefactura df,cliente cl "
                + "WHERE df.fecha between '"
                + fechaInicio+"' and '"+fechafin+"' AND df.idCliente=cl.idCliente";
        return consulta.executeQuery(sql);
    }
          public ResultSet getTotalVentas(Date fechaInicio,Date fechaFin) throws SQLException{
               Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT SUM(total) "
                + "FROM detallefactura  "
                + "WHERE fecha between '"
                + fechaInicio+"' and '"+fechaFin+"'";
        return consulta.executeQuery(sql);
          }
      
}
