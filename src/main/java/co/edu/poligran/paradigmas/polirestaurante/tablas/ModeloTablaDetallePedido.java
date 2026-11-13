
package co.edu.poligran.paradigmas.polirestaurante.tablas;

import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;

public class ModeloTablaDetallePedido
        extends ModeloTablaBase<DetallePedido> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaDetallePedido() {
        super(
            "ID Detalle",
            "ID Pedido",
            "ID Producto",
            "Precio Unitario",
            "Cantidad",
            "Subtotal"
        );
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        DetallePedido detalle = getFila(fila);

        if (detalle == null) {
            return null;
        }

        switch (columna) {

            case 0:
                return detalle.getIdDetalle();

            case 1:
                return detalle.getIdPedido();

            case 2:
                return detalle.getIdProducto();

            case 3:
                return detalle.getPrecioUnitario();

            case 4:
                return detalle.getCantidad();

            case 5:
                return detalle.getSubtotal();

            default:
                return null;
        }
    }
}
