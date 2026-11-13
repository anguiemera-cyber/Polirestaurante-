package co.edu.poligran.paradigmas.polirestaurante.vista;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoPedido;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;

import java.util.List;

public class PedidoVista {

    private final ErikManager operaciones;

    public PedidoVista(ErikManager operaciones) {

        if (operaciones == null) {
            throw new IllegalArgumentException(
                    "ErikManager no puede ser nulo."
            );
        }

        this.operaciones = operaciones;
    }

    // LISTAR PEDIDOS
    public List<Pedido> listarPedidos() {
        return operaciones.listarPedidos();
    }

    // BUSCAR PEDIDO POR ID
    public Pedido buscarPedidoPorId(int id) {
        return operaciones.buscarPedidoPorId(id);
    }

    // LISTAR PRODUCTOS DISPONIBLES
    public List<Producto> listarProductosDisponibles() {
        return operaciones.listarProductosDisponibles();
    }

    // AGREGAR PEDIDO
    public Pedido agregarPedido(
            Producto producto,
            int cantidad,
            String mesa,
            String detalles) {

        validarDatos(producto, cantidad, mesa);

        long costo = Math.round(producto.getPrecio() * cantidad);

        Pedido pedido = new Pedido(
                producto,
                cantidad,
                costo,
                mesa.trim(),
                detalles == null ? "" : detalles.trim()
        );

        return operaciones.crearPedido(pedido);
    }

    // ACTUALIZAR PEDIDO
    public boolean actualizarPedido(
            int id,
            Producto producto,
            int cantidad,
            String mesa,
            String detalles,
            EstadoPedido estado) {

        validarDatos(producto, cantidad, mesa);

        if (estado == null) {
            throw new IllegalArgumentException(
                    "Debes seleccionar un estado."
            );
        }

        Pedido existente = operaciones.buscarPedidoPorId(id);

        if (existente == null) {
            return false;
        }

        long costo = Math.round(producto.getPrecio() * cantidad);

        Pedido pedido = new Pedido(
                id,
                producto,
                cantidad,
                costo,
                mesa.trim(),
                detalles == null ? "" : detalles.trim(),
                estado
        );

        return operaciones.actualizarPedido(pedido);
    }

    // ELIMINAR PEDIDO
    public boolean eliminarPedido(int id) {
        return operaciones.eliminarPedido(id);
    }

    // CAMBIAR ESTADO
    public boolean cambiarEstadoPedido(
            int id,
            EstadoPedido estado) {

        return operaciones.cambiarEstadoPedido(id, estado);
    }

    // CALCULAR TOTAL DE LOS DETALLES
    public double calcularTotalPedido(int idPedido) {
        return operaciones.calcularTotalPedido(idPedido);
    }

    // VALIDAR INFORMACIÓN
    private void validarDatos(
            Producto producto,
            int cantidad,
            String mesa) {

        if (producto == null) {
            throw new IllegalArgumentException(
                    "Debes seleccionar un producto."
            );
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        if (mesa == null || mesa.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Debes ingresar la mesa o modalidad del pedido."
            );
        }

        if (!producto.isDisponible()) {
            throw new IllegalArgumentException(
                    "El producto seleccionado no está disponible."
            );
        }
    }

    // CONTADORES DE PEDIDOS
    public int contarPedidos() {
        return listarPedidos().size();
    }

    public int contarPedidosPorEstado(EstadoPedido estado) {

        int contador = 0;

        for (Pedido pedido : listarPedidos()) {
            if (pedido.getEstado() == estado) {
                contador++;
            }
        }

        return contador;
    }

    // ACCESO AL MANAGER
    public ErikManager getOperaciones() {
        return operaciones;
    }
}
