package co.edu.poligran.paradigmas.polirestaurante.tablas;

import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;

/**
 * Modelo de tabla para mostrar el listado de detalles de pedido.
 *
 * @version 1.0
 */
public class ModeloTablaDetallePedido extends ModeloTablaBase<DetallePedido> {

    private static final long serialVersionUID = 1L;

    /** Crea el modelo con las columnas de la tabla. */
    public ModeloTablaDetallePedido() {
        super("Id", "Pedido", "Precio unit.", "Cantidad", "Subtotal");
    }

    /**
     * Entrega el valor de una celda.
     *
     * @param fila    índice de la fila
     * @param columna índice de la columna
     * @return el valor de la celda, o {@code null} si el índice no es válido
     */
    @Override
    public Object getValueAt(int fila, int columna) {
        DetallePedido x = getFila(fila);
        if (x == null) {
            return null;
        }
        switch (columna) {
            case 0: return x.getIdDetalle();
            case 1: return x.getIdPedido();
            case 2: return x.getPrecioUnitario();
            case 3: return x.getCantidad();
            case 4: return x.getSubtotal();
            default: return null;
        }
    }
}
