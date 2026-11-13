package co.edu.poligran.paradigmas.polirestaurante.formularios;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoPedido;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.vista.PedidoVista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
    private DefaultTableModel modeloTabla;

    private JLabel lblEstadisticas;

    public DialogoPedido() {
        this(new ErikManager());
    }

    public DialogoPedido(ErikManager operaciones) {

        vista = new PedidoVista(operaciones);

        construirFormulario();
        configurarEventos();
        cargarProductos();
        cargarPedidos();

        setTitle("Gestión de pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
    }

    // CONSTRUIR FORMULARIO
    private void construirFormulario() {

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel(
                "GESTIÓN DE PEDIDOS",
                SwingConstants.CENTER
        );

        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));

        principal.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(4, 4, 10, 10));

        txtId = new JTextField();
        txtCantidad = new JTextField();
        txtMesa = new JTextField();
        txtDetalles = new JTextField();

        cmbProducto = new JComboBox<>();
        cmbEstado = new JComboBox<>(EstadoPedido.values());

        formulario.add(new JLabel("ID del pedido:"));
        formulario.add(txtId);

        formulario.add(new JLabel("Producto:"));
        formulario.add(cmbProducto);

        formulario.add(new JLabel("Cantidad:"));
        formulario.add(txtCantidad);

        formulario.add(new JLabel("Mesa / Modalidad:"));
        formulario.add(txtMesa);

        formulario.add(new JLabel("Detalles:"));
        formulario.add(txtDetalles);

        formulario.add(new JLabel("Estado:"));
        formulario.add(cmbEstado);

        JPanel contenedorFormulario = new JPanel(new BorderLayout());
        contenedorFormulario.add(formulario, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));

        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnListar = new JButton("Listar");
        btnCambiarEstado = new JButton("Cambiar estado");

        botones.add(btnAgregar);
        botones.add(btnBuscar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        botones.add(btnListar);
        botones.add(btnCambiarEstado);

        contenedorFormulario.add(botones, BorderLayout.SOUTH);

        // TABLA
        modeloTabla = new DefaultTableModel(
                new Object[]{
                    "ID",
                    "Producto",
                    "Cantidad",
                    "Costo",
                    "Mesa",
                    "Detalles",
                    "Estado"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaPedidos = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaPedidos);

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.add(contenedorFormulario, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        lblEstadisticas = new JLabel(
                "Pedidos: 0 | Pendientes: 0 | Entregados: 0"
        );

        centro.add(lblEstadisticas, BorderLayout.SOUTH);

        principal.add(centro, BorderLayout.CENTER);

        add(principal);
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

    // BUSCAR PEDIDO
    private void buscarPedido() {

        try {

            int id = Integer.parseInt(txtId.getText().trim());

            Pedido pedido = vista.buscarPedidoPorId(id);

            if (pedido != null) {
                mostrarPedido(pedido);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el pedido."
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingresa un ID válido."
            );
        }
    }

    // ACTUALIZAR PEDIDO
    private void actualizarPedido() {

        try {

            int id = Integer.parseInt(txtId.getText().trim());

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

            int id = Integer.parseInt(txtId.getText().trim());

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

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un pedido válido."
            );
        }
    }

    // CAMBIAR ESTADO
    private void cambiarEstado() {

        try {

            int id = Integer.parseInt(txtId.getText().trim());

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

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un pedido válido."
            );
        }
    }

    // LISTAR PEDIDOS
    private void cargarPedidos() {

        modeloTabla.setRowCount(0);

        List<Pedido> pedidos = vista.listarPedidos();

        for (Pedido pedido : pedidos) {

            String nombreProducto = "Sin producto";

            if (pedido.getProductoPedido() != null) {
                nombreProducto = pedido.getProductoPedido().getNombre();
            }

            modeloTabla.addRow(new Object[]{
                pedido.getId(),
                nombreProducto,
                pedido.getCantidad(),
                pedido.getCosto(),
                pedido.getMesa(),
                pedido.getDetalles(),
                pedido.getEstado()
            });
        }

        actualizarEstadisticas();
    }

    // MOSTRAR PEDIDO SELECCIONADO
    private void cargarSeleccionado() {

        int fila = tablaPedidos.getSelectedRow();

        if (fila >= 0) {

            int id = Integer.parseInt(
                    modeloTabla.getValueAt(fila, 0).toString()
            );

            Pedido pedido = vista.buscarPedidoPorId(id);

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

        txtId.setText("");
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
