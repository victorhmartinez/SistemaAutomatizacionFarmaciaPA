package LOG.Sistema;
import DAT.SISTEMA.DATClientes;
import ENT.Sistema.Clientes;
import ENT.Sistema.Contactos;
import ENT.Sistema.Direccion;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

            //Creamos un objeto de tipo CLiente
            Direccion dir = new Direccion(rs.getInt("idDireccion"), rs.getString("calleP"), rs.getString("calleS"),
                    rs.getString("numCasa"), rs.getString("referencia"), rs.getString("ciudad"));
            Contactos cont = new Contactos(rs.getInt("idContactos"), rs.getString("telefono"), rs.getString("celular"), rs.getString("correo"));

            Clientes objClientes = new Clientes(rs.getInt("idCliente"), rs.getString("nombreCli"), rs.getString("apellidoCli"), rs.getString("identificacion"), dir, cont);
//            Clientes objClientes = new Clientes(rs.getString("nombreCli"), rs.getString("apellidoCli"),
//                    rs.getString("identificacion"));
            listaClientes.add(objClientes);

        }

    }

    public void insertEmpleado(Clientes cli) throws SQLException {
        int var = 0;
        var = objDatClientes.insertCliente(cli);
//        if (var==1) {
//            System.out.println("Ingresado bien");
//        }else{
//            System.out.println("no se ingreso");
//        }
    }

    public void insertDireccion(Direccion dir) throws SQLException {
        objDatClientes.insertDireccion(dir);
    }

    public void insertContactos(Contactos contac) throws SQLException {
        objDatClientes.insertContactos(contac);
    }

    public int obtenerUltimaDireccion() throws SQLException {
        int idDir = 0;

        try {
            ResultSet r = objDatClientes.obtenerUltimaDireccion();
            while (r.next()) {
                idDir = r.getInt("idDir");
            }
        } catch (Exception e) {

        }
        return idDir;

    }
    public int obtenerUltimoContacto() throws SQLException {
        int idContactos = 0;
       
        try {
            ResultSet r = objDatClientes.obtenerUltimoContacto();
            while (r.next()) {
                idContactos = r.getInt("idCon");
            }
        } catch (Exception e) {

        }
        return idContactos;
    }
    
     public Clientes obtenerClienteBuscado(ArrayList<Clientes> listClientes, int id) {
        for (Clientes m : listClientes) {
            if (m.getIdCliente()== id) {
                return m;
            }
        }
        return null;
    }

     public boolean editarCliente(Clientes cli) throws SQLException, ClassNotFoundException {
        int resultado = objDatClientes.editarCliente(cli);
        if (resultado == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean eliminarCliente(String idCliente) throws ClassNotFoundException, SQLException {
        if (objDatClientes.eliminarCliente(idCliente)==0) {
            return false;
        }else
            return true;
    }
}
