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
public class Usuario {
    private int idUsuario;
    private String userName;
    private String contrasenia;
    private String tipoUsuario;
    
     public Usuario(){
         
     }

    public Usuario(int idUsuario, String userName, String contrasenia, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.userName = userName;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }
     
    public Usuario( String userName, String contrasenia, String tipoUsuario) {
       
        this.userName = userName;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
