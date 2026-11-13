package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * Diálogo modal para capturar o editar un {@link DetallePedido}.
 * <p>
 * El diálogo <b>no</b> guarda nada: solo valida y arma el objeto. Quien lo
 * invoca es el responsable de llamar al método correspondiente de
 * {@code AngieManager}.
 * </p>
 */
public class DialogoDetallePedido extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Manager de pedidos, usado únicamente para verificar que el pedido
     * exista. Puede ser {@code null} para omitir esa verificación.
     */
    private final transient ErikManager manager;

    /** Detalle que se está editando, o {@code null} si es uno nuevo. */
    private final transient DetallePedido original;

    /** Campo de captura del id del pedido (bloqueado al editar). */
    private final JSpinner spnPedido;

    /** Campo de captura del precio unitario. */
    private final JTextField txtPrecio = new JTextField(12);

    /** Campo de captura de la cantidad. */
    private final JSpinner spnCantidad;

    /** Detalle armado al aceptar; queda {@code null} si se cancela. */
    private transient DetallePedido resultado;

    /**
     * Crea el diálogo.
     *
     * @param padre    ventana sobre la cual se muestra el diálogo
     * @param manager  manager de pedidos para verificar que el pedido exista,
     *                 o {@code null} para no verificarlo
     * @param original detalle a editar, o {@code null} para crear uno nuevo
     */
    public DialogoDetallePedido(Window padre, ErikManager manager, DetallePedido original) {
        super(padre, (original == null) ? Mensajes.TITULO_DIALOGO_DETALLE_NUEVO
                : Mensajes.TITULO_DIALOGO_DETALLE_EDITAR, ModalityType.APPLICATION_MODAL);
        this.manager = manager;
        this.original = original;

        int pedido = 1;
        int cantidad = 1;
        if (original != null) {
            pedido = Math.min(1_000_000, Math.max(1, original.getIdPedido()));
            cantidad = Math.min(10_000, Math.max(1, original.getCantidad()));
            txtPrecio.setText(String.valueOf(original.getPrecioUnitario()));
        }
        spnPedido = new JSpinner(new SpinnerNumberModel(pedido, 1, 1_000_000, 1));
        spnCantidad = new JSpinner(new SpinnerNumberModel(cantidad, 1, 10_000, 1));
        // Un detalle no puede cambiar de pedido una vez creado.
        spnPedido.setEnabled(original == null);

        setLayout(new BorderLayout(8, 8));
        add(crearFormulario(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(padre);
    }

    /**
     * Arma el panel con las etiquetas y los campos de captura.
     *
     * @return panel del formulario
     */
    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 4, 12));
        panel.add(new JLabel(Mensajes.ETIQUETA_PEDIDO, SwingConstants.RIGHT));
        panel.add(spnPedido);
        panel.add(new JLabel(Mensajes.ETIQUETA_PRECIO, SwingConstants.RIGHT));
        panel.add(txtPrecio);
        panel.add(new JLabel(Mensajes.ETIQUETA_CANTIDAD, SwingConstants.RIGHT));
        panel.add(spnCantidad);
        return panel;
    }

    /**
     * Arma el panel inferior con los botones Guardar y Cancelar.
     *
     * @return panel de botones
     */
    private JPanel crearBotones() {
        JButton btnGuardar = new JButton(Mensajes.BOTON_GUARDAR);
        JButton btnCancelar = new JButton(Mensajes.BOTON_CANCELAR);
        btnGuardar.addActionListener(e -> aceptar());
        btnCancelar.addActionListener(e -> dispose());
        getRootPane().setDefaultButton(btnGuardar);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        panel.add(btnGuardar);
        panel.add(btnCancelar);
        return panel;
    }

    /**
     * Valida los datos capturados y, si son correctos, arma el detalle y
     * cierra el diálogo.
     * <p>
     * Al crear, se rechaza un pedido inexistente (si hay manager de pedidos).
     * El precio debe ser un número mayor o igual a cero; se acepta coma o
     * punto como separador decimal.
     * </p>
     */
    private void aceptar() {
        int idPedido = ((Number) spnPedido.getValue()).intValue();
        if (original == null && manager != null && manager.buscarPedidoPorId(idPedido) == null) {
            advertir(Mensajes.PEDIDO_NO_EXISTE);
            spnPedido.requestFocusInWindow();
            return;
        }
        double precio;
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            advertir(Mensajes.PRECIO_INVALIDO);
            txtPrecio.requestFocusInWindow();
            return;
        }
        if (Double.isNaN(precio) || Double.isInfinite(precio) || precio < 0) {
            advertir(Mensajes.PRECIO_INVALIDO);
            txtPrecio.requestFocusInWindow();
            return;
        }
        int cantidad = ((Number) spnCantidad.getValue()).intValue();
        if (original == null) {
            resultado = new DetallePedido(idPedido, precio, cantidad);
        } else {
            resultado = new DetallePedido(original.getIdDetalle(), original.getIdPedido(),
                    precio, cantidad);
        }
        dispose();
    }

    /**
     * Muestra un mensaje de advertencia al usuario.
     *
     * @param mensaje texto a mostrar
     */
    private void advertir(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, Mensajes.TITULO_VALIDACION,
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Obtiene el detalle capturado.
     *
     * @return el detalle con los datos ingresados, o {@code null} si el
     *         usuario canceló el diálogo
     */
    public DetallePedido getResultado() {
        return resultado;
    }
}
