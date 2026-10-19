
package co.edu.poligran.paradigmas.polirestaurante.negocio;

import co.edu.poligran.paradigmas.polirestaurante.dao.PedidoDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.ProductoDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.CocineroDAO;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Cocinero;
import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoPedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de administrar pedidos,
 * productos y cocineros del restaurante.
 *
 * Coordina las operaciones entre los modelos y sus DAO.
 *
 */
public class ErikManager {

    // Listas en memoria para almacenar las entidades.
    private final List<Pedido> pedidos;
    private final List<Producto> productos;
    private final List<Cocinero> cocineros;

    // DAO encargado de las operaciones de cada entidad.
    private final PedidoDAO pedidoDAO;
    private final ProductoDAO productoDAO;
    private final CocineroDAO cocineroDAO;

    // Inicializa las listas y los respectivos DAO.
    public ErikManager() {
        this.pedidos = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.cocineros = new ArrayList<>();

        this.pedidoDAO = new PedidoDAO();
        this.productoDAO = new ProductoDAO();
        this.cocineroDAO = new CocineroDAO();
    }

    // Métodos para administrar los pedidos.

    // Registra un pedido nuevo.
    public Pedido crearPedido(Pedido pedido) {
        return pedidoDAO.crear(pedidos, pedido);
    }

    // Obtiene todos los pedidos registrados.
    public List<Pedido> listarPedidos() {
        return pedidoDAO.listar(pedidos);
    }

    // Busca un pedido por su identificador.
    public Pedido buscarPedidoPorId(int id) {
        return pedidoDAO.buscarPorId(pedidos, id);
    }

    // Busca los pedidos asociados a una mesa.
    public List<Pedido> buscarPedidosPorMesa(String mesa) {
        return pedidoDAO.buscarPorMesa(pedidos, mesa);
    }

    // Actualiza los datos de un pedido existente.
    public boolean actualizarPedido(Pedido datos) {
        return pedidoDAO.actualizar(pedidos, datos) != null;
    }

    // Elimina un pedido mediante su identificador.
    public boolean eliminarPedido(int id) {
        return pedidoDAO.eliminar(pedidos, id);
    }

    // Cambia el estado actual de un pedido.
    public boolean cambiarEstadoPedido(int id, EstadoPedido estado) {
        if (estado == null) {
            throw new IllegalArgumentException(
                "El estado del pedido no puede ser nulo."
            );
        }

        Pedido pedido = pedidoDAO.buscarPorId(pedidos, id);

        if (pedido == null) {
            return false;
        }

        pedido.setEstado(estado);
        return true;
    }

    // Métodos para administrar los productos.

    // Registra un producto nuevo.
    public Producto crearProducto(Producto producto) {
        return productoDAO.crear(productos, producto);
    }

    // Obtiene todos los productos registrados.
    public List<Producto> listarProductos() {
        return productoDAO.listar(productos);
    }

    // Busca un producto por su identificador.
    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarPorId(productos, id);
    }

    // Busca un producto por su nombre.
    public Producto buscarProductoPorNombre(String nombre) {
        return productoDAO.buscarPorNombre(productos, nombre);
    }

    // Actualiza los datos de un producto existente.
    public boolean actualizarProducto(Producto datos) {
        return productoDAO.actualizar(productos, datos) != null;
    }

    // Elimina un producto mediante su identificador.
    public boolean eliminarProducto(int id) {
        return productoDAO.eliminar(productos, id);
    }

    // Métodos para administrar los cocineros.

    // Registra un cocinero nuevo.
    public Cocinero crearCocinero(Cocinero cocinero) {
        return cocineroDAO.crear(cocineros, cocinero);
    }

    // Obtiene todos los cocineros registrados.
    public List<Cocinero> listarCocineros() {
        return cocineroDAO.listar(cocineros);
    }

    // Busca un cocinero por su identificador.
    public Cocinero buscarCocineroPorId(int id) {
        return cocineroDAO.buscarPorId(cocineros, id);
    }

    // Busca un cocinero por su nombre de usuario.
    public Cocinero buscarCocineroPorUsuario(String usuario) {
        return cocineroDAO.buscarPorUsuario(cocineros, usuario);
    }

    // Actualiza los datos de un cocinero existente.
    public boolean actualizarCocinero(Cocinero datos) {
        return cocineroDAO.actualizar(cocineros, datos) != null;
    }

    // Elimina un cocinero mediante su identificador.
    public boolean eliminarCocinero(int id) {
        return cocineroDAO.eliminar(cocineros, id);
    }
}
