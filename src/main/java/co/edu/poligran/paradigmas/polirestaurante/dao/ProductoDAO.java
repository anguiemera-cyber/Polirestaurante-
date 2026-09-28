package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import java.util.Collections;
import java.util.List;

/**
 * DAO para la entidad Pedido.
 * Implementa operaciones CRUD sobre listas en memoria.
 */
public class PedidoDAO implements ICrudDAO<Pedido> {

    @Override
    public Pedido crear(List<Pedido> lista, Pedido pedido) {
        validar(lista, pedido);

        if (pedido.getid() <= 0) {
            pedido.setid(siguienteId(lista));
        } else if (buscarPorId(lista, pedido.getid()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un pedido con id " + pedido.getid()
            );
        }

        lista.add(pedido);
        return pedido;
    }

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

    @Override
    public boolean eliminar(List<Pedido> lista, int id) {
        if (lista == null) {
            return false;
        }

        return lista.removeIf(p -> p.getid() == id);
    }

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

    @Override
    public List<Pedido> listar(List<Pedido> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(lista);
    }

    /**
     * Busca pedidos asociados a una mesa.
     */
    public List<Pedido> buscarPorMesa(List<Pedido> lista, String mesa) {
        if (lista == null || mesa == null) {
            return Collections.emptyList();
        }

        return lista.stream()
                .filter(p -> mesa.equalsIgnoreCase(p.getmesa()))
                .toList();
    }

    private int siguienteId(List<Pedido> lista) {
        int max = 0;

        for (Pedido p : lista) {
            max = Math.max(max, p.getid());
        }

        return max + 1;
    }

    private void validar(List<Pedido> lista, Pedido pedido) {
        if (lista == null) {
            throw new IllegalArgumentException(
                "La lista de pedidos no puede ser nula"
            );
        }

        if (pedido == null) {
            throw new IllegalArgumentException(
                "El pedido no puede ser nulo"
            );
        }

        if (pedido.getproductopedido() == null) {
            throw new IllegalArgumentException(
                "El producto del pedido no puede ser nulo"
            );
        }
    }
}
