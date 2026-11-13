
package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetallePedidoDAO implements ICrudDAO<DetallePedido> {

    @Override
    public DetallePedido crear(
            List<DetallePedido> lista,
            DetallePedido detalle) {

        validar(lista, detalle);

        if (detalle.getIdDetalle() <= 0) {

            detalle.setIdDetalle(siguienteId(lista));

        } else if (buscarPorId(lista, detalle.getIdDetalle()) != null) {

            throw new IllegalArgumentException(
                    "Ya existe un detalle con ese ID."
            );
        }

        lista.add(detalle);
        return detalle;
    }

    @Override
    public DetallePedido actualizar(
            List<DetallePedido> lista,
            DetallePedido detalle) {

        validar(lista, detalle);

        for (int i = 0; i < lista.size(); i++) {

            DetallePedido actual = lista.get(i);

            if (actual != null
                    && actual.getIdDetalle() == detalle.getIdDetalle()) {

                if (actual.getIdPedido() != detalle.getIdPedido()) {
                    throw new IllegalArgumentException(
                            "No se puede cambiar el pedido de un detalle."
                    );
                }

                lista.set(i, detalle);
                return detalle;
            }
        }

        return null;
    }

    @Override
    public boolean eliminar(List<DetallePedido> lista, int id) {

        if (lista == null) {
            return false;
        }

        return lista.removeIf(
                d -> d != null && d.getIdDetalle() == id
        );
    }

    public boolean eliminarPorPedido(
            List<DetallePedido> lista,
            int idPedido) {

        if (lista == null) {
            return false;
        }

        return lista.removeIf(
                d -> d != null && d.getIdPedido() == idPedido
        );
    }

    @Override
    public DetallePedido buscarPorId(
            List<DetallePedido> lista, int id) {

        if (lista == null) {
            return null;
        }

        for (DetallePedido d : lista) {

            if (d != null && d.getIdDetalle() == id) {
                return d;
            }
        }

        return null;
    }

    public List<DetallePedido> buscarPorPedido(
            List<DetallePedido> lista, int idPedido) {

        List<DetallePedido> resultado = new ArrayList<>();

        if (lista == null) {
            return resultado;
        }

        for (DetallePedido d : lista) {

            if (d != null && d.getIdPedido() == idPedido) {
                resultado.add(d);
            }
        }

        return resultado;
    }

    public List<DetallePedido> buscarPorProducto(
            List<DetallePedido> lista, int idProducto) {

        List<DetallePedido> resultado = new ArrayList<>();

        if (lista == null) {
            return resultado;
        }

        for (DetallePedido d : lista) {

            if (d != null && d.getIdProducto() == idProducto) {
                resultado.add(d);
            }
        }

        return resultado;
    }

    public double calcularTotal(List<DetallePedido> lista) {

        if (lista == null) {
            return 0;
        }

        double total = 0;

        for (DetallePedido d : lista) {

            if (d != null) {
                total += d.getSubtotal();
            }
        }

        return total;
    }

    public double calcularTotalPedido(
            List<DetallePedido> lista, int idPedido) {

        return calcularTotal(
                buscarPorPedido(lista, idPedido)
        );
    }

    @Override
    public List<DetallePedido> listar(List<DetallePedido> lista) {

        if (lista == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(
                new ArrayList<>(lista)
        );
    }

    private int siguienteId(List<DetallePedido> lista) {

        int maximo = 0;

        for (DetallePedido d : lista) {

            if (d != null) {
                maximo = Math.max(maximo, d.getIdDetalle());
            }
        }

        return maximo + 1;
    }

    private void validar(
            List<DetallePedido> lista,
            DetallePedido detalle) {

        if (lista == null || detalle == null) {
            throw new IllegalArgumentException(
                    "La lista y el detalle son obligatorios."
            );
        }

        if (detalle.getIdPedido() <= 0) {
            throw new IllegalArgumentException(
                    "El detalle debe pertenecer a un pedido válido."
            );
        }

        if (detalle.getCantidad() <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        if (!Double.isFinite(detalle.getPrecioUnitario())
                || detalle.getPrecioUnitario() < 0) {

            throw new IllegalArgumentException(
                    "El precio unitario no es válido."
            );
        }
    }
}
