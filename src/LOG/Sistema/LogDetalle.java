/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG.Sistema;

import DAT.SISTEMA.DATDetalles;
import ENT.Sistema.DetalleFactura;
import ENT.Sistema.DetalleMedicamento;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class LogDetalle {
    DATDetalles objDetalle = new DATDetalles();
      public int obtenerUltimaFactura(){
        int i = 0;
       
        try {
            ResultSet r = objDetalle.obtenerUltimaFactura();
            while(r.next())
                i= r.getInt("NumeroDet");
        } catch (Exception e) {
            
        }
        return i;
    }
    public void insertDetalle(DetalleFactura detalle) throws SQLException{
        objDetalle.insertDetallefac(detalle);
    }
    public void inserDetalleMedic(DetalleMedicamento detalle) throws SQLException{
        objDetalle.insertDetalleMedic(detalle);
    }
}
