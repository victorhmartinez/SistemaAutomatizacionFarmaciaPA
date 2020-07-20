/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sistema;

import ENT.Sistema.Clientes;
import ENT.Sistema.DetalleFactura;
import ENT.Sistema.DetalleMedicamento;
import ENT.Sistema.Medicamentos;
import LOG.Sistema.LogClientes;
import LOG.Sistema.LogDetalle;
import LOG.Sistema.ObtenerMedicamentos;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import static com.itextpdf.text.Font.FontFamily.TIMES_ROMAN;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import static com.itextpdf.text.pdf.BaseFont.COURIER;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class Ventas extends javax.swing.JFrame {

    /**
     * Creates new form Ventas
     */
    ArrayList<Clientes> listaClientes = new ArrayList<>();
    ArrayList<Medicamentos> lisCarrito = new ArrayList<>();
    ArrayList<Medicamentos> lisMedicamentos = new ArrayList<>();
    ObtenerMedicamentos objMedicamentos = new ObtenerMedicamentos();
    LogDetalle objDetalle = new LogDetalle();
    LogClientes objLogCli = new LogClientes();

    DefaultTableModel dtm;
    TableRowSorter trs = null;
    int numDetalle;
    double subtotalT, totalT, iva;
    String idCliente;

    public Ventas() {
        initComponents();
        LocalDate fechaHoy = LocalDate.now();

        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/buy.png")).getImage());

        setTitle("Detalle de Venta");
        this.txtCedulaCliente.setEnabled(false);
        this.txtNombreCliente.setEnabled(false);
        this.txtCorreo.setEnabled(false);
        this.txtDireccion.setEnabled(false);
        this.txtFecha.setEnabled(false);
        txtFecha.setText(fechaHoy.toString());
        numDetalle = objDetalle.obtenerUltimaFactura() + 1;
        cargarMedicamentos(lisMedicamentos);
        llenarTablaClientes();
    }

    private void llenarTabla(ArrayList<Medicamentos> listMedicamentos) {

        dtm = (DefaultTableModel) tblDatMedicamento.getModel();
        Object[] tablFilas = new Object[dtm.getColumnCount()];
        for (Medicamentos medicamentos : listMedicamentos) {
            tablFilas[0] = medicamentos.getIdMedicamento();
            tablFilas[1] = medicamentos.getNombreMedic();
            tablFilas[2] = medicamentos.getExistenciTot();
            tablFilas[3] = medicamentos.getPrecioMedic();
            dtm.addRow(tablFilas);

        }
    }

    private void cargarMedicamentos(ArrayList<Medicamentos> listMedicamento) {
        try {
            objMedicamentos.getAllMedicamentos(listMedicamento);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se producido un error al cagar los datos de la base", "ATENCION", JOptionPane.ERROR_MESSAGE);
        }
        llenarTabla(listMedicamento);
    }

    private void cargarClientes(ArrayList<Clientes> listClientes) {
        try {
            objLogCli.obtenerTodosClientes(listClientes);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se producido un error al cagar "
                    + "los datos de los clientes", "ATENCION", JOptionPane.ERROR_MESSAGE);

        }

    }

    private void llenarTablaClientes() {
        DefaultTableModel modeloClientes;
        cargarClientes(listaClientes);
        modeloClientes = (DefaultTableModel) tblClientes.getModel();
        Object[] tablFilas = new Object[dtm.getColumnCount()];
        for (Clientes clientes : listaClientes) {
            tablFilas[0] = clientes.getIdentificacion();
            tablFilas[1] = clientes.getNombreCli();
            tablFilas[2] = clientes.getApellidoCli();
            modeloClientes.addRow(tablFilas);

        }
    }

      private void limpiarTablaClientes(){
       DefaultTableModel mdeloClientes;
       mdeloClientes=(DefaultTableModel)tblClientes.getModel();
       for (int i = 0; i < tblClientes.getRowCount(); i++) {
         
           mdeloClientes.removeRow(i); 
          
            i -= 1;
           
        }
}
//Modificar el estado de los productos
    private void modificarStock() {
        for (Medicamentos medicamentos : lisCarrito) {
            try {
                objMedicamentos.modificarMedicamento(medicamentos);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Hubo un error al registrar la venta", "ATENCION", JOptionPane.OK_OPTION);
            }
        }
    }
    //Limpiar tabla de medicamentos
   private void limpiarTablaMedicamentos() {
        for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
         
           dtm.removeRow(i); 
          
            i -= 1;
           
        }
    }
      //Enviar detalle
    private void setDetalle() {
        Clientes objClientes;
     objClientes = objLogCli.obtenerUnCliente(listaClientes, idCliente);
       
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;

        try {

            java.util.Date uDatete = fechaFormato.parse(txtFecha.getText());
            fecha = new Date(uDatete.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Fecha Incorrecta", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        DetalleFactura detalle = new DetalleFactura(numDetalle, fecha, Double.parseDouble(txtSubtotal.getText()), Double.parseDouble(txtTotal.getText()), 1, objClientes);
        try {
            objDetalle.insertDetalle(detalle);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
        }

    }
    //generacion de pdf
    public void generarPdf(String nombrePdf) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        
        FileOutputStream archivo = new FileOutputStream(nombrePdf + ".pdf");
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc,archivo);
            doc.open();
            
            //Logotipo
            Image objImg = Image.getInstance("src\\IMG\\Sistema\\LogoPrincipal.JPG");
            objImg.setAlignment(1);
            objImg.setWidthPercentage(100);
            doc.add(objImg);
            
            Font fontH1 = new Font(TIMES_ROMAN, 14, Font.BOLD);
            
            Font encabezado= new Font();
            encabezado.setFamily(COURIER);
            encabezado.setSize(14);
            
            Paragraph parrafo = new Paragraph("FARMACIA 'PRIMEROS AUXILIOS'", fontH1 );
            Paragraph parrafo2 = new Paragraph("Mercado Central junto al Hotel Karina, Macará", fontH1);
            Paragraph parrafo3 = new Paragraph("Venta al por mayor y menor", fontH1);
            Paragraph parrafo7 = new Paragraph("2694049 - 09996459734 ", fontH1);
            Paragraph parrafo4 = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            Paragraph parrafo5 = new Paragraph("DETALLE DE VENTA", encabezado);
            doc.add( Chunk.NEWLINE );
            parrafo.setAlignment(1);
            parrafo2.setAlignment(1);
            parrafo3.setAlignment(1);
            parrafo7.setAlignment(1);
            parrafo4.setAlignment(1);
            parrafo5.setAlignment(1);
            
            doc.add(parrafo);
            doc.add(parrafo2);
            doc.add(parrafo3);
            doc.add(parrafo7);
            doc.add(parrafo4);
            doc.add(parrafo5);
                 
            Paragraph saltoDeLinea = new Paragraph("                                                                                                                                                                                                                                                                                                                                                                                   ");
            doc.add(saltoDeLinea);          
            
            Font fuenteTxt= new Font();
            fuenteTxt.setSize(12);
            fuenteTxt.setColor(BaseColor.BLACK);
            fuenteTxt.setFamily(COURIER);
            doc.add(new Paragraph("Cedula: " + txtCedulaCliente.getText(),fuenteTxt));
            doc.add(new Paragraph("Nombre: " + txtNombreCliente.getText(),fuenteTxt));
            doc.add(new Paragraph("Dirección: " + txtDireccion.getText(),fuenteTxt));
            doc.add(new Paragraph("Correo: " + txtCorreo.getText(),fuenteTxt));
            doc.add(new Paragraph("Fecha: " + txtFecha.getText(),fuenteTxt));
            doc.add(new Phrase("\n")); 
            
            Paragraph parrafo6 = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            parrafo6.setAlignment(1);
            doc.add(parrafo6);
            doc.add(new Phrase("\n")); 

            //Table de Productos            
            Font fontH2= new Font(TIMES_ROMAN, 14, Font.BOLD);
            PdfPTable objTabelMed = new PdfPTable(5); 
            
            objTabelMed.addCell("CODIGO");
            objTabelMed.addCell("PRODUCTO");
            objTabelMed.addCell("CANTIDAD");
            objTabelMed.addCell("PRECIO UNIT");
            objTabelMed.addCell("TOTAL");
            for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
                String codigo = tblPreVenta.getValueAt(i,0).toString();
                String producto = tblPreVenta.getValueAt(i,1).toString();
                String cantidad = tblPreVenta.getValueAt(i,2).toString();
                String precioUnit = tblPreVenta.getValueAt(i,3).toString();
                String total = tblPreVenta.getValueAt(i,4).toString();
                
                objTabelMed.addCell(codigo);
                objTabelMed.addCell(producto);
                objTabelMed.addCell(cantidad);
                objTabelMed.addCell(precioUnit);
                objTabelMed.addCell(total);
                
            }
            objTabelMed.setWidthPercentage(90f);
            doc.add(objTabelMed);
            
            //TOTAL
            doc.add(new Phrase("\n")); 
            Paragraph parrafo8 = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            parrafo8.setAlignment((int) RIGHT_ALIGNMENT);
            doc.add(parrafo8);
            doc.add(new Phrase("\n")); 
            
            
            Font fuenteF= new Font();
            fuenteF.setSize(12);
            fuenteF.setColor(BaseColor.BLACK);
            fuenteF.setFamily(COURIER);
            Paragraph parrafo11 = new Paragraph("Subtotal: " + txtSubtotal.getText(),fuenteF);
            parrafo11.setAlignment(2);
            doc.add(parrafo11);
            Paragraph parrafo12 = new Paragraph("IVA 12%: " + txtIVA.getText(),fuenteF);
            parrafo12.setAlignment(2);
            doc.add(parrafo12);
            Paragraph parrafo13 = new Paragraph("Total: " + txtTotal.getText(),fuenteF);
            parrafo13.setAlignment(2);
            doc.add(parrafo13);
 
            //FOOTER
            Font fontH3 = new Font(TIMES_ROMAN, 14, Font.ITALIC);
            doc.add(new Phrase("\n")); 
            Paragraph parrafo9 = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            parrafo9.setAlignment((int) RIGHT_ALIGNMENT);
            doc.add(parrafo9);
            doc.add(new Phrase("\n")); 
            Paragraph parrafo10 = new Paragraph("SERA UN PLACER VOLVERLE ATENDER", fontH3);
            parrafo10.setAlignment(1);
            doc.add(parrafo10);
            //CERRAR          
            doc.close();
            JOptionPane.showMessageDialog(null, "Detalle de venta creado correctamente", "Información",1 );
        
    }
   private void limpiar() {
        txtCedulaCliente.setText("");
        txtNombreCliente.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtSubtotal.setText("");
        lisCarrito.clear();
        txtIVA.setText("");
        txtTotal.setText("");
        totalT=0.0;
        iva=0.0;
        subtotalT=0.0;

    }
//Realizar venta
    private void realizarVenta() {
        DefaultTableModel dtm;
        dtm = (DefaultTableModel) tblPreVenta.getModel();
        for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
            llenarCarrito(i);
            dtm.removeRow(i);
            i -= 1;

        }
    }
    //Llenado de carrito
    private void llenarCarrito(int fila) {
        Medicamentos m;
        String idProd = String.valueOf(tblPreVenta.getValueAt(fila, 0));
        int idSelect = Integer.parseInt(idProd);
        m = objMedicamentos.getOneMedicamento(lisMedicamentos, idSelect);
        int cant = Integer.parseInt(String.valueOf(tblPreVenta.getValueAt(fila, 2)));
        m.setExistenciTot(m.getExistenciTot() - cant);
        DetalleMedicamento dtMedicamento = new DetalleMedicamento(m.getIdMedicamento(), numDetalle, cant);
       
        try {
            objDetalle.inserDetalleMedic(dtMedicamento);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR DM"+ex);
        }
        lisCarrito.add(m);

    }
        public boolean seRepite(Medicamentos m) {
        if (tblPreVenta.getRowCount() > 0) {
            for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
                if (m.getIdMedicamento() == (Integer.parseInt(tblPreVenta.getValueAt(i, 0).toString()))) {
                    //break;
                    return true;
                }
            }
        }
        return false;
    }

    public int obtenerFilaR(Medicamentos m) {
        int var = 0;
        if (tblPreVenta.getRowCount() > 0) {
            for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
                if (m.getIdMedicamento() == (Integer.parseInt(tblPreVenta.getValueAt(i, 0).toString()))) {
                    var = i;
                    break;
                }
            }

        }
        return var;
    }

    public int obtenerCantidadA(Medicamentos m) {
        int var = 0;
        if (tblPreVenta.getRowCount() > 0) {
            for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
                if (m.getIdMedicamento() == (Integer.parseInt(tblPreVenta.getValueAt(i, 0).toString()))) {
                    var = Integer.parseInt(tblPreVenta.getValueAt(i, 2).toString());
                    break;
                }
            }

        }
        return var;
    }

    public double obtenerTotalA(Medicamentos m) {
        double var = 0;
        if (tblPreVenta.getRowCount() > 0) {
            for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
                if (m.getIdMedicamento() == (Integer.parseInt(tblPreVenta.getValueAt(i, 0).toString()))) {
                    var = Double.parseDouble(tblPreVenta.getValueAt(i, 4).toString());
                    break;
                }
            }

        }
        return var;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mostrarClientes = new javax.swing.JDialog();
        jLabelRegistro = new javax.swing.JLabel();
        jButtonBuscador = new javax.swing.JButton();
        jLabelNombre = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        txtCedulaBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel2Fondo = new javax.swing.JLabel();
        mostrarMedicamentos = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarMedicamento = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDatMedicamento = new javax.swing.JTable();
        txtCant = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblFondo2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        btnBuscarMedicamento = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPreVenta = new javax.swing.JTable();
        btnRealizarVenta = new javax.swing.JButton();
        btnEliminarP = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        mostrarClientes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRegistro.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRegistro.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelRegistro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRegistro.setText("CLIENTES REGISTRADOS");
        mostrarClientes.getContentPane().add(jLabelRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, 30));

        jButtonBuscador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/searchperson.png"))); // NOI18N
        jButtonBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscadorActionPerformed(evt);
            }
        });
        mostrarClientes.getContentPane().add(jButtonBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 40, 30));

        jLabelNombre.setBackground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setText("Cédula");
        mostrarClientes.getContentPane().add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 180, 40));

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/new1.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        mostrarClientes.getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, -1, -1));
        mostrarClientes.getContentPane().add(txtCedulaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 450, 30));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificacion", "Nombre", "Apellido"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        mostrarClientes.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 530, 180));

        jLabel2Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Fondo.jpg"))); // NOI18N
        jLabel2Fondo.setText("jLabel2");
        mostrarClientes.getContentPane().add(jLabel2Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-220, -110, 1030, 670));

        mostrarMedicamentos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarMedicamentoActionPerformed(evt);
            }
        });
        txtBuscarMedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarMedicamentoKeyTyped(evt);
            }
        });
        jPanel4.add(txtBuscarMedicamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 420, 30));

        tblDatMedicamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Nombre", "Cant.Disponible", "Precio"
            }
        ));
        jScrollPane3.setViewportView(tblDatMedicamento);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 450, 200));
        jPanel4.add(txtCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 50, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/accept.png"))); // NOI18N
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 120, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/search.png"))); // NOI18N
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 30, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Productos Disponibles");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 410, 50));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setText("Cant.");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 50, 40));

        lblFondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Fondo.jpg"))); // NOI18N
        jPanel4.add(lblFondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 440));

        mostrarMedicamentos.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/ventas.png"))); // NOI18N
        jLabel8.setText(" ");

        jLabel9.setBackground(new java.awt.Color(21, 52, 114));
        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("FARMACIA PRIMEROS AUXILIOS");

        jLabel10.setBackground(new java.awt.Color(21, 52, 114));
        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("AL POR MAYOR Y MENOR");

        jLabel4.setBackground(new java.awt.Color(21, 52, 114));
        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("VENTA DE MEDICAMENTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel8)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei UI", 0, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel1.setText("DNI Cliente:");

        txtCedulaCliente.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel3.setText("Correo:");

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel5.setText("Fecha:");

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel6.setText("Nombre Cliente:");

        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel11.setText("Direccion:");

        jLabel12.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel12.setText("BUSCAR MEDICAM:");

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel7.setText("BUSCAR CLIENTE:");

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/searchperson.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnBuscarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/searchmedicine.png"))); // NOI18N
        btnBuscarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(47, 47, 47)
                        .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel6)
                        .addGap(34, 34, 34)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(121, 121, 121)
                                .addComponent(jLabel7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121)
                                .addComponent(jLabel12)))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnBuscarCliente)
                        .addGap(11, 11, 11)
                        .addComponent(btnBuscarMedicamento)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPreVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "CANT", "PRECIO UNIT", "TOTAL"
            }
        ));
        tblPreVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPreVenta);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 741, 96));

        btnRealizarVenta.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnRealizarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/vender.png"))); // NOI18N
        btnRealizarVenta.setText("REALIZAR VENTA");
        btnRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarVentaActionPerformed(evt);
            }
        });
        jPanel3.add(btnRealizarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 115, -1, -1));

        btnEliminarP.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnEliminarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/eliminarventa.png"))); // NOI18N
        btnEliminarP.setText("ELIMINAR PRODUCTO");
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 115, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/cancelventa.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 115, -1, -1));

        txtSubtotal.setEditable(false);
        jPanel3.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 115, 67, 27));

        txtIVA.setEditable(false);
        jPanel3.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 153, 67, 27));

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel3.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 186, 67, 27));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Subtotal");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 115, 57, 27));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("I.V.A");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 153, 57, 27));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Total");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 186, 57, 27));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscadorActionPerformed
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jButtonBuscadorActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:

          ModuloClientes mc = new ModuloClientes (txtCedulaBuscar.getText());
        mc.setVisible(true);
        listaClientes.clear();
        limpiarTablaClientes();
        llenarTablaClientes();
        mostrarClientes.dispose();

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked

         Clientes objClientes;
        //Obtemos la fila a la q pertenece el medicamento
        int selection = tblClientes.rowAtPoint(evt.getPoint());
//        Obtenemos el id del medicamento seleccionado
        idCliente = String.valueOf(tblClientes.getValueAt(selection, 0));
        //Lo convertimos a  int para poder buscar
        //Obtemos el medicamento
        objClientes = objLogCli.obtenerUnCliente(listaClientes, idCliente);
//        Presentamos los datos del medicamento en los txt
        txtCedulaCliente.setText(objClientes.getIdentificacion());
        txtNombreCliente.setText(objClientes.getNombreCli() +" "+ objClientes.getApellidoCli());
        txtDireccion.setText(objClientes.getDireccion().getCalleP()+","+objClientes.getDireccion().getCalleS()+"/"+objClientes.getDireccion().getCiudad());
        txtCorreo.setText(objClientes.getContactos().getCorreo());
        
         JOptionPane.showMessageDialog(null,"Cliente Agregado");
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMedicamentoActionPerformed

    private void txtBuscarMedicamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedicamentoKeyTyped
        txtBuscarMedicamento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //Designamos a la fila en comluna en la que queremos buscar en este caso por nombre "1" y con lo debe compara
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarMedicamento.getText(), 1));
            }

        });
        trs = new TableRowSorter(dtm);
        tblDatMedicamento.setRowSorter(trs);
    }//GEN-LAST:event_txtBuscarMedicamentoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           int auxF = 0;
        int auxC = 0;
        double auxT = 0.0;
        int fsl = tblDatMedicamento.getSelectedRow();
        int cant = 0;
        int idSelect;
        DefaultTableModel model;
        double x = 0.0;
        String codigo, nombre, cantP, precioUn, totP;

        Medicamentos m;
        if (fsl == -1) {
            JOptionPane.showConfirmDialog(null, "Debe seleccionar un producto");

        } else {
            if (txtCant.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Se debe ingresar la cantidad");
            } else {
                cant = Integer.parseInt(txtCant.getText());
                idSelect = Integer.parseInt(tblDatMedicamento.getValueAt(fsl, 0).toString());
                m = objMedicamentos.getOneMedicamento(lisMedicamentos, idSelect);
                auxC = cant + obtenerCantidadA(m);
                auxT = obtenerTotalA(m);

                if (objDetalle.cantDisponible(m, cant)) {
                    codigo = String.valueOf(m.getIdMedicamento());
                    nombre = m.getNombreMedic();
                    cantP = String.valueOf(auxC);
                    precioUn = String.valueOf(m.getPrecioMedic());
                    x = (Double.parseDouble(precioUn)) * cant;
                    x = (Math.round(x * 100) / 100d) + auxT;

                    totP = String.valueOf(x);

                    if (seRepite(m) == true) {
                        auxF = obtenerFilaR(m);

                        System.out.println(auxF);
                        tblPreVenta.setValueAt(codigo, auxF, 0);
                        tblPreVenta.setValueAt(nombre, auxF, 1);
                        tblPreVenta.setValueAt(cantP, auxF, 2);
                        tblPreVenta.setValueAt(precioUn, auxF, 3);
                        tblPreVenta.setValueAt(totP, auxF, 4);
                    } else {
                        //Añadimos los datos a la tabla preventa
                        model = (DefaultTableModel) tblPreVenta.getModel();
                        String filaEle[] = {codigo, nombre, cantP, precioUn, totP};
                        model.addRow(filaEle);
                    }
                    auxC = cant + auxC;
                    //System.out.println("cantidad "+auxC);
                    //Calculamos los valores totales de la venta
                    //Quitar calculados
                    Double p = 0.0;
                    Double ivas = 0.0;
                    Double t = 0.0;
                    if (tblPreVenta.getRowCount() > 0) {
                        for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
                            p = Double.parseDouble(tblPreVenta.getValueAt(i, 4).toString());
                            t += p;
                        }

                    }
                    totalT = t;
                    totalT = Math.round(totalT * 100) / 100d;
                    ivas = totalT * 0.12;
                    iva = ivas;
                    iva = Math.round(iva * 100) / 100d;
                    subtotalT = totalT - ivas;
                    subtotalT = Math.round(subtotalT * 100) / 100d;
                    //Presentamos datos en los txt
                    txtSubtotal.setText(String.valueOf(subtotalT));
                    txtIVA.setText(String.valueOf(iva));
                    txtTotal.setText(String.valueOf(totalT));

                }
            }
        }
        txtCant.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed

        // TODO add your handling code here:
    
        mostrarClientes.setSize(800, 490);
        mostrarClientes.setLocationRelativeTo(null);
        mostrarClientes.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/cliente.png")).getImage());
        mostrarClientes.setModal(true);
        mostrarClientes.setVisible(true);

    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicamentoActionPerformed
        // TODO add your handling code here:

        mostrarMedicamentos.setSize(580, 475);
        mostrarMedicamentos.setLocationRelativeTo(null);
        mostrarMedicamentos.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/medicamentos.png")).getImage());
        mostrarMedicamentos.setModal(true);
        mostrarMedicamentos.setVisible(true);
    }//GEN-LAST:event_btnBuscarMedicamentoActionPerformed

    private void tblPreVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreVentaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblPreVentaMouseClicked

    private void btnRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarVentaActionPerformed
if(txtCedulaCliente.getText().equals("")){
    JOptionPane.showMessageDialog(null,"Debe ingresar todos los datos del cliente");
}else{
    

       Calendar cl = Calendar.getInstance();
        int hora, minuto, seg;
        hora = cl.get(Calendar.HOUR_OF_DAY);
        minuto = cl.get(Calendar.MINUTE);
        seg = cl.get(Calendar.SECOND);
        String cadena = "" + hora + minuto + seg;
        try {
            generarPdf(txtCedulaCliente.getText() + "_" + txtNombreCliente.getText() + "_" + cadena);
        } catch (FileNotFoundException ex) {

        } catch (DocumentException | IOException ex) {

        }
        setDetalle();
        realizarVenta();
        objDetalle.imprimirCarrito(lisCarrito);

        modificarStock();
        limpiar();
        lisMedicamentos.clear();
        limpiarTablaMedicamentos();
        cargarMedicamentos(lisMedicamentos);

        numDetalle = objDetalle.obtenerUltimaFactura() + 1;
        JOptionPane.showMessageDialog(null, "Venta Exitosa");
      } 
    }//GEN-LAST:event_btnRealizarVentaActionPerformed

    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
          if (tblPreVenta.getSelectedRow() != -1) {
            int descision = JOptionPane.showConfirmDialog(null, " Desea eliminar este producto?");
            if (descision == JOptionPane.YES_OPTION) {
                //idSelect = tblPreVenta.getSelectedRow();
                DefaultTableModel modeloPreventa = (DefaultTableModel) tblPreVenta.getModel();
                modeloPreventa.removeRow(tblPreVenta.getSelectedRow());
    }                                            
        }
        //Quitar calculados
        Double p = 0.0;
        Double ivas = 0.0;
        Double t = 0.0;
        if (tblPreVenta.getRowCount() > 0) {
            for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
                p = Double.parseDouble(tblPreVenta.getValueAt(i, 4).toString());
                t +=p;
            }

        }
        totalT = t;
        totalT = Math.round(totalT * 100) / 100d;
        ivas = totalT * 0.12;
        iva = ivas;
        iva = Math.round(iva * 100) / 100d;
        subtotalT = totalT - ivas;
        subtotalT = Math.round(subtotalT * 100) / 100d;
        //Presentamos datos en los txt
        txtSubtotal.setText(String.valueOf(subtotalT));
        txtIVA.setText(String.valueOf(iva));
        txtTotal.setText(String.valueOf(totalT));
    }//GEN-LAST:event_btnEliminarPActionPerformed

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // TODO add your handling code here:
        txtCedulaCliente.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtFecha.setText("");
        txtNombreCliente.setText("");

    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
         DefaultTableModel dm = (DefaultTableModel) tblPreVenta.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
        limpiar();
        JOptionPane.showMessageDialog(null, "Proceso cancelado");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarMedicamento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarP;
    private javax.swing.JButton btnRealizarVenta;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBuscador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel2Fondo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelRegistro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFondo2;
    private javax.swing.JDialog mostrarClientes;
    private javax.swing.JDialog mostrarMedicamentos;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblDatMedicamento;
    private javax.swing.JTable tblPreVenta;
    private javax.swing.JTextField txtBuscarMedicamento;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCedulaBuscar;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
