/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG.Sistema;

import DAT.SISTEMA.DATMedicamentos;
import ENT.Sistema.Compone;
import ENT.Sistema.Medicamentos;
import ENT.Sistema.Monodroga;
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

    public void insertarMedicamento(Medicamentos objMedic) throws SQLException, ClassNotFoundException {
        datMedicamento.insertarMedicamento(objMedic);

    }

    public boolean eliminarMedicamento(int idMedicamento) throws ClassNotFoundException, SQLException {
//        datMedicamento.eliminarMedicamento(idMedicamento);
        if (datMedicamento.eliminarMedicamento(idMedicamento) == 0) {
            return false;
        } else {
            return true;
        }
    }
//Monodrogas

    public void getAllMonodroga(ArrayList<Monodroga> listMonodroga) throws SQLException {
        ResultSet rs = datMedicamento.getAllMonodroga();
        while (rs.next()) {
            //Creamos el objeto monodroga
            Monodroga objMonodroga = new Monodroga(rs.getInt("idMonoDroga"), rs.getString("MonoDrogaNombre"));
            listMonodroga.add(objMonodroga);
        }
    }

    public Monodroga getOneMonodroga(ArrayList<Monodroga> listMonodroga, int id) {
        for (Monodroga m : listMonodroga) {
            if (m.getIdMonoDroga() == id) {
                return m;
            }
        }
        return null;
    }

    public void ingresarMonodroga(Monodroga objMonodroga) throws SQLException {
        datMedicamento.ingresarMonodroga(objMonodroga);
    }

    public boolean modificar(Monodroga objMonodroga) throws SQLException {
        int resultado = datMedicamento.modificarMonodroga(objMonodroga);
        if (resultado == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean eliminarMonodroga(int idMonodroga) throws SQLException, ClassNotFoundException {
        if (datMedicamento.eliminarMonodroga(idMonodroga) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void insertCompone(Compone objCompoene) throws SQLException, ClassNotFoundException {
        datMedicamento.insertCompone(objCompoene);

    }
    public void getAllCompone(ArrayList<Compone> listCompone) throws SQLException {
        ResultSet rs = datMedicamento.getAllCompone();
        while (rs.next()) {
            //Creamos el objeto monodroga
            Monodroga objMonodroga = new Monodroga(rs.getInt("idMonoDroga"), rs.getString("MonoDrogaNombre"));
            Medicamentos objMedicamentos = new Medicamentos();
            System.out.println("paso");
            objMedicamentos.setIdMedicamento(rs.getInt("idMedicamento"));
            System.out.println("no paso");
            objMedicamentos.setNombreMedic(rs.getString("nombreMedic"));
            Compone compone = new Compone(objMedicamentos, objMonodroga, rs.getString("porcentaje"));
            listCompone.add(compone);
        }
    }

}
