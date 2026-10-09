/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;
import java.util.Collections;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad {@link Mesa}.
 * <p>
 * Implementa las operaciones CRUD de {@link ICrudDAO} sobre listas en
 * memoria y añade la búsqueda de mesas por estado.
 * </p>
 *
 * @version 1.0
 * @see ICrudDAO
 * @see Mesa
 */
public class MesaDAO implements ICrudDAO<Mesa> {

    /**
     * Agrega una mesa a la lista.
     * <p>
     * Si su id es menor o igual a cero, se le asigna el siguiente id disponible.
     * </p>
     *
     * @param lista lista en memoria de mesas
     * @param mesa  mesa que se desea registrar
     * @return la mesa registrada, con su id asignado
     * @throws IllegalArgumentException si la lista o la mesa son {@code null},
     *                                  o si ya existe una mesa con el mismo id
     */
    @Override
    public Mesa crear(List<Mesa> lista, Mesa mesa) {
        validar(lista, mesa);
        if (mesa.getId() <= 0) {
            mesa.setId(siguienteId(lista));
        } else if (buscarPorId(lista, mesa.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una mesa con id " + mesa.getId());
        }
        lista.add(mesa);
        return mesa;
    }

    /**
     * Reemplaza la mesa que tenga el mismo id que la recibida.
     *
     * @param lista lista en memoria de mesas
     * @param mesa  mesa con los datos actualizados
     * @return la mesa actualizada, o {@code null} si no existe una con ese id
     * @throws IllegalArgumentException si la lista o la mesa son {@code null}
     */
    @Override
    public Mesa actualizar(List<Mesa> lista, Mesa mesa) {
        validar(lista, mesa);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == mesa.getId()) {
                lista.set(i, mesa);
                return mesa;
            }
        }
        return null;
    }

    /**
     * Elimina la mesa con el id indicado.
     *
     * @param lista lista en memoria de mesas
     * @param id    identificador de la mesa a eliminar
     * @return {@code true} si se eliminó; {@code false} si no se encontró
     */
    @Override
    public boolean eliminar(List<Mesa> lista, int id) {
        return lista.removeIf(m -> m.getId() == id);
    }

    /**
     * Busca una mesa por su identificador.
     *
     * @param lista lista en memoria de mesas
     * @param id    identificador buscado
     * @return la mesa encontrada, o {@code null} si no existe
     */
    @Override
    public Mesa buscarPorId(List<Mesa> lista, int id) {
        for (Mesa m : lista) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    /**
     * Busca las mesas que se encuentren en un estado determinado
     * (por ejemplo: libre, ocupada o reservada).
     *
     * @param lista  lista en memoria de mesas
     * @param estado estado que se desea buscar
     * @return lista (posiblemente vacía) de mesas que coinciden con el estado
     */
    public List<Mesa> buscarPorEstado(List<Mesa> lista, int estado) {
        return lista.stream()
                .filter(m -> m.getEstado() == estado)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Obtiene todas las mesas de la lista.
     *
     * @param lista lista en memoria de mesas
     * @return vista no modificable de la lista
     */
    @Override
    public List<Mesa> listar(List<Mesa> lista) {
        return Collections.unmodifiableList(lista);
    }

    /**
     * Calcula el siguiente identificador disponible (id máximo + 1).
     *
     * @param lista lista en memoria de mesas
     * @return el siguiente id; 1 si la lista está vacía
     */
    private int siguienteId(List<Mesa> lista) {
        int max = 0;
        for (Mesa m : lista) {
            max = Math.max(max, m.getId());
        }
        return max + 1;
    }

    /**
     * Verifica que la lista y la mesa no sean nulas.
     *
     * @param lista lista a validar
     * @param mesa  mesa a validar
     * @throws IllegalArgumentException si alguno de los parámetros es {@code null}
     */
    private void validar(List<Mesa> lista, Mesa mesa) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de mesas no puede ser nula");
        }
        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula");
        }
    }
}
