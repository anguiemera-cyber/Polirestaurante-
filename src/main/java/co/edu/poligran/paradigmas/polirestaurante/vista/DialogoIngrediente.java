package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.util.Validaciones;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * Diálogo modal para capturar o editar un {@link Ingrediente}.
 * <p>
 * El diálogo <b>no</b> guarda nada: solo valida y arma el objeto. Quien lo
 * invoca es el responsable de llamar al método correspondiente de
 * {@link AngieManager}.
 * </p>
 */
public class DialogoIngrediente extends JDialog {

    private static final long serialVersionUID = 1L;

    /** Manager usado únicamente para verificar nombres repetidos. */
    private final transient AngieManager manager;

    /** Ingrediente que se está editando, o {@code null} si es uno nuevo. */
    private final transient Ingrediente original;

    /** Campo de captura del nombre. */
    private final JTextField txtNombre = new JTextField(20);

    /** Campo de captura del estado (número mayor o igual a cero). */
    private final JSpinner spnEstado;

    /** Campo de captura de la cantidad en existencia. */
    private final JSpinner spnCantidad;

    /** Casilla de disponibilidad. */
    private final JCheckBox chkDisponible = new JCheckBox(Mensajes.ETIQUETA_DISPONIBLE, true);

    /** Ingrediente armado al aceptar; queda {@code null} si se cancela. */
    private transient Ingrediente resultado;

    /**
     * Crea el diálogo.
     *
     * @param padre    ventana sobre la cual se muestra el diálogo
     * @param manager  manager del catálogo, usado para detectar duplicados
     * @param original ingrediente a editar, o {@code null} para crear uno nuevo
     */
    public DialogoIngrediente(Window padre, AngieManager manager, Ingrediente original) {
        super(padre, (original == null) ? Mensajes.TITULO_DIALOGO_INGREDIENTE_NUEVO
                : Mensajes.TITULO_DIALOGO_INGREDIENTE_EDITAR, ModalityType.APPLICATION_MODAL);
        this.manager = manager;
        this.original = original;

        int estado = 0;
        int cantidad = 0;
        if (original != null) {
            txtNombre.setText(original.getNombre());
            estado = Math.min(999, Math.max(0, original.getEstado()));
            cantidad = Math.min(1_000_000, Math.max(0, original.getCantidad()));
            chkDisponible.setSelected(original.isDisponibilidad());
        }
        spnEstado = new JSpinner(new SpinnerNumberModel(estado, 0, 999, 1));
        spnCantidad = new JSpinner(new SpinnerNumberModel(cantidad, 0, 1_000_000, 1));

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
        panel.add(new JLabel(Mensajes.ETIQUETA_NOMBRE, SwingConstants.RIGHT));
        panel.add(txtNombre);
        panel.add(new JLabel(Mensajes.ETIQUETA_ESTADO, SwingConstants.RIGHT));
        panel.add(spnEstado);
        panel.add(new JLabel(Mensajes.ETIQUETA_CANTIDAD, SwingConstants.RIGHT));
        panel.add(spnCantidad);
        panel.add(new JLabel());
        panel.add(chkDisponible);
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
     * Valida los datos capturados y, si son correctos, arma el ingrediente y
     * cierra el diálogo.
     * <p>
     * Se rechaza un nombre vacío y uno que ya exista en el catálogo, salvo que
     * sea el del propio ingrediente que se está editando.
     * </p>
     */
    private void aceptar() {
        String nombre = txtNombre.getText().trim();
        if (!Validaciones.tieneContenido(nombre)) {
            advertir(Mensajes.NOMBRE_OBLIGATORIO);
            txtNombre.requestFocusInWindow();
            return;
        }
        Ingrediente existente = manager.buscarIngrediente(nombre);
        if (existente != null && (original == null || existente.getId() != original.getId())) {
            advertir(Mensajes.INGREDIENTE_DUPLICADO);
            txtNombre.requestFocusInWindow();
            return;
        }
        int estado = ((Number) spnEstado.getValue()).intValue();
        int cantidad = ((Number) spnCantidad.getValue()).intValue();
        resultado = new Ingrediente(nombre, estado, cantidad, chkDisponible.isSelected());
        if (original != null) {
            resultado.setId(original.getId());
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
     * Obtiene el ingrediente capturado.
     *
     * @return el ingrediente con los datos ingresados, o {@code null} si el
     *         usuario canceló el diálogo
     */
    public Ingrediente getResultado() {
        return resultado;
    }
}
