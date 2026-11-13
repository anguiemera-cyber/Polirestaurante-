package co.edu.poligran.paradigmas.polirestaurante.formularios;

import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.function.Supplier;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Ventana principal del PoliRestaurante (equivale a VentanaAgenda en el
 * proyecto Agenda). Desde aquí se abre cada formulario entregándole el manager
 * compartido que necesita.
 */
public class VentanaPrincipal extends JFrame {

    private final AngieManager catalogo;
    private final ErikManager operaciones;
    private final JuanManager personal;

    public VentanaPrincipal(AngieManager catalogo, ErikManager operaciones, JuanManager personal) {
        this.catalogo = catalogo;
        this.operaciones = operaciones;
        this.personal = personal;
        construir();
    }

    private void construir() {
        setTitle("PoliRestaurante");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("POLIRESTAURANTE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(35, 75, 120));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        JPanel centro = new JPanel(new GridLayout(1, 3, 15, 15));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        centro.add(grupo("Catálogo",
                boton("Mesas", () -> new FormularioMesa(catalogo)),
                boton("Ingredientes", () -> new FormularioIngrediente(catalogo)),
                boton("Categorías", () -> new FormularioCategoria(catalogo))));

        centro.add(grupo("Operaciones",
                boton("Detalles de pedido", () -> new FormularioDetallePedido(catalogo, operaciones)),
                boton("Producto", () -> new DialogoProducto(operaciones)),
                boton("Pedido", () -> new DialogoPedido(operaciones))));

        centro.add(grupo("Personal",
                boton("Administradores", () -> new FormularioAdministrador(personal)),
                boton("Meseros", () -> new FormularioMesero(personal)),
                boton("Cocineros", () -> new FormularioCocinero(operaciones)),
                boton("Clientes", () -> new FormularioCliente(personal))));
        
        centro.add(grupo("Gestion",
                boton("Administrador", () -> new DialogoAdministrador(operaciones)),
                boton("Cocinero", () -> new DialogoCocinero(operaciones)),
                boton("Mesero", () -> new DialogoMesero(operaciones))));

        JButton salir = new JButton("Salir");
        salir.addActionListener(e -> dispose());
        JPanel sur = new JPanel();
        sur.setBorder(BorderFactory.createEmptyBorder(0, 10, 15, 10));
        sur.add(salir);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    /** Panel con borde titulado y una columna de botones. */
    private JPanel grupo(String nombre, JButton... botones) {
        JPanel panel = new JPanel(new GridLayout(Math.max(botones.length, 4), 1, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder(nombre));
        for (JButton b : botones) {
            panel.add(b);
        }
        return panel;
    }

    /** Botón que abre el formulario creado por 'fabrica' centrado sobre esta ventana. */
    private JButton boton(String texto, Supplier<JFrame> fabrica) {
        JButton b = new JButton(texto);
        b.addActionListener(e -> {
            JFrame f = fabrica.get();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        });
        return b;
    }
}
