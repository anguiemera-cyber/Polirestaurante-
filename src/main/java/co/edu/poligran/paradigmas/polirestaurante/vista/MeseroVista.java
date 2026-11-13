package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.formularios.FormularioMesa;
import co.edu.poligran.paradigmas.polirestaurante.formularios.DialogoPedido;
import javax.swing.JFrame;

public class MeseroVista {
    private final AngieManager catalogo;
    private final ErikManager operaciones;

    public MeseroVista(AngieManager catalogo, ErikManager operaciones) {
        this.catalogo = catalogo;
        this.operaciones = operaciones;
    }

    public JFrame abrirModulo(String modulo) {
        if ("Mesas".equals(modulo)) {
            return new FormularioMesa(catalogo);
        }
        if ("Pedidos".equals(modulo)) {
            return new DialogoPedido(operaciones);
        }
        throw new IllegalArgumentException("Modulo no permitido: " + modulo);
    }
}
