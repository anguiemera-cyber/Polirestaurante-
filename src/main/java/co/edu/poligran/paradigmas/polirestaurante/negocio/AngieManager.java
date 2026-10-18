
package co.edu.poligran.paradigmas.polirestaurante.negocio;

import co.edu.poligran.paradigmas.polirestaurante.dao.CategoriaDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.DetallePedidoDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.IngredienteDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.MesaDAO;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Categoria;
import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * Capa de negocio del restaurante.
 * <p>
 * Coordina los DAO de {@link Mesa}, {@link Ingrediente}, {@link Categoria} y
 * {@link DetallePedido}, y mantiene las listas materializadas en memoria:
 * </p>
 * <ul>
 *   <li><b>mesas</b>: lista en memoria de las mesas del restaurante.</li>
 *   <li><b>ingredientes</b>: lista en memoria de los ingredientes.</li>
 *   <li><b>categorias</b>: lista en memoria de las categorías.</li>
 *   <li><b>detalles</b>: lista global de detalles de pedido, enlazada con
 *       el pedido mediante {@code idPedido} (composición).</li>
 * </ul>
 * <p>
 * Mesa, Ingrediente y Categoría son independientes entre sí, por lo que cada
 * lista se administra por separado. Los detalles de pedido, en cambio,
 * dependen de su pedido: sin pedido no deben quedar detalles huérfanos.
 * </p>
 * @see MesaDAO
 * @see IngredienteDAO
 * @see CategoriaDAO
 * @see DetallePedidoDAO
 * @author anyim
 */
public class AngieManager {
    
    /** Lista materializada de mesas. */
    private final List<Mesa> mesas;
 
    /** Lista materializada de ingredientes. */
    private final List<Ingrediente> ingredientes;
 
    /** Lista materializada de categorías. */
    private final List<Categoria> categorias;

    /** Lista materializada global de detalles de pedido. */
    private final List<DetallePedido> detalles;
 
    /** DAO para las operaciones sobre mesas. */
    private final MesaDAO mesaDAO;
 
    /** DAO para las operaciones sobre ingredientes. */
    private final IngredienteDAO ingredienteDAO;
 
    /** DAO para las operaciones sobre categorías. */
    private final CategoriaDAO categoriaDAO;

    /** DAO para las operaciones sobre detalles de pedido. */
    private final DetallePedidoDAO detallePedidoDAO;
 
    /**
     * Crea un manager con las listas de mesas, ingredientes, categorías y
     * detalles de pedido vacías.
     */
    public AngieManager() {
        this.mesas = new ArrayList<>();
        this.ingredientes = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.detalles = new ArrayList<>();
        this.mesaDAO = new MesaDAO();
        this.ingredienteDAO = new IngredienteDAO();
        this.categoriaDAO = new CategoriaDAO();
        this.detallePedidoDAO = new DetallePedidoDAO();
    }
 
    // =====================================================================
    // Mesas
    // =====================================================================
 
    /**
     * Registra una mesa.
     *
     * @param mesa mesa que se desea registrar
     * @return la mesa registrada, con su id asignado
     * @throws IllegalArgumentException si la mesa es {@code null} o su id ya existe
     */
    public Mesa crearMesa(Mesa mesa) {
        return mesaDAO.crear(mesas, mesa);
    }
 
    /**
     * Obtiene todas las mesas registradas.
     *
     * @return vista no modificable de la lista de mesas
     */
    public List<Mesa> listarMesas() {
        return mesaDAO.listar(mesas);
    }
 
    /**
     * Busca una mesa por su identificador.
     *
     * @param id identificador de la mesa
     * @return la mesa encontrada, o {@code null} si no existe
     */
    public Mesa buscarMesaPorId(int id) {
        return mesaDAO.buscarPorId(mesas, id);
    }
 
    /**
     * Busca las mesas que se encuentran en un estado determinado.
     *
     * @param estado estado que se desea buscar
     * @return lista (posiblemente vacía) de mesas con ese estado
     * @see MesaDAO#buscarPorEstado(List, int)
     */
    public List<Mesa> buscarMesasPorEstado(int estado) {
        return mesaDAO.buscarPorEstado(mesas, estado);
    }
 
    /**
     * Actualiza la capacidad y el estado de una mesa.
     *
     * @param datos mesa con el id a actualizar y los nuevos datos
     * @return {@code true} si se actualizó; {@code false} si no existe una
     *         mesa con ese id
     */
    public boolean actualizarMesa(Mesa datos) {
        return mesaDAO.actualizar(mesas, datos) != null;
    }
 
    /**
     * Elimina una mesa.
     *
     * @param id identificador de la mesa a eliminar
     * @return {@code true} si se eliminó; {@code false} si no existe
     */
    public boolean eliminarMesa(int id) {
        return mesaDAO.eliminar(mesas, id);
    }
 
    // =====================================================================
    // Ingredientes
    // =====================================================================
 
    /**
     * Registra un ingrediente.
     *
     * @param ingrediente ingrediente que se desea registrar
     * @return el ingrediente registrado, con su id asignado
     * @throws IllegalArgumentException si el ingrediente es {@code null} o su id ya existe
     */
    public Ingrediente crearIngrediente(Ingrediente ingrediente) {
        return ingredienteDAO.crear(ingredientes, ingrediente);
    }
 
    /**
     * Obtiene todos los ingredientes registrados.
     *
     * @return vista no modificable de la lista de ingredientes
     */
    public List<Ingrediente> listarIngredientes() {
        return ingredienteDAO.listar(ingredientes);
    }
 
    /**
     * Obtiene los ingredientes que están disponibles para su uso.
     *
     * @return lista (posiblemente vacía) de ingredientes disponibles
     * @see IngredienteDAO#listarDisponibles(List)
     */
    public List<Ingrediente> listarIngredientesDisponibles() {
        return ingredienteDAO.listarDisponibles(ingredientes);
    }
 
    /**
     * Busca un ingrediente por su identificador.
     *
     * @param id identificador del ingrediente
     * @return el ingrediente encontrado, o {@code null} si no existe
     */
    public Ingrediente buscarIngredientePorId(int id) {
        return ingredienteDAO.buscarPorId(ingredientes, id);
    }
 
    /**
     * Busca un ingrediente por su nombre.
     *
     * @param nombre nombre que se desea buscar (no distingue mayúsculas)
     * @return el primer ingrediente que coincide, o {@code null} si no existe
     * @see IngredienteDAO#buscarPorNombre(List, String)
     */
    public Ingrediente buscarIngrediente(String nombre) {
        return ingredienteDAO.buscarPorNombre(ingredientes, nombre);
    }
 
    /**
     * Actualiza los datos de un ingrediente.
     *
     * @param datos ingrediente con el id a actualizar y los nuevos datos
     * @return {@code true} si se actualizó; {@code false} si no existe un
     *         ingrediente con ese id
     */
    public boolean actualizarIngrediente(Ingrediente datos) {
        return ingredienteDAO.actualizar(ingredientes, datos) != null;
    }
 
    /**
     * Elimina un ingrediente.
     *
     * @param id identificador del ingrediente a eliminar
     * @return {@code true} si se eliminó; {@code false} si no existe
     */
    public boolean eliminarIngrediente(int id) {
        return ingredienteDAO.eliminar(ingredientes, id);
    }
 
    // =====================================================================
    // Categorías
    // =====================================================================
 
    /**
     * Registra una categoría.
     *
     * @param categoria categoría que se desea registrar
     * @return la categoría registrada, con su id asignado
     * @throws IllegalArgumentException si la categoría es {@code null} o su id ya existe
     */
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaDAO.crear(categorias, categoria);
    }
 
    /**
     * Obtiene todas las categorías registradas.
     *
     * @return vista no modificable de la lista de categorías
     */
    public List<Categoria> listarCategorias() {
        return categoriaDAO.listar(categorias);
    }
 
    /**
     * Busca una categoría por su identificador.
     *
     * @param id identificador de la categoría
     * @return la categoría encontrada, o {@code null} si no existe
     */
    public Categoria buscarCategoriaPorId(int id) {
        return categoriaDAO.buscarPorId(categorias, id);
    }
 
    /**
     * Busca una categoría por su nombre.
     *
     * @param nombre nombre que se desea buscar (no distingue mayúsculas)
     * @return la primera categoría que coincide, o {@code null} si no existe
     * @see CategoriaDAO#buscarPorNombre(List, String)
     */
    public Categoria buscarCategoria(String nombre) {
        return categoriaDAO.buscarPorNombre(categorias, nombre);
    }
 
    /**
     * Actualiza el nombre y la descripción de una categoría.
     *
     * @param datos categoría con el id a actualizar y los nuevos datos
     * @return {@code true} si se actualizó; {@code false} si no existe una
     *         categoría con ese id
     */
    public boolean actualizarCategoria(Categoria datos) {
        return categoriaDAO.actualizar(categorias, datos) != null;
    }
 
    /**
     * Elimina una categoría.
     *
     * @param id identificador de la categoría a eliminar
     * @return {@code true} si se eliminó; {@code false} si no existe
     */
    public boolean eliminarCategoria(int id) {
        return categoriaDAO.eliminar(categorias, id);
    }

    // =====================================================================
    // Detalles de pedido
    // =====================================================================

    /**
     * Registra un detalle de pedido.
     *
     * @param detalle detalle que se desea registrar; debe traer el
     *                {@code idPedido} al que pertenece
     * @return el detalle registrado, con su id asignado
     * @throws IllegalArgumentException si el detalle es {@code null}, no tiene
     *                                  {@code idPedido} válido o su id ya existe
     */
    public DetallePedido crearDetalle(DetallePedido detalle) {
        return detallePedidoDAO.crear(detalles, detalle);
    }

    /**
     * Obtiene todos los detalles de pedido registrados.
     *
     * @return vista no modificable de la lista global de detalles
     */
    public List<DetallePedido> listarDetalles() {
        return detallePedidoDAO.listar(detalles);
    }

    /**
     * Busca un detalle de pedido por su identificador.
     *
     * @param id identificador del detalle
     * @return el detalle encontrado, o {@code null} si no existe
     */
    public DetallePedido buscarDetallePorId(int id) {
        return detallePedidoDAO.buscarPorId(detalles, id);
    }

    /**
     * Busca todos los detalles que pertenecen a un pedido.
     *
     * @param idPedido identificador del pedido
     * @return lista (posiblemente vacía) con los detalles del pedido
     * @see DetallePedidoDAO#buscarPorPedido(List, int)
     */
    public List<DetallePedido> buscarDetallesPorPedido(int idPedido) {
        return detallePedidoDAO.buscarPorPedido(detalles, idPedido);
    }

    /**
     * Calcula el total de un pedido sumando los subtotales de sus detalles.
     *
     * @param idPedido identificador del pedido
     * @return el total del pedido; 0 si no tiene detalles
     * @see DetallePedidoDAO#calcularTotalPedido(List, int)
     */
    public double calcularTotalPedido(int idPedido) {
        return detallePedidoDAO.calcularTotalPedido(detalles, idPedido);
    }

    /**
     * Actualiza el precio unitario y la cantidad de un detalle.
     * <p>
     * Se conserva el {@code idPedido} original, porque un detalle no puede
     * cambiar de pedido.
     * </p>
     *
     * @param datos detalle con el id a actualizar y los nuevos datos
     * @return {@code true} si se actualizó; {@code false} si no existe un
     *         detalle con ese id
     */
    public boolean actualizarDetalle(DetallePedido datos) {
        DetallePedido actual = detallePedidoDAO.buscarPorId(detalles, datos.getIdDetalle());
        if (actual == null) {
            return false;
        }
        datos.setIdPedido(actual.getIdPedido());
        return detallePedidoDAO.actualizar(detalles, datos) != null;
    }

    /**
     * Elimina un detalle de pedido.
     *
     * @param id identificador del detalle a eliminar
     * @return {@code true} si se eliminó; {@code false} si no existe
     */
    public boolean eliminarDetalle(int id) {
        return detallePedidoDAO.eliminar(detalles, id);
    }

    /**
     * Elimina en cascada todos los detalles de un pedido.
     * <p>
     * Debe llamarse al eliminar un pedido, para mantener la composición y no
     * dejar detalles huérfanos.
     * </p>
     *
     * @param idPedido identificador del pedido cuyos detalles se eliminan
     * @return {@code true} si se eliminó al menos un detalle
     */
    public boolean eliminarDetallesDePedido(int idPedido) {
        return detallePedidoDAO.eliminarPorPedido(detalles, idPedido);
    }
}
