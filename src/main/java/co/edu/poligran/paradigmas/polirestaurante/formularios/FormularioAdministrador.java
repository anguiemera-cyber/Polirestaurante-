package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Administrador;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormularioAdministrador extends JFrame {

    private final JuanManager personal;

    private JTextField txt0;
    private JTextField txt1;
    private JTextField txt2;
    private JComboBox<String> txt3;
    private JTextField txt4;
    private JTextField txt5;
    private JTextField txt6;
    private JTextField txt7;
    private JPasswordField txt8;

    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public FormularioAdministrador() {
        this(new JuanManager());
    }

    public FormularioAdministrador(JuanManager personal) {

        if (personal == null) {
            throw new IllegalArgumentException(
                "El administrador de datos no puede ser nulo."
            );
        }

        this.personal = personal;

        initComponents();
        configurarTabla();
        configurarEventos();
        actualizarTabla();

        setLocationRelativeTo(null);
    }

    private void initComponents() {

        setTitle("Gestión de Administradores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1050, 720);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(
            new GridLayout(5, 4, 10, 10)
        );

        panelFormulario.setBorder(
            BorderFactory.createTitledBorder("Datos del administrador")
        );

        txt0 = new JTextField();
        txt0.setEditable(false);
        txt0.setText("Automático");

        txt1 = new JTextField();
        txt2 = new JTextField();

        txt3 = new JComboBox<>(new String[]{
            "1 - Cédula de Ciudadanía",
            "2 - Tarjeta de Identidad",
            "3 - Cédula de Extranjería",
            "4 - Pasaporte",
            "5 - NIT"
        });

        txt4 = new JTextField();
        txt5 = new JTextField();
        txt6 = new JTextField();
        txt7 = new JTextField();

        txt8 = new JPasswordField();
        txt8.setEchoChar('*');

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txt0);

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txt1);

        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txt2);

        panelFormulario.add(new JLabel("Tipo de documento:"));
        panelFormulario.add(txt3);

        panelFormulario.add(new JLabel("Número de documento:"));
        panelFormulario.add(txt4);

        panelFormulario.add(new JLabel("Celular:"));
        panelFormulario.add(txt5);

        panelFormulario.add(new JLabel("Correo:"));
        panelFormulario.add(txt6);

        panelFormulario.add(new JLabel("Usuario:"));
        panelFormulario.add(txt7);

        panelFormulario.add(new JLabel("Contraseña:"));
        panelFormulario.add(txt8);

        panelFormulario.add(new JLabel(""));
        panelFormulario.add(new JLabel(""));

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 15, 10)
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
            new Object[]{
                "ID",
                "Nombre",
                "Apellido",
                "Tipo documento",
                "Número documento",
                "Celular",
                "Correo",
                "Usuario"
            }, 0
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
            BorderFactory.createTitledBorder("Administradores registrados")
        );

        add(scroll, BorderLayout.CENTER);

        tabla.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                cargarSeleccionado();
            }

        });

    }

    private void configurarEventos() {

        btnGuardar.addActionListener(e -> guardarAdministrador());

        btnActualizar.addActionListener(e -> actualizarAdministrador());

        btnEliminar.addActionListener(e -> eliminarAdministrador());

        btnBuscar.addActionListener(e -> buscarAdministrador());

        btnLimpiar.addActionListener(e -> limpiarCampos());

    }

    private int obtenerTipoDocumento() {

        String seleccionado =
            txt3.getSelectedItem().toString();

        return Integer.parseInt(
            seleccionado.substring(0, 1)
        );

    }

    private String obtenerNombreTipoDocumento(int tipo) {

        switch (tipo) {
            case 1:
                return "Cédula de Ciudadanía";
            case 2:
                return "Tarjeta de Identidad";
            case 3:
                return "Cédula de Extranjería";
            case 4:
                return "Pasaporte";
            case 5:
                return "NIT";
            default:
                return "Desconocido";
        }

    }

    private void guardarAdministrador() {

        try {

            validarCampos();

            String nombre = txt1.getText().trim();
            String apellido = txt2.getText().trim();
            int tipoDoc = obtenerTipoDocumento();

            long numeroDoc = Long.parseLong(
                txt4.getText().trim()
            );

            long celular = Long.parseLong(
                txt5.getText().trim()
            );

            String correo = txt6.getText().trim();
            String usuario = txt7.getText().trim();

            String contraseña =
                new String(txt8.getPassword()).trim();

            Administrador existente =
                personal.buscarAdministradorPorUsuario(usuario);

            if (existente != null) {

                JOptionPane.showMessageDialog(
                    this,
                    "Ya existe un administrador con ese usuario."
                );

                return;
            }

            // El ID 0 indica que el DAO debe generarlo.
            Administrador administrador = new Administrador(
                0,
                nombre,
                apellido,
                tipoDoc,
                numeroDoc,
                celular,
                correo,
                contraseña,
                usuario
            );

            personal.crearAdministrador(administrador);

            int idGenerado = administrador.getId();

            actualizarTabla();
            limpiarCampos();

            JOptionPane.showMessageDialog(
                this,
                "Administrador registrado correctamente.\n"
                + "ID asignado: " + idGenerado
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Verifica que el número de documento y el celular sean numéricos."
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Error al registrar: " + e.getMessage()
            );

        }

    }

    private void actualizarAdministrador() {

        try {

            if (!tieneIdSeleccionado()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un administrador de la tabla o búscalo por usuario."
                );

                return;
            }

            validarCampos();

            int id = Integer.parseInt(txt0.getText().trim());

            String nombre = txt1.getText().trim();
            String apellido = txt2.getText().trim();
            int tipoDoc = obtenerTipoDocumento();

            long numeroDoc = Long.parseLong(
                txt4.getText().trim()
            );

            long celular = Long.parseLong(
                txt5.getText().trim()
            );

            String correo = txt6.getText().trim();
            String usuario = txt7.getText().trim();

            String contraseña =
                new String(txt8.getPassword()).trim();

            Administrador existente =
                personal.buscarAdministradorPorUsuario(usuario);

            if (existente != null && existente.getId() != id) {

                JOptionPane.showMessageDialog(
                    this,
                    "Ese usuario ya pertenece a otro administrador."
                );

                return;
            }

            Administrador administrador = new Administrador(
                id,
                nombre,
                apellido,
                tipoDoc,
                numeroDoc,
                celular,
                correo,
                contraseña,
                usuario
            );

            personal.actualizarAdministrador(administrador);

            actualizarTabla();
            limpiarCampos();

            JOptionPane.showMessageDialog(
                this,
                "Administrador actualizado correctamente."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Verifica los campos numéricos."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Error al actualizar: " + e.getMessage()
            );

        }

    }

    private void eliminarAdministrador() {

        try {

            if (!tieneIdSeleccionado()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un administrador de la tabla o búscalo por usuario."
                );

                return;
            }

            int id = Integer.parseInt(txt0.getText().trim());

            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar este administrador?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                personal.eliminarAdministrador(id);

                actualizarTabla();
                limpiarCampos();

                JOptionPane.showMessageDialog(
                    this,
                    "Administrador eliminado correctamente."
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Error al eliminar: " + e.getMessage()
            );

        }

    }

    private void buscarAdministrador() {

        try {

            Administrador administrador = null;

            if (tieneIdSeleccionado()) {

                int id = Integer.parseInt(txt0.getText().trim());

                administrador =
                    personal.buscarAdministradorPorId(id);

            } else if (!txt7.getText().trim().isEmpty()) {

                administrador =
                    personal.buscarAdministradorPorUsuario(
                        txt7.getText().trim()
                    );

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Escribe un usuario o selecciona un administrador de la tabla."
                );

                return;
            }

            if (administrador == null) {

                JOptionPane.showMessageDialog(
                    this,
                    "No se encontró el administrador."
                );

                return;
            }

            cargarDatos(administrador);

            seleccionarFilaPorId(administrador.getId());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Error al buscar: " + e.getMessage()
            );

        }

    }

    private void cargarDatos(Administrador administrador) {

        txt0.setText(
            String.valueOf(administrador.getId())
        );

        txt1.setText(administrador.getNombre());
        txt2.setText(administrador.getApellido());

        txt3.setSelectedIndex(
            administrador.getTipoDoc() - 1
        );

        txt4.setText(
            String.valueOf(administrador.getNumeroDoc())
        );

        txt5.setText(
            String.valueOf(administrador.getCelular())
        );

        txt6.setText(administrador.getCorreo());
        txt7.setText(administrador.getUsuario());

        txt8.setText(administrador.getContraseña());

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

        Administrador administrador =
            personal.buscarAdministradorPorId(id);

        if (administrador != null) {
            cargarDatos(administrador);
        }

    }

    private void seleccionarFilaPorId(int id) {

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {

            int idTabla = Integer.parseInt(
                modeloTabla.getValueAt(i, 0).toString()
            );

            if (idTabla == id) {

                int filaVista = tabla.convertRowIndexToView(i);

                tabla.setRowSelectionInterval(
                    filaVista,
                    filaVista
                );

                break;
            }

        }

    }

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (Administrador administrador :
                personal.listarAdministradores()) {

            modeloTabla.addRow(new Object[]{
                administrador.getId(),
                administrador.getNombre(),
                administrador.getApellido(),
                obtenerNombreTipoDocumento(
                    administrador.getTipoDoc()
                ),
                administrador.getNumeroDoc(),
                administrador.getCelular(),
                administrador.getCorreo(),
                administrador.getUsuario()
            });

        }

    }

    private boolean tieneIdSeleccionado() {

        String valor = txt0.getText().trim();

        return !valor.isEmpty()
            && !valor.equalsIgnoreCase("Automático");

    }

    private void validarCampos() {

        if (txt1.getText().trim().isEmpty()
                || txt2.getText().trim().isEmpty()
                || txt4.getText().trim().isEmpty()
                || txt5.getText().trim().isEmpty()
                || txt6.getText().trim().isEmpty()
                || txt7.getText().trim().isEmpty()
                || new String(txt8.getPassword()).trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Todos los campos son obligatorios."
            );

        }

        long numeroDoc = Long.parseLong(
            txt4.getText().trim()
        );

        long celular = Long.parseLong(
            txt5.getText().trim()
        );

        if (numeroDoc <= 0 || celular <= 0) {

            throw new IllegalArgumentException(
                "El documento y el celular deben ser mayores que cero."
            );

        }

        String correo = txt6.getText().trim();

        if (!correo.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            throw new IllegalArgumentException(
                "El correo electrónico no tiene un formato válido."
            );

        }

    }

    private void limpiarCampos() {

        txt0.setText("Automático");
        txt1.setText("");
        txt2.setText("");
        txt3.setSelectedIndex(0);
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");
        txt8.setText("");

        tabla.clearSelection();

        txt1.requestFocus();

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new FormularioAdministrador().setVisible(true);

        });

    }

}
