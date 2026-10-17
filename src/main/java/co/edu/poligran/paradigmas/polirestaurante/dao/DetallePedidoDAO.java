/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.dao;
import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author anyim
 */
public class DetallePedidoDAO implements ICrudDAO<DetallePedido> {
 
    /**
     * Agrega un detalle de pedido a la lista.
     * <p>
     * Si su id es menor o igual a cero, se le asigna el siguiente id disponible.
     * </p>
     *
     * @param lista   lista en memoria de detalles de pedido
     * @param detalle detalle que se desea registrar
     * @return el detalle registrado, con su id asignado
     * @throws IllegalArgumentException si la lista o el detalle son {@code null},
     *                                  si el detalle no tiene un {@code idPedido}
     *                                  válido, o si ya existe un detalle con el mismo id
     */
    @Override
    public DetallePedido crear(List<DetallePedido> lista, DetallePedido detalle) {
        validar(lista, detalle);
        if (detalle.getIdDetalle() <= 0) {
            detalle.setIdDetalle(siguienteId(lista));
        } else if (buscarPorId(lista, detalle.getIdDetalle()) != null) {
            throw new IllegalArgumentException("Ya existe un detalle con id " + detalle.getIdDetalle());
        }
        lista.add(detalle);
        return detalle;
    }
 
    /**
     * Reemplaza el detalle que tenga el mismo id que el recibido.
     *
     * @param lista   lista en memoria de detalles de pedido
     * @param detalle detalle con los datos actualizados
     * @return el detalle actualizado, o {@code null} si no existe uno con ese id
     * @throws IllegalArgumentException si la lista o el detalle son {@code null},
     *                                  o si el detalle no tiene un {@code idPedido} válido
     */
    @Override
    public DetallePedido actualizar(List<DetallePedido> lista, DetallePedido detalle) {
        validar(lista, detalle);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdDetalle() == detalle.getIdDetalle()) {
                lista.set(i, detalle);
                return detalle;
            }
        }
        return null;
    }
 
    /**
     * Elimina el detalle con el id indicado.
     *
     * @param lista lista en memoria de detalles de pedido
     * @param id    identificador del detalle a eliminar
     * @return {@code true} si se eliminó; {@code false} si no se encontró
     */
    @Override
    public boolean eliminar(List<DetallePedido> lista, int id) {
        return lista.removeIf(d -> d.getIdDetalle() == id);
    }
 
    /**
     * Elimina todos los detalles que pertenecen a un pedido.
     * <p>
     * Se usa al eliminar un pedido, para mantener la composición: sin pedido
     * no pueden quedar detalles huérfanos.
     * </p>
     *
     * @param lista    lista en memoria de detalles de pedido
     * @param idPedido identificador del pedido cuyos detalles se eliminan
     * @return {@code true} si se eliminó al menos un detalle
     */
    public boolean eliminarPorPedido(List<DetallePedido> lista, int idPedido) {
        return lista.removeIf(d -> d.getIdPedido() == idPedido);
    }
 
    /**
     * Busca un detalle por su identificador.
     *
     * @param lista lista en memoria de detalles de pedido
     * @param id    identificador buscado
     * @return el detalle encontrado, o {@code null} si no existe
     */
    @Override
    public DetallePedido buscarPorId(List<DetallePedido> lista, int id) {
        for (DetallePedido d : lista) {
            if (d.getIdDetalle() == id) {
                return d;
            }
        }
        return null;
    }
 
    /**
     * Busca todos los detalles que pertenecen a un pedido.
     *
     * @param lista    lista en memoria de detalles de pedido
     * @param idPedido identificador del pedido
     * @return lista (posiblemente vacía) con los detalles del pedido
     */
    public List<DetallePedido> buscarPorPedido(List<DetallePedido> lista, int idPedido) {
        return lista.stream()
                .filter(d -> d.getIdPedido() == idPedido)
                .collect(Collectors.toList());
    }
 
    /**
     * Calcula el total de una lista de detalles sumando el subtotal de cada uno.
     *
     * @param lista lista en memoria de detalles de pedido
     * @return la suma de los subtotales; 0 si la lista está vacía
     * @throws IllegalArgumentException si la lista es {@code null}
     */
    public double calcularTotal(List<DetallePedido> lista) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de detalles no puede ser nula");
        }
        double total = 0;
        for (DetallePedido d : lista) {
            total += d.getSubtotal();
        }
        return total;
    }
 
    /**
     * Calcula el total de un pedido sumando los subtotales de sus detalles.
     *
     * @param lista    lista en memoria de detalles de pedido
     * @param idPedido identificador del pedido
     * @return el total del pedido; 0 si no tiene detalles
     * @throws IllegalArgumentException si la lista es {@code null}
     */
    public double calcularTotalPedido(List<DetallePedido> lista, int idPedido) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de detalles no puede ser nula");
        }
        return calcularTotal(buscarPorPedido(lista, idPedido));
    }
 
    /**
     * Obtiene todos los detalles de la lista.
     *
     * @param lista lista en memoria de detalles de pedido
     * @return vista no modificable de la lista
     */
    @Override
    public List<DetallePedido> listar(List<DetallePedido> lista) {
        return Collections.unmodifiableList(lista);
    }
 
    /**
     * Calcula el siguiente identificador disponible (id máximo + 1).
     *
     * @param lista lista en memoria de detalles de pedido
     * @return el siguiente id; 1 si la lista está vacía
     */
    private int siguienteId(List<DetallePedido> lista) {
        int max = 0;
        for (DetallePedido d : lista) {
            max = Math.max(max, d.getIdDetalle());
        }
        return max + 1;
    }
 
    /**
     * Verifica que la lista y el detalle no sean nulos y que el detalle
     * pertenezca a un pedido (composición).
     *
     * @param lista   lista a validar
     * @param detalle detalle a validar
     * @throws IllegalArgumentException si la lista o el detalle son {@code null},
     *                                  o si el {@code idPedido} es menor o igual a cero
     */
    private void validar(List<DetallePedido> lista, DetallePedido detalle) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de detalles no puede ser nula");
        }
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle no puede ser nulo");
        }
        if (detalle.getIdPedido() <= 0) {
            throw new IllegalArgumentException("El detalle debe pertenecer a un pedido (idPedido > 0)");
        }
    }
}
 