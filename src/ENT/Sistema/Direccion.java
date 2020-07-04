
package ENT.Sistema;

public class Direccion {
    private int idDireccion;
    private String calleP;
    private String calleS;
    private String numCasa;
    private String referencia;
    private String ciudad;

    public Direccion() {
    }

    public Direccion(int idDireccion, String calleP, String calleS, String numCasa, String referencia, String ciudad) {
        this.idDireccion = idDireccion;
        this.calleP = calleP;
        this.calleS = calleS;
        this.numCasa = numCasa;
        this.referencia = referencia;
        this.ciudad = ciudad;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public String getCalleP() {
        return calleP;
    }

    public String getCalleS() {
        return calleS;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getCiudad() {
        return ciudad;
    }
    
    //set

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public void setCalleP(String calleP) {
        this.calleP = calleP;
    }

    public void setCalleS(String calleS) {
        this.calleS = calleS;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Direccion{" + "idDireccion=" + idDireccion + ", calleP=" + calleP + ", calleS=" + calleS + ", numCasa=" + numCasa + ", referencia=" + referencia + ", ciudad=" + ciudad + '}';
    }
    
    
}
