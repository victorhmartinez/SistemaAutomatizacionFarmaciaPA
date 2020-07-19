/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sistema;

import ENT.Sistema.Contactos;
import ENT.Sistema.Direccion;
import ENT.Sistema.Empleados;
import ENT.Sistema.Usuario;
import LOG.Sistema.LogEmpleados;
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
 * @author Christian
 */
public class ModuloEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form ModuloEmpleado
     */
    LogEmpleados objEmpleado = new LogEmpleados();
    ArrayList<Empleados> listEmpleados = new ArrayList<>();
    Usuario objAuxUsuario;
    Direccion objAuxDireccion;
    Contactos objAuxContactos;
    DefaultTableModel dtm;
    TableRowSorter trs = null;
    int idEmpSelect;

    public ModuloEmpleado() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnAgregar.setEnabled(false);
        llenarTblEmpleados();
        this.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/empleado.png")).getImage());
    }

    private void setUsuario() {
        String contrasenia = new String(txtContrasenia.getPassword());
        String tipoUsuario = (String) cmbTipoUsuario.getSelectedItem();

        Usuario objUsuario = new Usuario(txtUsuario.getText(), contrasenia, tipoUsuario);
        try {
            objEmpleado.insertUsuario(objUsuario);
            //JOptionPane.showMessageDialog(null, "Usuario Ingresado para este Empleado");
            objAuxUsuario = objEmpleado.obtenerUltimoUsuario(objAuxUsuario); //Llenar objeto global
            btnAgregar.setEnabled(true);
            btnAgregarUsuario.setEnabled(false);
            btnGuardarUsuario.setEnabled(false);
            btnCancelar.setEnabled(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR" + ex);
            //System.out.println("error en entrar usuario"+ tipoUsuario + " " +contrasenia);
        }
        //System.out.println(numIdUsuario+ " USUARIO"); //Ultimo usuario ingresado
        limpiarUsuario();
    }

    private void limpiarUsuario() {
        txtContrasenia.setText("");
        txtUsuario.setText("");
    }

    private void setDireccion() {
        Direccion objDireccion = new Direccion(txtCPrincipal.getText(), txtCSecundaria.getText(), txtNumCasa.getText(), txtReferencia.getText(), txtCiudad.getText());
        try {
            objEmpleado.insertDireccion(objDireccion);
           
            objAuxDireccion = objEmpleado.obtenerUltimaDireccion(objAuxDireccion); //Llenar objeto global
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR" + ex);
            //System.out.println("error en entrar usuario"+ tipoUsuario + " " +contrasenia);
        }
        //System.out.println(numIdDireccion+ " DIRECCION"); //Ultimo usuario ingresado

    }

    private void setContacto() {
        Contactos objContacto = new Contactos(txtTelefono.getText(), txtCelular.getText(), txtCorreo.getText());
        try {
            objEmpleado.insertContactos(objContacto);
            //JOptionPane.showMessageDialog(null, "Contacto Ingresado");
            objAuxContactos = objEmpleado.obtenerUltimoContacto(objAuxContactos); //Llenar objeto global
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR" + ex);
            //System.out.println("error en entrar usuario"+ tipoUsuario + " " +contrasenia);
        }
        //System.out.println(numIdContacto+ " CONTACTO"); //Ultimo usuario ingresado
    }

    private void setEmpleado() {
        Empleados objEmple = new Empleados(txtNombres.getText(), txtApellidos.getText(), txtCedula.getText(), objAuxUsuario, objAuxContactos, 1, objAuxDireccion);
        try {
            objEmpleado.insertEmpleado(objEmple);
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR" + ex);
            //System.out.println("error en entrar usuario"+ tipoUsuario + " " +contrasenia);
        }


    }

    //Validar campos llenos
    public boolean validarCampos() {
        boolean valida = true;
        if (txtNombres.getText().equals("")) {
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
                                    if (txtNumCasa.getText().equals("")) {
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

    private void limpiarDatosEmpleado() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtCiudad.setText("");
        txtNumCasa.setText("");
        txtCPrincipal.setText("");
        txtCSecundaria.setText("");
        txtReferencia.setText("");
    }

    private void llenarTblEmpleados() {
        cargarEmpleados(listEmpleados);

        dtm = (DefaultTableModel) tblEmpleados.getModel();
        
        Object[] tblFilas = new Object[dtm.getColumnCount()];
        for (Empleados emp : listEmpleados) {
            tblFilas[0] = emp.getIdEmpleado();
            tblFilas[1] = emp.getIdentificacionEmp();
            tblFilas[2] = emp.getNombreEmp();
            tblFilas[3] = emp.getApellidoEmp();
            tblFilas[4] = emp.getUsuario().getUserName();
            tblFilas[5] = emp.getUsuario().getContrasenia();
            tblFilas[6] = emp.getContactos().getCorreo();
            tblFilas[7] = emp.getContactos().getCelular();
            tblFilas[8] = emp.getDireccion().getCiudad();
            tblFilas[9] = emp.getDireccion().getCalleP();
            dtm.addRow(tblFilas);
        }
    }

    private void cargarEmpleados(ArrayList<Empleados> listEmpleados) {
        try {
            objEmpleado.obtenerEmpleados(listEmpleados);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Base de Datos", "", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void vaciarTabla() {
        for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
            dtm.removeRow(i);
            i -= 1;
        }
    }

    private void editarEmpleados(Empleados emp) {
        try {
            if (objEmpleado.editarEmpleado(emp)) {
                JOptionPane.showMessageDialog(null, "Datos Modificados Correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Advertencia", JOptionPane.ERROR_MESSAGE);
       JOptionPane.showMessageDialog(null, "Error");
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

        jDialog1 = new javax.swing.JDialog();
        usua = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtContrasenia = new javax.swing.JPasswordField();
        btnGuardarUsuario = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        Registro = new javax.swing.JLabel();
        Data = new javax.swing.JPanel();
        CI = new javax.swing.JLabel();
        Cel = new javax.swing.JLabel();
        Telf = new javax.swing.JLabel();
        Apellido = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Correo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        Dir = new javax.swing.JPanel();
        calle = new javax.swing.JLabel();
        txtCPrincipal = new javax.swing.JTextField();
        calle2 = new javax.swing.JLabel();
        txtCSecundaria = new javax.swing.JTextField();
        NumCa = new javax.swing.JLabel();
        Ref = new javax.swing.JLabel();
        txtNumCasa = new javax.swing.JTextField();
        Ciudad = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        Busc = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarUsuario = new javax.swing.JButton();
        txtBuscarEmpleado = new javax.swing.JTextField();
        Fondo = new javax.swing.JLabel();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usua.setForeground(new java.awt.Color(255, 255, 255));
        usua.setText("USUARIO:");
        jDialog1.getContentPane().add(usua, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        txtUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDialog1.getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 130, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CONTRASEÑA:");
        jDialog1.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TIPO DE USUSARIO:");
        jDialog1.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        cmbTipoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Empleado" }));
        cmbTipoUsuario.setBorder(null);
        cmbTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoUsuarioActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(cmbTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 130, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("INGRESO DE USUARIOS");
        jDialog1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        txtContrasenia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jDialog1.getContentPane().add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 130, 20));

        btnGuardarUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/accept.png"))); // NOI18N
        btnGuardarUsuario.setText("GUARDAR");
        btnGuardarUsuario.setBorder(null);
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(btnGuardarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 110, 40));

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Cancel.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBorder(null);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 110, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Fondo.jpg"))); // NOI18N
        jDialog1.getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-530, -310, 910, 640));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Empleados");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Registro.setBackground(new java.awt.Color(255, 255, 255));
        Registro.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        Registro.setForeground(new java.awt.Color(255, 255, 255));
        Registro.setText("REGISTRO DE EMPLEADOS");
        getContentPane().add(Registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 510, 30));

        Data.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        Data.setForeground(new java.awt.Color(255, 255, 255));

        CI.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CI.setText("CÉDULA:");

        Cel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cel.setText("CELULAR:");

        Telf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Telf.setText("TELÉFONO:");

        Apellido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Apellido.setText("APELLIDOS:");

        Nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Nombre.setText("NOMBRES:");

        Correo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Correo.setText("CORREO:");

        txtCorreo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtNombres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtApellidos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTelefono.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtCedula.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtCelular.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout DataLayout = new javax.swing.GroupLayout(Data);
        Data.setLayout(DataLayout);
        DataLayout.setHorizontalGroup(
            DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Correo)
                    .addComponent(Nombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataLayout.createSequentialGroup()
                        .addComponent(Telf)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DataLayout.createSequentialGroup()
                        .addComponent(Apellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataLayout.createSequentialGroup()
                        .addComponent(Cel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DataLayout.createSequentialGroup()
                        .addComponent(CI)
                        .addGap(18, 18, 18)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
        );
        DataLayout.setVerticalGroup(
            DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CI)
                        .addComponent(Apellido)
                        .addComponent(Nombre))
                    .addGroup(DataLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cel)
                    .addComponent(Telf)
                    .addComponent(Correo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(Data, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 930, 130));

        Dir.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dirección", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        Dir.setForeground(new java.awt.Color(204, 255, 204));

        calle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        calle.setText("CALLE PRINCIPAL:");

        txtCPrincipal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        calle2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        calle2.setText("CALLE SECUNDARIA:");

        txtCSecundaria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        NumCa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NumCa.setText("NÚMERO DE CASA:");

        Ref.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Ref.setText("REFERENCIA:");

        txtNumCasa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Ciudad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Ciudad.setText("CIUDAD:");

        txtReferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtCiudad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout DirLayout = new javax.swing.GroupLayout(Dir);
        Dir.setLayout(DirLayout);
        DirLayout.setHorizontalGroup(
            DirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DirLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(DirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DirLayout.createSequentialGroup()
                        .addComponent(calle)
                        .addGap(40, 40, 40)
                        .addComponent(txtCPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Ciudad)
                        .addGap(18, 18, 18)
                        .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(NumCa)
                        .addGap(26, 26, 26)
                        .addComponent(txtNumCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DirLayout.createSequentialGroup()
                        .addComponent(calle2)
                        .addGap(26, 26, 26)
                        .addComponent(txtCSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(Ref)
                        .addGap(56, 56, 56)
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        DirLayout.setVerticalGroup(
            DirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Ciudad)
                        .addComponent(NumCa)
                        .addComponent(calle)
                        .addComponent(txtCPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(DirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCSecundaria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(calle2)
                        .addComponent(Ref)
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(Dir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 930, 130));

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/edit.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/insert.png"))); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 630, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/delete.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 630, -1, -1));

        Busc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "C.I.", "NOMBRE", "APELLIDO", "USUARIO", "CONTRASEÑA", "CORREO", "CELULAR", "CIUDAD", "CALLE P"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleados);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/search.png"))); // NOI18N

        btnAgregarUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/anadirU.png"))); // NOI18N
        btnAgregarUsuario.setText("AÑADIR USUARIO");
        btnAgregarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarUsuarioMouseClicked(evt);
            }
        });
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });

        txtBuscarEmpleado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarEmpleadoActionPerformed(evt);
            }
        });
        txtBuscarEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarEmpleadoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout BuscLayout = new javax.swing.GroupLayout(Busc);
        Busc.setLayout(BuscLayout);
        BuscLayout.setHorizontalGroup(
            BuscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscLayout.createSequentialGroup()
                        .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addComponent(btnAgregarUsuario)
                        .addGap(111, 111, 111))
                    .addGroup(BuscLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        BuscLayout.setVerticalGroup(
            BuscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BuscLayout.createSequentialGroup()
                        .addGroup(BuscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarUsuario))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(Busc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 930, 260));

        Fondo.setBackground(new java.awt.Color(204, 204, 204));
        Fondo.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Sistema/Fondo.jpg"))); // NOI18N
        Fondo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoUsuarioActionPerformed

    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed
        // TODO add your handling code here:
        jDialog1.setSize(382, 370);
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setIconImage(new ImageIcon(getClass().getResource("/IMG/Sistema/anadirU.png")).getImage());
        jDialog1.setModal(true);
        jDialog1.setVisible(true);
        

    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void btnAgregarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarUsuarioMouseClicked

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        // TODO add your handling code here:
        setUsuario();

    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if (validarCampos()) {
            setDireccion();
            setContacto();
            setEmpleado();
            btnAgregarUsuario.setEnabled(true);
            btnGuardarUsuario.setEnabled(true);
            btnCancelar.setEnabled(true);

        }
        listEmpleados.clear();
        vaciarTabla();
        btnAgregar.setEnabled(false);
        limpiarDatosEmpleado();
        llenarTblEmpleados();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Empleados emp;
        emp = objEmpleado.obtenerEmpleadoBuscado(listEmpleados, idEmpSelect);

        emp.setNombreEmp(txtNombres.getText());
        emp.setApellidoEmp(txtApellidos.getText());
        emp.setIdentificacionEmp(txtCedula.getText());
        Contactos cont = new Contactos(txtTelefono.getText(), txtCelular.getText(), txtCorreo.getText());
        Direccion dir = new Direccion(txtCPrincipal.getText(), txtCSecundaria.getText(), txtNumCasa.getText(), txtReferencia.getText(), txtCiudad.getText());
        emp.setContactos(cont);
        emp.setDireccion(dir);

        editarEmpleados(emp);
        listEmpleados.clear();
        vaciarTabla();
        limpiarDatosEmpleado();
        llenarTblEmpleados();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        txtUsuario.setText("");
        txtContrasenia.setText("");
        jDialog1.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtBuscarEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyTyped
        // TODO add your handling code here:
        txtBuscarEmpleado.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //Designamos a la fila en comluna en la que queremos buscar en este caso por nombre "1" y con lo debe compara
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarEmpleado.getText(), 2));
            }

        });
        trs = new TableRowSorter(dtm);
        tblEmpleados.setRowSorter(trs);
    }//GEN-LAST:event_txtBuscarEmpleadoKeyTyped

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        // TODO add your handling code here:
        Empleados emp;
        int s = tblEmpleados.rowAtPoint(evt.getPoint());
        String id = String.valueOf(tblEmpleados.getValueAt(s, 0));
        idEmpSelect = Integer.parseInt(id);
        
        emp = objEmpleado.obtenerEmpleadoBuscado(listEmpleados, idEmpSelect);

        txtNombres.setText(emp.getNombreEmp());
        txtApellidos.setText(emp.getApellidoEmp());
        txtCedula.setText(emp.getIdentificacionEmp());
        txtCorreo.setText(emp.getContactos().getCorreo());
        txtTelefono.setText(emp.getContactos().getTelefono());
        txtCelular.setText(emp.getContactos().getCelular());
        txtCPrincipal.setText(emp.getDireccion().getCalleP());
        txtCSecundaria.setText(emp.getDireccion().getCalleS());
        txtCiudad.setText(emp.getDireccion().getCiudad());
        txtNumCasa.setText(emp.getDireccion().getNumCasa());
        txtReferencia.setText(emp.getDireccion().getReferencia());

    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int descision = JOptionPane.showConfirmDialog(null, " Eliminar Empleado");
        if (descision == JOptionPane.YES_OPTION) {
            try {
                if (objEmpleado.eliminarEmpleado(idEmpSelect)) {
                    JOptionPane.showMessageDialog(null, "Empleado Eliminado");
                }
            } catch (ClassNotFoundException ex) {
               JOptionPane.showMessageDialog(null, "Error al eliminar");
        
            } catch (SQLException ex) {
                Logger.getLogger(ModuloProductos.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        } else if (descision == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "OK");

        }

        listEmpleados.clear();
        //Limpiamos la tabla
        vaciarTabla();
        //Volvemos llenar la tabla con los datos
        llenarTblEmpleados();
        limpiarDatosEmpleado();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpleadoActionPerformed

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
            java.util.logging.Logger.getLogger(ModuloEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Apellido;
    private javax.swing.JPanel Busc;
    private javax.swing.JLabel CI;
    private javax.swing.JLabel Cel;
    private javax.swing.JLabel Ciudad;
    private javax.swing.JLabel Correo;
    private javax.swing.JPanel Data;
    private javax.swing.JPanel Dir;
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel NumCa;
    private javax.swing.JLabel Ref;
    private javax.swing.JLabel Registro;
    private javax.swing.JLabel Telf;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel calle;
    private javax.swing.JLabel calle2;
    private javax.swing.JComboBox<String> cmbTipoUsuario;
    private javax.swing.JLabel fondo;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscarEmpleado;
    private javax.swing.JTextField txtCPrincipal;
    private javax.swing.JTextField txtCSecundaria;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumCasa;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JLabel usua;
    // End of variables declaration//GEN-END:variables
}
