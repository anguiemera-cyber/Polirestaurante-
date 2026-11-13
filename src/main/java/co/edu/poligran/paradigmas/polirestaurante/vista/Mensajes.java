package co.edu.poligran.paradigmas.polirestaurante.vista;

/**
 * Centraliza los textos que muestran los diálogos de la interfaz gráfica
 * (equivale a Mensajes del proyecto Agenda).
 */
public final class Mensajes {

    private Mensajes() {
    }

    // Generales.
    public static final String TITULO_VALIDACION = "Validación";
    public static final String BOTON_GUARDAR = "Guardar";
    public static final String BOTON_CANCELAR = "Cancelar";

    // Títulos de los diálogos.
    public static final String TITULO_DIALOGO_MESA_NUEVO = "Nueva mesa";
    public static final String TITULO_DIALOGO_MESA_EDITAR = "Editar mesa";
    public static final String TITULO_DIALOGO_INGREDIENTE_NUEVO = "Nuevo ingrediente";
    public static final String TITULO_DIALOGO_INGREDIENTE_EDITAR = "Editar ingrediente";
    public static final String TITULO_DIALOGO_CATEGORIA_NUEVA = "Nueva categoría";
    public static final String TITULO_DIALOGO_CATEGORIA_EDITAR = "Editar categoría";
    public static final String TITULO_DIALOGO_DETALLE_NUEVO = "Nuevo detalle de pedido";
    public static final String TITULO_DIALOGO_DETALLE_EDITAR = "Editar detalle de pedido";

    // Etiquetas.
    public static final String ETIQUETA_CAPACIDAD = "Capacidad:";
    public static final String ETIQUETA_ESTADO = "Estado:";
    public static final String ETIQUETA_NOMBRE = "Nombre:";
    public static final String ETIQUETA_DESCRIPCION = "Descripción:";
    public static final String ETIQUETA_CANTIDAD = "Cantidad:";
    public static final String ETIQUETA_DISPONIBLE = "Disponible";
    public static final String ETIQUETA_PEDIDO = "Pedido:";
    public static final String ETIQUETA_PRECIO = "Precio unitario:";

    // Mensajes de validación.
    public static final String NOMBRE_OBLIGATORIO = "El nombre es obligatorio.";
    public static final String DESCRIPCION_OBLIGATORIA = "La descripción es obligatoria.";
    public static final String INGREDIENTE_DUPLICADO = "Ya existe un ingrediente con ese nombre.";
    public static final String CATEGORIA_DUPLICADA = "Ya existe una categoría con ese nombre.";
    public static final String PRECIO_INVALIDO = "El precio unitario debe ser un número mayor o igual a cero.";
    public static final String PEDIDO_NO_EXISTE = "No existe un pedido con ese id.";
}
