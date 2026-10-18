/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.negocio;

import co.edu.poligran.paradigmas.polirestaurante.dao.CategoriaDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.IngredienteDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.MesaDAO;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Categoria;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * Capa de negocio del restaurante.
 * <p>
 * Coordina los DAO de {@link Mesa}, {@link Ingrediente} y {@link Categoria},
 * y mantiene las listas materializadas en memoria:
 * </p>
 * <ul>
 *   <li><b>mesas</b>: lista en memoria de las mesas del restaurante.</li>
 *   <li><b>ingredientes</b>: lista en memoria de los ingredientes.</li>
 *   <li><b>categorias</b>: lista en memoria de las categorías.</li>
 * </ul>
 * <p>
 * A diferencia de Teléfono y Correo en la agenda, estas tres entidades son
 * independientes entre sí, por lo que cada lista se administra por separado.
 * </p>
 *
 * @version 1.0
 * @see MesaDAO
 * @see IngredienteDAO
 * @see CategoriaDAO
 * @author anyim
 */
public class Angiemanager {
    
    /** Lista materializada de mesas. */
    private final List<Mesa> mesas;
 
    /** Lista materializada de ingredientes. */
    private final List<Ingrediente> ingredientes;
 
    /** Lista materializada de categorías. */
    private final List<Categoria> categorias;
 
    /** DAO para las operaciones sobre mesas. */
    private final MesaDAO mesaDAO;
 
    /** DAO para las operaciones sobre ingredientes. */
    private final IngredienteDAO ingredienteDAO;
 
    /** DAO para las operaciones sobre categorías. */
    private final CategoriaDAO categoriaDAO;
 
    /**
     * Crea un manager con las listas de mesas, ingredientes y categorías vacías.
     */
    public AngieManager() {
        this.mesas = new ArrayList<>();
        this.ingredientes = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.mesaDAO = new MesaDAO();
        this.ingredienteDAO = new IngredienteDAO();
        this.categoriaDAO = new CategoriaDAO();
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
}
