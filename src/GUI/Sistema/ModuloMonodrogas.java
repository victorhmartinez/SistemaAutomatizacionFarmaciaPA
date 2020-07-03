/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sistema;

import ENT.Sistema.Monodroga;
import LOG.Sistema.ObtenerMedicamentos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ModuloMonodrogas extends javax.swing.JFrame {

    /**
     * Creates new form Monodroga
     */
    ArrayList<Monodroga> listMonodrogas = new ArrayList<>();
    ObtenerMedicamentos obMedica = new ObtenerMedicamentos();
    DefaultTableModel dtm;
    int idSelect;

    public ModuloMonodrogas() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Monodrogas");
        cargarTabla();
    }

    private void cargarDatos(ArrayList<Monodroga> listMonodroga) {
        try {
            obMedica.getAllMonodroga(listMonodrogas);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Se producido un error al cagar los datos de la base", "ATENCION", JOptionPane.ERROR_MESSAGE);
        }
    }
private void cargarTabla(){
    cargarDatos(listMonodrogas);
     dtm = (DefaultTableModel) tblMonodrogas.getModel();
     Object[] tablFilas = new Object[dtm.getColumnCount()];
        for (Monodroga monodroga : listMonodrogas) {
            tablFilas[0]=monodroga.getIdMonoDroga();
            tablFilas[1] = monodroga.getMonoDrogaNombre();
             dtm.addRow(tablFilas);
        }
}
  private void limpiarTabla() {
        for (int i = 0; i < tblMonodrogas.getRowCount(); i++) {
            dtm.removeRow(i);
            i -= 1;
        }
    }
  private void limpiarCampos(){
      txtNombreMonodroga.setText("");
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreMonodroga = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnIngresarMonodroga = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMonodrogas = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(229, 240, 241));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Monodroga", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei UI", 0, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel1.setText("Nombre:");

        txtNombreMonodroga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMonodrogaActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Cancel2.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        btnIngresarMonodroga.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnIngresarMonodroga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/insert.png"))); // NOI18N
        btnIngresarMonodroga.setText("INGRESAR");
        btnIngresarMonodroga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarMonodrogaActionPerformed(evt);
            }
        });

        tblMonodrogas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        tblMonodrogas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonodrogasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMonodrogas);

        btnSalir.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/cancel.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/cancel.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/cancel.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreMonodroga)
                        .addGap(18, 18, 18)
                        .addComponent(btnIngresarMonodroga)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(15, 15, 15))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(33, 33, 33)
                .addComponent(btnSalir)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreMonodroga, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnIngresarMonodroga)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/medical.png"))); // NOI18N
        jLabel8.setText(" ");

        jLabel4.setBackground(new java.awt.Color(21, 52, 114));
        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Monodrogas");

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // TODO add your handling code here:
        txtNombreMonodroga.setText("");
        this.setVisible(false);

    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_btnSalirMouseClicked

    private void txtNombreMonodrogaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMonodrogaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMonodrogaActionPerformed

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarMouseClicked

    private void btnIngresarMonodrogaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarMonodrogaActionPerformed
        // TODO add your handling code here:
        Monodroga objMonodroga=new Monodroga(0, txtNombreMonodroga.getText());
        try {
            obMedica.ingresarMonodroga(objMonodroga);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo ingresar los datos","ATENCION",JOptionPane.ERROR_MESSAGE);
        }
           listMonodrogas.clear();
        //Limpiamos la tabla
        limpiarTabla();
        //Volvemos llenar la tabla con los datos
        cargarTabla();
        limpiarCampos();
    }//GEN-LAST:event_btnIngresarMonodrogaActionPerformed

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
           int descision = JOptionPane.showConfirmDialog(null, " Desea eliminar este Monodroga?");
            if (descision == JOptionPane.YES_OPTION) {
            try {
                if (obMedica.eliminarMonodroga(idSelect)) {
                    JOptionPane.showMessageDialog(null,"Monodroga Eliminado");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ModuloProductos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Error no se pudo eliminar","ATENCION",JOptionPane.ERROR_MESSAGE);
            }
            } else if (descision == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "OK");

            }  
       
        listMonodrogas.clear();
        //Limpiamos la tabla
        limpiarTabla();
        //Volvemos llenar la tabla con los datos
        cargarTabla();
         limpiarCampos();
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblMonodrogasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonodrogasMouseClicked
       Monodroga m;
        //Obtemos la fila a la q pertenece el medicamento
        int selection = tblMonodrogas.rowAtPoint(evt.getPoint());
        //Obtenemos el id del medicamento seleccionado
        String id = String.valueOf(tblMonodrogas.getValueAt(selection, 0));
        //Lo convertimos a  int para poder buscar
        idSelect = Integer.parseInt(id);
       
        //Obtemos el medicamento
        m = obMedica.getOneMonodroga(listMonodrogas, idSelect);
        //Presentamos los datos del medicamento en los txt
        txtNombreMonodroga.setText(m.getMonoDrogaNombre());
        
    }//GEN-LAST:event_tblMonodrogasMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
    Monodroga m;
        m = obMedica.getOneMonodroga(listMonodrogas, idSelect);
        //Establecemos los nuevos valores al medicamento
        m.setMonoDrogaNombre(txtNombreMonodroga.getText());
          int descision = JOptionPane.showConfirmDialog(null, " Desea editar esta Monodroga?");
          if (descision == JOptionPane.YES_OPTION){
        try {
            if (obMedica.modificar(m)) {
                JOptionPane.showMessageDialog(null,"Monodroga Modificada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo Modificar");
        }
          }else if (descision == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "OK");

            }
      listMonodrogas.clear();
        //Limpiamos la tabla
        limpiarTabla();
        //Volvemos llenar la tabla con los datos
        cargarTabla();
         limpiarCampos();
    }//GEN-LAST:event_btnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(ModuloMonodrogas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloMonodrogas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloMonodrogas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloMonodrogas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloMonodrogas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIngresarMonodroga;
    private javax.swing.JButton btnSalir;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMonodrogas;
    private javax.swing.JTextField txtNombreMonodroga;
    // End of variables declaration//GEN-END:variables
}
