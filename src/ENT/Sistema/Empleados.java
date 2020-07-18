/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENT.Sistema;

/**
 *
 * @author LENOVO
 */
public class Empleados {
    private int idEmpleado;
    private String nombreEmp;
    private String apellidoEmp;
    private String identificacionEmp;
    private Usuario idUsuario;
    private Contactos idContactos;
    private  int idFarmacia;
    private Direccion idDireccion;

    public Empleados(int idEmpleado, String nombreEmp, String apellidoEmp, String identificacionEmp, Usuario idUsuario, Contactos idContactos, int idFarmacia, Direccion idDireccion) {
        this.idEmpleado = idEmpleado;
        this.nombreEmp = nombreEmp;
        this.apellidoEmp = apellidoEmp;
        this.identificacionEmp = identificacionEmp;
        this.idUsuario = idUsuario;
        this.idContactos = idContactos;
        this.idFarmacia = idFarmacia;
        this.idDireccion = idDireccion;
    }

    public Empleados(String nombreEmp, String apellidoEmp, String identificacionEmp, Usuario idUsuario, Contactos idContactos, int idFarmacia, Direccion idDireccion) {
        this.nombreEmp = nombreEmp;
        this.apellidoEmp = apellidoEmp;
        this.identificacionEmp = identificacionEmp;
        this.idUsuario = idUsuario;
        this.idContactos = idContactos;
        this.idFarmacia = idFarmacia;
        this.idDireccion = idDireccion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getApellidoEmp() {
        return apellidoEmp;
    }

    public void setApellidoEmp(String apellidoEmp) {
        this.apellidoEmp = apellidoEmp;
    }

    public String getIdentificacionEmp() {
        return identificacionEmp;
    }

    public void setIdentificacionEmp(String identificacionEmp) {
        this.identificacionEmp = identificacionEmp;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuari(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Contactos getIdContactos() {
        return idContactos;
    }

    public void setIdContactos(Contactos idContactos) {
        this.idContactos = idContactos;
    }

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public Direccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direccion idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    //Objetos
    public Direccion getDireccion() {
        return idDireccion;
    }

    public void setDireccion(Direccion direccion) {
        this.idDireccion = direccion;
    }
    
    public Usuario getUsuario() {
        return idUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.idUsuario = usuario;
    }
    
    public Contactos getContactos() {
        return idContactos;
    }

    public void setContactos(Contactos contactos) {
        this.idContactos = contactos;
    }
    
}
