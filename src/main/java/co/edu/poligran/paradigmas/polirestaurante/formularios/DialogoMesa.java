/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.formularios;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
/**
 *
 * @author anyim
 */
public class DialogoMesa extends javax.swing.JFrame {

  private static final long serialVersionUID = 1L;

    private static final String TITULO_NUEVO = "Nueva mesa";
    private static final String TITULO_EDITAR = "Editar mesa";
    private static final String TITULO_VALIDACION = "Validación";
    private static final String NUMERO_INVALIDO = "Ingrese un número entero válido.";

    /** Mesa que se está editando, o {@code null} si es una nueva. */
    private final transient Mesa original;

    /** Mesa armada al aceptar; queda {@code null} si se cancela. */
    private transient Mesa resultado;

    /**
     * Crea la ventana JFrame.
     *
     * @param original mesa a editar, o {@code null} para crear una nueva
     */
    public DialogoMesa(Mesa original) {
        super(original == null ? TITULO_NUEVO : TITULO_EDITAR);
        this.original = original;
        initComponents();

        if (original != null) {
            spnCantidad.setValue(Math.max(1, original.getCantidad()));
            spnEstado.setValue(original.getEstado());
        }
        getRootPane().setDefaultButton(btnGuardar);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        panelFormulario = new javax.swing.JPanel();
        lblCantidad = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        lblEstado = new javax.swing.JLabel();
        spnEstado = new javax.swing.JSpinner();
        panelBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 4, 12));
        panelFormulario.setLayout(new java.awt.GridLayout(0, 2, 8, 8));

        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCantidad.setText("Capacidad (personas):");
        panelFormulario.add(lblCantidad);

        spnCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(1), null, Integer.valueOf(1)));
        panelFormulario.add(spnCantidad);

        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEstado.setText("Estado (número):");
        panelFormulario.add(lblEstado);

        spnEstado.setModel(new SpinnerNumberModel(Integer.valueOf(0), null, null, Integer.valueOf(1)));
        panelFormulario.add(spnEstado);

        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);

        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 8));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelBotones.add(btnGuardar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelBotones.add(btnCancelar);

        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        aceptar();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void aceptar() {
        int cantidad;
        int estado;
        try {
            cantidad = valor(spnCantidad);
        } catch (ParseException e) {
            advertir(NUMERO_INVALIDO, spnCantidad);
            return;
        }
        try {
            estado = valor(spnEstado);
        } catch (ParseException e) {
            advertir(NUMERO_INVALIDO, spnEstado);
            return;
        }
        resultado = new Mesa(cantidad, estado);
        if (original != null) {
            resultado.setId(original.getId());
        }
        dispose();
    }

    private static int valor(javax.swing.JSpinner spinner) throws ParseException {
        spinner.commitEdit();
        return ((Number) spinner.getValue()).intValue();
    }

    private void advertir(String mensaje, java.awt.Component componente) {
        JOptionPane.showMessageDialog(this, mensaje, TITULO_VALIDACION,
                JOptionPane.WARNING_MESSAGE);
        componente.requestFocusInWindow();
    }

    public Mesa getResultado() {
        return resultado;
    }

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JSpinner spnEstado;
}
