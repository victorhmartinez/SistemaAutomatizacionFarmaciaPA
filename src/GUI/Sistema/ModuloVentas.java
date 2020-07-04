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
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class ModuloVentas extends javax.swing.JFrame {

    /**
     * Creates new form ModuloProductos
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
    double subtotalT,totalT,iva;
    String idCliente;

    public ModuloVentas() {
        initComponents();
        //Línea 1
        LocalDate fechaHoy = LocalDate.now();
        this.setSize(new Dimension(790, 650));
        setLocationRelativeTo(null);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Detalle de Venta");
        this.setMinimumSize(new Dimension(200, 200));
        this.txtCedulaCliente.setEnabled(false);
        this.txtNombreCliente.setEnabled(false);
        this.txtCorreo.setEnabled(false);
        this.txtDireccion.setEnabled(false);
        this.txtFecha.setEnabled(false);
        numDetalle = objDetalle.obtenerUltimaFactura()+1;
        cargarMedicamentos(lisMedicamentos);
        txtFecha.setText(fechaHoy.toString());
        llenarTablaClientes();
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
            System.out.println(e);
        }

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

    private void limpiar() {
        txtCedulaCliente.setText("");
        txtNombreCliente.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtSubtotal.setText("");
        txtIVA.setText("");
        txtTotal.setText("");

    }

    private void realizarVenta() {
        DefaultTableModel dtm;
        dtm = (DefaultTableModel) tblPreVenta.getModel();
        for (int i = 0; i < tblPreVenta.getRowCount(); i++) {
            llenarCarrito(i);
            dtm.removeRow(i);
            i -= 1;

        }
    }

    private void llenarCarrito(int fila) {
        Medicamentos m;
        String idProd = String.valueOf(tblPreVenta.getValueAt(fila, 0));
        int idSelect = Integer.parseInt(idProd);
        m = objMedicamentos.getOneMedicamento(lisMedicamentos, idSelect);
        int cant = Integer.parseInt(String.valueOf(tblPreVenta.getValueAt(fila, 2)));
        m.setExistenciTot(m.getExistenciTot() - cant);
        DetalleMedicamento dtMedicamento = new DetalleMedicamento(m.getIdMedicamento(), 1, cant);
        System.out.println("Detalles: "+dtMedicamento);
        try {
            objDetalle.inserDetalleMedic(dtMedicamento);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR DM"+ex);
        }
        lisCarrito.add(m);

    }
    private void llenarTablaClientes() {
        cargarClientes(listaClientes);
        dtm = (DefaultTableModel) tblClientes.getModel();
        Object[] tablFilas = new Object[dtm.getColumnCount()];
        for (Clientes clientes : listaClientes) {
            tablFilas[0] = clientes.getIdentificacion();
            tablFilas[1] = clientes.getNombreCli();
            tablFilas[2] = clientes.getApellidoCli();
            dtm.addRow(tablFilas);

        }
    }

    private void modificarStock() {
        for (Medicamentos medicamentos : lisCarrito) {
            try {
                objMedicamentos.modificarMedicamento(medicamentos);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Hubo un error al registrar la venta", "ATENCION", JOptionPane.OK_OPTION);
            }
        }
    }

    private void setDetalle() {
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;

        try {

            java.util.Date uDatete = fechaFormato.parse(txtFecha.getText());
            fecha = new Date(uDatete.getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fecha Incorrecta", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        DetalleFactura detalle = new DetalleFactura(numDetalle, fecha, Double.parseDouble(txtSubtotal.getText()), Double.parseDouble(txtTotal.getText()), 1, 1);
        try {
            objDetalle.insertDetalle(detalle);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
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

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jDialog1 = new javax.swing.JDialog();
        jLabelRegistro = new javax.swing.JLabel();
        jButtonBuscador = new javax.swing.JButton();
        jLabelNombre = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
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
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        btnBuscarMedicamento = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPreVenta = new javax.swing.JTable();
        btnRealizarVenta = new javax.swing.JButton();
        btnEditarVenta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnImprimirVenta = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRegistro.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRegistro.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelRegistro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRegistro.setText("CLIENTES REGISTRADOS");
        jDialog1.getContentPane().add(jLabelRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, 30));

        jButtonBuscador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/searchperson.png"))); // NOI18N
        jDialog1.getContentPane().add(jButtonBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 40, 30));

        jLabelNombre.setBackground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setText("Cédula");
        jDialog1.getContentPane().add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 180, 40));

        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/accept.png"))); // NOI18N
        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, -1, -1));
        jDialog1.getContentPane().add(txtCedulaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 450, 30));

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

        jDialog1.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 530, 180));

        jLabel2Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Fondo.jpg"))); // NOI18N
        jLabel2Fondo.setText("jLabel2");
        jDialog1.getContentPane().add(jLabel2Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-220, -110, 1030, 670));

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane1.setBackground(new java.awt.Color(229, 240, 241));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(800, 600));
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(21, 52, 114));
        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("VENTA DE MEDICAMENTOS");
        jDesktopPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei UI", 0, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel1.setText("DNI Cliente:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 31, -1, -1));

        txtCedulaCliente.setToolTipText("");
        jPanel2.add(txtCedulaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 30, 136, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel3.setText("Correo:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 97, 113, -1));

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel5.setText("Fecha:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 135, 62, -1));
        jPanel2.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 96, 211, -1));
        jPanel2.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 134, 211, -1));

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel6.setText("Nombre Cliente:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 31, -1, -1));
        jPanel2.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 30, 268, -1));

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/searchperson.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 79, 81, -1));

        btnBuscarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/searchmedicine.png"))); // NOI18N
        btnBuscarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicamentoActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarMedicamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 131, 81, -1));

        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel11.setText("Direccion:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 113, -1));
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 211, -1));

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel7.setText("BUSCAR CLIENTE:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 89, -1, -1));

        jLabel12.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel12.setText("BUSCAR MEDICAM:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 140, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 760, 190));

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

        btnEditarVenta.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnEditarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/eliminarventa.png"))); // NOI18N
        btnEditarVenta.setText("ELIMINAR PRODUCTO");
        btnEditarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarVentaActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 115, -1, -1));

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

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 760, 250));

        btnSalir.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/cancel.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 460, -1, -1));

        btnImprimirVenta.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        btnImprimirVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/pdf.png"))); // NOI18N
        btnImprimirVenta.setText("IMPRIMIR");
        btnImprimirVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirVentaMouseClicked(evt);
            }
        });
        jPanel1.add(btnImprimirVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, -1, -1));

        jDesktopPane1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 117, 780, 510));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/ventas.png"))); // NOI18N
        jLabel8.setText(" ");
        jDesktopPane1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 27, -1, -1));

        jLabel9.setBackground(new java.awt.Color(21, 52, 114));
        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("FARMACIA PRIMEROS AUXILIOS");
        jDesktopPane1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 21, -1, -1));

        jLabel10.setBackground(new java.awt.Color(21, 52, 114));
        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("AL POR MAYOR Y MENOR");
        jDesktopPane1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        getContentPane().add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // TODO add your handling code here:
        txtCedulaCliente.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtFecha.setText("");
        txtNombreCliente.setText("");
        dispose();

    }//GEN-LAST:event_btnCancelarMouseClicked

    private void tblPreVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreVentaMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_tblPreVentaMouseClicked

    private void btnRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarVentaActionPerformed
       setDetalle();
        realizarVenta();
        objDetalle.imprimirCarrito(lisCarrito);
        System.out.println(numDetalle);
        limpiar();
    }//GEN-LAST:event_btnRealizarVentaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnEditarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarVentaActionPerformed

    }//GEN-LAST:event_btnEditarVentaActionPerformed

    private void btnImprimirVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirVentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirVentaMouseClicked

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed

// TODO add your handling code here:
        jDialog1.setSize(800, 490);
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setModal(true);
        jDialog1.setVisible(true);
        
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMedicamentoActionPerformed

    private void btnBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicamentoActionPerformed
        // TODO add your handling code here:
        mostrarMedicamentos.setSize(580, 475);
        mostrarMedicamentos.setLocationRelativeTo(null);
        mostrarMedicamentos.setModal(true);
        mostrarMedicamentos.setVisible(true);
    }//GEN-LAST:event_btnBuscarMedicamentoActionPerformed

    private void txtBuscarMedicamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedicamentoKeyTyped
        // TODO add your handling code here:
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
        int fsl = tblDatMedicamento.getSelectedRow();
        int cant = 0;
        int idSelect;
      DefaultTableModel model ;
            double  x, ivas = 0.0;
       String codigo,nombre,cantP,precioUn,totP;
       
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
                if (objDetalle.cantDisponible(m, cant)) {
                    System.out.println("este se va vender" + objMedicamentos.getOneMedicamento(lisMedicamentos, idSelect));
                    codigo=String.valueOf(m.getIdMedicamento());
                    nombre=m.getNombreMedic();
                    cantP=String.valueOf(cant);
                    precioUn=String.valueOf(m.getPrecioMedic());
                    x = (Double.parseDouble(precioUn)) * cant;
                    x=Math.round(x * 100) / 100d;
                    totP=String.valueOf(x);
                    //Añadimos los datos a la tabla preventa
                    model=(DefaultTableModel) tblPreVenta.getModel();
                    String filaEle[] = {codigo, nombre, cantP, precioUn,totP};
                    model.addRow(filaEle);
                    //Calculamos los valores totales de la venta
                    totalT=totalT+x;
                    totalT=Math.round(totalT * 100) / 100d;
                    ivas = totalT * 0.12;
                    iva=ivas;
                    iva=Math.round(iva * 100) / 100d;
                    subtotalT = totalT - ivas;
                    subtotalT=Math.round(subtotalT * 100) / 100d;
                    //Presentamos datos en los txt
                    txtSubtotal.setText(String.valueOf(subtotalT));
                    txtIVA.setText(String.valueOf(iva));
                    txtTotal.setText(String.valueOf(totalT));
                    System.out.println("Subtotal: "+subtotalT);
                    System.out.println("iva: "+iva);
                    System.out.println("Total: "+totalT);
                }
            }
        }
        txtCant.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
         Clientes objClientes;
        //Obtemos la fila a la q pertenece el medicamento
        int selection = tblClientes.rowAtPoint(evt.getPoint());
        //Obtenemos el id del medicamento seleccionado
        idCliente = String.valueOf(tblClientes.getValueAt(selection, 0));
        //Lo convertimos a  int para poder buscar
        //Obtemos el medicamento
        objClientes = objLogCli.obtenerUnCliente(listaClientes, idCliente);
        //Presentamos los datos del medicamento en los txt
        txtCedulaCliente.setText(objClientes.getIdentificacion());
        txtNombreCliente.setText(objClientes.getNombreCli() +" "+ objClientes.getApellidoCli());
        txtDireccion.setText("Bernardo V");
        txtCorreo.setText("cnt@@gmail.com");

    }//GEN-LAST:event_tblClientesMouseClicked

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        // TODO add your handling code here:
        listaClientes.clear();
        cargarClientes(listaClientes);
    }//GEN-LAST:event_jButtonAgregarActionPerformed

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
            java.util.logging.Logger.getLogger(ModuloVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarMedicamento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditarVenta;
    private javax.swing.JButton btnImprimirVenta;
    private javax.swing.JButton btnRealizarVenta;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscador;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
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
