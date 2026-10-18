
package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import java.util.Collections;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad Producto.
 *
 * Implementa las operaciones CRUD sobre listas en memoria.
 * También permite buscar productos por su nombre.
 *
 * @version 1.0
 * @see ICrudDAO
 * @see Producto
 */
public class ProductoDAO implements ICrudDAO<Producto> {

    // Registra un producto nuevo en la lista.
    // Si el producto no tiene un ID válido, le asigna uno automáticamente.
    // Si el ID ya existe, genera una excepción para evitar duplicados.

    @Override
    public Producto crear(List<Producto> lista, Producto producto) {
        validar(lista, producto);

        if (producto.getid() <= 0) {
            producto.setid(siguienteId(lista));
        } else if (buscarPorId(lista, producto.getid()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un producto con id " + producto.getid()
            );
        }

        lista.add(producto);
        return producto;
    }

    // Busca un producto mediante su ID.
    // Si lo encuentra, reemplaza el objeto por los datos actualizados.
    // Si no existe, devuelve null.

    @Override
    public Producto actualizar(List<Producto> lista, Producto producto) {
        validar(lista, producto);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getid() == producto.getid()) {
                lista.set(i, producto);
                return producto;
            }
        }

        return null;
    }

   
    // Elimina el producto que tenga el ID indicado.
    // Devuelve true si lo elimina y false si no existe o la lista es null.

    @Override
    public boolean eliminar(List<Producto> lista, int id) {
        if (lista == null) {
            return false;
        }

        return lista.removeIf(
            producto -> producto != null && producto.getid() == id
        );
    }

    
    // Recorre la lista hasta encontrar el producto con el ID solicitado.
    // Si no encuentra coincidencias, devuelve null.

    @Override
    public Producto buscarPorId(List<Producto> lista, int id) {
        if (lista == null) {
            return null;
        }

        for (Producto producto : lista) {
            if (producto != null && producto.getid() == id) {
                return producto;
            }
        }

        return null;
    }

    
    // Devuelve todos los productos registrados.
    // La lista retornada no permite agregar ni eliminar elementos
    // directamente desde el objeto que la recibe.

    @Override
    public List<Producto> listar(List<Producto> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(lista);
    }

   
    // Compara los nombres ignorando mayúsculas y minúsculas.
    // También ignora los espacios al inicio y al final del texto.
    // Devuelve el primer producto que coincida o null si no existe.

    public Producto buscarPorNombre(List<Producto> lista, String nombre) {
        if (lista == null || nombre == null) {
            return null;
        }

        for (Producto producto : lista) {
            if (producto != null
                    && producto.getnombre() != null
                    && producto.getnombre().trim()
                        .equalsIgnoreCase(nombre.trim())) {
                return producto;
            }
        }

        return null;
    }

    
    // Busca el identificador más alto de la lista y le suma uno.
    // Esto permite asignar IDs consecutivos a los productos nuevos.

    private int siguienteId(List<Producto> lista) {
        int max = 0;

        for (Producto producto : lista) {
            if (producto != null) {
                max = Math.max(max, producto.getid());
            }
        }

        return max + 1;
    }

    
    // Comprueba que la lista y el producto existan antes de ejecutar
    // operaciones que dependen de ambos parámetros.
    // Si alguno es null, informa el error mediante una excepción.

    private void validar(List<Producto> lista, Producto producto) {
        if (lista == null) {
            throw new IllegalArgumentException(
                "La lista de productos no puede ser nula"
            );
        }

        if (producto == null) {
            throw new IllegalArgumentException(
                "El producto no puede ser nulo"
            );
        }
    }
}
