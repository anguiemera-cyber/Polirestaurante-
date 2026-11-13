package co.edu.poligran.paradigmas.polirestaurante.formularios;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DialogoProducto extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(DialogoProducto.class.getName());

    private final ErikManager operaciones;
    private DefaultTableModel modeloTabla;

    public DialogoProducto() {
        this(new ErikManager());
    }

    public DialogoProducto(ErikManager operaciones) {

        if (operaciones == null) {
            throw new IllegalArgumentException(
                    "ErikManager no puede ser nulo."
            );
        }

        this.operaciones = operaciones;

        initComponents();
        configurarFormulario();
        configurarTabla();
        cargarProductos();
    }

    private void configurarFormulario() {

        setTitle("Gestión de productos");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtId.setEditable(false);
        chkDisponible.setSelected(true);

        btnAgregar.addActionListener(e -> agregarProducto());
        btnBuscar.addActionListener(e -> buscarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnListar.addActionListener(e -> cargarProductos());
    }

    private void configurarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{
                    "ID",
                    "Nombre",
                    "Tipo",
                    "Descripción",
                    "Precio",
                    "Disponible"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblProductos.setModel(modeloTabla);

        tblProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarProductoSeleccionado();
            }
        });
    }

    private void agregarProducto() {

        try {

            if (!validarCampos()) {
                return;
            }

            String nombre = txtNombre.getText().trim();
            String tipo = cmbTipo.getSelectedItem().toString();
            String descripcion = txtDescripcion.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());

            Producto producto = new Producto(
                    nombre,
                    tipo,
                    descripcion,
                    precio
            );

            Producto registrado = operaciones.crearProducto(producto);

            if (registrado != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto registrado correctamente.\nID: "
                        + registrado.getId()
                );

                cargarProductos();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar el producto."
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "El precio debe ser un número válido."
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void buscarProducto() {

        try {

            if (txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona un producto de la tabla para consultarlo."
                );
                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());

            Producto producto = operaciones.buscarProductoPorId(id);

            if (producto != null) {
                mostrarDatos(producto);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el producto."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void actualizarProducto() {

        try {

            if (txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona un producto para actualizar."
                );
                return;
            }

            if (!validarCampos()) {
                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());

            Producto producto = new Producto(
                    id,
                    txtNombre.getText().trim(),
                    cmbTipo.getSelectedItem().toString(),
                    txtDescripcion.getText().trim(),
                    Double.parseDouble(txtPrecio.getText().trim()),
                    chkDisponible.isSelected()
            );

            boolean actualizado = operaciones.actualizarProducto(producto);

            if (actualizado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto actualizado correctamente."
                );

                cargarProductos();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible actualizar el producto."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Verifica el ID y el precio."
            );
        }
    }

    private void eliminarProducto() {

        try {

            if (txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona un producto para eliminar."
                );
                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de eliminar este producto?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                boolean eliminado = operaciones.eliminarProducto(id);

                if (eliminado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto eliminado correctamente."
                    );

                    cargarProductos();
                    limpiarCampos();

                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar. Puede estar asociado a un pedido."
                    );
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void cargarProductos() {

        modeloTabla.setRowCount(0);

        List<Producto> productos = operaciones.listarProductos();

        for (Producto producto : productos) {

            modeloTabla.addRow(new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getTipo(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.isDisponible() ? "Sí" : "No"
            });
        }
    }

    private void cargarProductoSeleccionado() {

        int fila = tblProductos.getSelectedRow();

        if (fila >= 0) {

            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            cmbTipo.setSelectedItem(modeloTabla.getValueAt(fila, 2).toString());
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtPrecio.setText(modeloTabla.getValueAt(fila, 4).toString());

            chkDisponible.setSelected(
                    modeloTabla.getValueAt(fila, 5).toString().equals("Sí")
            );
        }
    }

    private void mostrarDatos(Producto producto) {

        txtId.setText(String.valueOf(producto.getId()));
        txtNombre.setText(producto.getNombre());
        cmbTipo.setSelectedItem(producto.getTipo());
        txtDescripcion.setText(producto.getDescripcion());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        chkDisponible.setSelected(producto.isDisponible());
    }

    private boolean validarCampos() {

        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre.");
            txtNombre.requestFocus();
            return false;
        }

        if (cmbTipo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecciona el tipo.");
            return false;
        }

        if (txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el precio.");
            txtPrecio.requestFocus();
            return false;
        }

        try {
            double precio = Double.parseDouble(txtPrecio.getText().trim());

            if (precio < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "El precio no puede ser negativo."
                );
                return false;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingresa un precio numérico válido."
            );
            txtPrecio.requestFocus();
            return false;
        }

        return true;
    }

    private void limpiarCampos() {

        txtId.setText("");
        txtNombre.setText("");
        cmbTipo.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtPrecio.setText("");
        chkDisponible.setSelected(true);

        tblProductos.clearSelection();
        txtNombre.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();

        chkDisponible = new javax.swing.JCheckBox();

        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 22));
        jLabel1.setText("GESTIÓN DE PRODUCTOS");

        jLabel2.setText("ID:");
        jLabel3.setText("Nombre:");
        jLabel4.setText("Tipo:");
        jLabel5.setText("Descripción:");
        jLabel6.setText("Precio:");

        txtId.setEditable(false);

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Comida", "Bebida"}
        ));

        chkDisponible.setText("Disponible");
        chkDisponible.setSelected(true);

        btnAgregar.setText("Agregar");
        btnBuscar.setText("Buscar");
        btnActualizar.setText("Actualizar");
        btnEliminar.setText("Eliminar");
        btnLimpiar.setText("Limpiar");
        btnListar.setText("Listar");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Nombre", "Tipo", "Descripción", "Precio", "Disponible"
                }
        ));
        jScrollPane1.setViewportView(tblProductos);

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkDisponible)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(8)
                        .addComponent(btnBuscar)
                        .addGap(8)
                        .addComponent(btnActualizar)
                        .addGap(8)
                        .addComponent(btnEliminar)
                        .addGap(8)
                        .addComponent(btnLimpiar)
                        .addGap(8)
                        .addComponent(btnListar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGap(20)
            .addComponent(jLabel1)
            .addGap(15)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8)
                    .addComponent(jLabel4)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(chkDisponible)))
            .addGap(20)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAgregar)
                .addComponent(btnBuscar)
                .addComponent(btnActualizar)
                .addComponent(btnEliminar)
                .addComponent(btnLimpiar)
                .addComponent(btnListar))
            .addGap(20)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() ->
                new DialogoProducto().setVisible(true)
        );
    }

    // Variables declaration
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;

    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPrecio;

    private javax.swing.JCheckBox chkDisponible;

    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListar;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    // End of variables declaration
}
