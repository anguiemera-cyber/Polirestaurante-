package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;
import co.edu.poligran.paradigmas.polirestaurante.formularios.*;
import javax.swing.JFrame;

/** Navegación del administrador; reutiliza los managers compartidos. */
public final class AdministradorVista {
    private final AngieManager catalogo;
    private final ErikManager operaciones;
    private final JuanManager personal;

    public AdministradorVista(AngieManager catalogo, ErikManager operaciones, JuanManager personal) {
        if (catalogo == null || operaciones == null || personal == null) {
            throw new IllegalArgumentException("Los tres managers son obligatorios.");
        }
        this.catalogo = catalogo;
        this.operaciones = operaciones;
        this.personal = personal;
    }

    public JFrame abrirModulo(String modulo) {
        switch (modulo) {
            case "Administradores": return new FormularioAdministrador(personal);
            case "Meseros": return new FormularioMesero(personal);
            case "Cocineros": return new FormularioCocinero(operaciones);
            case "Pedidos": return new DialogoPedido(operaciones);
            case "Productos": return new DialogoProducto(operaciones);
            case "Mesas": return new FormularioMesa(catalogo);
            case "Categorías": return new FormularioCategoria(catalogo);
            case "Ingredientes": return new FormularioIngrediente(catalogo);
            case "Detalles de pedido": return new FormularioDetallePedido(catalogo, operaciones);
            default: throw new IllegalArgumentException("Módulo desconocido: " + modulo);
        }
    }
}
