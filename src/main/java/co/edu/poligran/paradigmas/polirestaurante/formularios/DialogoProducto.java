package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.tablas.ModeloTablaProducto;
import co.edu.poligran.paradigmas.polirestaurante.vista.ProductoVista;
import javax.swing.JOptionPane;

/** JFrame editable desde NetBeans GUI Builder. */
public class DialogoProducto extends javax.swing.JFrame {
    private final ProductoVista vista;
    private final ModeloTablaProducto modelo = new ModeloTablaProducto();

    public DialogoProducto() { this(new ErikManager()); }

    public DialogoProducto(ErikManager operaciones) {
        vista = new ProductoVista(operaciones);
        initComponents();
        setLocationRelativeTo(null);
        tblProductos.setModel(modelo);
        txtId.setEditable(false);
        tblProductos.setAutoCreateRowSorter(true);
        tblProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarFila();
        });
        btnAgregar.addActionListener(e -> agregar());
        btnBuscar.addActionListener(e -> buscar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnListar.addActionListener(e -> listar());
        listar();
    }

    private void listar() { modelo.setDatos(vista.listar()); }

    private int idSeleccionado() {
        String valor = txtId.getText().trim();
        if (valor.isEmpty() || valor.equals("Automático"))
            throw new IllegalArgumentException("Selecciona un producto en la tabla.");
        try { return Integer.parseInt(valor); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("ID inválido."); }
    }

    private void agregar() {
        try {
            Producto p = vista.agregar(txtNombre.getText(),
                    (String) cmbTipo.getSelectedItem(), txtDescripcion.getText(),
                    txtPrecio.getText(), chkDisponible.isSelected());
            listar(); limpiar();
            JOptionPane.showMessageDialog(this, "Producto registrado. ID: " + p.getId());
        } catch (IllegalArgumentException ex) { error(ex); }
    }

    private void buscar() {
        try {
            Producto p = vista.buscar(idSeleccionado());
            if (p == null) throw new IllegalArgumentException("No se encontró el producto.");
            mostrar(p);
        } catch (IllegalArgumentException ex) { error(ex); }
    }

    private void actualizar() {
        try {
            boolean ok = vista.actualizar(idSeleccionado(), txtNombre.getText(),
                    (String) cmbTipo.getSelectedItem(), txtDescripcion.getText(),
                    txtPrecio.getText(), chkDisponible.isSelected());
            if (!ok) throw new IllegalArgumentException("No fue posible actualizar el producto.");
            listar(); limpiar();
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
        } catch (IllegalArgumentException ex) { error(ex); }
    }

    private void eliminar() {
        try {
            int id = idSeleccionado();
            if (JOptionPane.showConfirmDialog(this, "¿Eliminar este producto?",
                    "Confirmar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
            if (!vista.eliminar(id))
                throw new IllegalArgumentException("No se puede eliminar: puede estar asociado a un pedido.");
            listar(); limpiar();
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
        } catch (IllegalArgumentException ex) { error(ex); }
    }

    private void seleccionarFila() {
        int seleccion = tblProductos.getSelectedRow();
        if (seleccion < 0) return;
        Producto p = modelo.getFila(tblProductos.convertRowIndexToModel(seleccion));
        if (p != null) mostrar(p);
    }

    private void mostrar(Producto p) {
        txtId.setText(String.valueOf(p.getId()));
        txtNombre.setText(p.getNombre());
        cmbTipo.setSelectedItem(p.getTipo());
        txtDescripcion.setText(p.getDescripcion());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        chkDisponible.setSelected(p.isDisponible());
    }

    private void limpiar() {
        tblProductos.clearSelection();
        txtId.setText("Automático");
        txtNombre.setText("");
        cmbTipo.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtPrecio.setText("");
        chkDisponible.setSelected(true);
        txtNombre.requestFocusInWindow();
    }

    private void error(IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblDisponible = new javax.swing.JLabel();
        chkDisponible = new javax.swing.JCheckBox();
        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        panelPrincipal = new javax.swing.JPanel();
        panelSuperior = new javax.swing.JPanel();
        panelFormulario = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de productos");
        panelPrincipal.setLayout(new java.awt.BorderLayout(0, 14));
        panelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18));
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("GESTIÓN DE PRODUCTOS");
        panelPrincipal.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panelSuperior.setLayout(new java.awt.BorderLayout(0, 12));
        panelFormulario.setLayout(new java.awt.GridLayout(3, 4, 12, 8));
        lblId.setText("ID (automático):");
        panelFormulario.add(lblId);
        txtId.setEditable(false);
        txtId.setText("Automático");
        panelFormulario.add(txtId);
        lblNombre.setText("Nombre:");
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        lblTipo.setText("Tipo:");
        panelFormulario.add(lblTipo);
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Comida", "Bebida"}));
        panelFormulario.add(cmbTipo);
        lblDescripcion.setText("Descripción:");
        panelFormulario.add(lblDescripcion);
        panelFormulario.add(txtDescripcion);
        lblPrecio.setText("Precio:");
        panelFormulario.add(lblPrecio);
        panelFormulario.add(txtPrecio);
        lblDisponible.setText("Disponibilidad:");
        panelFormulario.add(lblDisponible);
        chkDisponible.setText("Disponible");
        chkDisponible.setSelected(true);
        panelFormulario.add(chkDisponible);
        panelSuperior.add(panelFormulario, java.awt.BorderLayout.CENTER);
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 4));
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
        panelSuperior.add(panelBotones, java.awt.BorderLayout.SOUTH);
        panelCentro = new javax.swing.JPanel();
        panelCentro.setLayout(new java.awt.BorderLayout(0, 14));
        panelCentro.add(panelSuperior, java.awt.BorderLayout.NORTH);
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Nombre", "Tipo", "Descripción", "Precio", "Disponible"}));
        jScrollPane1.setViewportView(tblProductos);
        panelCentro.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        panelPrincipal.add(panelCentro, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);
        setSize(new java.awt.Dimension(950, 640));
    }//GEN-END:initComponents
    // </editor-fold>

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new DialogoProducto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JLabel lblDisponible;
    private javax.swing.JCheckBox chkDisponible;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
