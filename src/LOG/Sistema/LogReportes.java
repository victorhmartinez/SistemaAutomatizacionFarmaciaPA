package LOG.Sistema;

import DAT.SISTEMA.DATDetalles;
import ENT.Sistema.Clientes;
import ENT.Sistema.DetalleFactura;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class LogReportes {
    DATDetalles datDetalles = new DATDetalles();
    public void getReportes(ArrayList<DetalleFactura> listReportes,Date fechaInicio,Date fechaFin) throws SQLException{
        ResultSet rs = datDetalles.getReportes(fechaInicio, fechaFin);
        while (rs.next()) {  
            Clientes cliente = new Clientes(rs.getString("nombreCli"), rs.getString("apellidoCli"), "");
            DetalleFactura reporte= new DetalleFactura(rs.getInt("idDetalleFactura"), rs.getDate("fecha"), rs.getDouble("subTotal"), rs.getDouble("total"), 1, cliente);
       listReportes.add(reporte);
        }
        
    }
    public double getTotal(Date fechaInicio,Date fechaFin) throws SQLException{
       double total = 0;
        ResultSet rs = datDetalles.getTotalVentas(fechaInicio, fechaFin);
        while (rs.next()) {            
            total=rs.getDouble("SUM(total)");
          
        }
        return total;
    }
}
