package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Categoria;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.util.Validaciones;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Diálogo modal para capturar o editar una {@link Categoria}.
 * <p>
 * El diálogo <b>no</b> guarda nada: solo valida y arma el objeto. Quien lo
 * invoca es el responsable de llamar al método correspondiente de
 * {@link AngieManager}.
 * </p>
 */
public class DialogoCategoria extends JDialog {

    private static final long serialVersionUID = 1L;

    /** Manager usado únicamente para verificar nombres repetidos. */
    private final transient AngieManager manager;

    /** Categoría que se está editando, o {@code null} si es una nueva. */
    private final transient Categoria original;

    /** Campo de captura del nombre. */
    private final JTextField txtNombre = new JTextField(20);

    /** Campo de captura de la descripción. */
    private final JTextField txtDescripcion = new JTextField(20);

    /** Categoría armada al aceptar; queda {@code null} si se cancela. */
    private transient Categoria resultado;

    /**
     * Crea el diálogo.
     *
     * @param padre    ventana sobre la cual se muestra el diálogo
     * @param manager  manager del catálogo, usado para detectar duplicados
     * @param original categoría a editar, o {@code null} para crear una nueva
     */
    public DialogoCategoria(Window padre, AngieManager manager, Categoria original) {
        super(padre, (original == null) ? Mensajes.TITULO_DIALOGO_CATEGORIA_NUEVA
                : Mensajes.TITULO_DIALOGO_CATEGORIA_EDITAR, ModalityType.APPLICATION_MODAL);
        this.manager = manager;
        this.original = original;

        if (original != null) {
            txtNombre.setText(original.getNombre());
            txtDescripcion.setText(original.getDescripcion());
        }

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
        panel.add(new JLabel(Mensajes.ETIQUETA_DESCRIPCION, SwingConstants.RIGHT));
        panel.add(txtDescripcion);
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
     * Valida los datos capturados y, si son correctos, arma la categoría y
     * cierra el diálogo.
     * <p>
     * Se rechaza un nombre o una descripción vacíos y un nombre que ya exista
     * en el catálogo, salvo que sea el de la propia categoría que se está
     * editando.
     * </p>
     */
    private void aceptar() {
        String nombre = txtNombre.getText().trim();
        if (!Validaciones.tieneContenido(nombre)) {
            advertir(Mensajes.NOMBRE_OBLIGATORIO);
            txtNombre.requestFocusInWindow();
            return;
        }
        String descripcion = txtDescripcion.getText().trim();
        if (!Validaciones.tieneContenido(descripcion)) {
            advertir(Mensajes.DESCRIPCION_OBLIGATORIA);
            txtDescripcion.requestFocusInWindow();
            return;
        }
        Categoria existente = manager.buscarCategoria(nombre);
        if (existente != null && (original == null || existente.getId() != original.getId())) {
            advertir(Mensajes.CATEGORIA_DUPLICADA);
            txtNombre.requestFocusInWindow();
            return;
        }
        resultado = new Categoria(nombre, descripcion);
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
     * Obtiene la categoría capturada.
     *
     * @return la categoría con los datos ingresados, o {@code null} si el
     *         usuario canceló el diálogo
     */
    public Categoria getResultado() {
        return resultado;
    }
}
