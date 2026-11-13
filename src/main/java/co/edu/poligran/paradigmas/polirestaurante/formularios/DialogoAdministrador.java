package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;
import co.edu.poligran.paradigmas.polirestaurante.vista.AdministradorVista;

public class DialogoAdministrador extends javax.swing.JFrame {
    private AdministradorVista vista;
    public DialogoAdministrador() { this(new AngieManager(), null, new JuanManager()); }
    public DialogoAdministrador(ErikManager operaciones) { this(operaciones.getAngieManager(), operaciones, new JuanManager()); }
    public DialogoAdministrador(AngieManager catalogo, ErikManager operaciones, JuanManager personal) {
        initComponents();
        if (operaciones == null) operaciones = new ErikManager(catalogo);
        vista = new AdministradorVista(catalogo, operaciones, personal);
        setLocationRelativeTo(null);
    }

    private void abrir(String modulo) {
        try {
            javax.swing.JFrame ventana = vista.abrirModulo(modulo);
            ventana.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            ventana.setLocationRelativeTo(this);
            ventana.setVisible(true);
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panelBotones = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnPersonal = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnMesas = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        btnIngredientes = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PoliRestaurante - Administrador");
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 20));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("MENU ADMINISTRADOR");
        getContentPane().add(lblTitulo, java.awt.BorderLayout.NORTH);

        panelBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panelBotones.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

        btnPersonal.setText("Personal");
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });
        panelBotones.add(btnPersonal);

        btnPedidos.setText("Pedidos");
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });
        panelBotones.add(btnPedidos);

        btnProductos.setText("Productos");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        panelBotones.add(btnProductos);

        btnMesas.setText("Mesas");
        btnMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesasActionPerformed(evt);
            }
        });
        panelBotones.add(btnMesas);

        btnCategorias.setText("Categorías");
        btnCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriasActionPerformed(evt);
            }
        });
        panelBotones.add(btnCategorias);

        btnIngredientes.setText("Ingredientes");
        btnIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngredientesActionPerformed(evt);
            }
        });
        panelBotones.add(btnIngredientes);

        btnDetalles.setText("Detalles de pedido");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        panelBotones.add(btnDetalles);

        getContentPane().add(panelBotones, java.awt.BorderLayout.CENTER);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(540, 400));
    }// </editor-fold>//GEN-END:initComponents

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {
        String[] opciones = {"Administradores", "Meseros", "Cocineros"};
        String opcion = (String) javax.swing.JOptionPane.showInputDialog(this,
                "Seleccione una opcion", "Personal", javax.swing.JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        if (opcion != null) abrir(opcion);
    }

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {
        abrir("Pedidos");
    }

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {
        abrir("Productos");
    }

    private void btnMesasActionPerformed(java.awt.event.ActionEvent evt) {
        abrir("Mesas");
    }

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {
        abrir("Categorías");
    }

    private void btnIngredientesActionPerformed(java.awt.event.ActionEvent evt) {
        abrir("Ingredientes");
    }

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {
        abrir("Detalles de pedido");
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new DialogoAdministrador().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPersonal;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnIngredientes;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
