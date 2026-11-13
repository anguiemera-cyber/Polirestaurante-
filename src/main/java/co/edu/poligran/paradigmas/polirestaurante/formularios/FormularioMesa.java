package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoMesa;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.tablas.ModeloTablaMesa;

/**
 * Formulario (ventana) para gestionar mesas: Guardar, Actualizar,
 * Eliminar, Limpiar y Buscar.
 * <ul>
 *   <li><b>Guardar</b>: crea un registro nuevo (el ID se asigna solo; debe estar vacío).</li>
 *   <li><b>Actualizar</b>: modifica el registro cuyo ID está escrito.</li>
 *   <li><b>Eliminar</b>: borra el registro cuyo ID está escrito.</li>
 *   <li><b>Buscar</b>: carga en el formulario el registro cuyo ID está escrito.</li>
 *   <li><b>Limpiar</b>: vacía los campos para empezar un registro nuevo.</li>
 * </ul>
 */
public class FormularioMesa extends javax.swing.JFrame {

    private static final String TITULO_VALIDACION = "Validación";
    private static final String TITULO_RESULTADO = "Mesas";
    private static final String ID_LLENO = "Para crear un registro nuevo deje el ID vacío (use Limpiar). "
            + "Para modificar uno existente use Actualizar.";
    private static final String NO_ENCONTRADO = "No se encontró una mesa con ese ID.";


    /** Manager donde se guardan las mesas. */
    private final transient AngieManager manager;

    /** Crea el formulario con su propio manager (útil para probarlo solo). */
    public FormularioMesa() {
        this(new AngieManager());
    }

    /**
     * Crea el formulario.
     *
     * @param manager manager de Angie compartido con el resto del programa
     */
    public FormularioMesa(AngieManager manager) {
        this.manager = manager;
        initComponents();
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSeleccionado();
            }
        });
        actualizarTabla();
        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnBuscar.addActionListener(e -> buscar());
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
        lblCapacidad = new javax.swing.JLabel();
        lblCapacidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCapacidad.setText("Capacidad (personas):");
        panelFormulario.add(lblCapacidad);
        txtCapacidad = new javax.swing.JTextField();
        txtCapacidad.setColumns(22);
        panelFormulario.add(txtCapacidad);
        lblEstado = new javax.swing.JLabel();
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEstado.setText("Estado:");
        panelFormulario.add(lblEstado);
        cmbEstado = new javax.swing.JComboBox<>(EstadoMesa.values());
        panelFormulario.add(cmbEstado);
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
        setTitle("Registro de Mesa");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        modeloTabla = new ModeloTablaMesa();
        tabla = new javax.swing.JTable(modeloTabla);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(25);
        scrollTabla = new javax.swing.JScrollPane(tabla);
        scrollTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("Mesas registradas"));
        scrollTabla.setPreferredSize(new java.awt.Dimension(560, 230));
        getContentPane().add(panelFormulario, java.awt.BorderLayout.NORTH);
        getContentPane().add(scrollTabla, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
        setMinimumSize(getSize());
    }
    // </editor-fold>

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormularioMesa().setVisible(true));
    }

    // ------------------------------------------------------------------ Acciones

    private void guardar() {
        if (!txtId.getText().trim().isEmpty()) {
            advertir(ID_LLENO, txtId);
            return;
        }
        Integer capacidad = leerEntero(txtCapacidad, "La capacidad", 1);
        if (capacidad == null) {
            return;
        }
        Integer estado = leerEstado();
        if (estado == null) {
            return;
        }
        try {
            Mesa mesa = manager.crearMesa(new Mesa(capacidad, estado));
            txtId.setText(String.valueOf(mesa.getId()));
            actualizarTabla();
            seleccionarEnTabla(mesa.getId());
            informar("Mesa creada con id: " + mesa.getId());
        } catch (IllegalArgumentException e) {
            advertir(e.getMessage(), txtCapacidad);
        }
    }

    private void actualizar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        if (manager.buscarMesaPorId(id) == null) {
            advertir(NO_ENCONTRADO, txtId);
            return;
        }
        Integer capacidad = leerEntero(txtCapacidad, "La capacidad", 1);
        if (capacidad == null) {
            return;
        }
        Integer estado = leerEstado();
        if (estado == null) {
            return;
        }
        if (manager.actualizarMesa(new Mesa(id, capacidad, estado))) {
            actualizarTabla();
            seleccionarEnTabla(id);
            informar("Mesa actualizada correctamente.");
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void eliminar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        if (!confirmar("¿Seguro que desea eliminar la mesa " + id + "?")) {
            return;
        }
        if (manager.eliminarMesa(id)) {
            limpiar();
            actualizarTabla();
            informar("Mesa eliminada.");
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtCapacidad.setText("");
        cmbEstado.setSelectedIndex(0);
        tabla.clearSelection();
        txtId.requestFocusInWindow();
    }

    private void buscar() {
        buscarRegistro();
        try {
            seleccionarEnTabla(Integer.parseInt(txtId.getText().trim()));
        } catch (NumberFormatException e) {
            // No hay un id válido que resaltar.
        }
    }

    private void buscarRegistro() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        Mesa mesa = manager.buscarMesaPorId(id);
        if (mesa == null) {
            advertir(NO_ENCONTRADO, txtId);
            return;
        }
        mostrar(mesa);
    }

    // -------------------------------------------------------------------- Tabla

    /** Vuelve a cargar la tabla con todos los registros del manager. */
    private void actualizarTabla() {
        modeloTabla.setDatos(manager.listarMesas());
    }

    /** Selecciona en la tabla la fila del registro con ese id. */
    private void seleccionarEnTabla(int id) {
        for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {
            Mesa registro = modeloTabla.getFila(fila);
            if (registro != null && registro.getId() == id) {
                tabla.setRowSelectionInterval(fila, fila);
                tabla.scrollRectToVisible(tabla.getCellRect(fila, 0, true));
                return;
            }
        }
    }

    /** Carga en el formulario el registro seleccionado en la tabla. */
    private void cargarSeleccionado() {
        Mesa registro = modeloTabla.getFila(tabla.getSelectedRow());
        if (registro != null) {
            mostrar(registro);
        }
    }

    /** Carga una mesa en los campos del formulario. */
    private void mostrar(Mesa mesa) {
        txtId.setText(String.valueOf(mesa.getId()));
        txtCapacidad.setText(String.valueOf(mesa.getCantidad()));
        // Si el código no es de ningún estado conocido, el desplegable queda vacío.
        cmbEstado.setSelectedItem(EstadoMesa.desdeCodigo(mesa.getEstado()));
    }

    /** Devuelve el código del estado elegido; advierte y devuelve null si no hay ninguno. */
    private Integer leerEstado() {
        EstadoMesa estado = (EstadoMesa) cmbEstado.getSelectedItem();
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
    private ModeloTablaMesa modeloTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JComboBox<EstadoMesa> cmbEstado;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnBuscar;
    // End of variables declaration
}
