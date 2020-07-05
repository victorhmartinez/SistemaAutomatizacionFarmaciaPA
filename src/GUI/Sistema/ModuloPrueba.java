/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sistema;
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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import static com.itextpdf.text.pdf.BaseFont.COURIER;
import static com.itextpdf.text.pdf.BaseFont.COURIER_BOLD;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class ModuloPrueba extends javax.swing.JFrame {

    private String Courier_New;

    /**
     * Creates new form ModuloPrueba
     */
    public ModuloPrueba() {
        initComponents();
    }
    public void generarPdf(String nombrePdf) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        if(!(txtNombreCliente.getText().isEmpty())){
            
            FileOutputStream archivo = new FileOutputStream(nombrePdf + ".pdf");
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc,archivo);
            doc.open();
            
            //Logotipo
            Image objImg = Image.getInstance("D:\\Documentos\\GP8vo\\Metodologias Agiles\\SistemaAutomatizacionFarmaciaPA\\src\\IMG\\Sistema\\LogoPrincipal.JPG");
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
            
            BaseFont bf = BaseFont.createFont(
                           BaseFont.TIMES_ROMAN,
                           BaseFont.CP1252,
                           BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);
            
            
            PdfPTable table = new PdfPTable(1);

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
            Paragraph parrafo13 = new Paragraph("Subtotal: " + txtSubtotal.getText(),fuenteF);
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
            
        }else{
            JOptionPane.showMessageDialog(null, "Llene los datos no sea tonto", "Información", 2);
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGenerar = new javax.swing.JButton();
        txtNombreCliente = new javax.swing.JTextField();
        txtCedulaCliente = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPreVenta = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGenerar.setText("GENERAR PDF");
        btnGenerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenerarMouseClicked(evt);
            }
        });
        getContentPane().add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, -1, -1));
        getContentPane().add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 280, -1));

        txtCedulaCliente.setToolTipText("");
        getContentPane().add(txtCedulaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 136, -1));
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 211, -1));
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 211, -1));
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 211, -1));

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel1.setText("DNI Cliente:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel11.setText("Direccion:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 113, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel3.setText("Correo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 113, -1));

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel5.setText("Fecha:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 62, -1));

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel6.setText("Nombre Cliente:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, -1));

        tblPreVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"111", "alcohol", "2", "1.30", "2.60"},
                {"222", "guantes", "2", "0.25", "0.50"},
                {"333", "mascarilla", "5", "1.00", "5.00"}
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 741, 96));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Subtotal");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 330, 57, 27));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("I.V.A");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, 57, 27));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Total");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, 57, 27));
        getContentPane().add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 140, -1));
        getContentPane().add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 150, -1));
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarMouseClicked
        try{
            generarPdf(txtNombreCliente.getText());
        }catch(FileNotFoundException ex){
            Logger.getLogger(ModuloPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }catch(DocumentException ex){
            Logger.getLogger(ModuloPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModuloPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGenerarMouseClicked

    private void tblPreVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreVentaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblPreVentaMouseClicked

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
            java.util.logging.Logger.getLogger(ModuloPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloPrueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPreVenta;
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
