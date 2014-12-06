package bingoclienteadmin;


import bingo.common.interfaces.IFigura;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Interfaz para Administradores
 * @author maurocarrero/fernandogonzalez
 */
public final class VistaAdmin extends JFrame {
    
    /**
     * Creates new form InterfazAdmin
     */
    public VistaAdmin() {
        initComponents();
        this.setTitle("Bingo - Administrador");
        
        menuConfigurar.setActionCommand("CONFIGURAR");
        menuFinalizar.setActionCommand("FINALIZAR_APLICACION");

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

    public boolean getChkCentro() {
        return chkCentro.isSelected();
    }

    public boolean getChkDiagonal() {
        return chkDiagonal.isSelected();
    }

    public boolean getChkLinea() {
        return chkLinea.isSelected();
    }

    public String getTiempo() {
        return txtTiempo.getText();
    }  
    
    public void mostrarPanelConfiguracion() {
        ocultarPaneles();
        panelConfigurar.setVisible(true);
        pack();

    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    public void mostrarInfo(String mensaje, String title) {
        JOptionPane.showMessageDialog(null, mensaje, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void ocultarPaneles() {
        panelConfigurar.setVisible(false);
        panelLogin.setVisible(false);
    }
    
    public void poblarCamposConfiguracion(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton, int tiempo,
            List<IFigura> figuras) throws RemoteException{
        txtCantFilas.setText("" + cantFilas);
        txtCantColumnas.setText("" + cantColumnas);
        txtCantMaxCartones.setText("" + cantMaxCartones);
        txtCantJugadores.setText("" + cantJugadores);
        txtValorCarton.setText("" + valorCarton);
        txtTiempo.setText("" + tiempo);
        for(IFigura f : figuras){
            if (f.getNombre().equals("linea")) {
                chkLinea.setSelected(true);
            }
            if (f.getNombre().equals("diagonal")) {
                chkDiagonal.setSelected(true);
            }
            if (f.getNombre().equals("centro")) {
                chkCentro.setSelected(true);
            }
        }
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
        menuFinalizar.addActionListener(c);
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
        chkLinea = new javax.swing.JCheckBox();
        chkDiagonal = new javax.swing.JCheckBox();
        chkCentro = new javax.swing.JCheckBox();
        chkCartonLleno = new javax.swing.JCheckBox();
        lblValorCarton1 = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        mnuAdministrar = new javax.swing.JMenu();
        menuConfigurar = new javax.swing.JMenuItem();
        menuFinalizar = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel2.setText("Contrasenia");

        btnIngresar.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnIngresar.setText("Ingresar");

        txtUsuario.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

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
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtPassword))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Configurar");

        lblCantFilas.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblCantFilas.setText("Cantidad de filas:");

        txtCantFilas.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N
        txtCantFilas.setPreferredSize(new java.awt.Dimension(10, 25));

        lblCantColumnas.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblCantColumnas.setText("Cantidad de columnas:");

        txtCantColumnas.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N
        txtCantColumnas.setPreferredSize(new java.awt.Dimension(10, 25));

        lblCantCartones.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblCantCartones.setText("Cantidad máxima de cartones:");

        txtCantMaxCartones.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N
        txtCantMaxCartones.setPreferredSize(new java.awt.Dimension(10, 25));

        lblCantJugadores.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblCantJugadores.setText("Cantidad de jugadores:");

        txtCantJugadores.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N
        txtCantJugadores.setPreferredSize(new java.awt.Dimension(10, 25));

        lblValorCarton.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblValorCarton.setText("Valor del cartón:");

        txtValorCarton.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N
        txtValorCarton.setPreferredSize(new java.awt.Dimension(10, 25));

        btnAceptar.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnAceptar.setText("Aceptar");

        chkLinea.setText("Linea");

        chkDiagonal.setText("Diagonal");

        chkCentro.setText("Centro");

        chkCartonLleno.setSelected(true);
        chkCartonLleno.setText("Carton Lleno");
        chkCartonLleno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkCartonLleno.setEnabled(false);

        lblValorCarton1.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblValorCarton1.setText("Valor del tiempo en segundos:");

        txtTiempo.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N
        txtTiempo.setPreferredSize(new java.awt.Dimension(10, 25));

        javax.swing.GroupLayout panelConfigurarLayout = new javax.swing.GroupLayout(panelConfigurar);
        panelConfigurar.setLayout(panelConfigurarLayout);
        panelConfigurarLayout.setHorizontalGroup(
            panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigurarLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(jLabel3)
                    .addGroup(panelConfigurarLayout.createSequentialGroup()
                        .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCantFilas)
                            .addComponent(lblCantColumnas)
                            .addComponent(lblCantCartones)
                            .addComponent(lblCantJugadores)
                            .addComponent(lblValorCarton)
                            .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblValorCarton1)
                                .addComponent(chkLinea)
                                .addComponent(chkCartonLleno)
                                .addComponent(chkDiagonal)
                                .addComponent(chkCentro)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(txtCantColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCantFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCantJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantMaxCartones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtValorCarton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelConfigurarLayout.setVerticalGroup(
            panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigurarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantFilas)
                    .addComponent(txtCantFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantColumnas)
                    .addComponent(txtCantColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantMaxCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantCartones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantJugadores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorCarton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorCarton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorCarton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCartonLleno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkDiagonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkLinea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCentro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(11, 11, 11))
        );

        mnuAdministrar.setText("Administrar");

        menuConfigurar.setText("Configurar");
        mnuAdministrar.add(menuConfigurar);

        menuFinalizar.setText("Finalizar aplicación");
        mnuAdministrar.add(menuFinalizar);

        menuBar.add(mnuAdministrar);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(panelConfigurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelConfigurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JCheckBox chkCartonLleno;
    private javax.swing.JCheckBox chkCentro;
    private javax.swing.JCheckBox chkDiagonal;
    private javax.swing.JCheckBox chkLinea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblCantCartones;
    private javax.swing.JLabel lblCantColumnas;
    private javax.swing.JLabel lblCantFilas;
    private javax.swing.JLabel lblCantJugadores;
    private javax.swing.JLabel lblValorCarton;
    private javax.swing.JLabel lblValorCarton1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuConfigurar;
    private javax.swing.JMenuItem menuFinalizar;
    private javax.swing.JMenu mnuAdministrar;
    private javax.swing.JPanel panelConfigurar;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JTextField txtCantColumnas;
    private javax.swing.JTextField txtCantFilas;
    private javax.swing.JTextField txtCantJugadores;
    private javax.swing.JTextField txtCantMaxCartones;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTiempo;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValorCarton;
    // End of variables declaration//GEN-END:variables

}
