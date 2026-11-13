package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.formularios.FormularioIngrediente;
import co.edu.poligran.paradigmas.polirestaurante.formularios.DialogoPedido;

public class CocineroVista {
    private final AngieManager catalogo;
    private final ErikManager operaciones;

    public CocineroVista(AngieManager catalogo, ErikManager operaciones) {
        if (catalogo == null || operaciones == null) {
            throw new IllegalArgumentException("Los managers son obligatorios");
        }
        this.catalogo = catalogo;
        this.operaciones = operaciones;
    }

    public void abrirIngredientes() {
        FormularioIngrediente ventana = new FormularioIngrediente(catalogo);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public void abrirPedidos() {
        DialogoPedido ventana = new DialogoPedido(operaciones);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
