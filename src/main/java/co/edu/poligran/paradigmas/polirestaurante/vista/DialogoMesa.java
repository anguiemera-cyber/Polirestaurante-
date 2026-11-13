package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoMesa;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * Diálogo modal para capturar o editar una {@link Mesa}.
 * <p>
 * El diálogo <b>no</b> guarda nada: solo valida y arma el objeto. Quien lo
 * invoca es el responsable de llamar al método correspondiente de
 * {@code AngieManager}.
 * </p>
 */
public class DialogoMesa extends JDialog {

    private static final long serialVersionUID = 1L;

    /** Mesa que se está editando, o {@code null} si es una nueva. */
    private final transient Mesa original;

    /** Campo de captura de la capacidad. */
    private final JSpinner spnCapacidad;

    /** Selector del estado de la mesa. */
    private final JComboBox<EstadoMesa> cmbEstado = new JComboBox<>(EstadoMesa.values());

    /** Mesa armada al aceptar; queda {@code null} si se cancela. */
    private transient Mesa resultado;

    /**
     * Crea el diálogo.
     *
     * @param padre    ventana sobre la cual se muestra el diálogo
     * @param original mesa a editar, o {@code null} para crear una nueva
     */
    public DialogoMesa(Window padre, Mesa original) {
        super(padre, (original == null) ? Mensajes.TITULO_DIALOGO_MESA_NUEVO
                : Mensajes.TITULO_DIALOGO_MESA_EDITAR, ModalityType.APPLICATION_MODAL);
        this.original = original;

        int capacidad = 4;
        if (original != null) {
            capacidad = Math.min(999, Math.max(1, original.getCantidad()));
            EstadoMesa estado = EstadoMesa.desdeCodigo(original.getEstado());
            if (estado != null) {
                cmbEstado.setSelectedItem(estado);
            }
        }
        spnCapacidad = new JSpinner(new SpinnerNumberModel(capacidad, 1, 999, 1));

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
        panel.add(new JLabel(Mensajes.ETIQUETA_CAPACIDAD, SwingConstants.RIGHT));
        panel.add(spnCapacidad);
        panel.add(new JLabel(Mensajes.ETIQUETA_ESTADO, SwingConstants.RIGHT));
        panel.add(cmbEstado);
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
     * Arma la mesa con los datos capturados y cierra el diálogo. Los campos
     * numéricos son selectores acotados, por lo que no pueden quedar inválidos.
     */
    private void aceptar() {
        int capacidad = ((Number) spnCapacidad.getValue()).intValue();
        int estado = ((EstadoMesa) cmbEstado.getSelectedItem()).getCodigo();
        resultado = new Mesa(capacidad, estado);
        if (original != null) {
            resultado.setId(original.getId());
        }
        dispose();
    }

    /**
     * Obtiene la mesa capturada.
     *
     * @return la mesa con los datos ingresados, o {@code null} si el usuario
     *         canceló el diálogo
     */
    public Mesa getResultado() {
        return resultado;
    }
}
