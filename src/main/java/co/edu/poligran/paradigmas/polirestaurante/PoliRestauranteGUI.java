/**
 * Punto de entrada de la versión con interfaz gráfica (equivale a
 * AgendaFormularios en el proyecto Agenda).
 * <p>
 * Crea UNA sola instancia de cada manager y se las entrega a la ventana
 */

package co.edu.poligran.paradigmas.polirestaurante;

import co.edu.poligran.paradigmas.polirestaurante.formularios.VentanaPrincipal;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PoliRestauranteGUI {

    private PoliRestauranteGUI() {
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            aplicarAparienciaDelSistema();

            // Crear los Managers una sola vez
            AngieManager catalogo = new AngieManager();

            // ErikManager utiliza el mismo catálogo
            ErikManager operaciones = new ErikManager(catalogo);

            JuanManager personal = new JuanManager();

            // Enviar las mismas instancias a la ventana principal
            JFrame ventana = new VentanaPrincipal(
                    catalogo,
                    operaciones,
                    personal
            );

            ventana.setVisible(true);
        });
    }

    private static void aplicarAparienciaDelSistema() {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );

        } catch (Exception e) {

            System.err.println(
                    "No se pudo aplicar la apariencia del sistema: "
                    + e.getMessage()
            );
        }
    }
}
