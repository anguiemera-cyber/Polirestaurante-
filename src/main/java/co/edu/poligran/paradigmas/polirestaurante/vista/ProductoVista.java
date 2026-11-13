package co.edu.poligran.paradigmas.polirestaurante.vista;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import java.util.List;

/** Coordina la presentación de productos sin depender de Swing. */
public class ProductoVista {
    private final ErikManager manager;

    public ProductoVista(ErikManager manager) {
        if (manager == null) throw new IllegalArgumentException("ErikManager es obligatorio.");
        this.manager = manager;
    }

    public List<Producto> listar() { return manager.listarProductos(); }
    public Producto buscar(int id) { return manager.buscarProductoPorId(id); }

    public Producto agregar(String nombre, String tipo, String descripcion,
                            String precioTexto, boolean disponible) {
        double precio = validar(nombre, tipo, precioTexto);
        Producto producto = new Producto(nombre.trim(), tipo.trim(), descripcion.trim(), precio);
        producto.setDisponible(disponible);
        return manager.crearProducto(producto);
    }

    public boolean actualizar(int id, String nombre, String tipo, String descripcion,
                              String precioTexto, boolean disponible) {
        if (id <= 0) throw new IllegalArgumentException("Selecciona un producto válido.");
        double precio = validar(nombre, tipo, precioTexto);
        Producto producto = new Producto(id, nombre.trim(), tipo.trim(),
                descripcion.trim(), precio, disponible);
        return manager.actualizarProducto(producto);
    }

    public boolean eliminar(int id) {
        if (id <= 0) throw new IllegalArgumentException("Selecciona un producto válido.");
        return manager.eliminarProducto(id);
    }

    private double validar(String nombre, String tipo, String precioTexto) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("Ingresa el nombre del producto.");
        if (tipo == null || tipo.trim().isEmpty())
            throw new IllegalArgumentException("Selecciona el tipo de producto.");
        if (precioTexto == null || precioTexto.trim().isEmpty())
            throw new IllegalArgumentException("Ingresa el precio.");
        final double precio;
        try { precio = Double.parseDouble(precioTexto.trim()); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("El precio debe ser numérico."); }
        if (!Double.isFinite(precio) || precio < 0)
            throw new IllegalArgumentException("El precio debe ser un número no negativo.");
        return precio;
    }
}
