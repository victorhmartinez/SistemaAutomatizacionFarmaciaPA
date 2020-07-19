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
public class Compone {
    private Medicamentos Medicamento_idMedicamento;
    private Monodroga MonoDroga_idMonoDroga;
    private String porcentaje;

    public Compone() {
    }

    public Compone(Medicamentos Medicamento_idMedicamento, Monodroga MonoDroga_idMonoDroga, String porcentaje) {
        this.Medicamento_idMedicamento = Medicamento_idMedicamento;
        this.MonoDroga_idMonoDroga = MonoDroga_idMonoDroga;
        this.porcentaje = porcentaje;
    }

    public Medicamentos getMedicamento_idMedicamento() {
        return Medicamento_idMedicamento;
    }

    public void setMedicamento_idMedicamento(Medicamentos Medicamento_idMedicamento) {
        this.Medicamento_idMedicamento = Medicamento_idMedicamento;
    }

    public Monodroga getMonoDroga_idMonoDroga() {
        return MonoDroga_idMonoDroga;
    }

    public void setMonoDroga_idMonoDroga(Monodroga MonoDroga_idMonoDroga) {
        this.MonoDroga_idMonoDroga = MonoDroga_idMonoDroga;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    
}
