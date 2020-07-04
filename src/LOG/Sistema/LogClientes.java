
package LOG.Sistema;

import DAT.SISTEMA.DATClientes;
import ENT.Sistema.Clientes;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class LogClientes {
    DATClientes objDatClientes = new DATClientes();
    public Clientes obtenerUnCliente(ArrayList<Clientes> lisClientes, String cedula) {
        for (Clientes objClientes : lisClientes) {
            if (objClientes.getIdentificacion().equals(cedula)) {
                return objClientes;
            }
        }
        return null;
    }
    
    public void obtenerTodosClientes(ArrayList<Clientes> listaClientes)
            throws IOException, ClassNotFoundException, SQLException {
        //creamos un objecto de tip DATcliente

        //Creamos un resultSet para  par obtener todo los clientes de la base de datos
        ResultSet rs = objDatClientes.getAllCliente();
        //Recorremos con un whiletodo el resulSet

        while (rs.next()) {
            
            //Creamos un objeto de tipo Medicamentos
            Clientes objClientes = new Clientes(rs.getString("nombreCli"), rs.getString("apellidoCli"),
                    rs.getString("identificacion"));
            JOptionPane.showMessageDialog(null, "Prueba5555555");
            listaClientes.add(objClientes);
            
        }

    }
    
    
}
