package co.edu.poligran.paradigmas.polirestaurante.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabla genérico y de solo lectura.
 * <p>
 * Guarda una copia de la lista que recibe para alimentar un {@code JTable} y
 * permite recuperar el objeto que corresponde a una fila. Las subclases solo
 * deben declarar los nombres de las columnas e implementar
 * {@link #getValueAt(int, int)}.
 * </p>
 *
 * @param <T> tipo de los objetos que se muestran en la tabla
 * @version 1.0
 */
public abstract class ModeloTablaBase<T> extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    /** Nombres de las columnas de la tabla. */
    private final String[] columnas;

    /** Copia de los datos que se están mostrando. */
    protected List<T> datos = new ArrayList<>();

    /**
     * Crea el modelo con los nombres de columna indicados.
     *
     * @param columnas títulos de las columnas, en orden
     */
    protected ModeloTablaBase(String... columnas) {
        this.columnas = columnas;
    }

    /**
     * Reemplaza los datos mostrados y refresca la tabla.
     * <p>
     * Se guarda una copia, de modo que la vista nunca modifica las listas que
     * administra la capa de negocio.
     * </p>
     *
     * @param nuevos lista con los objetos a mostrar; si es {@code null} la
     *               tabla queda vacía
     */
    public void setDatos(List<T> nuevos) {
        datos = (nuevos == null) ? new ArrayList<T>() : new ArrayList<>(nuevos);
        fireTableDataChanged();
    }

    /**
     * Obtiene el objeto que corresponde a una fila.
     *
     * @param fila índice de la fila, tal como lo entrega el {@code JTable}
     * @return el objeto de esa fila, o {@code null} si el índice no es válido
     *         (por ejemplo, cuando no hay ninguna fila seleccionada)
     */
    public T getFila(int fila) {
        if (fila < 0 || fila >= datos.size()) {
            return null;
        }
        return datos.get(fila);
    }

    /**
     * {@inheritDoc}
     *
     * @return cantidad de filas que se están mostrando
     */
    @Override
    public int getRowCount() {
        return datos.size();
    }

    /**
     * {@inheritDoc}
     *
     * @return cantidad de columnas declaradas
     */
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    /**
     * {@inheritDoc}
     *
     * @param columna índice de la columna
     * @return título de la columna
     */
    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    /**
     * Impide la edición directa sobre la tabla: los cambios se hacen desde los
     * diálogos, que son los que llaman al manager.
     *
     * @param fila    índice de la fila
     * @param columna índice de la columna
     * @return siempre {@code false}
     */
    @Override
    public boolean isCellEditable(int fila, int columna) {
        return false;
    }
}
