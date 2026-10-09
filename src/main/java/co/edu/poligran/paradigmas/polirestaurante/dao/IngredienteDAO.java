/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;
import java.util.Collections;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad {@link Ingrediente}.
 * <p>
 * Implementa las operaciones CRUD de {@link ICrudDAO} sobre listas en
 * memoria y añade la búsqueda por nombre y por disponibilidad.
 * </p>
 *
 * @version 1.0
 * @see ICrudDAO
 * @see Ingrediente
 */
public class IngredienteDAO implements ICrudDAO<Ingrediente> {

    /**
     * Agrega un ingrediente a la lista.
     * <p>
     * Si su id es menor o igual a cero, se le asigna el siguiente id disponible.
     * </p>
     *
     * @param lista       lista en memoria de ingredientes
     * @param ingrediente ingrediente que se desea registrar
     * @return el ingrediente registrado, con su id asignado
     * @throws IllegalArgumentException si la lista o el ingrediente son {@code null},
     *                                  o si ya existe un ingrediente con el mismo id
     */
    @Override
    public Ingrediente crear(List<Ingrediente> lista, Ingrediente ingrediente) {
        validar(lista, ingrediente);
        if (ingrediente.getId() <= 0) {
            ingrediente.setId(siguienteId(lista));
        } else if (buscarPorId(lista, ingrediente.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un ingrediente con id " + ingrediente.getId());
        }
        lista.add(ingrediente);
        return ingrediente;
    }

    /**
     * Reemplaza el ingrediente que tenga el mismo id que el recibido.
     *
     * @param lista       lista en memoria de ingredientes
     * @param ingrediente ingrediente con los datos actualizados
     * @return el ingrediente actualizado, o {@code null} si no existe uno con ese id
     * @throws IllegalArgumentException si la lista o el ingrediente son {@code null}
     */
    @Override
    public Ingrediente actualizar(List<Ingrediente> lista, Ingrediente ingrediente) {
        validar(lista, ingrediente);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == ingrediente.getId()) {
                lista.set(i, ingrediente);
                return ingrediente;
            }
        }
        return null;
    }

    /**
     * Elimina el ingrediente con el id indicado.
     *
     * @param lista lista en memoria de ingredientes
     * @param id    identificador del ingrediente a eliminar
     * @return {@code true} si se eliminó; {@code false} si no se encontró
     */
    @Override
    public boolean eliminar(List<Ingrediente> lista, int id) {
        return lista.removeIf(i -> i.getId() == id);
    }

    /**
     * Busca un ingrediente por su identificador.
     *
     * @param lista lista en memoria de ingredientes
     * @param id    identificador buscado
     * @return el ingrediente encontrado, o {@code null} si no existe
     */
    @Override
    public Ingrediente buscarPorId(List<Ingrediente> lista, int id) {
        for (Ingrediente i : lista) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Busca un ingrediente por su nombre.
     * <p>
     * La comparación ignora mayúsculas/minúsculas y espacios al inicio y al
     * final. Si varios ingredientes comparten el nombre, se retorna el primero
     * encontrado.
     * </p>
     *
     * @param lista  lista en memoria de ingredientes
     * @param nombre nombre que se desea buscar
     * @return el primer ingrediente cuyo nombre coincide, o {@code null} si no
     *         hay coincidencias o alguno de los parámetros es {@code null}
     */
    public Ingrediente buscarPorNombre(List<Ingrediente> lista, String nombre) {
        if (lista == null || nombre == null) {
            return null;
        }
        for (Ingrediente i : lista) {
            if (i.getNombre() != null && i.getNombre().trim().equalsIgnoreCase(nombre.trim())) {
                return i;
            }
        }
        return null;
    }

    /**
     * Obtiene los ingredientes que se encuentran disponibles.
     *
     * @param lista lista en memoria de ingredientes
     * @return lista (posiblemente vacía) de ingredientes disponibles
     */
    public List<Ingrediente> listarDisponibles(List<Ingrediente> lista) {
        return lista.stream()
                .filter(Ingrediente::isDisponibilidad)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Obtiene todos los ingredientes de la lista.
     *
     * @param lista lista en memoria de ingredientes
     * @return vista no modificable de la lista
     */
    @Override
    public List<Ingrediente> listar(List<Ingrediente> lista) {
        return Collections.unmodifiableList(lista);
    }

    /**
     * Calcula el siguiente identificador disponible (id máximo + 1).
     *
     * @param lista lista en memoria de ingredientes
     * @return el siguiente id; 1 si la lista está vacía
     */
    private int siguienteId(List<Ingrediente> lista) {
        int max = 0;
        for (Ingrediente i : lista) {
            max = Math.max(max, i.getId());
        }
        return max + 1;
    }

    /**
     * Verifica que la lista y el ingrediente no sean nulos.
     *
     * @param lista       lista a validar
     * @param ingrediente ingrediente a validar
     * @throws IllegalArgumentException si alguno de los parámetros es {@code null}
     */
    private void validar(List<Ingrediente> lista, Ingrediente ingrediente) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de ingredientes no puede ser nula");
        }
        if (ingrediente == null) {
            throw new IllegalArgumentException("El ingrediente no puede ser nulo");
        }
    }
}
