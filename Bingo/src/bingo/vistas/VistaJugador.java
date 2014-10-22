package bingo.vistas;

import bingo.controladores.ControlJugador;
import bingo.controladores.Controlador;
import bingo.interfaces.IJugador;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Interfaz para Jugadores
 * @author maurocarrero/fernandogonzalez
 */
public final class VistaJugador extends javax.swing.JFrame implements InterfazVista {

    private ControlJugador jugador;
    private JPanel contenedor;
    
    /**
     * Creates new form InterfazJugador
     */
    public VistaJugador() {
        initComponents();
        btnIngresar.setActionCommand("INGRESAR");
        btnNO.setActionCommand("NO_CONTINUAR");
        btnSI.setActionCommand("SI_CONTINUAR");
        ocultarPaneles();
        pack();        
    }
    
    public String getUsuario(){
        return this.txtUsuario.getText();
    }
    
    public void setSaldo(String saldo){
        this.lblSaldo.setText(saldo);
    }
    
    public void setBolilla(int bolilla) {
        lblBolilla.setText(bolilla + "");
    }
    
    public char[] getPassword(){
        return this.txtPassword.getPassword();
    }
    
    public String getCantCartones(){
        return this.txtCantCartones.getText();
    }
    
    public void esperarComienzoJuego() {
        panelLoginJugador.setVisible(false);
        panelEspera.setVisible(true);
    }
    
    public void ocultarPaneles() {
        panelEspera.setVisible(false);
        panelLoginJugador.setVisible(false);
        panelContinuarJugando.setVisible(false);
        panelCartones.setVisible(false);
        panelInfo.setVisible(false);
        panelMensaje.setVisible(false);
    }
    
    
    public void ocultarPanelContinuar() {
        panelContinuarJugando.setVisible(false);
    }
    
    public void mostrarPanelContinuar() {
        panelContinuarJugando.setVisible(true);
    }
   
    public void dibujarContenedorCartones(int tamanio, int cantFilas, int cantColumnas) {
        ocultarPaneles();
        panelInfo.setVisible(true);
        panelMensaje.setVisible(true);
        panelContinuarJugando.setVisible(true);
        panelCartones.setLayout(new GridLayout(0, 5));
    }  
    
    public List<JCasillero> dibujarCarton(int[][] numeros, int cantFilas, int cantColumnas) {
        JCarton carton = new JCarton(numeros, cantFilas, cantColumnas);
        panelCartones.add(carton);
        return carton.getCasilleros();
    }
    
    public void mostrarCartones() {
        panelCartones.setVisible(true);
        pack();
    }
    
    public void mostrarInfo(String nombreJugador, double pozo, double saldoActual, List<IJugador> jugadores) {
        setTitle(nombreJugador);
        lblPozo.setText(pozo + "");
        lblSaldoActual.setText(saldoActual + "");
        listJugadores.setListData(jugadores.toArray());
    }
    
    public void actualizarPozo(double pozo){
        lblPozo.setText(pozo + "");
    }
    
    public void abandonarPartida() {
        ocultarPaneles();
        panelLoginJugador.setVisible(true);
        pack();
    }
    
    public void mostrarMensaje(String msg) {
        lblMensaje.setText(msg);
        pack();
    }
    
    public void finalizarJuego(String usuario, double saldo, double pozo){
        mostrarMensaje("Fin del juego, Ganador: " + usuario);
        actualizarPozo(pozo);
        lblSaldoActual.setText(saldo + "");        
        panelContinuarJugando.setVisible(false);
        listJugadores.removeAll();
        listJugadores.setVisible(false);
    }

    @Override
    public void setControlador(Controlador c) {
        // Login
        btnIngresar.addActionListener(c);
        txtUsuario.addActionListener(c);
        txtPassword.addActionListener(c);
        txtCantCartones.addActionListener(c);
        btnNO.addActionListener(c);
        btnSI.addActionListener(c);
    }

    @Override
    public void ejecutar() {
        ocultarPaneles();
        panelLoginJugador.setVisible(true);
        setTitle("Interfaz de Jugador");
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLoginJugador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        lblCantCartones = new javax.swing.JLabel();
        txtCantCartones = new javax.swing.JTextField();
        panelEspera = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelContinuarJugando = new javax.swing.JPanel();
        lblContinuarJugando = new javax.swing.JLabel();
        btnSI = new javax.swing.JButton();
        btnNO = new javax.swing.JButton();
        lblSaldo = new javax.swing.JLabel();
        panelCartones = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        lblPozoName = new javax.swing.JLabel();
        lblSaldoName = new javax.swing.JLabel();
        lblSaldoActual = new javax.swing.JLabel();
        lblPozo = new javax.swing.JLabel();
        lblJugadoresName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listJugadores = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        lblBolilla = new javax.swing.JLabel();
        panelMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel2.setText("Contrasenia");

        txtUsuario.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

        btnIngresar.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnIngresar.setText("Ingresar");

        lblCantCartones.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblCantCartones.setText("Cantidad de cartones");

        txtCantCartones.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelLoginJugadorLayout = new javax.swing.GroupLayout(panelLoginJugador);
        panelLoginJugador.setLayout(panelLoginJugadorLayout);
        panelLoginJugadorLayout.setHorizontalGroup(
            panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                        .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(lblCantCartones))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLoginJugadorLayout.setVerticalGroup(
            panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtPassword)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantCartones))
                .addGap(23, 23, 23)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jLabel3.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel3.setText("Esperando inicio del juego...");

        javax.swing.GroupLayout panelEsperaLayout = new javax.swing.GroupLayout(panelEspera);
        panelEspera.setLayout(panelEsperaLayout);
        panelEsperaLayout.setHorizontalGroup(
            panelEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEsperaLayout.setVerticalGroup(
            panelEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperaLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        lblContinuarJugando.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblContinuarJugando.setText("¿Desea continuar jugando? ");

        btnSI.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnSI.setText("SI");

        btnNO.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnNO.setText("NO");

        javax.swing.GroupLayout panelContinuarJugandoLayout = new javax.swing.GroupLayout(panelContinuarJugando);
        panelContinuarJugando.setLayout(panelContinuarJugandoLayout);
        panelContinuarJugandoLayout.setHorizontalGroup(
            panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addGroup(panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnSI, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnNO, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblContinuarJugando)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        panelContinuarJugandoLayout.setVerticalGroup(
            panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContinuarJugando)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNO)
                    .addComponent(btnSI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelCartonesLayout = new javax.swing.GroupLayout(panelCartones);
        panelCartones.setLayout(panelCartonesLayout);
        panelCartonesLayout.setHorizontalGroup(
            panelCartonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        panelCartonesLayout.setVerticalGroup(
            panelCartonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        lblPozoName.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblPozoName.setText("Pozo:");

        lblSaldoName.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblSaldoName.setText("Saldo actual:");

        lblSaldoActual.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblSaldoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblPozo.setFont(new java.awt.Font("Yuanti SC", 1, 24)); // NOI18N
        lblPozo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblJugadoresName.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblJugadoresName.setText("Jugadores en el juego:");

        listJugadores.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        listJugadores.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listJugadores);

        jLabel4.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel4.setText("Bolilla sorteada:");

        lblBolilla.setBackground(new java.awt.Color(0, 0, 0));
        lblBolilla.setFont(new java.awt.Font("Yuanti SC", 1, 36)); // NOI18N
        lblBolilla.setForeground(new java.awt.Color(204, 255, 204));
        lblBolilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBolilla.setOpaque(true);

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoLayout.createSequentialGroup()
                        .addComponent(lblJugadoresName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPozoName)
                            .addComponent(lblSaldoName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPozo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lblBolilla, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblPozoName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBolilla, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(lblPozo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSaldoName)
                            .addComponent(lblSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJugadoresName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMensaje.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(0, 102, 102));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelMensajeLayout = new javax.swing.GroupLayout(panelMensaje);
        panelMensaje.setLayout(panelMensajeLayout);
        panelMensajeLayout.setHorizontalGroup(
            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMensajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMensajeLayout.setVerticalGroup(
            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMensajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCartones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelContinuarJugando, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(panelMensaje, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelEspera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCartones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnNO;
    private javax.swing.JButton btnSI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBolilla;
    private javax.swing.JLabel lblCantCartones;
    private javax.swing.JLabel lblContinuarJugando;
    private javax.swing.JLabel lblJugadoresName;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblPozo;
    private javax.swing.JLabel lblPozoName;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblSaldoActual;
    private javax.swing.JLabel lblSaldoName;
    private javax.swing.JList listJugadores;
    private javax.swing.JPanel panelCartones;
    private javax.swing.JPanel panelContinuarJugando;
    private javax.swing.JPanel panelEspera;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelLoginJugador;
    private javax.swing.JPanel panelMensaje;
    private javax.swing.JTextField txtCantCartones;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
