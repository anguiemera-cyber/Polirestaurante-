package co.edu.poligran.paradigmas.polirestaurante.formularios;

/** Formulario visual de inicio de sesión de PoliRestaurante. */
public class FormularioInicioSesion extends javax.swing.JFrame {

    public FormularioInicioSesion() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        panelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        lblRol = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        panelBotones = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PoliRestaurante - Inicio de sesión");
        setResizable(false);

        getContentPane().setBackground(new java.awt.Color(245, 247, 250));
        getContentPane().setLayout(new java.awt.BorderLayout(12, 18));

        panelTitulo.setBackground(new java.awt.Color(35, 55, 80));
        panelTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 12, 18, 12));
        panelTitulo.setLayout(new java.awt.GridLayout(2, 1, 0, 5));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 25));
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("POLIRESTAURANTE");
        panelTitulo.add(lblTitulo);
        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblSubtitulo.setForeground(new java.awt.Color(220, 230, 240));
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("Sistema de gestión de pedidos");
        panelTitulo.add(lblSubtitulo);
        getContentPane().add(panelTitulo, java.awt.BorderLayout.NORTH);

        panelFormulario.setBackground(java.awt.Color.WHITE);
        panelFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 18, 20, 18));
        panelFormulario.setLayout(new java.awt.GridLayout(3, 2, 10, 16));
        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblUsuario.setText("Usuario:");
        panelFormulario.add(lblUsuario);
        txtUsuario.setColumns(20);
        panelFormulario.add(txtUsuario);
        lblContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblContrasena.setText("Contraseña:");
        panelFormulario.add(lblContrasena);
        txtContrasena.setColumns(20);
        panelFormulario.add(txtContrasena);
        lblRol.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblRol.setText("Tipo de usuario:");
        panelFormulario.add(lblRol);
        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Mesero", "Cocinero" }));
        panelFormulario.add(cmbRol);
        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);

        panelBotones.setOpaque(false);
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 4));
        btnIngresar.setBackground(new java.awt.Color(35, 100, 155));
        btnIngresar.setForeground(java.awt.Color.WHITE);
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(evt -> btnIngresarActionPerformed(evt));
        panelBotones.add(btnIngresar);
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(evt -> btnLimpiarActionPerformed(evt));
        panelBotones.add(btnLimpiar);
        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("Ingrese sus datos para acceder");
        panelBotones.add(lblEstado);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
    }// </editor-fold>

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {
        lblEstado.setText("Formulario visual: inicio de sesión aún no conectado.");
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        txtUsuario.setText("");
        txtContrasena.setText("");
        cmbRol.setSelectedIndex(0);
        lblEstado.setText("Campos limpiados.");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormularioInicioSesion().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration
}
