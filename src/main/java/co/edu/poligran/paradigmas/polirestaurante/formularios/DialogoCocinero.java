package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.vista.CocineroVista;

public class DialogoCocinero extends javax.swing.JFrame {
    private CocineroVista vista;
    public DialogoCocinero() { this(new AngieManager(), null); }
    public DialogoCocinero(ErikManager operaciones) { this(operaciones.getAngieManager(), operaciones); }
    public DialogoCocinero(AngieManager catalogo, ErikManager operaciones) {
        initComponents();
        if (operaciones == null) operaciones = new ErikManager(catalogo);
        vista = new CocineroVista(catalogo, operaciones);
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panelBotones = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnIngredientes = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PoliRestaurante - Cocinero");
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 20));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("MENU COCINERO");
        getContentPane().add(lblTitulo, java.awt.BorderLayout.NORTH);

        panelBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panelBotones.setLayout(new java.awt.GridLayout(2, 1, 10, 10));

        btnIngredientes.setText("Ingredientes");
        btnIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngredientesActionPerformed(evt);
            }
        });
        panelBotones.add(btnIngredientes);

        btnPedidos.setText("Pedidos");
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });
        panelBotones.add(btnPedidos);

        getContentPane().add(panelBotones, java.awt.BorderLayout.CENTER);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(400, 280));
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngredientesActionPerformed(java.awt.event.ActionEvent evt) {
        vista.abrirIngredientes();
    }

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {
        vista.abrirPedidos();
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new DialogoCocinero().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngredientes;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
