package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;

/**
 * Formulario (ventana) para gestionar detalles de pedido: Guardar, Actualizar,
 * Eliminar, Limpiar y Buscar.
 * <ul>
 *   <li><b>Guardar</b>: crea un registro nuevo (el ID se asigna solo; debe estar vacío).</li>
 *   <li><b>Actualizar</b>: modifica el registro cuyo ID está escrito.</li>
 *   <li><b>Eliminar</b>: borra el registro cuyo ID está escrito.</li>
 *   <li><b>Buscar</b>: carga en el formulario el registro cuyo ID está escrito.</li>
 *   <li><b>Limpiar</b>: vacía los campos para empezar un registro nuevo.</li>
 * </ul>
 */
public class FormularioDetallePedido extends javax.swing.JFrame {

    private static final String TITULO_VALIDACION = "Validación";
    private static final String TITULO_RESULTADO = "Detalles de pedido";
    private static final String ID_LLENO = "Para crear un registro nuevo deje el ID vacío (use Limpiar). "
            + "Para modificar uno existente use Actualizar.";
    private static final String NO_ENCONTRADO = "No se encontró un detalle con ese ID.";


    /** Manager donde se guardan los detalles. */
    private final transient AngieManager angie;

    /** Manager usado para comprobar que el pedido exista; puede ser null (no se comprueba). */
    private final transient ErikManager erik;

    /** Crea el formulario con su propio manager (útil para probarlo solo; no valida pedidos). */
    public FormularioDetallePedido() {
        this(new AngieManager(), null);
    }

    /**
     * Crea el formulario.
     *
     * @param angie manager de Angie compartido con el resto del programa
     * @param erik  manager de Erik, para verificar que el pedido exista; puede ser null
     */
    public FormularioDetallePedido(AngieManager angie, ErikManager erik) {
        this.angie = angie;
        this.erik = erik;
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
        lblId.setText("ID del detalle:");
        panelFormulario.add(lblId);
        txtId = new javax.swing.JTextField();
        txtId.setColumns(22);
        panelFormulario.add(txtId);
        lblIdPedido = new javax.swing.JLabel();
        lblIdPedido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIdPedido.setText("ID del pedido:");
        panelFormulario.add(lblIdPedido);
        txtIdPedido = new javax.swing.JTextField();
        txtIdPedido.setColumns(22);
        panelFormulario.add(txtIdPedido);
        lblPrecio = new javax.swing.JLabel();
        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio.setText("Precio unitario:");
        panelFormulario.add(lblPrecio);
        txtPrecio = new javax.swing.JTextField();
        txtPrecio.setColumns(22);
        panelFormulario.add(txtPrecio);
        lblCantidad = new javax.swing.JLabel();
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCantidad.setText("Cantidad:");
        panelFormulario.add(lblCantidad);
        txtCantidad = new javax.swing.JTextField();
        txtCantidad.setColumns(22);
        panelFormulario.add(txtCantidad);
        lblSubtotal = new javax.swing.JLabel();
        lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotal.setText("Subtotal:");
        panelFormulario.add(lblSubtotal);
        txtSubtotal = new javax.swing.JTextField();
        txtSubtotal.setColumns(22);
        txtSubtotal.setEditable(false);
        panelFormulario.add(txtSubtotal);
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
        setTitle("Registro de Detalle de Pedido");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
    }
    // </editor-fold>

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormularioDetallePedido().setVisible(true));
    }

    // ------------------------------------------------------------------ Acciones

    private void guardar() {
        if (!txtId.getText().trim().isEmpty()) {
            advertir(ID_LLENO, txtId);
            return;
        }
        Integer idPedido = leerEntero(txtIdPedido, "El id del pedido", 1);
        if (idPedido == null) {
            return;
        }
        Double precio = leerDecimal(txtPrecio, "El precio unitario", 0);
        if (precio == null) {
            return;
        }
        Integer cantidad = leerEntero(txtCantidad, "La cantidad", 1);
        if (cantidad == null) {
            return;
        }
        if (erik != null && erik.buscarPedidoPorId(idPedido) == null) {
            advertir("No existe un pedido con ese id.", txtIdPedido);
            return;
        }
        try {
            DetallePedido detalle = angie.crearDetalle(new DetallePedido(idPedido, precio, cantidad));
            mostrar(detalle);
            informar("Detalle creado con id: " + detalle.getIdDetalle()
                    + "\nSubtotal: " + detalle.getSubtotal());
        } catch (IllegalArgumentException e) {
            advertir(e.getMessage(), txtIdPedido);
        }
    }

    private void actualizar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        DetallePedido actual = angie.buscarDetallePorId(id);
        if (actual == null) {
            advertir(NO_ENCONTRADO, txtId);
            return;
        }
        Integer idPedido = leerEntero(txtIdPedido, "El id del pedido", 1);
        if (idPedido == null) {
            return;
        }
        if (idPedido != actual.getIdPedido()) {
            advertir("Un detalle no puede cambiar de pedido (pedido actual: "
                    + actual.getIdPedido() + ").", txtIdPedido);
            return;
        }
        Double precio = leerDecimal(txtPrecio, "El precio unitario", 0);
        if (precio == null) {
            return;
        }
        Integer cantidad = leerEntero(txtCantidad, "La cantidad", 1);
        if (cantidad == null) {
            return;
        }
        DetallePedido datos = new DetallePedido(id, actual.getIdPedido(), precio, cantidad);
        if (angie.actualizarDetalle(datos)) {
            mostrar(datos);
            informar("Detalle actualizado correctamente.\nSubtotal: " + datos.getSubtotal());
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void eliminar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        if (!confirmar("¿Seguro que desea eliminar el detalle " + id + "?")) {
            return;
        }
        if (angie.eliminarDetalle(id)) {
            informar("Detalle eliminado.");
            limpiar();
        } else {
            advertir(NO_ENCONTRADO, txtId);
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtIdPedido.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
        txtId.requestFocusInWindow();
    }

    private void buscar() {
        Integer id = leerEntero(txtId, "El ID", 1);
        if (id == null) {
            return;
        }
        DetallePedido detalle = angie.buscarDetallePorId(id);
        if (detalle == null) {
            advertir(NO_ENCONTRADO, txtId);
            return;
        }
        mostrar(detalle);
    }

    /** Carga un detalle en los campos del formulario. */
    private void mostrar(DetallePedido detalle) {
        txtId.setText(String.valueOf(detalle.getIdDetalle()));
        txtIdPedido.setText(String.valueOf(detalle.getIdPedido()));
        txtPrecio.setText(String.valueOf(detalle.getPrecioUnitario()));
        txtCantidad.setText(String.valueOf(detalle.getCantidad()));
        txtSubtotal.setText(String.valueOf(detalle.getSubtotal()));
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

    /** Lee un decimal con un mínimo; si no es válido advierte y devuelve null. */
    private Double leerDecimal(javax.swing.JTextField campo, String nombre, double minimo) {
        try {
            double n = Double.parseDouble(campo.getText().trim().replace(',', '.'));
            if (n < minimo) {
                advertir(nombre + " debe ser como mínimo " + minimo + ".", campo);
                return null;
            }
            return n;
        } catch (NumberFormatException e) {
            advertir(nombre + " debe ser un número válido.", campo);
            return null;
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblIdPedido;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnBuscar;
    // End of variables declaration
}

