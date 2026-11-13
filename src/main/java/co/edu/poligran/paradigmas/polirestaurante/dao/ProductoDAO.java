
package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductoDAO implements ICrudDAO<Producto> {

    @Override
    public Producto crear(List<Producto> lista, Producto producto) {

        validar(lista, producto);

        if (buscarPorNombre(lista, producto.getNombre()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con ese nombre."
            );
        }

        if (producto.getId() <= 0) {
            producto.setId(siguienteId(lista));
        } else if (buscarPorId(lista, producto.getId()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con ese ID."
            );
        }

        lista.add(producto);
        return producto;
    }

    @Override
    public Producto actualizar(List<Producto> lista, Producto producto) {

        validar(lista, producto);

        for (int i = 0; i < lista.size(); i++) {

            Producto actual = lista.get(i);

            if (actual != null
                    && actual.getId() == producto.getId()) {

                Producto repetido =
                        buscarPorNombre(lista, producto.getNombre());

                if (repetido != null
                        && repetido.getId() != producto.getId()) {

                    throw new IllegalArgumentException(
                            "Otro producto ya utiliza ese nombre."
                    );
                }

                lista.set(i, producto);
                return producto;
            }
        }

        return null;
    }

    @Override
    public boolean eliminar(List<Producto> lista, int id) {

        if (lista == null) {
            return false;
        }

        return lista.removeIf(
                p -> p != null && p.getId() == id
        );
    }

    @Override
    public Producto buscarPorId(List<Producto> lista, int id) {

        if (lista == null) {
            return null;
        }

        for (Producto p : lista) {

            if (p != null && p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    @Override
    public List<Producto> listar(List<Producto> lista) {

        if (lista == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(
                new ArrayList<>(lista)
        );
    }

    public Producto buscarPorNombre(
            List<Producto> lista, String nombre) {

        if (lista == null || nombre == null
                || nombre.trim().isEmpty()) {
            return null;
        }

        for (Producto p : lista) {

            if (p != null && p.getNombre() != null
                    && p.getNombre().trim()
                    .equalsIgnoreCase(nombre.trim())) {

                return p;
            }
        }

        return null;
    }

    public List<Producto> buscarPorTipo(
            List<Producto> lista, String tipo) {

        List<Producto> resultado = new ArrayList<>();

        if (lista == null || tipo == null) {
            return resultado;
        }

        for (Producto p : lista) {

            if (p != null && p.getTipo() != null
                    && p.getTipo().equalsIgnoreCase(tipo.trim())) {

                resultado.add(p);
            }
        }

        return resultado;
    }

    public List<Producto> listarBebidas(List<Producto> lista) {
        return buscarPorTipo(lista, "Bebida");
    }

    public List<Producto> listarDisponibles(List<Producto> lista) {

        List<Producto> resultado = new ArrayList<>();

        if (lista == null) {
            return resultado;
        }

        for (Producto p : lista) {

            if (p != null && p.isDisponible()) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    private int siguienteId(List<Producto> lista) {

        int maximo = 0;

        for (Producto p : lista) {

            if (p != null) {
                maximo = Math.max(maximo, p.getId());
            }
        }

        return maximo + 1;
    }

    private void validar(List<Producto> lista, Producto producto) {

        if (lista == null || producto == null) {
            throw new IllegalArgumentException(
                    "La lista y el producto son obligatorios."
            );
        }

        if (producto.getNombre() == null
                || producto.getNombre().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre del producto es obligatorio."
            );
        }

        if (producto.getTipo() == null
                || producto.getTipo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El tipo del producto es obligatorio."
            );
        }

        if (!Double.isFinite(producto.getPrecio())
                || producto.getPrecio() < 0) {

            throw new IllegalArgumentException(
                    "El precio debe ser un número válido mayor o igual a cero."
            );
        }
    }
}
