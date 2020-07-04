
package ENT.Sistema;


public class Clientes {
    private int idCliente;
    private String nombreCli;
    private String apellidoCli;
    private String identificacion;
    Direccion direccion;
    Contactos contactos;

    public Clientes() {
    }

    public Clientes(int idCliente, String nombreCli, String apellidoCli, String identificacion, Direccion direccion, Contactos contactos) {
        this.idCliente = idCliente;
        this.nombreCli = nombreCli;
        this.apellidoCli = apellidoCli;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.contactos = contactos;
    }

    public Clientes(String nombreCli, String apellidoCli, String identificacion) {

        this.nombreCli = nombreCli;
        this.apellidoCli = apellidoCli;
        this.identificacion = identificacion;
    }

    
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public String getApellidoCli() {
        return apellidoCli;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Contactos getContactos() {
        return contactos;
    }
    

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public void setApellidoCli(String apellidoCli) {
        this.apellidoCli = apellidoCli;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setContactos(Contactos contactos) {
        this.contactos = contactos;
    }

    @Override
    public String toString() {
        return "Clientes{" + "idCliente=" + idCliente + ", nombreCli=" + nombreCli + ", apellidoCli=" + apellidoCli + ", identificacion=" + identificacion + ", direccion=" + direccion + ", contactos=" + contactos + '}';
    }
    
    
}
