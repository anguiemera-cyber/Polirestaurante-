package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoIngrediente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Categoria;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.tablas.ModeloTablaIngrediente;

/**
 * Formulario (ventana) para gestionar ingredientes: Guardar, Actualizar,
 * Eliminar, Limpiar y Buscar.
 * <ul>
 *   <li><b>Guardar</b>: crea un registro nuevo (el ID se asigna solo; debe estar vacío).</li>
 *   <li><b>Actualizar</b>: modifica el registro cuyo ID está escrito.</li>
 *   <li><b>Eliminar</b>: borra el registro cuyo ID está escrito.</li>
 *   <li><b>Buscar</b>: carga en el formulario el registro cuyo ID está escrito.</li>
 *   <li><b>Limpiar</b>: vacía los campos para empezar un registro nuevo.</li>
 * </ul>
 */
public class FormularioIngrediente extends javax.swing.JFrame {

    private static final String TITULO_VALIDACION = "Validación";
    private static final String TITULO_RESULTADO = "Ingredientes";
    private static final String ID_LLENO = "Para crear un registro nuevo deje el ID vacío (use Limpiar). "
            + "Para modificar uno existente use Actualizar.";
    private static final String NO_ENCONTRADO = "No se encontró un ingrediente con ese ID.";


    /** Manager donde se guardan los ingredientes. */
    private final transient AngieManager manager;

    /** Crea el formulario con su propio manager (útil para probarlo solo). */
    public FormularioIngrediente() {
        this(new AngieManager());
    }

    /**
     * Crea el formulario.
     *
     * @param manager manager de Angie compartido con el resto del programa
     */
    public FormularioIngrediente(AngieManager manager) {
        this.manager = manager;
        initComponents();
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSeleccionado();
            }
        });
        modeloTabla.setNombreCategoria(id -> {
            Categoria c = manager.buscarCategoriaPorId(id);
            return c == null ? "(Sin categoría)" : c.getNombre();
        });
        actualizarTabla();
        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnBuscar.addActionListener(e -> buscar());
        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override public void windowGainedFocus(java.awt.event.WindowEvent e) { cargarCategorias(); actualizarTabla(); }
        });
        cargarCategorias();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        panelFormulario = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        lblId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblId.setText("ID:");
        panelFormulario.add(lblId);
        txtId = new javax.swing.JTextField();
        txtId.setColumns(22);
        panelFormulario.add(txtId);
        lblNombre = new javax.swing.JLabel();
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre.setText("Nombre:");
        panelFormulario.add(lblNombre);
        txtNombre = new javax.swing.JTextField();
        txtNombre.setColumns(22);
        panelFormulario.add(txtNombre);
        lblCategoria = new javax.swing.JLabel("Categoría:");
        lblCategoria.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        panelFormulario.add(lblCategoria);
        cmbCategoria = new javax.swing.JComboBox<>();
        panelFormulario.add(cmbCategoria);
        lblEstado = new javax.swing.JLabel();
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEstado.setText("Estado:");
        panelFormulario.add(lblEstado);
        cmbEstado = new javax.swing.JComboBox<>(EstadoIngrediente.values());
        panelFormulario.add(cmbEstado);
        lblCantidad = new javax.swing.JLabel();
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCantidad.setText("Cantidad:");
        panelFormulario.add(lblCantidad);
        txtCantidad = new javax.swing.JTextField();
        txtCantidad.setColumns(22);
        panelFormulario.add(txtCantidad);
        lblDisponible = new javax.swing.JLabel();
        lblDisponible.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDisponible.setText("Disponible:");
        panelFormulario.add(lblDisponible);
        chkDisponible = new javax.swing.JCheckBox();
        chkDisponible.setSelected(true);
        chkDisponible.setText("Sí");
        panelFormulario.add(chkDisponible);
        panelFormulario.setLayout(new java.awt.GridLayout(0, 2, 8, 8));
        panelFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 4, 12));
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 6));
        btnGuardar = new javax.swing.JButton();
        btnGuardar.setText("Guardar");
        panelBotones.add(btnGuardar);
        btnActualizar = new javax.swing.JButton();
        btnActualizar.setText("Actualizar");
        panelBotones.add(btnActualizar);
        btnEliminar = new javax.swing.JButton();
        btnEliminar.setText("Eliminar");
        panelBotones.add(btnEliminar);
        btnLimpiar = new javax.swing.JButton();
        btnLimpiar.setText("Limpiar");
        panelBotones.add(btnLimpiar);
        btnBuscar = new javax.swing.JButton();
        btnBuscar.setText("Buscar");
        panelBotones.add(btnBuscar);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Ingrediente");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        modeloTabla = new ModeloTablaIngrediente();
        tabla = new javax.swing.JTable(modeloTabla);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(25);
        scrollTabla = new javax.swing.JScrollPane(tabla);
        scrollTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingredientes registrados"));
        scrollTabla.setPreferredSize(new java.awt.Dimension(620, 230));
        getContentPane().add(panelFormulario, java.awt.BorderLayout.NORTH);
        getContentPane().add(scrollTabla, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
        setMinimumSize(getSize());
    }
    // </editor-fold>

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormularioIngrediente().setVisible(true));
    }

    // ------------------------------------------------------------------ Acciones

    private void guardar() {
        if (!txtId.getText().trim().isEmpty()) {
            advertir(ID_LLENO, txtId);
            return;
        }
        String nombre = leerTexto(txtNombre, "El nombre");
        if (nombre == null) {
            return;
        }
        if (manager.buscarIngrediente(nombre) != null) {
            advertir("Ya existe un ingrediente con ese nombre.", txtNombre);
            return;
        }
        Integer estado = leerEstado();
        if (estado == null) {
            return;
        }
        Integer cantidad = leerEntero(txtCantidad, "La cantidad", 0);
        if (cantidad == null) {
            return;
        }
        try {
            Categoria categoria = (Categoria) cmbCategoria.getSelectedItem();
            if (categoria == null) { advertir("Primero registre y seleccione una categoría.", cmbCategoria); return; }
            Ingrediente nuevo = new Ingrediente(nombre, estado, cantidad, chkDisponible.isSelected());
            nuevo.setIdCategoria(categoria.getId());
            Ingrediente ingrediente = manager.crearIngrediente(nuevo);
            txtId.setText(String.valueOf(ingrediente.getId()));
            actualizarTabla();
            seleccionarEnTabla(ingrediente.getId());
            informar("Ingrediente creado con id: " + ingrediente.getId());
        } catch (IllegalArgumentException e) {
            advertir(e.getMessage(), txtNombre);
        }
    }

    private void actualizar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        if (manager.buscarIngredientePorId(id) == null) {
            advertir(NO_ENCONTRADO, txtId);
            return;
        }
        String nombre = leerTexto(txtNombre, "El nombre");
        if (nombre == null) {
            return;
        }
        Ingrediente existente = manager.buscarIngrediente(nombre);
        if (existente != null && existente.getId() != id) {
            advertir("Ya existe otro ingrediente con ese nombre.", txtNombre);
            return;
        }
        Integer estado = leerEstado();
        if (estado == null) {
            return;
        }
        Integer cantidad = leerEntero(txtCantidad, "La cantidad", 0);
        if (cantidad == null) {
            return;
        }
        Categoria categoria = (Categoria) cmbCategoria.getSelectedItem();
        if (categoria == null) { advertir("Seleccione una categoría.", cmbCategoria); return; }
        Ingrediente datos = new Ingrediente(id, nombre, estado, cantidad, chkDisponible.isSelected());
        datos.setIdCategoria(categoria.getId());
        if (manager.actualizarIngrediente(datos)) {
            actualizarTabla();
            seleccionarEnTabla(id);
            informar("Ingrediente actualizado correctamente.");
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void eliminar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        if (!confirmar("¿Seguro que desea eliminar el ingrediente " + id + "?")) {
            return;
        }
        if (manager.eliminarIngrediente(id)) {
            limpiar();
            actualizarTabla();
            informar("Ingrediente eliminado.");
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        cargarCategorias();
        cmbEstado.setSelectedIndex(0);
        txtCantidad.setText("");
        chkDisponible.setSelected(true);
        tabla.clearSelection();
        txtId.requestFocusInWindow();
    }

    /** Busca por ID; si el ID está vacío y hay un nombre escrito, busca por nombre. */
    private void buscar() {
        buscarRegistro();
        try {
            seleccionarEnTabla(Integer.parseInt(txtId.getText().trim()));
        } catch (NumberFormatException e) {
            // No hay un id válido que resaltar.
        }
    }

    private void buscarRegistro() {
        Ingrediente ingrediente;
        if (txtId.getText().trim().isEmpty() && !txtNombre.getText().trim().isEmpty()) {
            ingrediente = manager.buscarIngrediente(txtNombre.getText().trim());
            if (ingrediente == null) {
                advertir("No se encontró un ingrediente con ese nombre.", txtNombre);
                return;
            }
        } else {
            Integer id = leerEntero(txtId, "El ID", 1);
            if (id == null) {
                return;
            }
            ingrediente = manager.buscarIngredientePorId(id);
            if (ingrediente == null) {
                advertir(NO_ENCONTRADO, txtId);
                return;
            }
        }
        mostrar(ingrediente);
    }

    // -------------------------------------------------------------------- Tabla

    /** Vuelve a cargar la tabla con todos los registros del manager. */
    private void cargarCategorias() {
        int anterior = -1;
        Categoria seleccion = (Categoria) cmbCategoria.getSelectedItem();
        if (seleccion != null) anterior = seleccion.getId();
        cmbCategoria.removeAllItems();
        for (Categoria c : manager.listarCategorias()) {
            cmbCategoria.addItem(c);
            if (c.getId() == anterior) cmbCategoria.setSelectedItem(c);
        }
    }

    private void actualizarTabla() {
        modeloTabla.setDatos(manager.listarIngredientes());
    }

    /** Selecciona en la tabla la fila del registro con ese id. */
    private void seleccionarEnTabla(int id) {
        for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {
            Ingrediente registro = modeloTabla.getFila(fila);
            if (registro != null && registro.getId() == id) {
                tabla.setRowSelectionInterval(fila, fila);
                tabla.scrollRectToVisible(tabla.getCellRect(fila, 0, true));
                return;
            }
        }
    }

    /** Carga en el formulario el registro seleccionado en la tabla. */
    private void cargarSeleccionado() {
        Ingrediente registro = modeloTabla.getFila(tabla.getSelectedRow());
        if (registro != null) {
            mostrar(registro);
        }
    }

    /** Carga un ingrediente en los campos del formulario. */
    private void mostrar(Ingrediente ingrediente) {
        txtId.setText(String.valueOf(ingrediente.getId()));
        txtNombre.setText(ingrediente.getNombre());
        cargarCategorias();
        for (int i = 0; i < cmbCategoria.getItemCount(); i++) {
            if (cmbCategoria.getItemAt(i).getId() == ingrediente.getIdCategoria()) {
                cmbCategoria.setSelectedIndex(i); break;
            }
        }
        // Si el código no es de ningún estado conocido, el desplegable queda vacío.
        cmbEstado.setSelectedItem(EstadoIngrediente.desdeCodigo(ingrediente.getEstado()));
        txtCantidad.setText(String.valueOf(ingrediente.getCantidad()));
        chkDisponible.setSelected(ingrediente.isDisponibilidad());
    }

    /** Devuelve el código del estado elegido; advierte y devuelve null si no hay ninguno. */
    private Integer leerEstado() {
        EstadoIngrediente estado = (EstadoIngrediente) cmbEstado.getSelectedItem();
        if (estado == null) {
            advertir("Seleccione un estado.", cmbEstado);
            return null;
        }
        return estado.getCodigo();
    }

    // ---------------------------------------------------------------- Utilidades

    private void advertir(String mensaje, java.awt.Component campo) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, TITULO_VALIDACION,
                javax.swing.JOptionPane.WARNING_MESSAGE);
        if (campo != null) {
            campo.requestFocusInWindow();
        }
    }

    private void informar(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, TITULO_RESULTADO,
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean confirmar(String mensaje) {
        return javax.swing.JOptionPane.showConfirmDialog(this, mensaje, "Confirmar",
                javax.swing.JOptionPane.YES_NO_OPTION) == javax.swing.JOptionPane.YES_OPTION;
    }

    /** Lee un texto obligatorio; si está vacío advierte y devuelve null. */
    private String leerTexto(javax.swing.JTextField campo, String nombre) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            advertir(nombre + " es obligatorio.", campo);
            return null;
        }
        return texto;
    }

    /** Lee un entero con un mínimo; si no es válido advierte y devuelve null. */
    private Integer leerEntero(javax.swing.JTextField campo, String nombre, int minimo) {
        try {
            int n = Integer.parseInt(campo.getText().trim());
            if (n < minimo) {
                advertir(nombre + " debe ser como mínimo " + minimo + ".", campo);
                return null;
            }
            return n;
        } catch (NumberFormatException e) {
            advertir(nombre + " debe ser un número entero.", campo);
            return null;
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JComboBox<Categoria> cmbCategoria;
    private ModeloTablaIngrediente modeloTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JComboBox<EstadoIngrediente> cmbEstado;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JLabel lblDisponible;
    private javax.swing.JCheckBox chkDisponible;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnBuscar;
    // End of variables declaration
}

