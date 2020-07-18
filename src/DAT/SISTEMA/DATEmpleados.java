/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAT.SISTEMA;

import ENT.Sistema.Contactos;
import ENT.Sistema.Direccion;
import ENT.Sistema.Empleados;
import ENT.Sistema.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author LENOVO
 */
public class DATEmpleados {

    PreparedStatement ps = null;
    public ResultSet rs;

    ConexionBD conecta = new ConexionBD();
    
    
    //Consultas para Gestión Empleados
    public int insertEmpleado(Empleados emp) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia = "INSERT INTO empleado(nombreEmp,apellidoEmp,identificacionEmp,idUsuario,idContactos, idFarmacia,idDireccion)"
                + "VALUES('"+emp.getNombreEmp()+"','"+emp.getApellidoEmp()+"','"
                + emp.getIdentificacionEmp()+ "',"+ emp.getUsuario().getIdUsuario()+","
                + emp.getContactos().getIdContactos()+","
                + emp.getIdFarmacia()+","+ emp.getDireccion().getIdDireccion()+")";
        intRetorno = st.executeUpdate(sentencia);
        return intRetorno;
    }
    
    //Consultas para Gestión Usuario
    public int insertUsuario(Usuario usuario) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia = "INSERT INTO usuario(userName,contrasenia,tipoUsuario)"
                + "VALUES('"+usuario.getUserName()+"','"+usuario.getContrasenia()+"','"+ usuario.getTipoUsuario() + "')";
                intRetorno = st.executeUpdate(sentencia);
        return intRetorno;
    }

    //Consultas para Gestión Dirección
    public int insertDireccion(Direccion direccion) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia = "INSERT INTO direccion(calleP,calleS,numCasa,referencia,ciudad)"
                + "VALUES('"+direccion.getCalleP()+"','"+direccion.getCalleS()+"','"+ direccion.getNumCasa()+ "','"+ direccion.getReferencia()+ "','"+ direccion.getCiudad()+ "')";
                intRetorno = st.executeUpdate(sentencia);
        return intRetorno;
    }
    
    //Consultas para Gestión Contactos
    public int insertContactos(Contactos contactos) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia = "INSERT INTO contactos(telefono,celular,correo)"
                + "VALUES('"+contactos.getTelefono()+"','"+contactos.getCelular()+"','"+ contactos.getCorreo()+ "')";
                intRetorno = st.executeUpdate(sentencia);
        return intRetorno;
    }

    
    //PRUEBAS 
    public ResultSet obtenerUltimoUsuario() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT idUsuario, userName, contrasenia, tipoUsuario FROM usuario ORDER BY idUsuario DESC LIMIT 1";
        return consulta.executeQuery(sql);
    }
    
    public ResultSet obtenerUltimaDireccion() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT idDireccion, calleP, calleS, numCasa, referencia, ciudad FROM direccion ORDER BY idDireccion DESC LIMIT 1";
        return consulta.executeQuery(sql);
    }
    
    public ResultSet obtenerUltimoContacto() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT idContactos, telefono, celular, correo FROM contactos ORDER BY idContactos DESC LIMIT 1";
        return consulta.executeQuery(sql);
    }
    
    public ResultSet obtenerEmpleados() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT * FROM empleado e, usuario u, contactos c, direccion d WHERE e.idUsuario=u.idUsuario AND c.idContactos=e.idContactos AND e.idDireccion=d.idDireccion";
        return consulta.executeQuery(sql);
    }
    
    public int editarEmpleado(Empleados empleado) throws SQLException, ClassNotFoundException {
        Statement consulta = conecta.conectarBD().createStatement();
        int resultado = 0;
        String sql = "UPDATE empleado e INNER JOIN contactos c ON e.idContactos=c.idContactos INNER JOIN direccion d ON e.idDireccion=d.idDireccion SET e.nombreEmp = '" + empleado.getNombreEmp() +"', e.apellidoEmp = '" + empleado.getApellidoEmp()
                + "', e.identificacionEmp = '" + empleado.getIdentificacionEmp()
                + "', c.telefono = '"+ empleado.getContactos().getTelefono()
                + "', c.celular = '"+ empleado.getContactos().getCelular()
                + "', c.correo = '"+ empleado.getContactos().getCorreo()
                + "', d.calleP = '"+ empleado.getDireccion().getCalleP()
                + "', d.calleS = '"+ empleado.getDireccion().getCalleS()
                + "', d.numCasa = '"+ empleado.getDireccion().getNumCasa()
                + "', d.referencia = '"+ empleado.getDireccion().getReferencia()
                + "', d.ciudad = '"+ empleado.getDireccion().getCiudad()
                + "' WHERE  e.idEmpleado = " + empleado.getIdEmpleado();
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;

    }
    
    public int eliminarEmpleado(int idEmpleado) throws SQLException, ClassNotFoundException {
        Statement consulta = conecta.conectarBD().createStatement();
        int resultado = 0;
        String sql = "DELETE FROM empleado WHERE idEmpleado = " + idEmpleado;
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;

    }
}