/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG.Sistema;

import DAT.SISTEMA.DATEmpleados;
import ENT.Sistema.Contactos;
import ENT.Sistema.Direccion;
import ENT.Sistema.Empleados;
import ENT.Sistema.Usuario;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class LogEmpleados {

    DATEmpleados objEmpleado = new DATEmpleados();

    public void insertEmpleado(Empleados emp) throws SQLException {
        int var=0;
        var = objEmpleado.insertEmpleado(emp);
        if (var==1) {
            System.out.println("Ingresado bien");
        }else{
            System.out.println("no se ingreso");
        }
    }

    public void insertUsuario(Usuario user) throws SQLException {
        objEmpleado.insertUsuario(user);
    }

    
    public void insertDireccion(Direccion dir) throws SQLException {
        objEmpleado.insertDireccion(dir);
    }

   
    public void insertContactos(Contactos contac) throws SQLException {
        objEmpleado.insertContactos(contac);
    }
  
    public Usuario obtenerUltimoUsuario(Usuario user) throws SQLException {
        int idUsuario = 0;
        String userName = "";
        String contrasenia = "";
        String tipoUsuario = "";
        try {
            ResultSet r = objEmpleado.obtenerUltimoUsuario();
            while (r.next()) {
                idUsuario = r.getInt("idUsuario");
                userName = r.getString("userName");
                contrasenia = r.getString("contrasenia");
                tipoUsuario = r.getString("tipoUsuario");
                user = new Usuario(idUsuario, userName, contrasenia, tipoUsuario);
                return user;
            }
        } catch (Exception e) {
        }
        return null;

    }

    public Direccion obtenerUltimaDireccion(Direccion dir) throws SQLException {
        int idDireccion = 0;
        String calleP = "";
        String calleS = "";
        String numCasa = "";
        String referencia = "";
        String ciudad = "";
        try {
            ResultSet r = objEmpleado.obtenerUltimaDireccion();
            while (r.next()) {
                idDireccion = r.getInt("idDireccion");
                calleP = r.getString("calleP");
                calleS = r.getString("calleS");
                numCasa = r.getString("numCasa");
                referencia = r.getString("referencia");
                ciudad = r.getString("ciudad");
                dir = new Direccion(idDireccion, calleP, calleS, numCasa, referencia, ciudad);
                return dir;
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e);
        }
        return null;

    }

    public Contactos obtenerUltimoContacto(Contactos contac) throws SQLException {
        int idContactos = 0;
        String telefono = "";
        String celular = "";
        String correo = "";
        try {
            ResultSet r = objEmpleado.obtenerUltimoContacto();
            while (r.next()) {
                idContactos = r.getInt("idContactos");
                telefono = r.getString("telefono");
                celular = r.getString("celular");
                correo = r.getString("correo");
                contac = new Contactos(idContactos, telefono, celular, correo);
                return contac;
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e);
        }
        return null;
    }
    
    public void obtenerEmpleados(ArrayList<Empleados> listEmpleados)throws IOException, ClassNotFoundException, SQLException {
        ResultSet rs = objEmpleado.obtenerEmpleados();
        
        while (rs.next()) {           
            Usuario usuario = new Usuario(rs.getInt("idUsuario"),rs.getString("userName"),rs.getString("contrasenia"),rs.getString("tipoUsuario"));     
            Contactos contacto = new Contactos(rs.getInt("idContactos"),rs.getString("telefono"),rs.getString("celular"),rs.getString("correo"));
            
            Direccion direccion = new Direccion(rs.getInt("idDireccion"),rs.getString("calleP"),rs.getString("calleS"),rs.getString("numCasa"),
                                                rs.getString("referencia"),rs.getString("ciudad"));
        //System.out.println("estoy aqui ");
            Empleados objEmp = new Empleados(rs.getInt("idEmpleado"),rs.getString("nombreEmp"),rs.getString("apellidoEmp"),rs.getString("identificacionEmp"),
                                                usuario,contacto,rs.getInt("idFarmacia"),direccion);
         listEmpleados.add(objEmp);
         
        }
    }
    
    public Empleados obtenerEmpleadoBuscado(ArrayList<Empleados> listEmpleado, int id) {
        for (Empleados m : listEmpleado) {
            if (m.getIdEmpleado()== id) {
                return m;
            }
        }
        return null;
    }
    
    public boolean editarEmpleado(Empleados emp) throws SQLException, ClassNotFoundException {
        int resultado = objEmpleado.editarEmpleado(emp);
        if (resultado == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean eliminarEmpleado(int idEmpleado) throws ClassNotFoundException, SQLException {
        if (objEmpleado.eliminarEmpleado(idEmpleado)==0) {
            return false;
        }else
            return true;
    }
}
