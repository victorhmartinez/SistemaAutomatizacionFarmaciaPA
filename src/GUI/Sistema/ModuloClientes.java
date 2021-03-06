/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sistema;

import ENT.Sistema.Clientes;
import ENT.Sistema.Contactos;
import ENT.Sistema.Direccion;
import LOG.Sistema.LogClientes;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class ModuloClientes extends javax.swing.JFrame {

    /**
     * Creates new form ModuloCliente
     */
    LogClientes objLogCli = new LogClientes();
    int objAuxDireccion;
    int objAuxContactos;
    Contactos objContacto = null;
    Direccion objDireccion = null;
    ArrayList<Clientes> listClientes = new ArrayList<>();
    DefaultTableModel dtm;
    TableRowSorter trs = null;
    String id;
    
    
    public ModuloClientes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/cliente.png")).getImage());
        llenarTblClientes();
    }

    public ModuloClientes(String cedulaBuscada) {
        initComponents();
        this.setLocationRelativeTo(null);
        txtCedula.setText(cedulaBuscada);
        this.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/cliente.png")).getImage());
        llenarTblClientes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDireccion = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtBarrio = new javax.swing.JTextField();
        txtCPrincipal = new javax.swing.JTextField();
        txtNunCasa = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCSecundaria = new javax.swing.JTextField();
        txtReferencia = new javax.swing.JTextField();
        panelDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDireccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Direccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("BARRIO:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("CALLE PRICINPAL:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("NÚMERO DE CASA:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("CIUDAD:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("CALLE SECUNADRIA:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("REFERENCIA :");

        txtReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReferenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDireccionLayout = new javax.swing.GroupLayout(panelDireccion);
        panelDireccion.setLayout(panelDireccionLayout);
        panelDireccionLayout.setHorizontalGroup(
            panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDireccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelDireccionLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNunCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDireccionLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDireccionLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtCPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCSecundaria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReferencia))
                .addGap(30, 30, 30))
        );
        panelDireccionLayout.setVerticalGroup(
            panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDireccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNunCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getContentPane().add(panelDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 690, 170));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("NOMBRES:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CÉDULA:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("CELULAR");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("APELLIDOS:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("CORREO:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("TELEFONO");

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre))
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 690, 160));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Cédula", "Correo", "Telefono", "Celular", "Direccion"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/search.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 690, 230));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("REGISTRO DE CLIENTES");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 340, 30));

        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/edit.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 650, -1, -1));

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/insert.png"))); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 650, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/delete.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 650, -1, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Fondo.jpg"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-240, 0, 950, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setDireccion() {
        objDireccion = new Direccion(txtCPrincipal.getText(), txtCSecundaria.getText(), txtNunCasa.getText(), txtReferencia.getText(), txtCiudad.getText());
        try {
            objLogCli.insertDireccion(objDireccion);

            objAuxDireccion = objLogCli.obtenerUltimaDireccion(); //Llenar objeto global
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR en set Direccion" + ex);
            //System.out.println("error en entrar usuario"+ tipoUsuario + " " +contrasenia);
        }
        //System.out.println(numIdDireccion+ " DIRECCION"); //Ultimo usuario ingresado
        System.out.println("Encontrando error en Direccion");
    }

    private void setContacto() {
        objContacto = new Contactos(txtTelefono.getText(), txtCelular.getText(), txtCorreo.getText());
        try {
            objLogCli.insertContactos(objContacto);
            //JOptionPane.showMessageDialog(null, "Contacto Ingresado");
            objAuxContactos = objLogCli.obtenerUltimoContacto(); //Llenar objeto global
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR en set contacto" + ex);
            //System.out.println("error en entrar usuario"+ tipoUsuario + " " +contrasenia);
        }
        System.out.println("Encontrando error en contactos");
        //System.out.println(numIdContacto+ " CONTACTO"); //Ultimo usuario ingresado
    }

    private void setCliente() {
        objDireccion.setIdDireccion(objAuxDireccion);
        objContacto.setIdContactos(objAuxContactos);
        Clientes objEmple = new Clientes(0, txtNombre.getText(), txtApellidos.getText(), txtCedula.getText(), objDireccion, objContacto);
        try {
            objLogCli.insertEmpleado(objEmple);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR en set Cliente" + ex);
            //System.out.println("error en entrar usuario"+ tipoUsuario + " " +contrasenia);
        }
        System.out.println("Encontrando error en SetCliente");
    }

    private void cargarClientes(ArrayList<Clientes> listEmpleados) {
        try {
            objLogCli.obtenerTodosClientes(listEmpleados);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Base de Datos", "", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void llenarTblClientes() {
        cargarClientes(listClientes);

        dtm = (DefaultTableModel) tblClientes.getModel();

        Object[] tblFilas = new Object[dtm.getColumnCount()];
        for (Clientes clien : listClientes) {
            tblFilas[0] = clien.getNombreCli();
            tblFilas[1] = clien.getApellidoCli();
            tblFilas[2] = clien.getIdentificacion();
            tblFilas[3] = clien.getContactos().getCorreo();
            tblFilas[4] = clien.getContactos().getTelefono();
            tblFilas[5] = clien.getContactos().getCelular();
            tblFilas[6] = clien.getDireccion().getCalleP() + clien.getDireccion().getCalleS();
            dtm.addRow(tblFilas);
        }
    }

    private void limpiarDatosCliente() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtCiudad.setText("");
        txtNunCasa.setText("");
        txtCPrincipal.setText("");
        txtCSecundaria.setText("");
        txtReferencia.setText("");
    }

    private void vaciarTabla() {
        for (int i = 0; i < tblClientes.getRowCount(); i++) {
            dtm.removeRow(i);
            i -= 1;
        }
    }

    private void editarClientes(Clientes cli) {
        try {
            if (objLogCli.editarCliente(cli)) {
                JOptionPane.showMessageDialog(null, "Datos Modificados Correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Advertencia", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Error en editar 2"+e);
        }
    }

    //Validar campos llenos
    public boolean validarCampos() {
        boolean valida = true;
        if (txtNombre.getText().equals("")) {
            valida = false;
            JOptionPane.showMessageDialog(null, "Ingrese los Nombres", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            if (txtApellidos.getText().equals("")) {
                valida = false;
                JOptionPane.showMessageDialog(null, "Ingrese los Apellidos", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                if (txtCedula.getText().equals("")) {
                    valida = false;
                    JOptionPane.showMessageDialog(null, "Ingrese la Cedula", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (txtCorreo.getText().equals("")) {
                        valida = false;
                        JOptionPane.showMessageDialog(null, "Ingrese el Correo", "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (txtTelefono.getText().equals("")) {
                            valida = false;
                            JOptionPane.showMessageDialog(null, "Ingrese el Teléfono", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (txtCelular.getText().equals("")) {
                                valida = false;
                                JOptionPane.showMessageDialog(null, "Ingrese el Celular", "Error", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (txtCiudad.getText().equals("")) {
                                    valida = false;
                                    JOptionPane.showMessageDialog(null, "Ingrese la Ciudad", "Error", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    if (txtNunCasa.getText().equals("")) {
                                        valida = false;
                                        JOptionPane.showMessageDialog(null, "Ingrese el Número de Casa", "Error", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        if (txtCPrincipal.getText().equals("")) {
                                            valida = false;
                                            JOptionPane.showMessageDialog(null, "Ingrese la Calle Principal", "Error", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            if (txtCSecundaria.getText().equals("")) {
                                                valida = false;
                                                JOptionPane.showMessageDialog(null, "Ingrese la Calle Secundaria", "Error", JOptionPane.WARNING_MESSAGE);
                                            } else {
                                                if (txtReferencia.getText().equals("")) {
                                                    valida = false;
                                                    JOptionPane.showMessageDialog(null, "Ingrese una Referencia", "Error", JOptionPane.WARNING_MESSAGE);
                                                }
                                            }

                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }
            return valida;
        }
        return valida;
    }

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if (validarCampos()) {
            setDireccion();
            setContacto();
            setCliente();
            JOptionPane.showMessageDialog(null, "Cliente Agregado","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
        listClientes.clear();
        vaciarTabla();
        
        limpiarDatosCliente();
        llenarTblClientes();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
         txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //Designamos a la fila en comluna en la que queremos buscar en este caso por nombre "1" y con lo debe compara
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), 1));
            }
        });
        trs = new TableRowSorter(dtm);
        tblClientes.setRowSorter(trs);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
         Clientes clientes;
        int s = tblClientes.rowAtPoint(evt.getPoint());
        id = String.valueOf(tblClientes.getValueAt(s, 2));
        
        clientes = objLogCli.obtenerUnCliente(listClientes,id);

        txtNombre.setText(clientes.getNombreCli());
        txtApellidos.setText(clientes.getApellidoCli());
        txtCedula.setText(clientes.getIdentificacion());
        txtCorreo.setText(clientes.getContactos().getCorreo());
        txtTelefono.setText(clientes.getContactos().getTelefono());
        txtCelular.setText(clientes.getContactos().getCelular());
        txtCPrincipal.setText(clientes.getDireccion().getCalleP());
        txtCSecundaria.setText(clientes.getDireccion().getCalleS());
        txtCiudad.setText(clientes.getDireccion().getCiudad());
        txtNunCasa.setText(clientes.getDireccion().getNumCasa());
        txtReferencia.setText(clientes.getDireccion().getReferencia());

    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
         Clientes clientes;
        clientes = objLogCli.obtenerUnCliente(listClientes, id);

        clientes.setNombreCli(txtNombre.getText());
        clientes.setApellidoCli(txtApellidos.getText());
        clientes.setIdentificacion(txtCedula.getText());
        Contactos cont = new Contactos(txtTelefono.getText(), txtCelular.getText(), txtCorreo.getText());
        Direccion dir = new Direccion(txtCPrincipal.getText(), txtCSecundaria.getText(), txtNunCasa.getText(), txtReferencia.getText(), txtCiudad.getText());
        clientes.setContactos(cont);
        clientes.setDireccion(dir);

        editarClientes(clientes);
        listClientes.clear();
        vaciarTabla();
        limpiarDatosCliente();
        llenarTblClientes();
        //JOptionPane.showMessageDialog(null, "Datos editados correctamente","Aviso",JOptionPane.OK_OPTION);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int descision = JOptionPane.showConfirmDialog(null, " Eliminar Cliente ?");
        if (descision == JOptionPane.YES_OPTION) {
            try {
                if (objLogCli.eliminarCliente(id)) {
                    JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                }
            } catch (ClassNotFoundException ex) {
               JOptionPane.showMessageDialog(null, "Error al eliminar"+ex);
        
            } catch (SQLException ex) {
                Logger.getLogger(ModuloProductos.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(null, "Error al eliminar"+ex);
            }
        } else if (descision == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "OK");
        }

        listClientes.clear();
        //Limpiamos la tabla
        vaciarTabla();
        //Volvemos llenar la tabla con los datos
        llenarTblClientes();
        limpiarDatosCliente();
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(ModuloClientes.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloClientes.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloClientes.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloClientes.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelDireccion;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCPrincipal;
    private javax.swing.JTextField txtCSecundaria;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNunCasa;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
