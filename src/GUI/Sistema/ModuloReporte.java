/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sistema;

import ENT.Sistema.DetalleFactura;
import LOG.Sistema.LogReportes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
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
        
    }
 private void limpiarTabla() {
   
     model=(DefaultTableModel)tblReporte.getModel();
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
        jTextField1 = new javax.swing.JTextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 360, 30));

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
           total= logReportes.getTotal( fechaI, fechaF);
          
            total=Math.round(total * 100) / 100d;
             txtValorTotal.setText(String.valueOf(total));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo recuperar los datos"+ex);
        }
       
    }//GEN-LAST:event_jButtonGenerarActionPerformed

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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
