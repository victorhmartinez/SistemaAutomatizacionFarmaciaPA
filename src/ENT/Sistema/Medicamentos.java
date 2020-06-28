/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENT.Sistema;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Medicamentos {
    private int idMedicamento;
    private String nombreMedic;
    private double precioMedic;
    private int existenciTot;
    private Date fechaElab;
    private Date fecha_Expira;
    private String lote;
    private ArrayList<Compone>listCompone;

    public Medicamentos() {
    }

    public Medicamentos(int idMedicamento, String nombreMedic, double precioMedic, int existenciTot, Date fechaElab, Date fecha_Expira, String lote) {
        this.idMedicamento = idMedicamento;
        this.nombreMedic = nombreMedic;
        this.precioMedic = precioMedic;
        this.existenciTot = existenciTot;
        this.fechaElab = fechaElab;
        this.fecha_Expira = fecha_Expira;
        this.listCompone=null;
        this.lote = lote;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombreMedic() {
        return nombreMedic;
    }

    public void setNombreMedic(String nombreMedic) {
        this.nombreMedic = nombreMedic;
    }

    public double getPrecioMedic() {
        return precioMedic;
    }

    public void setPrecioMedic(double precioMedic) {
        this.precioMedic = precioMedic;
    }

    public int getExistenciTot() {
        return existenciTot;
    }

    public void setExistenciTot(int existenciTot) {
        this.existenciTot = existenciTot;
    }

    public Date getFechaElab() {
        return fechaElab;
    }

    public void setFechaElab(Date fechaElab) {
        this.fechaElab = fechaElab;
    }

    public Date getFecha_Expira() {
        return fecha_Expira;
    }

    public void setFecha_Expira(Date fecha_Expira) {
        this.fecha_Expira = fecha_Expira;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public ArrayList<Compone> getListCompone() {
        return listCompone;
    }

    public void setListCompone(ArrayList<Compone> listCompone) {
        this.listCompone = listCompone;
    }

    @Override
    public String toString() {
        return "Medicamentos{" + "idMedicamento=" + idMedicamento + ", nombreMedic=" + nombreMedic + ", precioMedic=" + precioMedic + ", existenciTot=" + existenciTot + ", fechaElab=" + fechaElab + ", fecha_Expira=" + fecha_Expira + ", lote=" + lote + '}';
    }
    
}
