package co.edu.poligran.paradigmas.polirestaurante.tablas;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;

public class ModeloTablaPedido extends ModeloTablaBase<Pedido> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaPedido() {
        super(
            "ID",
            "Producto",
            "Cantidad",
            "Mesa",
            "Costo",
            "Estado",
            "Detalles"
        );
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Pedido pedido = getFila(fila);

        if (pedido == null) {
            return null;
        }

        Producto producto = pedido.getProductoPedido();

        switch (columna) {

            case 0:
                return pedido.getId();

            case 1:
                return producto != null
                        ? producto.getNombre() : "Sin producto";

            case 2:
                return pedido.getCantidad();

            case 3:
                return pedido.getMesa();

            case 4:
                return pedido.getCosto();

            case 5:
                return pedido.getEstado();

            case 6:
                return pedido.getDetalles();

            default:
                return null;
        }
    }
}
