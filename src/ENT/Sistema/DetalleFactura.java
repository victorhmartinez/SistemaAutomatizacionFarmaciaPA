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
    private Clientes cliente;

    public DetalleFactura(int idDetalle, Date fecha, double subtotal, double total, int idFarmacia, Clientes cliente) {
        this.idDetalle = idDetalle;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.total = total;
        this.idFarmacia = idFarmacia;
        this.cliente = cliente;
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

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "idDetalle=" + idDetalle + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total=" + total + ", idFarmacia=" + idFarmacia + ", cliente=" + cliente + '}';
    }

   

   
    
}
