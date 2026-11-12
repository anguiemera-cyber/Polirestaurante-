package co.edu.poligran.paradigmas.polirestaurante.util;

/**
 * Validaciones de formato compartidas por todos los formularios del
 * PoliRestaurante (equivale a agenda.util.Validaciones del proyecto Agenda).
 * <p>
 * Es una clase de utilidad: no se puede instanciar y todos sus métodos son
 * estáticos.
 * </p>
 */
public final class Validaciones {

    /** Cuenta de correo del estilo {@code usuario@dominio.com}. */
    public static final String REGEX_CORREO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    /** Celular colombiano: 10 dígitos que empiezan por 3. */
    public static final String REGEX_CELULAR = "^3\\d{9}$";

    /** Número de documento: solo dígitos, de 5 a 12. */
    public static final String REGEX_DOCUMENTO = "^\\d{5,12}$";

    /** Usuario: 4 a 20 caracteres entre letras, dígitos, punto, guion y guion bajo. */
    public static final String REGEX_USUARIO = "^[A-Za-z0-9._-]{4,20}$";

    /** Longitud mínima de una contraseña. */
    public static final int LONGITUD_MIN_CONTRASENA = 4;

    private Validaciones() {
    }

    /** @return {@code true} si el texto no es nulo ni está vacío (sin contar espacios). */
    public static boolean tieneContenido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /** @return {@code true} si el texto tiene formato de correo electrónico. */
    public static boolean esCorreo(String cuenta) {
        return cuenta != null && cuenta.trim().matches(REGEX_CORREO);
    }

    /** @return {@code true} si el texto es un celular de 10 dígitos que empieza por 3. */
    public static boolean esCelular(String numero) {
        return numero != null && numero.trim().matches(REGEX_CELULAR);
    }

    /** @return {@code true} si el texto es un documento de 5 a 12 dígitos. */
    public static boolean esDocumento(String numero) {
        return numero != null && numero.trim().matches(REGEX_DOCUMENTO);
    }

    /** @return {@code true} si el texto es un nombre de usuario válido. */
    public static boolean esUsuario(String usuario) {
        return usuario != null && usuario.trim().matches(REGEX_USUARIO);
    }

    /** @return {@code true} si la contraseña alcanza la longitud mínima. */
    public static boolean esContrasena(String contrasena) {
        return contrasena != null && contrasena.trim().length() >= LONGITUD_MIN_CONTRASENA;
    }
}
