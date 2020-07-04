/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG.Sistema;

import DAT.SISTEMA.DATDetalles;
import ENT.Sistema.DetalleFactura;
import ENT.Sistema.DetalleMedicamento;
import ENT.Sistema.Medicamentos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
    public boolean cantDisponible(Medicamentos m , int cant){
        if(m.getExistenciTot()>=cant){
           return true;
        }else{
            JOptionPane.showMessageDialog(null, "No hay la cantidad suficiente");
            return  false;
        }
    }
    public void imprimirCarrito(ArrayList<Medicamentos>carrito){
        for(Medicamentos m : carrito){
            System.out.println("Vendidos: "+m);
        }
    }
}
