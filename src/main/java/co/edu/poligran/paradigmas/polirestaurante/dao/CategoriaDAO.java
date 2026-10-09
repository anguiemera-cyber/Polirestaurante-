/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Categoria;
import java.util.Collections;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad {@link Categoria}.
 * <p>
 * Implementa las operaciones CRUD de {@link ICrudDAO} sobre listas en
 * memoria y añade la búsqueda por nombre.
 * </p>
 *
 * @version 1.0
 * @see ICrudDAO
 * @see Categoria
 */
public class CategoriaDAO implements ICrudDAO<Categoria> {

    /**
     * Agrega una categoría a la lista.
     * <p>
     * Si su id es menor o igual a cero, se le asigna el siguiente id disponible.
     * </p>
     *
     * @param lista     lista en memoria de categorías
     * @param categoria categoría que se desea registrar
     * @return la categoría registrada, con su id asignado
     * @throws IllegalArgumentException si la lista o la categoría son {@code null},
     *                                  o si ya existe una categoría con el mismo id
     */
    @Override
    public Categoria crear(List<Categoria> lista, Categoria categoria) {
        validar(lista, categoria);
        if (categoria.getId() <= 0) {
            categoria.setId(siguienteId(lista));
        } else if (buscarPorId(lista, categoria.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una categoría con id " + categoria.getId());
        }
        lista.add(categoria);
        return categoria;
    }

    /**
     * Reemplaza la categoría que tenga el mismo id que la recibida.
     *
     * @param lista     lista en memoria de categorías
     * @param categoria categoría con los datos actualizados
     * @return la categoría actualizada, o {@code null} si no existe una con ese id
     * @throws IllegalArgumentException si la lista o la categoría son {@code null}
     */
    @Override
    public Categoria actualizar(List<Categoria> lista, Categoria categoria) {
        validar(lista, categoria);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == categoria.getId()) {
                lista.set(i, categoria);
                return categoria;
            }
        }
        return null;
    }

    /**
     * Elimina la categoría con el id indicado.
     *
     * @param lista lista en memoria de categorías
     * @param id    identificador de la categoría a eliminar
     * @return {@code true} si se eliminó; {@code false} si no se encontró
     */
    @Override
    public boolean eliminar(List<Categoria> lista, int id) {
        return lista.removeIf(c -> c.getId() == id);
    }

    /**
     * Busca una categoría por su identificador.
     *
     * @param lista lista en memoria de categorías
     * @param id    identificador buscado
     * @return la categoría encontrada, o {@code null} si no existe
     */
    @Override
    public Categoria buscarPorId(List<Categoria> lista, int id) {
        for (Categoria c : lista) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca una categoría por su nombre.
     * <p>
     * La comparación ignora mayúsculas/minúsculas y espacios al inicio y al
     * final. Si varias categorías comparten el nombre, se retorna la primera
     * encontrada.
     * </p>
     *
     * @param lista  lista en memoria de categorías
     * @param nombre nombre que se desea buscar
     * @return la primera categoría cuyo nombre coincide, o {@code null} si no
     *         hay coincidencias o alguno de los parámetros es {@code null}
     */
    public Categoria buscarPorNombre(List<Categoria> lista, String nombre) {
        if (lista == null || nombre == null) {
            return null;
        }
        for (Categoria c : lista) {
            if (c.getNombre() != null && c.getNombre().trim().equalsIgnoreCase(nombre.trim())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Obtiene todas las categorías de la lista.
     *
     * @param lista lista en memoria de categorías
     * @return vista no modificable de la lista
     */
    @Override
    public List<Categoria> listar(List<Categoria> lista) {
        return Collections.unmodifiableList(lista);
    }

    /**
     * Calcula el siguiente identificador disponible (id máximo + 1).
     *
     * @param lista lista en memoria de categorías
     * @return el siguiente id; 1 si la lista está vacía
     */
    private int siguienteId(List<Categoria> lista) {
        int max = 0;
        for (Categoria c : lista) {
            max = Math.max(max, c.getId());
        }
        return max + 1;
    }

    /**
     * Verifica que la lista y la categoría no sean nulas.
     *
     * @param lista     lista a validar
     * @param categoria categoría a validar
     * @throws IllegalArgumentException si alguno de los parámetros es {@code null}
     */
    private void validar(List<Categoria> lista, Categoria categoria) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de categorías no puede ser nula");
        }
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
    }
}
