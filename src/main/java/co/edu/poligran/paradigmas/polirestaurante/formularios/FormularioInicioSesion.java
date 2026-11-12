
package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Administrador;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesero;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Cocinero;

import javax.swing.*;
import java.awt.*;

public class FormularioInicioSesion extends JFrame {

    private final JuanManager personal;
    private final ErikManager operaciones;

    private JTextField txtUsuario;
    private JPasswordField txtContraseña;

    private JButton btnIngresar;
    private JButton btnLimpiar;
    private JButton btnMostrar;

    private JLabel lblMensaje;

    private boolean mostrarContraseña = false;

    public FormularioInicioSesion(
            JuanManager personal,
            ErikManager operaciones) {

        this.personal = personal;
        this.operaciones = operaciones;

        initComponents();
    }

    private void initComponents() {

        setTitle("PoliRestaurante - Inicio de sesión");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(15, 15));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(25, 35, 25, 35)
        );

        // Título
        JLabel lblTitulo = new JLabel(
                "POLIRESTAURANTE",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        lblTitulo.setForeground(new Color(35, 75, 120));

        JLabel lblSubtitulo = new JLabel(
                "Inicio de sesión",
                SwingConstants.CENTER
        );

        lblSubtitulo.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        JPanel panelTitulo = new JPanel(new GridLayout(2, 1, 5, 5));
        panelTitulo.setOpaque(false);
        panelTitulo.add(lblTitulo);
        panelTitulo.add(lblSubtitulo);

        // Campos
        JPanel panelCampos = new JPanel(new GridLayout(5, 1, 8, 8));
        panelCampos.setOpaque(false);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setFont(new Font("Arial", Font.BOLD, 14));

        txtContraseña = new JPasswordField();
        txtContraseña.setFont(new Font("Arial", Font.PLAIN, 15));
        txtContraseña.setEchoChar('*');

        btnMostrar = new JButton("Mostrar contraseña");
        btnMostrar.addActionListener(e -> cambiarVisibilidad());

        panelCampos.add(lblUsuario);
        panelCampos.add(txtUsuario);
        panelCampos.add(lblContraseña);
        panelCampos.add(txtContraseña);
        panelCampos.add(btnMostrar);

        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setOpaque(false);

        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIngresar.setBackground(new Color(35, 75, 120));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);

        btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));

        btnIngresar.addActionListener(e -> iniciarSesion());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnIngresar);
        panelBotones.add(btnLimpiar);

        // Mensaje
        lblMensaje = new JLabel(
                "Ingrese sus datos para continuar",
                SwingConstants.CENTER
        );

        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));
        panelInferior.setOpaque(false);
        panelInferior.add(panelBotones, BorderLayout.CENTER);
        panelInferior.add(lblMensaje, BorderLayout.SOUTH);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        txtContraseña.addActionListener(e -> iniciarSesion());
    }

    // Mostrar u ocultar contraseña
    private void cambiarVisibilidad() {

        mostrarContraseña = !mostrarContraseña;

        if (mostrarContraseña) {
            txtContraseña.setEchoChar((char) 0);
            btnMostrar.setText("Ocultar contraseña");
        } else {
            txtContraseña.setEchoChar('*');
            btnMostrar.setText("Mostrar contraseña");
        }
    }

    // Validar inicio de sesión
    private void iniciarSesion() {

        String usuario = txtUsuario.getText().trim();

        String contraseña = new String(
                txtContraseña.getPassword()
        );

        if (usuario.isEmpty() || contraseña.isEmpty()) {

            lblMensaje.setText("Complete todos los campos.");
            lblMensaje.setForeground(Color.RED);
            return;
        }

        // Administrador
        Administrador administrador =
                personal.buscarAdministradorPorUsuario(usuario);

        if (administrador != null
                && administrador.getContraseña() != null
                && administrador.getContraseña().equals(contraseña)) {

            mostrarBienvenida("Administrador",
                    administrador.getNombre());
            return;
        }

        // Mesero
        Mesero mesero =
                personal.buscarMeseroPorUsuario(usuario);

        if (mesero != null
                && mesero.getContraseña() != null
                && mesero.getContraseña().equals(contraseña)) {

            mostrarBienvenida("Mesero", mesero.getNombre());
            return;
        }

        // Cocinero
        Cocinero cocinero =
                operaciones.buscarCocineroPorUsuario(usuario);

        if (cocinero != null
                && cocinero.getContraseña() != null
                && cocinero.getContraseña().equals(contraseña)) {

            mostrarBienvenida("Cocinero",
                    cocinero.getCocinero().getNombre());
            return;
        }

        lblMensaje.setText("Usuario o contraseña incorrectos.");
        lblMensaje.setForeground(Color.RED);

        txtContraseña.setText("");
    }

    // Mostrar bienvenida
    private void mostrarBienvenida(String rol, String nombre) {

        JOptionPane.showMessageDialog(
                this,
                "Bienvenido " + nombre
                + "\nRol: " + rol,
                "Inicio de sesión exitoso",
                JOptionPane.INFORMATION_MESSAGE
        );

        limpiarCampos();
    }

    // Limpiar campos
    private void limpiarCampos() {

        txtUsuario.setText("");
        txtContraseña.setText("");

        lblMensaje.setText("Ingrese sus datos para continuar");
        lblMensaje.setForeground(Color.DARK_GRAY);

        txtUsuario.requestFocus();
    }

    // Método principal
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JuanManager personal = new JuanManager();
            ErikManager operaciones = new ErikManager();

            FormularioInicioSesion ventana =
                    new FormularioInicioSesion(
                            personal,
                            operaciones
                    );

            ventana.setVisible(true);
        });
    }
}
