/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAT.SISTEMA;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ConexionBD {
    
    
     //Conectarse a la BDD
   public Connection con;
   public String driver = "com.mysql.jdbc.Driver";
    
   //mismo nombre de la clase
    public ConexionBD() {
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10354643", "sql10354643", "7JxJb7597V");
           if (con !=null) {
               //JOptionPane.showMessageDialog(null, "Conexion Exitosa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar conectarse con la BD");
        }
    }
    public Connection conectarBD(){
        return con;
    }
    public void desconectarBD(){
        con = null;
        if (con==null) {
          JOptionPane.showMessageDialog(null, "Conexion cerrada...");

        }
    }
}
