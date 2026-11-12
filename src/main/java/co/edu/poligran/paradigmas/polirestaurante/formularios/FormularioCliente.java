package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Cliente;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormularioCliente extends JFrame {

    private final JuanManager personal;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtBuscar;

    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    public FormularioCliente() {
        this(JuanManager.getInstancia());
    }

    public FormularioCliente(JuanManager personal) {
        this.personal = personal;

        inicializarComponentes();
        cargarClientes();
    }

    private void inicializarComponentes() {

        setTitle("PoliRestaurante - Gestión de clientes");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel principal = new JPanel(new BorderLayout(15, 15));
        principal.setBackground(new Color(245, 247, 250));
        principal.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        // Título
        JLabel lblTitulo = new JLabel(
                "GESTIÓN DE CLIENTES",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(new Color(35, 75, 120));

        // Panel de registro
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 10, 10));
        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Información del cliente")
        );
        panelFormulario.setBackground(new Color(245, 247, 250));

        JLabel lblId = new JLabel("ID del cliente:");
        JLabel lblNombre = new JLabel("Nombre completo:");

        txtId = new JTextField("Automático");
        txtId.setEditable(false);
        txtId.setBackground(new Color(230, 230, 230));

        txtNombre = new JTextField();

        panelFormulario.add(lblId);
        panelFormulario.add(txtId);
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 8, 8));
        panelBotones.setBackground(new Color(245, 247, 250));

        btnRegistrar = crearBoton("Registrar");
        btnActualizar = crearBoton("Actualizar");
        btnEliminar = crearBoton("Eliminar");
        btnLimpiar = crearBoton("Limpiar");
        btnBuscar = crearBoton("Buscar ID");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnBuscar);

        // Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(new Color(245, 247, 250));

        JLabel lblBuscar = new JLabel("Buscar por ID:");
        txtBuscar = new JTextField(12);

        JButton btnConsultar = crearBoton("Consultar");

        panelBusqueda.add(lblBuscar);
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnConsultar);

        // Tabla
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre del cliente"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setRowHeight(25);
        tablaClientes.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollTabla = new JScrollPane(tablaClientes);

        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBackground(new Color(245, 247, 250));
        panelTabla.setBorder(
                BorderFactory.createTitledBorder("Clientes registrados")
        );

        panelTabla.add(panelBusqueda, BorderLayout.NORTH);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 15));
        panelSuperior.setBackground(new Color(245, 247, 250));

        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        principal.add(panelSuperior, BorderLayout.NORTH);
        principal.add(panelTabla, BorderLayout.CENTER);

        add(principal);

        // Eventos
        btnRegistrar.addActionListener(e -> registrarCliente());
        btnActualizar.addActionListener(e -> actualizarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnBuscar.addActionListener(e -> buscarCliente());
        btnConsultar.addActionListener(e -> buscarCliente());

        tablaClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarCliente();
            }
        });
    }

    // Diseño de botones
    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setBackground(new Color(35, 75, 120));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);

        return boton;
    }

    // Registrar cliente
    private void registrarCliente() {

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del cliente."
            );
            txtNombre.requestFocus();
            return;
        }

        try {
            int nuevoId = obtenerSiguienteId();

            Cliente cliente = new Cliente(nuevoId, nombre);

            Cliente registrado = personal.crearCliente(cliente);

            if (registrado != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente registrado correctamente.\nID: "
                        + registrado.getId()
                );

                cargarClientes();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar el cliente."
                );
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al registrar: " + ex.getMessage()
            );
        }
    }

    // Obtener el siguiente ID disponible
    private int obtenerSiguienteId() {

        List<Cliente> clientes = personal.listarClientes();

        int mayorId = 0;

        for (Cliente cliente : clientes) {
            if (cliente.getId() > mayorId) {
                mayorId = cliente.getId();
            }
        }

        return mayorId + 1;
    }

    // Actualizar cliente
    private void actualizarCliente() {

        if (txtId.getText().equals("Automático")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Primero seleccione un cliente de la tabla."
            );
            return;
        }

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del cliente."
            );
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());

            Cliente cliente = new Cliente(id, nombre);

            Cliente actualizado = personal.actualizarCliente(cliente);

            if (actualizado != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente actualizado correctamente."
                );

                cargarClientes();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el cliente."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "El ID del cliente no es válido."
            );
        }
    }

    // Eliminar cliente
    private void eliminarCliente() {

        if (txtId.getText().equals("Automático")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un cliente para eliminar."
            );
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar este cliente?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            try {
                int id = Integer.parseInt(txtId.getText());

                boolean eliminado = personal.eliminarCliente(id);

                if (eliminado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Cliente eliminado correctamente."
                    );

                    cargarClientes();
                    limpiarCampos();

                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "No se encontró el cliente."
                    );
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "ID no válido."
                );
            }
        }
    }

    // Buscar cliente por ID
    private void buscarCliente() {

        String textoId = txtBuscar.getText().trim();

        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el ID que desea buscar."
            );
            return;
        }

        try {
            int id = Integer.parseInt(textoId);

            Cliente cliente = personal.buscarClientePorId(id);

            if (cliente != null) {

                txtId.setText(String.valueOf(cliente.getId()));
                txtNombre.setText(cliente.getNombre());

                tablaClientes.clearSelection();

                for (int i = 0; i < modeloTabla.getRowCount(); i++) {

                    int idTabla = Integer.parseInt(
                            modeloTabla.getValueAt(i, 0).toString()
                    );

                    if (idTabla == id) {
                        tablaClientes.setRowSelectionInterval(i, i);
                        break;
                    }
                }

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró ningún cliente con ese ID."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número entero."
            );
        }
    }

    // Cargar clientes en la tabla
    private void cargarClientes() {

        modeloTabla.setRowCount(0);

        List<Cliente> clientes = personal.listarClientes();

        for (Cliente cliente : clientes) {

            modeloTabla.addRow(new Object[]{
                cliente.getId(),
                cliente.getNombre()
            });
        }
    }

    // Seleccionar cliente de la tabla
    private void seleccionarCliente() {

        int fila = tablaClientes.getSelectedRow();

        if (fila >= 0) {

            txtId.setText(
                    modeloTabla.getValueAt(fila, 0).toString()
            );

            txtNombre.setText(
                    modeloTabla.getValueAt(fila, 1).toString()
            );
        }
    }

    // Limpiar campos
    private void limpiarCampos() {

        txtId.setText("Automático");
        txtNombre.setText("");
        txtBuscar.setText("");

        tablaClientes.clearSelection();

        txtNombre.requestFocus();
    }

    // Método principal para probar el formulario
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            FormularioCliente formulario =
                    new FormularioCliente();

            formulario.setVisible(true);
        });
    }
}
