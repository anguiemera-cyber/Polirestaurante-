package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Categoria;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;

/**
 * Formulario (ventana) para gestionar categorías: Guardar, Actualizar,
 * Eliminar, Limpiar y Buscar.
 * <ul>
 *   <li><b>Guardar</b>: crea un registro nuevo (el ID se asigna solo; debe estar vacío).</li>
 *   <li><b>Actualizar</b>: modifica el registro cuyo ID está escrito.</li>
 *   <li><b>Eliminar</b>: borra el registro cuyo ID está escrito.</li>
 *   <li><b>Buscar</b>: carga en el formulario el registro cuyo ID está escrito.</li>
 *   <li><b>Limpiar</b>: vacía los campos para empezar un registro nuevo.</li>
 * </ul>
 */
public class FormularioCategoria extends javax.swing.JFrame {

    private static final String TITULO_VALIDACION = "Validación";
    private static final String TITULO_RESULTADO = "Categorías";
    private static final String ID_LLENO = "Para crear un registro nuevo deje el ID vacío (use Limpiar). "
            + "Para modificar uno existente use Actualizar.";
    private static final String NO_ENCONTRADO = "No se encontró una categoría con ese ID.";


    /** Manager donde se guardan las categorías. */
    private final transient AngieManager manager;

    /** Crea el formulario con su propio manager (útil para probarlo solo). */
    public FormularioCategoria() {
        this(new AngieManager());
    }

    /**
     * Crea el formulario.
     *
     * @param manager manager de Angie compartido con el resto del programa
     */
    public FormularioCategoria(AngieManager manager) {
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
        lblDescripcion = new javax.swing.JLabel();
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripcion.setText("Descripción:");
        panelFormulario.add(lblDescripcion);
        txtDescripcion = new javax.swing.JTextField();
        txtDescripcion.setColumns(22);
        panelFormulario.add(txtDescripcion);
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
        setTitle("Registro de Categoría");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
    }
    // </editor-fold>

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormularioCategoria().setVisible(true));
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
        String descripcion = leerTexto(txtDescripcion, "La descripción");
        if (descripcion == null) {
            return;
        }
        if (manager.buscarCategoria(nombre) != null) {
            advertir("Ya existe una categoría con ese nombre.", txtNombre);
            return;
        }
        try {
            Categoria categoria = manager.crearCategoria(new Categoria(nombre, descripcion));
            txtId.setText(String.valueOf(categoria.getId()));
            informar("Categoría creada con id: " + categoria.getId());
        } catch (IllegalArgumentException e) {
            advertir(e.getMessage(), txtNombre);
        }
    }

    private void actualizar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        if (manager.buscarCategoriaPorId(id) == null) {
            advertir(NO_ENCONTRADO, txtId);
            return;
        }
        String nombre = leerTexto(txtNombre, "El nombre");
        if (nombre == null) {
            return;
        }
        String descripcion = leerTexto(txtDescripcion, "La descripción");
        if (descripcion == null) {
            return;
        }
        Categoria existente = manager.buscarCategoria(nombre);
        if (existente != null && existente.getId() != id) {
            advertir("Ya existe otra categoría con ese nombre.", txtNombre);
            return;
        }
        if (manager.actualizarCategoria(new Categoria(id, nombre, descripcion))) {
            informar("Categoría actualizada correctamente.");
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void eliminar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        if (!confirmar("¿Seguro que desea eliminar la categoría " + id + "?")) {
            return;
        }
        if (manager.eliminarCategoria(id)) {
            informar("Categoría eliminada.");
            limpiar();
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtId.requestFocusInWindow();
    }

    /** Busca por ID; si el ID está vacío y hay un nombre escrito, busca por nombre. */
    private void buscar() {
        Categoria categoria;
        if (txtId.getText().trim().isEmpty() && !txtNombre.getText().trim().isEmpty()) {
            categoria = manager.buscarCategoria(txtNombre.getText().trim());
            if (categoria == null) {
                advertir("No se encontró una categoría con ese nombre.", txtNombre);
                return;
            }
        } else {
            Integer id = leerEntero(txtId, "El ID", 1);
            if (id == null) {
                return;
            }
            categoria = manager.buscarCategoriaPorId(id);
            if (categoria == null) {
                advertir(NO_ENCONTRADO, txtId);
                return;
            }
        }
        txtId.setText(String.valueOf(categoria.getId()));
        txtNombre.setText(categoria.getNombre());
        txtDescripcion.setText(categoria.getDescripcion());
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
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnBuscar;
    // End of variables declaration
}

