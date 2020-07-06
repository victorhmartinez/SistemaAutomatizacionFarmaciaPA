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
    public String user = "root";
    public String password = "";
    public String url = "jdbc:mysql://localhost:3306/bfarmacia";
    
   //mismo nombre de la clase
    public ConexionBD() {
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
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
