package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 * Estados posibles de un ingrediente.
 * <p>
 * El modelo guarda solo el código numérico; esta enumeración traduce entre el
 * código, el texto que se muestra y el desplegable de los formularios.
 * </p>
 */
public enum EstadoIngrediente {

    FRESCO_NATURAL(1, "Fresco/Natural"),
    CONGELADO(2, "Congelado"),
    SECO_DESHIDRATADO(3, "Seco/Deshidratados"),
    PROCESADO_PREPARADO(4, "Procesados/Preparados");

    private final int codigo;
    private final String etiqueta;

    EstadoIngrediente(int codigo, String etiqueta) {
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
    public static EstadoIngrediente desdeCodigo(int codigo) {
        for (EstadoIngrediente estado : values()) {
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
        EstadoIngrediente estado = desdeCodigo(codigo);
        return (estado == null) ? "Desconocido (" + codigo + ")" : estado.etiqueta;
    }

    /** Texto del desplegable: "1 - Limpia". */
    @Override
    public String toString() {
        return codigo + " - " + etiqueta;
    }
}
