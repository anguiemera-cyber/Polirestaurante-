package co.edu.poligran.paradigmas.polirestaurante.tablas;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;

public class ModeloTablaProducto extends ModeloTablaBase<Producto> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaProducto() {
        super(
            "ID",
            "Nombre",
            "Tipo",
            "Descripción",
            "Precio",
            "Disponible"
        );
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Producto producto = getFila(fila);

        if (producto == null) {
            return null;
        }

        switch (columna) {
            case 0:
                return producto.getId();

            case 1:
                return producto.getNombre();

            case 2:
                return producto.getTipo();

            case 3:
                return producto.getDescripcion();

            case 4:
                return producto.getPrecio();

            case 5:
                return producto.isDisponible()
                        ? "Sí" : "No";

            default:
                return null;
        }
    }
}
