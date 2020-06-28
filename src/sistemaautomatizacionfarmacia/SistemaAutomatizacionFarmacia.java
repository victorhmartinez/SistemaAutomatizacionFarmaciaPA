/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaautomatizacionfarmacia;

import ENT.Sistema.Medicamentos;
import LOG.Sistema.ObtenerMedicamentos;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class SistemaAutomatizacionFarmacia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
             ArrayList<Medicamentos> listMedicamentos = new ArrayList<>();
        ObtenerMedicamentos objMe= new ObtenerMedicamentos();
        System.out.println("Holllaaaa");
        try {
            objMe.getAllMedicamentos(listMedicamentos);
        } catch (Exception e) {
            System.out.println("ERROR"+e);
        } 
        for (Medicamentos m : listMedicamentos) {
            System.out.println("MEdicamento"+m);
        }
    }
    
}
