package DAT.SISTEMA;

import ENT.Sistema.Clientes;
import ENT.Sistema.Contactos;
import ENT.Sistema.Direccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DATClientes {

    PreparedStatement ps = null;
    public ResultSet rs;

    ConexionBD conecta = new ConexionBD();
//Senetencia para tener todos los medicamentos

    public ResultSet getAllCliente() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT *"
                + " FROM cliente cl, direccion d,contactos c "
                + "WHERe cl.idDireccion=d.idDireccion AND cl.idContactos=c.idContactos";
        return consulta.executeQuery(sql);
    }

    public ResultSet getOneCliente(int idCliente) throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT nombreCli,apellidoCli,identificacion FROM cliente WHERE idCliente =" + idCliente;
        return consulta.executeQuery(sql);
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
      public int insertCliente(Clientes clien) throws SQLException {
        int intRetorno = 0;
        Statement st = conecta.conectarBD().createStatement();
        String sentencia = "INSERT INTO cliente(nombreCli,apellidoCli,identificacion,idContactos,idDireccion)"
                + "VALUES('"+clien.getNombreCli()+"','"+clien.getApellidoCli()+"','"
                + clien.getIdentificacion()+ "',"+ clien.getContactos().getIdContactos()+","
                + clien.getDireccion().getIdDireccion()+")";
        intRetorno = st.executeUpdate(sentencia);
        return intRetorno;
    }
    
    public int eliminarCliente(String cedula) throws SQLException, ClassNotFoundException {
        Statement consulta = conecta.conectarBD().createStatement();
        int resultado = 0;
        //String sql = "DELETE FROM cliente WHERE identificacion = '" + cedula +"'";
        String sql = "DELETE FROM cliente WHERE identificacion='"+cedula+"'";
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;
    }
    
     public int editarCliente(Clientes clientes) throws SQLException, ClassNotFoundException {
        Statement consulta = conecta.conectarBD().createStatement();
        int resultado = 0;
        String sql = "UPDATE cliente cli  INNER JOIN contactos c ON cli.idContactos=c.idContactos INNER JOIN direccion d ON cli.idDireccion=d.idDireccion SET cli.nombreCli = '" + clientes.getNombreCli() +"', cli.apellidoCli = '" + clientes.getApellidoCli()
                + "', cli.identificacion = '" + clientes.getIdentificacion()
                + "', c.telefono = '"+ clientes.getContactos().getTelefono()
                + "', c.celular = '"+ clientes.getContactos().getCelular()
                + "', c.correo = '"+ clientes.getContactos().getCorreo()
                + "', d.calleP = '"+ clientes.getDireccion().getCalleP()
                + "', d.calleS = '"+ clientes.getDireccion().getCalleS()
                + "', d.numCasa = '"+ clientes.getDireccion().getNumCasa()
                + "', d.referencia = '"+ clientes.getDireccion().getReferencia()
                + "', d.ciudad = '"+ clientes.getDireccion().getCiudad()
                + "' WHERE  cli.idCliente = " + clientes.getIdCliente();
        resultado = consulta.executeUpdate(sql);
        consulta.close();
        return resultado;

    }
     
     //PRUEBAS 
      
    public ResultSet obtenerUltimaDireccion() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT MAX(idDireccion) idDir from direccion";
        return consulta.executeQuery(sql);
    }
    
    public ResultSet obtenerUltimoContacto() throws SQLException {
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT MAX(idContactos) idCon from contactos";
        return consulta.executeQuery(sql);
    }
    
}
