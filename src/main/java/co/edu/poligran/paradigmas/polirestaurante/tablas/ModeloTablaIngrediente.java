package co.edu.poligran.paradigmas.polirestaurante.tablas;

import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoIngrediente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;

/**
 * Modelo de tabla para mostrar el listado de ingredientes.
 *
 * @version 1.0
 */
public class ModeloTablaIngrediente extends ModeloTablaBase<Ingrediente> {

    private static final long serialVersionUID = 1L;
    private java.util.function.IntFunction<String> nombreCategoria = id -> String.valueOf(id);

    public void setNombreCategoria(java.util.function.IntFunction<String> resolver) {
        this.nombreCategoria = resolver;
        fireTableDataChanged();
    }

    private String nombreCategoria(int id) { return nombreCategoria.apply(id); }

    /** Crea el modelo con las columnas de la tabla. */
    public ModeloTablaIngrediente() {
        super("Id", "Nombre", "Categoría", "Estado", "Cantidad", "Disponible");
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
        Ingrediente x = getFila(fila);
        if (x == null) {
            return null;
        }
        switch (columna) {
            case 0: return x.getId();
            case 1: return x.getNombre();
            case 2: return nombreCategoria(x.getIdCategoria());
            case 3: return EstadoIngrediente.etiquetaDe(x.getEstado());
            case 4: return x.getCantidad();
            case 5: return x.isDisponibilidad() ? "Sí" : "No";
            default: return null;
        }
    }
}
