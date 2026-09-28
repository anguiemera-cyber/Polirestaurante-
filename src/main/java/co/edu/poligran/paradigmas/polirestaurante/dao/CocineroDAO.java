package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Cocinero;
import java.util.Collections;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad {@link Cocinero}.
 * <p>
 * Implementa las operaciones CRUD de {@link ICrudDAO} sobre listas en
 * memoria y añade la búsqueda de cocineros por nombre de usuario.
 * </p>
 *
 * @version 1.0
 * @see ICrudDAO
 * @see Cocinero
 */
public class CocineroDAO implements ICrudDAO<Cocinero> {

    /**
     * Agrega un cocinero a la lista.
     * <p>
     * Si el identificador de la persona asociada es menor o igual a cero,
     * se asigna automáticamente el siguiente id disponible.
     * </p>
     *
     * @param lista lista en memoria de cocineros
     * @param cocinero cocinero que se desea registrar
     * @return el cocinero registrado
     * @throws IllegalArgumentException si la lista o el cocinero son nulos,
     * o si ya existe un cocinero con el mismo id
     */
    @Override
    public Cocinero crear(List<Cocinero> lista, Cocinero cocinero) {
        validar(lista, cocinero);

        if (cocinero.getCocinero().getId() <= 0) {
            cocinero.getCocinero().setId(siguienteId(lista));
        } else if (buscarPorId(lista, cocinero.getCocinero().getId()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un cocinero con id "
                    + cocinero.getCocinero().getId());
        }

        lista.add(cocinero);
        return cocinero;
    }

    /**
     * Actualiza la información de un cocinero existente.
     *
     * @param lista lista en memoria de cocineros
     * @param cocinero cocinero con los datos actualizados
     * @return el cocinero actualizado o {@code null} si no existe
     * @throws IllegalArgumentException si la lista o el cocinero son nulos
     */
    @Override
    public Cocinero actualizar(List<Cocinero> lista, Cocinero cocinero) {
        validar(lista, cocinero);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCocinero().getId()
                    == cocinero.getCocinero().getId()) {

                lista.set(i, cocinero);
                return cocinero;
            }
        }

        return null;
    }

    /**
     * Elimina un cocinero mediante su identificador.
     *
     * @param lista lista en memoria de cocineros
     * @param id identificador del cocinero a eliminar
     * @return {@code true} si fue eliminado;
     * {@code false} si no existe o la lista es nula
     */
    @Override
    public boolean eliminar(List<Cocinero> lista, int id) {
        if (lista == null) {
            return false;
        }

        return lista.removeIf(c ->
                c.getCocinero() != null
                && c.getCocinero().getId() == id);
    }

    /**
     * Busca un cocinero por su identificador.
     *
     * @param lista lista en memoria de cocineros
     * @param id identificador del cocinero
     * @return el cocinero encontrado o {@code null} si no existe
     */
    @Override
    public Cocinero buscarPorId(List<Cocinero> lista, int id) {
        if (lista == null) {
            return null;
        }

        for (Cocinero c : lista) {
            if (c.getCocinero() != null
                    && c.getCocinero().getId() == id) {
                return c;
            }
        }

        return null;
    }

    /**
     * Busca un cocinero por su nombre de usuario.
     * <p>
     * La comparación ignora mayúsculas, minúsculas y espacios al inicio y al
     * final de la cadena.
     * </p>
     *
     * @param lista lista en memoria de cocineros
     * @param usuario nombre de usuario a buscar
     * @return el cocinero encontrado o {@code null} si no existe
     */
    public Cocinero buscarPorUsuario(List<Cocinero> lista, String usuario) {
        if (lista == null || usuario == null) {
            return null;
        }

        for (Cocinero c : lista) {
            if (c.getUsuario() != null
                    && c.getUsuario().trim()
                            .equalsIgnoreCase(usuario.trim())) {
                return c;
            }
        }

        return null;
    }

    /**
     * Obtiene todos los cocineros registrados.
     *
     * @param lista lista en memoria de cocineros
     * @return una vista no modificable de la lista
     */
    @Override
    public List<Cocinero> listar(List<Cocinero> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(lista);
    }

    /**
     * Calcula el siguiente identificador disponible.
     * <p>
     * Se toma el id máximo encontrado y se incrementa en uno.
     * </p>
     *
     * @param lista lista de cocineros
     * @return el siguiente id disponible
     */
    private int siguienteId(List<Cocinero> lista) {
        int max = 0;

        for (Cocinero c : lista) {
            if (c.getCocinero() != null) {
                max = Math.max(max, c.getCocinero().getId());
            }
        }

        return max + 1;
    }

    /**
     * Verifica que la lista y el cocinero sean válidos.
     *
     * @param lista lista a validar
     * @param cocinero cocinero a validar
     * @throws IllegalArgumentException si la lista, el cocinero o la persona
     * asociada al cocinero son nulos
     */
    private void validar(List<Cocinero> lista, Cocinero cocinero) {
        if (lista == null) {
            throw new IllegalArgumentException(
                    "La lista de cocineros no puede ser nula");
        }

        if (cocinero == null) {
            throw new IllegalArgumentException(
                    "El cocinero no puede ser nulo");
        }

        if (cocinero.getCocinero() == null) {
            throw new IllegalArgumentException(
                    "La persona asociada al cocinero no puede ser nula");
        }
    }
}
