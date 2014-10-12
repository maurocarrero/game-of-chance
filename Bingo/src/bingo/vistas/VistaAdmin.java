package bingo.vistas;


import bingo.controladores.ControlAdmin;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Interfaz para Administradores
 * @author maurocarrero
 */
public final class VistaAdmin extends JFrame {
    
    private ControlAdmin admin;
    
    /**
     * Creates new form InterfazAdmin
     */
    public VistaAdmin() {
        initComponents();
        this.setTitle("Bingo - Administrador");
        
        menuConfigurar.setActionCommand("CONFIGURAR");
        menuCrearInterfaces.setActionCommand("CREAR_INTERFACES");

        btnIngresar.setActionCommand("INGRESAR");
        btnAceptar.setActionCommand("GUARDAR_CONFIGURACION");
        
        txtUsuario.setActionCommand("INGRESAR");
        txtPassword.setActionCommand("INGRESAR");
        
    }
    
    public char[] getPassword() {
        return txtPassword.getPassword();
    }
    
    public String getUsuario() {
        return txtUsuario.getText();
    }
    
    public String getCantFilas() {
        return txtCantFilas.getText();
    }
    
    public String getCantColumnas() {
        return txtCantColumnas.getText();
    }
    
    public String getCantMaxCartones() {
        return txtCantMaxCartones.getText();
    }
    
    public String getCantJugadores() {
        return txtCantJugadores.getText();
    }
    
    public String getValorCarton() {
        return txtValorCarton.getText();
    }
    
    public void mostrarMenu() {
        menuBar.setVisible(true);
    }    
    
    public void mostrarPanelConfiguracion() {
        ocultarPaneles();
        panelConfigurar.setVisible(true);
        pack();

    }
    
    public void mostrarCrearInterfaces() {
        ocultarPaneles();
        panelCrearInterfaces.setVisible(true);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    public void mostrarInfo(String mensaje, String title) {
        JOptionPane.showMessageDialog(null, mensaje, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void ocultarPaneles() {
        panelCrearInterfaces.setVisible(false);
        panelConfigurar.setVisible(false);
        panelLogin.setVisible(false);
    }
    
    public void poblarCamposConfiguracion(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton) {
        txtCantFilas.setText("" + cantFilas);
        txtCantColumnas.setText("" + cantColumnas);
        txtCantMaxCartones.setText("" + cantMaxCartones);
        txtCantJugadores.setText("" + cantJugadores);
        txtValorCarton.setText("" + valorCarton);
    }
    
    
    public void ejecutar() {
        ocultarPaneles();
        panelLogin.setVisible(true);
        menuBar.setVisible(false);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void setControlador(ControlAdmin c) {
                
        // Login
        btnIngresar.addActionListener(c);
        txtUsuario.addActionListener(c);
        txtPassword.addActionListener(c);
        
        // Configuración
        txtCantFilas.addActionListener(c);
        txtCantColumnas.addActionListener(c);
        txtCantJugadores.addActionListener(c);
        txtCantMaxCartones.addActionListener(c);
        txtValorCarton.addActionListener(c);
        btnAceptar.addActionListener(c);
        
        // Menu
        menuConfigurar.addActionListener(c);
        menuCrearInterfaces.addActionListener(c);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        panelLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        panelConfigurar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblCantFilas = new javax.swing.JLabel();
        txtCantFilas = new javax.swing.JTextField();
        lblCantColumnas = new javax.swing.JLabel();
        txtCantColumnas = new javax.swing.JTextField();
        lblCantCartones = new javax.swing.JLabel();
        txtCantMaxCartones = new javax.swing.JTextField();
        lblCantJugadores = new javax.swing.JLabel();
        txtCantJugadores = new javax.swing.JTextField();
        lblValorCarton = new javax.swing.JLabel();
        txtValorCarton = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        panelCrearInterfaces = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuAdministrar = new javax.swing.JMenu();
        menuConfigurar = new javax.swing.JMenuItem();
        menuCrearInterfaces = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        btnIngresar.setText("Ingresar");

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(txtPassword))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Configurar");

        lblCantFilas.setText("Cantidad de filas:");

        txtCantFilas.setPreferredSize(new java.awt.Dimension(10, 25));

        lblCantColumnas.setText("Cantidad de columnas:");

        txtCantColumnas.setPreferredSize(new java.awt.Dimension(10, 25));

        lblCantCartones.setText("Cantidad máxima de cartones:");

        txtCantMaxCartones.setPreferredSize(new java.awt.Dimension(10, 25));

        lblCantJugadores.setText("Cantidad de jugadores:");

        txtCantJugadores.setPreferredSize(new java.awt.Dimension(10, 25));

        lblValorCarton.setText("Valor del cartón:");

        txtValorCarton.setPreferredSize(new java.awt.Dimension(10, 25));

        btnAceptar.setText("Aceptar");

        javax.swing.GroupLayout panelConfigurarLayout = new javax.swing.GroupLayout(panelConfigurar);
        panelConfigurar.setLayout(panelConfigurarLayout);
        panelConfigurarLayout.setHorizontalGroup(
            panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigurarLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnAceptar)
                        .addGroup(panelConfigurarLayout.createSequentialGroup()
                            .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblCantFilas)
                                .addComponent(lblCantColumnas)
                                .addComponent(lblCantCartones)
                                .addComponent(lblCantJugadores)
                                .addComponent(lblValorCarton))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(txtCantColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtCantJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCantMaxCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtValorCarton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelConfigurarLayout.setVerticalGroup(
            panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigurarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantFilas)
                    .addComponent(txtCantFilas, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantColumnas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantMaxCartones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantCartones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantJugadores)
                    .addComponent(txtCantJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorCarton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorCarton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Crear Interfaces de usuario");

        javax.swing.GroupLayout panelCrearInterfacesLayout = new javax.swing.GroupLayout(panelCrearInterfaces);
        panelCrearInterfaces.setLayout(panelCrearInterfacesLayout);
        panelCrearInterfacesLayout.setHorizontalGroup(
            panelCrearInterfacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearInterfacesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addContainerGap(246, Short.MAX_VALUE))
        );
        panelCrearInterfacesLayout.setVerticalGroup(
            panelCrearInterfacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearInterfacesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        mnuAdministrar.setText("Administrar");

        menuConfigurar.setText("Configurar");
        mnuAdministrar.add(menuConfigurar);

        menuCrearInterfaces.setText("Crear Interfaces");
        mnuAdministrar.add(menuCrearInterfaces);

        menuBar.add(mnuAdministrar);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelConfigurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCrearInterfaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCrearInterfaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelConfigurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblCantCartones;
    private javax.swing.JLabel lblCantColumnas;
    private javax.swing.JLabel lblCantFilas;
    private javax.swing.JLabel lblCantJugadores;
    private javax.swing.JLabel lblValorCarton;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuConfigurar;
    private javax.swing.JMenuItem menuCrearInterfaces;
    private javax.swing.JMenu mnuAdministrar;
    private javax.swing.JPanel panelConfigurar;
    private javax.swing.JPanel panelCrearInterfaces;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JTextField txtCantColumnas;
    private javax.swing.JTextField txtCantFilas;
    private javax.swing.JTextField txtCantJugadores;
    private javax.swing.JTextField txtCantMaxCartones;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValorCarton;
    // End of variables declaration//GEN-END:variables

}
