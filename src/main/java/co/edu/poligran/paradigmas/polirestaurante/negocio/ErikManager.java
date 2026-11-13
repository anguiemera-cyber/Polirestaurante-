
package co.edu.poligran.paradigmas.polirestaurante.negocio;

import co.edu.poligran.paradigmas.polirestaurante.dao.PedidoDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.ProductoDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.CocineroDAO;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Cocinero;
import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoPedido;

import java.util.ArrayList;
import java.util.List;

public class ErikManager {

    private final List<Pedido> pedidos;
    private final List<Producto> productos;
    private final List<Cocinero> cocineros;

    private final PedidoDAO pedidoDAO;
    private final ProductoDAO productoDAO;
    private final CocineroDAO cocineroDAO;

    private final AngieManager angie;

    public ErikManager() {
        this(new AngieManager());
    }

    public ErikManager(AngieManager angie) {

        if (angie == null) {
            throw new IllegalArgumentException(
                    "AngieManager no puede ser nulo."
            );
        }

        this.angie = angie;

        pedidos = new ArrayList<>();
        productos = new ArrayList<>();
        cocineros = new ArrayList<>();

        pedidoDAO = new PedidoDAO();
        productoDAO = new ProductoDAO();
        cocineroDAO = new CocineroDAO();
    }

    // =========================================
    // PRODUCTOS
    // =========================================

    public Producto crearProducto(Producto producto) {
        return productoDAO.crear(productos, producto);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listar(productos);
    }

    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarPorId(productos, id);
    }

    public Producto buscarProductoPorNombre(String nombre) {
        return productoDAO.buscarPorNombre(productos, nombre);
    }

    public List<Producto> buscarProductosPorTipo(String tipo) {
        return productoDAO.buscarPorTipo(productos, tipo);
    }

    public List<Producto> listarProductosDisponibles() {
        return productoDAO.listarDisponibles(productos);
    }

    public List<Producto> listarBebidas() {
        return productoDAO.listarBebidas(productos);
    }

    public List<Producto> listarBebidasDisponibles() {

        List<Producto> resultado = new ArrayList<>();

        for (Producto producto : listarBebidas()) {

            if (producto.isDisponible()) {
                resultado.add(producto);
            }
        }

        return resultado;
    }

    public boolean actualizarProducto(Producto datos) {
        return productoDAO.actualizar(productos, datos) != null;
    }

    public boolean eliminarProducto(int id) {

        // No se elimina un producto que ya está en un pedido.
        for (DetallePedido detalle : angie.listarDetalles()) {

            if (detalle.getIdProducto() == id) {
                return false;
            }
        }

        // Tampoco se elimina si está asociado a un pedido antiguo.
        for (Pedido pedido : pedidos) {

            if (pedido.getProductoPedido() != null
                    && pedido.getProductoPedido().getId() == id) {
                return false;
            }
        }

        return productoDAO.eliminar(productos, id);
    }

    public boolean cambiarDisponibilidadProducto(
            int id, boolean disponible) {

        Producto producto = buscarProductoPorId(id);

        if (producto == null) {
            return false;
        }

        producto.setDisponible(disponible);
        return true;
    }

    // =========================================
    // PEDIDOS
    // =========================================

    public Pedido crearPedido(Pedido pedido) {

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "El pedido no puede ser nulo."
            );
        }

        return pedidoDAO.crear(pedidos, pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.listar(pedidos);
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoDAO.buscarPorId(pedidos, id);
    }

    public List<Pedido> buscarPedidosPorMesa(String mesa) {
        return pedidoDAO.buscarPorMesa(pedidos, mesa);
    }

    public List<Pedido> buscarPedidosPorEstado(EstadoPedido estado) {
        return pedidoDAO.buscarPorEstado(pedidos, estado);
    }

    public boolean actualizarPedido(Pedido datos) {
        return pedidoDAO.actualizar(pedidos, datos) != null;
    }

    public boolean eliminarPedido(int id) {

        Pedido pedido = buscarPedidoPorId(id);

        if (pedido == null) {
            return false;
        }

        // Primero se eliminan sus detalles.
        angie.eliminarDetallesDePedido(id);

        // Después se elimina el pedido.
        return pedidoDAO.eliminar(pedidos, id);
    }

    public boolean cambiarEstadoPedido(
            int id, EstadoPedido estado) {

        if (estado == null) {
            throw new IllegalArgumentException(
                    "El estado del pedido no puede ser nulo."
            );
        }

        Pedido pedido = buscarPedidoPorId(id);

        if (pedido == null) {
            return false;
        }

        pedido.setEstado(estado);
        return true;
    }

    // =========================================
    // DETALLES DE PEDIDOS
    // =========================================

    public DetallePedido crearDetallePedido(DetallePedido detalle) {

        if (detalle == null) {
            throw new IllegalArgumentException(
                    "El detalle no puede ser nulo."
            );
        }

        if (buscarPedidoPorId(detalle.getIdPedido()) == null) {
            throw new IllegalArgumentException(
                    "No existe el pedido indicado."
            );
        }

        Producto producto =
                buscarProductoPorId(detalle.getIdProducto());

        if (producto == null) {
            throw new IllegalArgumentException(
                    "No existe el producto indicado."
            );
        }

        if (!producto.isDisponible()) {
            throw new IllegalArgumentException(
                    "El producto no está disponible."
            );
        }

        // Se utiliza el precio actual del producto.
        detalle.setPrecioUnitario(producto.getPrecio());

        return angie.crearDetalle(detalle);
    }

    public List<DetallePedido> listarDetalles() {
        return angie.listarDetalles();
    }

    public List<DetallePedido> buscarDetallesPorPedido(int idPedido) {
        return angie.buscarDetallesPorPedido(idPedido);
    }

    public double calcularTotalPedido(int idPedido) {
        return angie.calcularTotalPedido(idPedido);
    }

    public boolean actualizarDetallePedido(DetallePedido detalle) {

        if (detalle == null) {
            return false;
        }

        if (buscarPedidoPorId(detalle.getIdPedido()) == null) {
            return false;
        }

        Producto producto =
                buscarProductoPorId(detalle.getIdProducto());

        if (producto == null || !producto.isDisponible()) {
            return false;
        }

        detalle.setPrecioUnitario(producto.getPrecio());

        return angie.actualizarDetalle(detalle);
    }

    public boolean eliminarDetallePedido(int id) {
        return angie.eliminarDetalle(id);
    }

    // =========================================
    // COCINEROS
    // =========================================

    public Cocinero crearCocinero(Cocinero cocinero) {
        return cocineroDAO.crear(cocineros, cocinero);
    }

    public List<Cocinero> listarCocineros() {
        return cocineroDAO.listar(cocineros);
    }

    public Cocinero buscarCocineroPorId(int id) {
        return cocineroDAO.buscarPorId(cocineros, id);
    }

    public Cocinero buscarCocineroPorUsuario(String usuario) {
        return cocineroDAO.buscarPorUsuario(cocineros, usuario);
    }

    public boolean actualizarCocinero(Cocinero datos) {
        return cocineroDAO.actualizar(cocineros, datos) != null;
    }

    public boolean eliminarCocinero(int id) {
        return cocineroDAO.eliminar(cocineros, id);
    }
    // ACCESO AL MANAGER COMPARTIDO

    public AngieManager getAngieManager() {
        return angie;
    }
}
