/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENT.Sistema;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Monodroga {
    private int idMonoDroga;
    private String MonoDrogaNombre;
    private ArrayList<Compone>listCompone;

    public Monodroga() {
    }

    public Monodroga(int idMonoDroga, String MonoDrogaNombre) {
        this.idMonoDroga = idMonoDroga;
        this.MonoDrogaNombre = MonoDrogaNombre;
       listCompone=null;
    }

    public int getIdMonoDroga() {
        return idMonoDroga;
    }

    public void setIdMonoDroga(int idMonoDroga) {
        this.idMonoDroga = idMonoDroga;
    }

    public String getMonoDrogaNombre() {
        return MonoDrogaNombre;
    }

    public void setMonoDrogaNombre(String MonoDrogaNombre) {
        this.MonoDrogaNombre = MonoDrogaNombre;
    }

    public ArrayList<Compone> getListCompone() {
        return listCompone;
    }

    public void setListCompone(ArrayList<Compone> listCompone) {
        this.listCompone = listCompone;
    }
    
}
