/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG.Sistema;

import DAT.SISTEMA.DATMedicamentos;
import ENT.Sistema.Medicamentos;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ObtenerMedicamentos {

    DATMedicamentos datMedicamento = new DATMedicamentos();

    public void getAllMedicamentos(ArrayList<Medicamentos> listMedicamentos)
            throws IOException, ClassNotFoundException, SQLException {
        //creamos un objecto de tip DATcliente

        //Creamos un resultSet para  par obtener todo los clientes de la base de datos
        ResultSet rs = datMedicamento.getAllMedicamentos();
        //Recorremos con un whiletodo el resulSet

        while (rs.next()) {
            //Creamos un objeto de tipo Medicamentos
            Medicamentos objMedicamento = new Medicamentos(rs.getInt("idMedicamento"),
                    rs.getString("nombreMedic"), rs.getDouble("precioMedic"), rs.getInt("existenciTot"),
                    rs.getDate("fechaElab"), rs.getDate("fechaExpira"), rs.getString("lote"));
            listMedicamentos.add(objMedicamento);
        }

    }

    public Medicamentos getOneMedicamento(ArrayList<Medicamentos> lisMedicamento, int id) {
        for (Medicamentos m : lisMedicamento) {
            if (m.getIdMedicamento() == id) {
                return m;
            }
        }
        return null;
    }

    public boolean modificarMedicamento(Medicamentos m) throws SQLException, ClassNotFoundException {
        int resultado = datMedicamento.modificarMediacamento(m);
        if (resultado == 0) {
            return false;
        } else {
            return true;
        }

    }

    public void insertarArticulo(Medicamentos objMedic) throws SQLException, ClassNotFoundException {
        datMedicamento.insertarMedicamento(objMedic);

    }

    public boolean eliminarMedicamento(String nombreMedic) {
        datMedicamento.eliminarMedicamento(nombreMedic);
        return true;
    }
}
