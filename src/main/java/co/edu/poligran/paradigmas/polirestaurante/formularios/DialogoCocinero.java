package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.vista.CocineroVista;
import javax.swing.*;
import java.awt.*;

public class DialogoCocinero extends JFrame {
    private final CocineroVista vista;

    public DialogoCocinero(ErikManager operaciones) {
        this(operaciones.getAngieManager(), operaciones);
    }

    public DialogoCocinero(AngieManager catalogo, ErikManager operaciones) {
        vista = new CocineroVista(catalogo, operaciones);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setTitle("PoliRestaurante - Cocinero");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        JLabel titulo = new JLabel("MENU COCINERO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        JButton ingredientes = new JButton("Gestionar Ingredientes");
        JButton pedidos = new JButton("Gestionar Pedidos");
        JButton salir = new JButton("Salir");
        ingredientes.addActionListener(e -> vista.abrirIngredientes());
        pedidos.addActionListener(e -> vista.abrirPedidos());
        salir.addActionListener(e -> dispose());
        panel.add(titulo);
        panel.add(ingredientes);
        panel.add(pedidos);
        panel.add(salir);
        setContentPane(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}
