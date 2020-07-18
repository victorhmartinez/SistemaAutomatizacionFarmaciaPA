/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG.Sistema;

import DAT.SISTEMA.DATLogin;
import ENT.Sistema.Clientes;
import ENT.Sistema.DetalleFactura;
import ENT.Sistema.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class LogUsuario {
    DATLogin datLogin = new DATLogin();
    public Usuario login(String username,String password) throws SQLException{
          Usuario user = null;
          ResultSet rs = datLogin.getUsuario(username, password);
        while (rs.next()) {  
            user = new Usuario(rs.getInt("idUsuario"), rs.getString("userName"),rs.getString("contrasenia"),rs.getString("tipoUsuario"));
      
        }
        return user;
    }
}
