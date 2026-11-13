package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 * Estados posibles de una mesa del restaurante.
 * <p>
 * El modelo guarda solo el código numérico; esta enumeración traduce entre el
 * código, el texto que se muestra y el desplegable de los formularios.
 * </p>
 */
public enum EstadoMesa {

    LIMPIA(1, "Limpia"),
    POR_LIMPIAR(2, "Por limpiar"),
    OCUPADA(3, "Ocupada");

    private final int codigo;
    private final String etiqueta;

    EstadoMesa(int codigo, String etiqueta) {
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }

    /** @return el código numérico que se guarda en el modelo. */
    public int getCodigo() {
        return codigo;
    }

    /** @return el texto del estado, sin el número. */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Busca el estado que corresponde a un código.
     *
     * @param codigo código guardado en el modelo
     * @return el estado, o {@code null} si el código no corresponde a ninguno
     */
    public static EstadoMesa desdeCodigo(int codigo) {
        for (EstadoMesa estado : values()) {
            if (estado.codigo == codigo) {
                return estado;
            }
        }
        return null;
    }

    /**
     * Texto para mostrar en tablas.
     *
     * @param codigo código guardado en el modelo
     * @return la etiqueta del estado, o "Desconocido (n)" si el código no existe
     */
    public static String etiquetaDe(int codigo) {
        EstadoMesa estado = desdeCodigo(codigo);
        return (estado == null) ? "Desconocido (" + codigo + ")" : estado.etiqueta;
    }

    /** Texto del desplegable: "1 - Limpia". */
    @Override
    public String toString() {
        return codigo + " - " + etiqueta;
    }
}
