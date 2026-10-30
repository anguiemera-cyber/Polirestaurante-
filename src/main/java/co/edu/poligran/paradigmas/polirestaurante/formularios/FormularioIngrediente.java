package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;

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
        lblNombre = new javax.swing.JLabel();
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre.setText("Nombre:");
        panelFormulario.add(lblNombre);
        txtNombre = new javax.swing.JTextField();
        txtNombre.setColumns(22);
        panelFormulario.add(txtNombre);
        lblEstado = new javax.swing.JLabel();
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEstado.setText("Estado (número):");
        panelFormulario.add(lblEstado);
        txtEstado = new javax.swing.JTextField();
        txtEstado.setColumns(22);
        panelFormulario.add(txtEstado);
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
        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
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
        Integer estado = leerEntero(txtEstado, "El estado", 0);
        if (estado == null) {
            return;
        }
        Integer cantidad = leerEntero(txtCantidad, "La cantidad", 0);
        if (cantidad == null) {
            return;
        }
        try {
            Ingrediente ingrediente = manager.crearIngrediente(
                    new Ingrediente(nombre, estado, cantidad, chkDisponible.isSelected()));
            txtId.setText(String.valueOf(ingrediente.getId()));
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
        Integer estado = leerEntero(txtEstado, "El estado", 0);
        if (estado == null) {
            return;
        }
        Integer cantidad = leerEntero(txtCantidad, "La cantidad", 0);
        if (cantidad == null) {
            return;
        }
        if (manager.actualizarIngrediente(
                new Ingrediente(id, nombre, estado, cantidad, chkDisponible.isSelected()))) {
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
            informar("Ingrediente eliminado.");
            limpiar();
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtEstado.setText("");
        txtCantidad.setText("");
        chkDisponible.setSelected(true);
        txtId.requestFocusInWindow();
    }

    /** Busca por ID; si el ID está vacío y hay un nombre escrito, busca por nombre. */
    private void buscar() {
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
        txtId.setText(String.valueOf(ingrediente.getId()));
        txtNombre.setText(ingrediente.getNombre());
        txtEstado.setText(String.valueOf(ingrediente.getEstado()));
        txtCantidad.setText(String.valueOf(ingrediente.getCantidad()));
        chkDisponible.setSelected(ingrediente.isDisponibilidad());
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
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTextField txtEstado;
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

