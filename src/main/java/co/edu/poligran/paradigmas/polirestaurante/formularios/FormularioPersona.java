
package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormularioPersona extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;

    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private final List<Persona> personas = new ArrayList<>();

    private int siguienteId = 1;

    public FormularioPersona() {

        initComponents();
        configurarTabla();
        configurarEventos();
        actualizarTabla();

        setLocationRelativeTo(null);
    }

    private void initComponents() {

        setTitle("Gestión de Personas");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Datos de la persona")
        );

        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setText("Automático");

        txtNombre = new JTextField();

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10)
        );

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tabla.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setBorder(
                BorderFactory.createTitledBorder("Personas registradas")
        );

        add(scroll, BorderLayout.CENTER);

        tabla.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                cargarSeleccionado();
            }

        });
    }

    private void configurarEventos() {

        btnGuardar.addActionListener(e -> guardarPersona());

        btnActualizar.addActionListener(e -> actualizarPersona());

        btnEliminar.addActionListener(e -> eliminarPersona());

        btnBuscar.addActionListener(e -> buscarPersona());

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void guardarPersona() {

        try {

            validarCampos();

            Persona persona = new Persona(
                    siguienteId,
                    txtNombre.getText().trim()
            );

            personas.add(persona);

            siguienteId++;

            actualizarTabla();
            limpiarCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Persona registrada correctamente."
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void actualizarPersona() {

        try {

            if (!tieneIdSeleccionado()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una persona de la tabla."
                );

                return;
            }

            validarCampos();

            int id = Integer.parseInt(txtId.getText().trim());

            Persona persona = buscarPorId(id);

            if (persona == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró la persona."
                );

                return;
            }

            persona.setNombre(txtNombre.getText().trim());

            actualizarTabla();
            limpiarCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Persona actualizada correctamente."
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void eliminarPersona() {

        try {

            if (!tieneIdSeleccionado()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una persona de la tabla."
                );

                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas eliminar esta persona?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                Persona persona = buscarPorId(id);

                if (persona != null) {

                    personas.remove(persona);

                    actualizarTabla();
                    limpiarCampos();

                    JOptionPane.showMessageDialog(
                            this,
                            "Persona eliminada correctamente."
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al eliminar: " + e.getMessage()
            );
        }
    }

    private void buscarPersona() {

        try {

            if (!tieneIdSeleccionado()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una persona de la tabla para consultar su información."
                );

                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());

            Persona persona = buscarPorId(id);

            if (persona == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Persona no encontrada."
                );

                return;
            }

            cargarDatos(persona);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al buscar: " + e.getMessage()
            );
        }
    }

    private Persona buscarPorId(int id) {

        for (Persona persona : personas) {

            if (persona.getId() == id) {
                return persona;
            }
        }

        return null;
    }

    private void cargarSeleccionado() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            return;
        }

        int filaModelo = tabla.convertRowIndexToModel(fila);

        int id = Integer.parseInt(
                modeloTabla.getValueAt(filaModelo, 0).toString()
        );

        Persona persona = buscarPorId(id);

        if (persona != null) {
            cargarDatos(persona);
        }
    }

    private void cargarDatos(Persona persona) {

        txtId.setText(String.valueOf(persona.getId()));
        txtNombre.setText(persona.getNombre());
    }

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (Persona persona : personas) {

            modeloTabla.addRow(new Object[]{
                persona.getId(),
                persona.getNombre()
            });
        }
    }

    private boolean tieneIdSeleccionado() {

        String valor = txtId.getText().trim();

        return !valor.isEmpty()
                && !valor.equalsIgnoreCase("Automático");
    }

    private void validarCampos() {

        if (txtNombre.getText().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }
    }

    private void limpiarCampos() {

        txtId.setText("Automático");
        txtNombre.setText("");

        tabla.clearSelection();

        txtNombre.requestFocus();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new FormularioPersona().setVisible(true);
        });
    }
}

