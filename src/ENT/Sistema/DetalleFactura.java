/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENT.Sistema;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class DetalleFactura {
    private int idDetalle;
    private Date fecha;
    private double subtotal;
    private double total;
    private  int idFarmacia;
    private int idCliente;

    public DetalleFactura(int idDetalle, Date fecha, double subtotal, double total, int idFarmacia, int idCliente) {
        this.idDetalle = idDetalle;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.total = total;
        this.idFarmacia = idFarmacia;
        this.idCliente = idCliente;
    }

    public DetalleFactura() {
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
