package co.edu.poligran.paradigmas.polirestaurante;

import co.edu.poligran.paradigmas.polirestaurante.formularios.VentanaPrincipal;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada de la versión con interfaz gráfica (equivale a
 * AgendaFormularios en el proyecto Agenda).
 * <p>
 * Crea UNA sola instancia de cada manager y se las entrega a la ventana
 * principal, para que todos los formularios trabajen sobre los mismos datos.
 * </p>
 */
public class PoliRestauranteGUI {

    /** Constructor privado: esta clase solo arranca la aplicación. */
    private PoliRestauranteGUI() {
    }

    /**
     * Arranca la aplicación dentro del hilo de despacho de eventos de Swing.
     *
     * @param args no se utilizan
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            aplicarAparienciaDelSistema();
            AngieManager catalogo = new AngieManager();
            ErikManager operaciones = new ErikManager();
            JuanManager personal = new JuanManager();
            JFrame ventana = new VentanaPrincipal(catalogo, operaciones, personal);
            ventana.setVisible(true);
        });
    }

    /** Usa la apariencia nativa del sistema operativo si está disponible. */
    private static void aplicarAparienciaDelSistema() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ReflectiveOperationException | UnsupportedOperationException
                | javax.swing.UnsupportedLookAndFeelException e) {
            // Se continúa con la apariencia predeterminada.
        }
    }
}
