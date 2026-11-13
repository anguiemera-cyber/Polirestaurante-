
package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoDAO implements ICrudDAO<Pedido> {

    @Override
    public Pedido crear(List<Pedido> lista, Pedido pedido) {

        validar(lista, pedido);

        if (pedido.getId() <= 0) {
            pedido.setId(siguienteId(lista));
        } else if (buscarPorId(lista, pedido.getId()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un pedido con ese ID."
            );
        }

        lista.add(pedido);
        return pedido;
    }

    @Override
    public Pedido actualizar(List<Pedido> lista, Pedido pedido) {

        validar(lista, pedido);

        for (int i = 0; i < lista.size(); i++) {

            Pedido actual = lista.get(i);

            if (actual != null
                    && actual.getId() == pedido.getId()) {

                lista.set(i, pedido);
                return pedido;
            }
        }

        return null;
    }

    @Override
    public boolean eliminar(List<Pedido> lista, int id) {

        if (lista == null) {
            return false;
        }

        return lista.removeIf(
                p -> p != null && p.getId() == id
        );
    }

    @Override
    public Pedido buscarPorId(List<Pedido> lista, int id) {

        if (lista == null) {
            return null;
        }

        for (Pedido p : lista) {

            if (p != null && p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    @Override
    public List<Pedido> listar(List<Pedido> lista) {

        if (lista == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(
                new ArrayList<>(lista)
        );
    }

    public List<Pedido> buscarPorMesa(
            List<Pedido> lista, String mesa) {

        List<Pedido> resultado = new ArrayList<>();

        if (lista == null || mesa == null
                || mesa.trim().isEmpty()) {
            return resultado;
        }

        for (Pedido p : lista) {

            if (p != null && p.getMesa() != null
                    && p.getMesa().trim()
                    .equalsIgnoreCase(mesa.trim())) {

                resultado.add(p);
            }
        }

        return resultado;
    }

    public List<Pedido> buscarPorEstado(
            List<Pedido> lista,
            co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoPedido estado) {

        List<Pedido> resultado = new ArrayList<>();

        if (lista == null || estado == null) {
            return resultado;
        }

        for (Pedido p : lista) {

            if (p != null && p.getEstado() == estado) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    private int siguienteId(List<Pedido> lista) {

        int maximo = 0;

        for (Pedido p : lista) {

            if (p != null) {
                maximo = Math.max(maximo, p.getId());
            }
        }

        return maximo + 1;
    }

    private void validar(List<Pedido> lista, Pedido pedido) {

        if (lista == null || pedido == null) {
            throw new IllegalArgumentException(
                    "La lista y el pedido son obligatorios."
            );
        }

        if (pedido.getCantidad() <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad del pedido debe ser mayor que cero."
            );
        }

        if (pedido.getProductoPedido() == null) {
            throw new IllegalArgumentException(
                    "El pedido debe tener un producto."
            );
        }

        if (pedido.getMesa() == null
                || pedido.getMesa().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Debes indicar la mesa del pedido."
            );
        }

        if (pedido.getCosto() < 0) {
            throw new IllegalArgumentException(
                    "El costo no puede ser negativo."
            );
        }

        if (pedido.getEstado() == null) {
            throw new IllegalArgumentException(
                    "El pedido debe tener un estado."
            );
        }
    }
}
