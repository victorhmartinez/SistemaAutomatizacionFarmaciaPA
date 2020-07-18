/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sistema;

import ENT.Sistema.DetalleFactura;
import LOG.Sistema.LogReportes;
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
import com.toedter.calendar.JDateChooser;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christian
 */
public class ModuloReporte extends javax.swing.JFrame {

    /**
     * Creates new form ModuloReporte
     */
    LogReportes logReportes = new LogReportes();
    ArrayList<DetalleFactura> listReportes = new ArrayList<>();
    DefaultTableModel model;
     
    public ModuloReporte() {
        initComponents();
        jLabelFondo.setSize(500, 490);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/seo-report.png")).getImage());
    }

    private void limpiarTabla() {

        model = (DefaultTableModel) tblReporte.getModel();
        for (int i = 0; i < tblReporte.getRowCount(); i++) {
            model.removeRow(i);

            i -= 1;

        }
    }
    
    

    private void cargarDatos(java.sql.Date fechaI, java.sql.Date fechaF, ArrayList<DetalleFactura> lisDetalle) {
        try {
            logReportes.getReportes(lisDetalle, fechaI, fechaF);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos" + ex);
        }
    }

    public void cargarDatosTabla(ArrayList<DetalleFactura> lista, java.sql.Date fechaI, java.sql.Date fechaF) {
        DefaultTableModel dtm;
        cargarDatos(fechaI, fechaF, lista);
        dtm = (DefaultTableModel) tblReporte.getModel();
        Object[] tblaFilas = new Object[dtm.getColumnCount()];
        for (DetalleFactura reporte : listReportes) {
            tblaFilas[0] = reporte.getIdDetalle();
            tblaFilas[1] = reporte.getCliente().getNombreCli();
            tblaFilas[2] = reporte.getCliente().getApellidoCli();
            tblaFilas[3] = reporte.getFecha();
            tblaFilas[4] = reporte.getSubtotal();
            tblaFilas[5] = reporte.getTotal();
            dtm.addRow(tblaFilas);
        }
    }
    public String obtenerFechas(JDateChooser fechaEntrada){
        Date fecha = fechaEntrada.getDate();
        DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String fecha2 = f.format(fecha);
        return fecha2;
    }

    public void generarPdf(String nombrePdf) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        FileOutputStream archivo = new FileOutputStream(nombrePdf + ".pdf");
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, archivo);
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
            Paragraph parrafo5 = new Paragraph("REPORTE DE VENTAS REALIZADAS", encabezado);
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
            doc.add(new Paragraph("Asunto: " + txtAsunto.getText(),fuenteTxt));
            doc.add(new Paragraph("Reporte desde: " + obtenerFechas(fechaInicio),fuenteTxt));
            doc.add(new Paragraph("Reporte hasta: " + obtenerFechas(fechaFin),fuenteTxt));
            doc.add(new Phrase("\n")); 
            
            Paragraph parrafo6 = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            parrafo6.setAlignment(1);
            doc.add(parrafo6);
            doc.add(new Phrase("\n")); 
            
            //Table de Productos            
            Font fontH2= new Font(TIMES_ROMAN, 14, Font.BOLD);
            PdfPTable objTabelReporV = new PdfPTable(6);
            
            objTabelReporV.addCell("Nro Detalle");
            objTabelReporV.addCell("Nombres Cliente");
            objTabelReporV.addCell("Apellidos Cliente");
            objTabelReporV.addCell("Fecha");
            objTabelReporV.addCell("Subtotal");
            objTabelReporV.addCell("Total");
            
            for (int i = 0; i < tblReporte.getRowCount(); i++) {
                String nroDetalle = tblReporte.getValueAt(i,0).toString();
                String nombreCli = tblReporte.getValueAt(i,1).toString();
                String apelliCli = tblReporte.getValueAt(i,2).toString();
                String fechaDet = tblReporte.getValueAt(i,3).toString();
                String subCompra = tblReporte.getValueAt(i,4).toString();
                String totCompra = tblReporte.getValueAt(i,5).toString();
                
                objTabelReporV.addCell(nroDetalle);
                objTabelReporV.addCell(nombreCli);
                objTabelReporV.addCell(apelliCli);
                objTabelReporV.addCell(fechaDet);
                objTabelReporV.addCell(subCompra);
                objTabelReporV.addCell(totCompra);
            }
            objTabelReporV.setWidthPercentage(90f);
            doc.add(objTabelReporV);
            
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
            Paragraph parrafo11 = new Paragraph("Valor Recaudado : " + txtValorTotal.getText(),fuenteF);
            parrafo11.setAlignment(2);
            doc.add(parrafo11);
            
            //FOOTER
            Font fontH3 = new Font(TIMES_ROMAN, 14, Font.ITALIC);
            doc.add(new Phrase("\n")); 
            Paragraph parrafo9 = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            parrafo9.setAlignment((int) RIGHT_ALIGNMENT);
            doc.add(parrafo9);
            doc.add(new Phrase("\n")); 
            Paragraph parrafo10 = new Paragraph("INFORME VÁLIDO PARA ADMINISTRACIÓN", fontH3);
            parrafo10.setAlignment(1);
            doc.add(parrafo10);
            //CERRAR          
            doc.close();
            JOptionPane.showMessageDialog(null, "Reporte generado correctamente", "Información",1 );
            

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabeltITULO = new javax.swing.JLabel();
        jLabelAsunto = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        jLabelFechaFin = new javax.swing.JLabel();
        jButtonGenerar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        txtValorTotal = new javax.swing.JTextField();
        jLabelFechaFin1 = new javax.swing.JLabel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabeltITULO.setBackground(new java.awt.Color(255, 255, 255));
        jLabeltITULO.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabeltITULO.setForeground(new java.awt.Color(255, 255, 255));
        jLabeltITULO.setText("REPORTE");
        getContentPane().add(jLabeltITULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabelAsunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelAsunto.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsunto.setText("ASUNTO:");
        getContentPane().add(jLabelAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));
        getContentPane().add(txtAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 360, 30));

        jLabelFechaFin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelFechaFin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaFin.setText("FECHA DE FINALIZACION:");
        getContentPane().add(jLabelFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, -1, -1));

        jButtonGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/new1.png"))); // NOI18N
        jButtonGenerar.setText("Consultar");
        jButtonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, -1, 50));

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/pastilla.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGuardarMouseClicked(evt);
            }
        });
        getContentPane().add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 140, 50));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FECHA DE INICIO:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        fechaFin.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 170, 30));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, -1));

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Detalle", "Nombre Cliente", "Apellido Cliente", "Fecha Detalle", "Subtotal Compra", "Total Compra"
            }
        ));
        jScrollPane2.setViewportView(tblReporte);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 700, 150));

        txtValorTotal.setEditable(false);
        txtValorTotal.setText(" ");
        getContentPane().add(txtValorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(629, 400, 110, 30));

        jLabelFechaFin1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFechaFin1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaFin1.setText("Valor total de venta");
        getContentPane().add(jLabelFechaFin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 397, 190, 30));

        fechaInicio.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 170, 30));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Fondo.jpg"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-430, -150, 1290, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed
        // TODO add your handling code here:
        listReportes.clear();
        limpiarTabla();
        txtValorTotal.setText("");
        Date DfechaInicio = this.fechaInicio.getDate();
        double total = 0;
        long dI = DfechaInicio.getTime();
        java.sql.Date fechaI = new java.sql.Date(dI);
        Date DfechaFin = this.fechaFin.getDate();
        long dF = DfechaFin.getTime();
        java.sql.Date fechaF = new java.sql.Date(dF);
        cargarDatosTabla(listReportes, fechaI, fechaF);
        //        for (DetalleFactura reporte : listReportes) {
//            System.out.println(reporte.getIdDetalle()+"_"+reporte.getFecha()+"_"+reporte.getSubtotal()+reporte.getTotal()
//            +"_"+reporte.getCliente().getNombreCli()+"_"+reporte.getCliente().getApellidoCli());
//        }
        try {
            total = logReportes.getTotal(fechaI, fechaF);

            total = Math.round(total * 100) / 100d;
            txtValorTotal.setText(String.valueOf(total));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo recuperar los datos" + ex);
        }

    }//GEN-LAST:event_jButtonGenerarActionPerformed

    private void jButtonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseClicked
        // TODO add your handling code here:
        /*Date fecha = fechaInicio.getDate();
        DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String fecha2 = f.format(fecha);
        System.out.println(txtAsunto.getText()+"_"+fecha2+"_");*/
        
        Calendar cl = Calendar.getInstance();
        int hora, minuto, seg;
        hora = cl.get(Calendar.HOUR_OF_DAY);
        minuto = cl.get(Calendar.MINUTE);
        seg = cl.get(Calendar.SECOND);
        String cadena = "" + hora + minuto + seg;
        try {
            generarPdf(txtAsunto.getText() + "_" + obtenerFechas(fechaInicio)+ "_" + cadena);
        } catch (FileNotFoundException ex) {
           
        } catch (DocumentException | IOException ex) {
          
        }
    }//GEN-LAST:event_jButtonGuardarMouseClicked

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
            java.util.logging.Logger.getLogger(ModuloReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloReporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAsunto;
    private javax.swing.JLabel jLabelFechaFin;
    private javax.swing.JLabel jLabelFechaFin1;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabeltITULO;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
