/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAT.SISTEMA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class DATLogin {
    
      public ResultSet getUsuario(String username,String password) throws SQLException {
             ConexionBD conecta = new ConexionBD();
        Statement consulta = conecta.conectarBD().createStatement();
        String sql = "SELECT idUsuario,userName,contrasenia,tipoUsuario FROM usuario "
                + "WHERE userName='"+username+"'AND contrasenia='"+password+"'";
        return consulta.executeQuery(sql);
    }
}
