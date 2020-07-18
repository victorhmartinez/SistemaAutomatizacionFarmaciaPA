
package ENT.Sistema;


public class Contactos {
    private int idContactos;
    private String telefono;
    private String celular;
    private String correo;

    public Contactos() {
    }
    
    public Contactos(String telefono, String celular, String correo) {
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
    }
    
    public Contactos(int idContactos, String telefono, String celular, String correo) {
        this.idContactos = idContactos;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
    }

    public int getIdContactos() {
        return idContactos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCelular() {
        return celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setIdContactos(int idContactos) {
        this.idContactos = idContactos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Contactos{" + "idContactos=" + idContactos + ", telefono=" + telefono + ", celular=" + celular + ", correo=" + correo + '}';
    }
    
    
}
