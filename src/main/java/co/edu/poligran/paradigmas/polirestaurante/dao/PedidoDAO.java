package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import java.util.Collections;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad {@link Pedido}.
 * <p>
 * Implementa las operaciones CRUD de {@link ICrudDAO} sobre listas en
 * memoria y añade búsquedas específicas relacionadas con los pedidos.
 * </p>
 *
 * @version 1.0
 * @see ICrudDAO
 * @see Pedido
 */
public class PedidoDAO implements ICrudDAO<Pedido> {

    /**
     * Registra un pedido en la lista.
     * <p>
     * Si el identificador del pedido es menor o igual a cero, se asigna
     * automáticamente el siguiente id disponible.
     * </p>
     *
     * @param lista lista en memoria de pedidos
     * @param pedido pedido que se desea registrar
     * @return el pedido registrado
     * @throws IllegalArgumentException si la lista o el pedido son nulos,
     * o si ya existe un pedido con el mismo identificador
     */
    @Override
    public Pedido crear(List<Pedido> lista, Pedido pedido) {
        validar(lista, pedido);

        if (pedido.getid() <= 0) {
            pedido.setid(siguienteId(lista));
        } else if (buscarPorId(lista, pedido.getid()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un pedido con ID " + pedido.getid());
        }

        lista.add(pedido);
        return pedido;
    }

    /**
     * Actualiza la información de un pedido existente.
     *
     * @param lista lista en memoria de pedidos
     * @param pedido pedido con los datos actualizados
     * @return el pedido actualizado o {@code null} si no existe un pedido
     * con el mismo identificador
     * @throws IllegalArgumentException si la lista o el pedido son nulos
     */
    @Override
    public Pedido actualizar(List<Pedido> lista, Pedido pedido) {
        validar(lista, pedido);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getid() == pedido.getid()) {
                lista.set(i, pedido);
                return pedido;
            }
        }

        return null;
    }

    /**
     * Elimina un pedido según su identificador.
     *
     * @param lista lista en memoria de pedidos
     * @param id identificador del pedido a eliminar
     * @return {@code true} si el pedido fue eliminado;
     * {@code false} si no se encontró o la lista es nula
     */
    @Override
    public boolean eliminar(List<Pedido> lista, int id) {
        if (lista == null) {
            return false;
        }

        return lista.removeIf(p -> p.getid() == id);
    }

    /**
     * Busca un pedido por su identificador.
     *
     * @param lista lista en memoria de pedidos
     * @param id identificador del pedido
     * @return el pedido encontrado o {@code null} si no existe
     */
    @Override
    public Pedido buscarPorId(List<Pedido> lista, int id) {
        if (lista == null) {
            return null;
        }

        for (Pedido p : lista) {
            if (p.getid() == id) {
                return p;
            }
        }

        return null;
    }

    /**
     * Obtiene todos los pedidos registrados.
     *
     * @param lista lista en memoria de pedidos
     * @return una vista no modificable de la lista de pedidos;
     * si la lista es nula retorna una lista vacía
     */
    @Override
    public List<Pedido> listar(List<Pedido> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(lista);
    }

    /**
     * Busca todos los pedidos asociados a una mesa específica.
     * <p>
     * La comparación ignora diferencias entre mayúsculas y minúsculas.
     * </p>
     *
     * @param lista lista en memoria de pedidos
     * @param mesa nombre o número de la mesa
     * @return lista de pedidos asociados a la mesa indicada;
     * retorna una lista vacía si no hay coincidencias o si los parámetros
     * son inválidos
     */
    public List<Pedido> buscarPorMesa(List<Pedido> lista, String mesa) {
        if (lista == null || mesa == null) {
            return Collections.emptyList();
        }

        return lista.stream()
                .filter(p -> p.getmesa() != null
                        && p.getmesa().equalsIgnoreCase(mesa))
                .toList();
    }

    /**
     * Calcula el siguiente identificador disponible para un pedido.
     * <p>
     * El valor retornado corresponde al id más alto encontrado en la lista
     * más uno.
     * </p>
     *
     * @param lista lista de pedidos
     * @return siguiente identificador disponible
     */
    private int siguienteId(List<Pedido> lista) {
        int max = 0;

        for (Pedido p : lista) {
            max = Math.max(max, p.getid());
        }

        return max + 1;
    }

    /**
     * Verifica la validez de los parámetros utilizados por el DAO.
     *
     * @param lista lista de pedidos a validar
     * @param pedido pedido a validar
     * @throws IllegalArgumentException si la lista es nula
     * @throws IllegalArgumentException si el pedido es nulo
     * @throws IllegalArgumentException si el producto asociado al pedido es nulo
     */
    private void validar(List<Pedido> lista, Pedido pedido) {

        if (lista == null) {
            throw new IllegalArgumentException(
                    "La lista de pedidos no puede ser nula");
        }

        if (pedido == null) {

