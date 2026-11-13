package co.edu.poligran.paradigmas.polirestaurante.formularios;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoPedido;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.vista.PedidoVista;

import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import co.edu.poligran.paradigmas.polirestaurante.tablas.ModeloTablaPedido;

public class DialogoPedido extends JFrame {

    private final PedidoVista vista;

    private JTextField txtId;
    private JTextField txtCantidad;
    private JTextField txtMesa;
    private JTextField txtDetalles;

    private JComboBox<Producto> cmbProducto;
    private JComboBox<EstadoPedido> cmbEstado;

    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnListar;
    private JButton btnCambiarEstado;

    private JTable tablaPedidos;
    private ModeloTablaPedido modeloTabla;

    private JLabel lblEstadisticas;

    public DialogoPedido() {
        this(new ErikManager());
    }

    public DialogoPedido(ErikManager operaciones) {

        vista = new PedidoVista(operaciones);

        initComponents();
        configurarApariencia();
        configurarEventos();
        cargarProductos();
        cargarPedidos();

        setTitle("Gestión de pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
    }

    // Los componentes de este bloque se editan desde NetBeans > Design.
    // GEN-BEGIN:initComponents
    private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        panelCentro = new javax.swing.JPanel();
        panelSuperior = new javax.swing.JPanel();
        panelFormulario = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblMesa = new javax.swing.JLabel();
        txtMesa = new javax.swing.JTextField();
        lblDetalles = new javax.swing.JLabel();
        txtDetalles = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        panelBotones = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnCambiarEstado = new javax.swing.JButton();
        scrollPedidos = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        lblEstadisticas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de pedidos");
        getContentPane().setLayout(new java.awt.BorderLayout());

        panelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 20, 16, 20));
        panelPrincipal.setLayout(new java.awt.BorderLayout(0, 14));

        lblTitulo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("GESTIÓN DE PEDIDOS");
        panelPrincipal.add(lblTitulo, java.awt.BorderLayout.NORTH);

        panelCentro.setLayout(new java.awt.BorderLayout(0, 14));
        panelSuperior.setLayout(new java.awt.BorderLayout(0, 10));
        panelFormulario.setLayout(new java.awt.GridLayout(3, 4, 12, 8));

        lblId.setText("ID del pedido:");
        panelFormulario.add(lblId);
        txtId.setEditable(false);
        txtId.setText("Automático");
        panelFormulario.add(txtId);
        lblProducto.setText("Producto:");
        panelFormulario.add(lblProducto);
        panelFormulario.add(cmbProducto);
        lblCantidad.setText("Cantidad:");
        panelFormulario.add(lblCantidad);
        panelFormulario.add(txtCantidad);
        lblMesa.setText("Mesa / Modalidad:");
        panelFormulario.add(lblMesa);
        panelFormulario.add(txtMesa);
        lblDetalles.setText("Detalles:");
        panelFormulario.add(lblDetalles);
        panelFormulario.add(txtDetalles);
        lblEstado.setText("Estado:");
        panelFormulario.add(lblEstado);
        panelFormulario.add(cmbEstado);
        panelSuperior.add(panelFormulario, java.awt.BorderLayout.CENTER);

        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 4));
        btnAgregar.setText("Agregar");
        panelBotones.add(btnAgregar);
        btnBuscar.setText("Buscar");
        panelBotones.add(btnBuscar);
        btnActualizar.setText("Actualizar");
        panelBotones.add(btnActualizar);
        btnEliminar.setText("Eliminar");
        panelBotones.add(btnEliminar);
        btnLimpiar.setText("Limpiar");
        panelBotones.add(btnLimpiar);
        btnListar.setText("Listar");
        panelBotones.add(btnListar);
        btnCambiarEstado.setText("Cambiar estado");
        panelBotones.add(btnCambiarEstado);
        panelSuperior.add(panelBotones, java.awt.BorderLayout.SOUTH);
        panelCentro.add(panelSuperior, java.awt.BorderLayout.NORTH);

        scrollPedidos.setViewportView(tablaPedidos);
        panelCentro.add(scrollPedidos, java.awt.BorderLayout.CENTER);
        lblEstadisticas.setText("Pedidos: 0 | Pendientes: 0 | Entregados: 0");
        panelCentro.add(lblEstadisticas, java.awt.BorderLayout.SOUTH);
        panelPrincipal.add(panelCentro, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);
        pack();
    }
    // GEN-END:initComponents

    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JScrollPane scrollPedidos;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblEstado;

    private void configurarApariencia() {
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(EstadoPedido.values()));
        modeloTabla = new ModeloTablaPedido();
        tablaPedidos.setModel(modeloTabla);
        tablaPedidos.setRowHeight(26);
        tablaPedidos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.setAutoCreateRowSorter(true);
        setMinimumSize(new java.awt.Dimension(920, 580));
        setSize(1120, 700);
    }

    // CONFIGURAR EVENTOS
    private void configurarEventos() {

        btnAgregar.addActionListener(e -> agregarPedido());

        btnBuscar.addActionListener(e -> buscarPedido());

        btnActualizar.addActionListener(e -> actualizarPedido());

        btnEliminar.addActionListener(e -> eliminarPedido());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnListar.addActionListener(e -> cargarPedidos());

        btnCambiarEstado.addActionListener(e -> cambiarEstado());

        tablaPedidos.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                cargarSeleccionado();
            }
        });

        // Mostrar el nombre del producto en el JComboBox.
        ListCellRenderer<? super Producto> renderizador =
                new DefaultListCellRenderer() {

            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> lista,
                    Object valor,
                    int indice,
                    boolean seleccionado,
                    boolean enfocado) {

                super.getListCellRendererComponent(
                        lista,
                        valor,
                        indice,
                        seleccionado,
                        enfocado
                );

                if (valor instanceof Producto) {
                    setText(((Producto) valor).getNombre());
                }

                return this;
            }
        };

        cmbProducto.setRenderer(renderizador);
    }

    // CARGAR PRODUCTOS DISPONIBLES
    private void cargarProductos() {

        cmbProducto.removeAllItems();

        List<Producto> productos = vista.listarProductosDisponibles();

        for (Producto producto : productos) {
            cmbProducto.addItem(producto);
        }
    }

    // AGREGAR PEDIDO
    private void agregarPedido() {

        try {

            Producto producto = (Producto) cmbProducto.getSelectedItem();

            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            Pedido pedido = vista.agregarPedido(
                    producto,
                    cantidad,
                    txtMesa.getText(),
                    txtDetalles.getText()
            );

            if (pedido != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pedido registrado correctamente.\nID: "
                        + pedido.getId()
                );

                cargarPedidos();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el pedido."
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingresa una cantidad válida."
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private int obtenerIdPedido() {
        String valor = txtId.getText().trim();
        if (valor.isEmpty() || "Automático".equalsIgnoreCase(valor)) {
            String respuesta = JOptionPane.showInputDialog(this, "Ingresa el ID del pedido:");
            if (respuesta == null) {
                throw new IllegalArgumentException("Operación cancelada.");
            }
            valor = respuesta.trim();
        }
        try {
            int id = Integer.parseInt(valor);
            if (id <= 0) throw new NumberFormatException();
            return id;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Ingresa un ID numérico mayor que cero.");
        }
    }

    // BUSCAR PEDIDO
    private void buscarPedido() {

        try {

            int id = obtenerIdPedido();

            Pedido pedido = vista.buscarPedidoPorId(id);

            if (pedido != null) {
                mostrarPedido(pedido);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el pedido."
                );
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ACTUALIZAR PEDIDO
    private void actualizarPedido() {

        try {

            int id = obtenerIdPedido();

            Producto producto = (Producto) cmbProducto.getSelectedItem();

            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            EstadoPedido estado = (EstadoPedido) cmbEstado.getSelectedItem();

            boolean resultado = vista.actualizarPedido(
                    id,
                    producto,
                    cantidad,
                    txtMesa.getText(),
                    txtDetalles.getText(),
                    estado
            );

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pedido actualizado correctamente."
                );

                cargarPedidos();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el pedido."
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Verifica el ID y la cantidad."
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ELIMINAR PEDIDO
    private void eliminarPedido() {

        try {

            int id = obtenerIdPedido();

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas eliminar este pedido y sus detalles?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                boolean resultado = vista.eliminarPedido(id);

                if (resultado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Pedido eliminado correctamente."
                    );

                    cargarPedidos();
                    limpiarCampos();

                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar el pedido."
                    );
                }
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // CAMBIAR ESTADO
    private void cambiarEstado() {

        try {

            int id = obtenerIdPedido();

            EstadoPedido estado = (EstadoPedido) cmbEstado.getSelectedItem();

            boolean resultado = vista.cambiarEstadoPedido(id, estado);

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Estado actualizado correctamente."
                );

                cargarPedidos();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el estado."
                );
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // LISTAR PEDIDOS
    private void cargarPedidos() {

        modeloTabla.setDatos(vista.listarPedidos());

        actualizarEstadisticas();
    }

    // MOSTRAR PEDIDO SELECCIONADO
    private void cargarSeleccionado() {

        int fila = tablaPedidos.getSelectedRow();

        if (fila >= 0) {

            int filaModelo = tablaPedidos.convertRowIndexToModel(fila);
            Pedido pedido = modeloTabla.getFila(filaModelo);

            if (pedido != null) {
                mostrarPedido(pedido);
            }
        }
    }

    // MOSTRAR INFORMACIÓN
    private void mostrarPedido(Pedido pedido) {

        txtId.setText(String.valueOf(pedido.getId()));

        if (pedido.getProductoPedido() != null) {
            seleccionarProducto(pedido.getProductoPedido().getId());
        }

        txtCantidad.setText(String.valueOf(pedido.getCantidad()));
        txtMesa.setText(pedido.getMesa());
        txtDetalles.setText(pedido.getDetalles());

        cmbEstado.setSelectedItem(pedido.getEstado());
    }

    private void seleccionarProducto(int idProducto) {

        for (int i = 0; i < cmbProducto.getItemCount(); i++) {

            Producto producto = cmbProducto.getItemAt(i);

            if (producto.getId() == idProducto) {
                cmbProducto.setSelectedIndex(i);
                return;
            }
        }
        Producto encontrado = vista.getOperaciones().buscarProductoPorId(idProducto);
        if (encontrado != null) {
            cmbProducto.addItem(encontrado);
            cmbProducto.setSelectedItem(encontrado);
        }
    }

    // ESTADÍSTICAS
    private void actualizarEstadisticas() {

        int total = vista.contarPedidos();

        int pendientes = vista.contarPedidosPorEstado(
                EstadoPedido.PENDIENTE
        );

        int entregados = vista.contarPedidosPorEstado(
                EstadoPedido.ENTREGADO
        );

        lblEstadisticas.setText(
                "Pedidos: " + total
                + " | Pendientes: " + pendientes
                + " | Entregados: " + entregados
        );
    }

    // LIMPIAR CAMPOS
    private void limpiarCampos() {

        txtId.setText("Automático");
        txtCantidad.setText("");
        txtMesa.setText("");
        txtDetalles.setText("");

        if (cmbProducto.getItemCount() > 0) {
            cmbProducto.setSelectedIndex(0);
        }

        cmbEstado.setSelectedItem(EstadoPedido.PENDIENTE);

        tablaPedidos.clearSelection();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() ->
                new DialogoPedido().setVisible(true)
        );
    }
}
