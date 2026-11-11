package co.edu.poligran.paradigmas.polirestaurante.tablas;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;

/**
 * Modelo de tabla para mostrar el listado de mesas.
 *
 * @version 1.0
 */
public class ModeloTablaMesa extends ModeloTablaBase<Mesa> {

    private static final long serialVersionUID = 1L;

    /** Crea el modelo con las columnas de la tabla. */
    public ModeloTablaMesa() {
        super("Id", "Capacidad", "Estado");
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
        Mesa x = getFila(fila);
        if (x == null) {
            return null;
        }
        switch (columna) {
            case 0: return x.getId();
            case 1: return x.getCantidad();
            case 2: return x.getEstado();
            default: return null;
        }
    }
}
