/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENT.Sistema;

/**
 *
 * @author Usuario
 */
public class DetalleMedicamento {
    private int idMedicamento;
    private int idDetalle;
    private int cant;
    public DetalleMedicamento() {
    }

    public DetalleMedicamento(int idMedicamento, int idDetalle, int cant) {
        this.idMedicamento = idMedicamento;
        this.idDetalle = idDetalle;
        this.cant = cant;
    }

 

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
}
