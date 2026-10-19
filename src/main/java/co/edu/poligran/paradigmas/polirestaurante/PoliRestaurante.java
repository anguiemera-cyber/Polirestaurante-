package co.edu.poligran.paradigmas.polirestaurante;


import co.edu.poligran.paradigmas.polirestaurante.modelo.Administrador;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Categoria;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Cliente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Cocinero;
import co.edu.poligran.paradigmas.polirestaurante.modelo.DetallePedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.EstadoPedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Ingrediente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesa;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesero;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Persona;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Producto;
import co.edu.poligran.paradigmas.polirestaurante.negocio.AngieManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.ErikManager;
import co.edu.poligran.paradigmas.polirestaurante.negocio.JuanManager;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada del PoliRestaurante. Menú de consola que gestiona:
 * <ul>
 *   <li>{@link AngieManager}: mesas, ingredientes, categorías y detalles de pedido.</li>
 *   <li>{@link ErikManager}: pedidos, productos y cocineros.</li>
 *   <li>{@link JuanManager}: administradores, meseros y clientes.</li>
 * </ul>
 */
public class PoliRestaurante {

    private static final AngieManager catalogo = new AngieManager();
    private static final ErikManager operaciones = new ErikManager();
    private static final JuanManager personal = new JuanManager();
    private static final Scanner sc = new Scanner(System.in);

    private static final String NL = System.lineSeparator();
    private static final String LINEA = "------------------------------------------";
    private static final String SELECCIONE = "Seleccione una opción: ";
    private static final String INVALIDA = "Opción no válida. Intente de nuevo.";
    private static final String NO_ENCONTRADO = "No se encontró el registro.";
    private static final String SIN_REGISTROS = "No hay registros.";
    private static final String CANCELADA = "Operación cancelada.";

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      BIENVENIDO A POLIRESTAURANTE");
        System.out.println("==========================================");
        int opcion;
        do {
            mostrarMenu("MENÚ PRINCIPAL",
                    "Mesas, ingredientes, categorías y detalles ",
                    "Pedidos, productos y cocineros ",
                    "Administradores, meseros y clientes ");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: menuCatalogo(); break;
                case 2: menuOperaciones(); break;
                case 3: menuPersonal(); break;
                case 0: System.out.println("¡Hasta pronto!"); break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    // =====================================================================
    // Menús de módulos
    // =====================================================================

    private static void menuCatalogo() {
        int opcion;
        do {
            mostrarMenu("GESTIÓN DE CATÁLOGO", "Gestionar mesas", "Gestionar ingredientes",
                    "Gestionar categorías", "Gestionar detalles de pedido");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: menuMesas(); break;
                case 2: menuIngredientes(); break;
                case 3: menuCategorias(); break;
                case 4: menuDetalles(); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuOperaciones() {
        int opcion;
        do {
            mostrarMenu("GESTIÓN DE PEDIDOS", "Gestionar pedidos", "Gestionar productos",
                    "Gestionar cocineros");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: menuPedidos(); break;
                case 2: menuProductos(); break;
                case 3: menuCocineros(); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuPersonal() {
        int opcion;
        do {
            mostrarMenu("GESTIÓN DE PERSONAL", "Gestionar administradores", "Gestionar meseros",
                    "Gestionar clientes");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: menuAdministradores(); break;
                case 2: menuMeseros(); break;
                case 3: menuClientes(); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    // =====================================================================
    // Submenús de catálogo
    // =====================================================================

    private static void menuMesas() {
        int opcion;
        do {
            mostrarMenu("MESAS", "Crear mesa", "Listar mesas", "Buscar mesa por id",
                    "Buscar mesas por estado", "Actualizar mesa", "Eliminar mesa");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearMesa); break;
                case 2: ejecutar(PoliRestaurante::listarMesas); break;
                case 3: ejecutar(PoliRestaurante::buscarMesa); break;
                case 4: ejecutar(PoliRestaurante::buscarMesasPorEstado); break;
                case 5: ejecutar(PoliRestaurante::actualizarMesa); break;
                case 6: ejecutar(PoliRestaurante::eliminarMesa); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuIngredientes() {
        int opcion;
        do {
            mostrarMenu("INGREDIENTES", "Crear ingrediente", "Listar ingredientes",
                    "Listar ingredientes disponibles", "Buscar ingrediente por nombre",
                    "Actualizar ingrediente", "Eliminar ingrediente");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearIngrediente); break;
                case 2: ejecutar(PoliRestaurante::listarIngredientes); break;
                case 3: ejecutar(PoliRestaurante::listarIngredientesDisponibles); break;
                case 4: ejecutar(PoliRestaurante::buscarIngrediente); break;
                case 5: ejecutar(PoliRestaurante::actualizarIngrediente); break;
                case 6: ejecutar(PoliRestaurante::eliminarIngrediente); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuCategorias() {
        int opcion;
        do {
            mostrarMenu("CATEGORÍAS", "Crear categoría", "Listar categorías",
                    "Buscar categoría por nombre", "Actualizar categoría",
                    "Eliminar categoría");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearCategoria); break;
                case 2: ejecutar(PoliRestaurante::listarCategorias); break;
                case 3: ejecutar(PoliRestaurante::buscarCategoria); break;
                case 4: ejecutar(PoliRestaurante::actualizarCategoria); break;
                case 5: ejecutar(PoliRestaurante::eliminarCategoria); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuDetalles() {
        int opcion;
        do {
            mostrarMenu("DETALLES DE PEDIDO", "Crear detalle", "Listar detalles",
                    "Buscar detalles de un pedido", "Calcular total de un pedido",
                    "Actualizar detalle", "Eliminar detalle");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearDetalle); break;
                case 2: ejecutar(PoliRestaurante::listarDetalles); break;
                case 3: ejecutar(PoliRestaurante::buscarDetallesPorPedido); break;
                case 4: ejecutar(PoliRestaurante::calcularTotalPedido); break;
                case 5: ejecutar(PoliRestaurante::actualizarDetalle); break;
                case 6: ejecutar(PoliRestaurante::eliminarDetalle); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    // =====================================================================
    // Submenús de pedidos
    // =====================================================================

    private static void menuPedidos() {
        int opcion;
        do {
            mostrarMenu("PEDIDOS", "Crear pedido", "Listar pedidos", "Buscar pedido por id",
                    "Buscar pedidos por mesa", "Actualizar pedido",
                    "Cambiar estado de un pedido", "Eliminar pedido");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearPedido); break;
                case 2: ejecutar(PoliRestaurante::listarPedidos); break;
                case 3: ejecutar(PoliRestaurante::buscarPedido); break;
                case 4: ejecutar(PoliRestaurante::buscarPedidosPorMesa); break;
                case 5: ejecutar(PoliRestaurante::actualizarPedido); break;
                case 6: ejecutar(PoliRestaurante::cambiarEstadoPedido); break;
                case 7: ejecutar(PoliRestaurante::eliminarPedido); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuProductos() {
        int opcion;
        do {
            mostrarMenu("PRODUCTOS", "Crear producto", "Listar productos",
                    "Buscar producto por nombre", "Actualizar producto",
                    "Eliminar producto");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearProducto); break;
                case 2: ejecutar(PoliRestaurante::listarProductos); break;
                case 3: ejecutar(PoliRestaurante::buscarProducto); break;
                case 4: ejecutar(PoliRestaurante::actualizarProducto); break;
                case 5: ejecutar(PoliRestaurante::eliminarProducto); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuCocineros() {
        int opcion;
        do {
            mostrarMenu("COCINEROS", "Crear cocinero", "Listar cocineros",
                    "Buscar cocinero por id", "Buscar cocinero por usuario",
                    "Actualizar cocinero", "Eliminar cocinero");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearCocinero); break;
                case 2: ejecutar(PoliRestaurante::listarCocineros); break;
                case 3: ejecutar(PoliRestaurante::buscarCocineroPorId); break;
                case 4: ejecutar(PoliRestaurante::buscarCocineroPorUsuario); break;
                case 5: ejecutar(PoliRestaurante::actualizarCocinero); break;
                case 6: ejecutar(PoliRestaurante::eliminarCocinero); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    // =====================================================================
    // Submenús de personal
    // =====================================================================

    private static void menuAdministradores() {
        int opcion;
        do {
            mostrarMenu("ADMINISTRADORES", "Crear administrador", "Listar administradores",
                    "Buscar administrador por id", "Actualizar administrador",
                    "Eliminar administrador");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearAdministrador); break;
                case 2: ejecutar(PoliRestaurante::listarAdministradores); break;
                case 3: ejecutar(PoliRestaurante::buscarAdministrador); break;
                case 4: ejecutar(PoliRestaurante::actualizarAdministrador); break;
                case 5: ejecutar(PoliRestaurante::eliminarAdministrador); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuMeseros() {
        int opcion;
        do {
            mostrarMenu("MESEROS", "Crear mesero", "Listar meseros", "Buscar mesero por id",
                    "Actualizar mesero", "Eliminar mesero");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearMesero); break;
                case 2: ejecutar(PoliRestaurante::listarMeseros); break;
                case 3: ejecutar(PoliRestaurante::buscarMesero); break;
                case 4: ejecutar(PoliRestaurante::actualizarMesero); break;
                case 5: ejecutar(PoliRestaurante::eliminarMesero); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    private static void menuClientes() {
        int opcion;
        do {
            mostrarMenu("CLIENTES", "Crear cliente", "Listar clientes", "Buscar cliente por id",
                    "Actualizar cliente", "Eliminar cliente");
            opcion = leerEntero(SELECCIONE);
            switch (opcion) {
                case 1: ejecutar(PoliRestaurante::crearCliente); break;
                case 2: ejecutar(PoliRestaurante::listarClientes); break;
                case 3: ejecutar(PoliRestaurante::buscarCliente); break;
                case 4: ejecutar(PoliRestaurante::actualizarCliente); break;
                case 5: ejecutar(PoliRestaurante::eliminarCliente); break;
                case 0: break;
                default: System.out.println(INVALIDA);
            }
        } while (opcion != 0);
    }

    // =====================================================================
    // Mesas
    // =====================================================================

    private static void crearMesa() {
        int cantidad = leerEnteroNoNegativo("Capacidad (personas): ");
        int estado = leerEntero("Estado (número): ");
        Mesa m = catalogo.crearMesa(new Mesa(cantidad, estado));
        System.out.println("Mesa creada con id: " + m.getId());
    }

    private static void listarMesas() {
        imprimirLista(catalogo.listarMesas());
    }

    private static void buscarMesa() {
        Mesa m = catalogo.buscarMesaPorId(leerEntero("Id de la mesa: "));
        System.out.println(m == null ? NO_ENCONTRADO : m.toString());
    }

    private static void buscarMesasPorEstado() {
        imprimirLista(catalogo.buscarMesasPorEstado(leerEntero("Estado a buscar: ")));
    }

    private static void actualizarMesa() {
        Mesa actual = catalogo.buscarMesaPorId(leerEntero("Id de la mesa: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(actual);
        int cantidad = leerEnteroOpcional("Capacidad", actual.getCantidad());
        int estado = leerEnteroOpcional("Estado", actual.getEstado());
        System.out.println(catalogo.actualizarMesa(new Mesa(actual.getId(), cantidad, estado))
                ? "Mesa actualizada correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarMesa() {
        int id = leerEntero("Id de la mesa: ");
        if (!confirmar("¿Seguro que desea eliminar la mesa " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(catalogo.eliminarMesa(id) ? "Mesa eliminada." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Ingredientes
    // =====================================================================

    private static void crearIngrediente() {
        String nombre = leerTexto("Nombre: ");
        int estado = leerEntero("Estado (número): ");
        int cantidad = leerEnteroNoNegativo("Cantidad: ");
        boolean disponible = confirmar("¿Está disponible?");
        Ingrediente i = catalogo.crearIngrediente(new Ingrediente(nombre, estado, cantidad, disponible));
        System.out.println("Ingrediente creado con id: " + i.getId());
    }

    private static void listarIngredientes() {
        imprimirLista(catalogo.listarIngredientes());
    }

    private static void listarIngredientesDisponibles() {
        imprimirLista(catalogo.listarIngredientesDisponibles());
    }

    private static void buscarIngrediente() {
        Ingrediente i = catalogo.buscarIngrediente(leerTexto("Nombre a buscar: "));
        System.out.println(i == null ? NO_ENCONTRADO : i.toString());
    }

    private static void actualizarIngrediente() {
        Ingrediente actual = catalogo.buscarIngredientePorId(leerEntero("Id del ingrediente: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(actual);
        String nombre = leerTextoOpcional("Nombre", actual.getNombre());
        int estado = leerEnteroOpcional("Estado", actual.getEstado());
        int cantidad = leerEnteroOpcional("Cantidad", actual.getCantidad());
        boolean disponible = leerBooleanOpcional("¿Disponible?", actual.isDisponibilidad());
        System.out.println(catalogo.actualizarIngrediente(
                new Ingrediente(actual.getId(), nombre, estado, cantidad, disponible))
                ? "Ingrediente actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarIngrediente() {
        int id = leerEntero("Id del ingrediente: ");
        if (!confirmar("¿Seguro que desea eliminar el ingrediente " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(catalogo.eliminarIngrediente(id) ? "Ingrediente eliminado." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Categorías
    // =====================================================================

    private static void crearCategoria() {
        String nombre = leerTexto("Nombre: ");
        String descripcion = leerTexto("Descripción: ");
        Categoria c = catalogo.crearCategoria(new Categoria(nombre, descripcion));
        System.out.println("Categoría creada con id: " + c.getId());
    }

    private static void listarCategorias() {
        imprimirLista(catalogo.listarCategorias());
    }

    private static void buscarCategoria() {
        Categoria c = catalogo.buscarCategoria(leerTexto("Nombre a buscar: "));
        System.out.println(c == null ? NO_ENCONTRADO : c.toString());
    }

    private static void actualizarCategoria() {
        Categoria actual = catalogo.buscarCategoriaPorId(leerEntero("Id de la categoría: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(actual);
        String nombre = leerTextoOpcional("Nombre", actual.getNombre());
        String descripcion = leerTextoOpcional("Descripción", actual.getDescripcion());
        System.out.println(catalogo.actualizarCategoria(new Categoria(actual.getId(), nombre, descripcion))
                ? "Categoría actualizada correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarCategoria() {
        int id = leerEntero("Id de la categoría: ");
        if (!confirmar("¿Seguro que desea eliminar la categoría " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(catalogo.eliminarCategoria(id) ? "Categoría eliminada." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Detalles de pedido
    // =====================================================================

    private static void crearDetalle() {
        int idPedido = leerEntero("Id del pedido: ");
        if (operaciones.buscarPedidoPorId(idPedido) == null) {
            System.out.println("No existe un pedido con ese id; no se puede crear el detalle.");
            return;
        }
        double precio = leerDoubleNoNegativo("Precio unitario: ");
        int cantidad = leerEnteroPositivo("Cantidad: ");
        DetallePedido d = catalogo.crearDetalle(new DetallePedido(idPedido, precio, cantidad));
        System.out.println("Detalle creado con id: " + d.getIdDetalle());
    }

    private static void listarDetalles() {
        imprimirLista(catalogo.listarDetalles());
    }

    private static void buscarDetallesPorPedido() {
        imprimirLista(catalogo.buscarDetallesPorPedido(leerEntero("Id del pedido: ")));
    }

    private static void calcularTotalPedido() {
        int idPedido = leerEntero("Id del pedido: ");
        System.out.println("Total del pedido " + idPedido + ": " + catalogo.calcularTotalPedido(idPedido));
    }

    private static void actualizarDetalle() {
        DetallePedido actual = catalogo.buscarDetallePorId(leerEntero("Id del detalle: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(actual);
        double precio = leerDoubleOpcional("Precio unitario", actual.getPrecioUnitario());
        int cantidad = leerEnteroOpcional("Cantidad", actual.getCantidad());
        DetallePedido datos = new DetallePedido(actual.getIdDetalle(), actual.getIdPedido(), precio, cantidad);
        System.out.println(catalogo.actualizarDetalle(datos)
                ? "Detalle actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarDetalle() {
        int id = leerEntero("Id del detalle: ");
        if (!confirmar("¿Seguro que desea eliminar el detalle " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(catalogo.eliminarDetalle(id) ? "Detalle eliminado." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Pedidos
    // =====================================================================

    private static void crearPedido() {
        Producto producto = seleccionarProducto();
        if (producto == null) {
            return;
        }
        int cantidad = leerEnteroPositivo("Cantidad: ");
        long costo = leerLongNoNegativo("Costo: ");
        String mesa = leerTexto("Mesa: ");
        String detalles = leerTexto("Detalles: ");
        Pedido p = operaciones.crearPedido(new Pedido(producto, cantidad, costo, mesa, detalles));
        System.out.println("Pedido creado con id: " + p.getid());
    }

    private static void listarPedidos() {
        List<Pedido> lista = operaciones.listarPedidos();
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Pedido p : lista) {
            System.out.println(describir(p));
        }
    }

    private static void buscarPedido() {
        Pedido p = operaciones.buscarPedidoPorId(leerEntero("Id del pedido: "));
        System.out.println(p == null ? NO_ENCONTRADO : describir(p));
    }

    private static void buscarPedidosPorMesa() {
        List<Pedido> lista = operaciones.buscarPedidosPorMesa(leerTexto("Mesa a buscar: "));
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Pedido p : lista) {
            System.out.println(describir(p));
        }
    }

    private static void actualizarPedido() {
        Pedido actual = operaciones.buscarPedidoPorId(leerEntero("Id del pedido: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(describir(actual));
        Pedido datos = new Pedido(actual.getid());
        datos.setproductopedido(actual.getproductopedido());
        datos.setcantidad(leerEnteroOpcional("Cantidad", actual.getcantidad()));
        datos.setcosto(leerLongOpcional("Costo", actual.getcosto()));
        datos.setmesa(leerTextoOpcional("Mesa", actual.getmesa()));
        datos.setdetalles(leerTextoOpcional("Detalles", actual.getdetalles()));
        datos.setEstado(actual.getEstado());
        System.out.println(operaciones.actualizarPedido(datos)
                ? "Pedido actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void cambiarEstadoPedido() {
        Pedido actual = operaciones.buscarPedidoPorId(leerEntero("Id del pedido: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(describir(actual));
        EstadoPedido[] estados = EstadoPedido.values();
        for (int i = 0; i < estados.length; i++) {
            System.out.println(" " + (i + 1) + ". " + estados[i]);
        }
        int n;
        do {
            n = leerEntero("Nuevo estado: ");
        } while (n < 1 || n > estados.length);
        System.out.println(operaciones.cambiarEstadoPedido(actual.getid(), estados[n - 1])
                ? "Estado actualizado." : NO_ENCONTRADO);
    }

    private static void eliminarPedido() {
        int id = leerEntero("Id del pedido: ");
        if (!confirmar("¿Seguro que desea eliminar el pedido " + id + " y sus detalles?")) {
            System.out.println(CANCELADA);
            return;
        }
        if (operaciones.eliminarPedido(id)) {
            // Composición: al eliminar el pedido se eliminan sus detalles.
            catalogo.eliminarDetallesDePedido(id);
            System.out.println("Pedido eliminado junto con sus detalles.");
        } else {
            System.out.println(NO_ENCONTRADO);
        }
    }

    // =====================================================================
    // Productos
    // =====================================================================

    private static void crearProducto() {
        String nombre = leerTexto("Nombre: ");
        String tipo = leerTexto("Tipo (ej. Bebida, Plato fuerte): ");
        Producto p = operaciones.crearProducto(new Producto(nombre, tipo));
        System.out.println("Producto creado con id: " + p.getid());
    }

    private static void listarProductos() {
        List<Producto> lista = operaciones.listarProductos();
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Producto p : lista) {
            System.out.println(describir(p));
        }
    }

    private static void buscarProducto() {
        Producto p = operaciones.buscarProductoPorNombre(leerTexto("Nombre a buscar: "));
        System.out.println(p == null ? NO_ENCONTRADO : describir(p));
    }

    private static void actualizarProducto() {
        Producto actual = operaciones.buscarProductoPorId(leerEntero("Id del producto: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(describir(actual));
        Producto datos = new Producto(
                leerTextoOpcional("Nombre", actual.getnombre()),
                leerTextoOpcional("Tipo", actual.gettipo()));
        datos.setid(actual.getid());
        System.out.println(operaciones.actualizarProducto(datos)
                ? "Producto actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarProducto() {
        int id = leerEntero("Id del producto: ");
        if (!confirmar("¿Seguro que desea eliminar el producto " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(operaciones.eliminarProducto(id) ? "Producto eliminado." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Cocineros
    // =====================================================================

    private static void crearCocinero() {
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        int tipoDoc = leerEntero("Tipo de documento (número): ");
        long numeroDoc = leerLongNoNegativo("Número de documento: ");
        long celular = leerLongNoNegativo("Celular: ");
        String correo = leerTexto("Correo: ");
        String contrasena = leerTexto("Contraseña: ");
        String usuario = leerTexto("Usuario: ");
        Cocinero c = operaciones.crearCocinero(new Cocinero(new Persona(0, nombre), apellido,
                tipoDoc, numeroDoc, celular, correo, contrasena, usuario));
        System.out.println("Cocinero creado con id: " + idCocinero(c));
    }

    private static void listarCocineros() {
        List<Cocinero> lista = operaciones.listarCocineros();
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Cocinero c : lista) {
            System.out.println(describir(c));
        }
    }

    private static void buscarCocineroPorId() {
        Cocinero c = operaciones.buscarCocineroPorId(leerEntero("Id del cocinero: "));
        System.out.println(c == null ? NO_ENCONTRADO : describir(c));
    }

    private static void buscarCocineroPorUsuario() {
        Cocinero c = operaciones.buscarCocineroPorUsuario(leerTexto("Usuario a buscar: "));
        System.out.println(c == null ? NO_ENCONTRADO : describir(c));
    }

    private static void actualizarCocinero() {
        Cocinero actual = operaciones.buscarCocineroPorId(leerEntero("Id del cocinero: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(describir(actual));
        String nombreActual = actual.getCocinero() == null ? "" : actual.getCocinero().getNombre();
        String nombre = leerTextoOpcional("Nombre", nombreActual);
        String apellido = leerTextoOpcional("Apellido", actual.getApellido());
        int tipoDoc = leerEnteroOpcional("Tipo de documento", actual.getTipoDoc());
        long numeroDoc = leerLongOpcional("Número de documento", actual.getNumeroDoc());
        long celular = leerLongOpcional("Celular", actual.getCelular());
        String correo = leerTextoOpcional("Correo", actual.getCorreo());
        String contrasena = leerTextoOpcional("Contraseña", actual.getContraseña());
        String usuario = leerTextoOpcional("Usuario", actual.getUsuario());
        Cocinero datos = new Cocinero(new Persona(idCocinero(actual), nombre), apellido,
                tipoDoc, numeroDoc, celular, correo, contrasena, usuario);
        System.out.println(operaciones.actualizarCocinero(datos)
                ? "Cocinero actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarCocinero() {
        int id = leerEntero("Id del cocinero: ");
        if (!confirmar("¿Seguro que desea eliminar el cocinero " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(operaciones.eliminarCocinero(id) ? "Cocinero eliminado." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Administradores
    // =====================================================================

    private static void crearAdministrador() {
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        int tipoDoc = leerEntero("Tipo de documento (número): ");
        long numeroDoc = leerLongNoNegativo("Número de documento: ");
        long celular = leerLongNoNegativo("Celular: ");
        String correo = leerTexto("Correo: ");
        String contrasena = leerTexto("Contraseña: ");
        String usuario = leerTexto("Usuario: ");
        Administrador a = personal.crearAdministrador(new Administrador(0, nombre, apellido,
                tipoDoc, numeroDoc, celular, correo, contrasena, usuario));
        System.out.println("Administrador creado con id: " + a.getId());
    }

    private static void listarAdministradores() {
        List<Administrador> lista = personal.listarAdministradores();
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Administrador a : lista) {
            System.out.println(describir(a));
        }
    }

    private static void buscarAdministrador() {
        Administrador a = personal.buscarAdministradorPorId(leerEntero("Id del administrador: "));
        System.out.println(a == null ? NO_ENCONTRADO : describir(a));
    }

    private static void actualizarAdministrador() {
        Administrador actual = personal.buscarAdministradorPorId(leerEntero("Id del administrador: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(describir(actual));
        Administrador datos = new Administrador(actual.getId(),
                leerTextoOpcional("Nombre", actual.getNombre()),
                leerTextoOpcional("Apellido", actual.getApellido()),
                leerEnteroOpcional("Tipo de documento", actual.getTipoDoc()),
                leerLongOpcional("Número de documento", actual.getNumeroDoc()),
                leerLongOpcional("Celular", actual.getCelular()),
                leerTextoOpcional("Correo", actual.getCorreo()),
                leerTextoOpcional("Contraseña", actual.getContraseña()),
                leerTextoOpcional("Usuario", actual.getUsuario()));
        System.out.println(personal.actualizarAdministrador(datos) != null
                ? "Administrador actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarAdministrador() {
        int id = leerEntero("Id del administrador: ");
        if (!confirmar("¿Seguro que desea eliminar el administrador " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(personal.eliminarAdministrador(id) ? "Administrador eliminado." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Meseros
    // =====================================================================

    private static void crearMesero() {
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        int tipoDoc = leerEntero("Tipo de documento (número): ");
        long numeroDoc = leerLongNoNegativo("Número de documento: ");
        long celular = leerLongNoNegativo("Celular: ");
        String correo = leerTexto("Correo: ");
        String contrasena = leerTexto("Contraseña: ");
        String usuario = leerTexto("Usuario: ");
        Mesero m = personal.crearMesero(new Mesero(0, nombre, apellido, tipoDoc, numeroDoc, celular,
                correo, contrasena, usuario));
        System.out.println("Mesero creado con id: " + m.getId());
    }

    private static void listarMeseros() {
        List<Mesero> lista = personal.listarMeseros();
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Mesero m : lista) {
            System.out.println(describir(m));
        }
    }

    private static void buscarMesero() {
        Mesero m = personal.buscarMeseroPorId(leerEntero("Id del mesero: "));
        System.out.println(m == null ? NO_ENCONTRADO : describir(m));
    }

    private static void actualizarMesero() {
        Mesero actual = personal.buscarMeseroPorId(leerEntero("Id del mesero: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(describir(actual));
        Mesero datos = new Mesero (actual.getId(),
                leerTextoOpcional("Nombre", actual.getNombre()),
                leerTextoOpcional("Apellido", actual.getApellido()),
                leerEnteroOpcional("Tipo de documento", actual.getTipoDoc()),
                leerLongOpcional("Número de documento", actual.getNumeroDoc()),
                leerLongOpcional("Celular", actual.getCelular()),
                leerTextoOpcional("Correo", actual.getCorreo()),
                leerTextoOpcional("Contraseña", actual.getContraseña()),
                leerTextoOpcional("Usuario", actual.getUsuario()));
        datos.setId(actual.getId());
        System.out.println(personal.actualizarMesero(datos) != null
                ? "Mesero actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarMesero() {
        int id = leerEntero("Id del mesero: ");
        if (!confirmar("¿Seguro que desea eliminar el mesero " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(personal.eliminarMesero(id) ? "Mesero eliminado." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Clientes
    // =====================================================================

    private static void crearCliente() {
        Cliente c = personal.crearCliente(new Cliente(0, leerTexto("Nombre: ")));
        System.out.println("Cliente creado con id: " + c.getId());
    }

    private static void listarClientes() {
        List<Cliente> lista = personal.listarClientes();
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Cliente c : lista) {
            System.out.println(describir(c));
        }
    }

    private static void buscarCliente() {
        Cliente c = personal.buscarClientePorId(leerEntero("Id del cliente: "));
        System.out.println(c == null ? NO_ENCONTRADO : describir(c));
    }

    private static void actualizarCliente() {
        Cliente actual = personal.buscarClientePorId(leerEntero("Id del cliente: "));
        if (actual == null) {
            System.out.println(NO_ENCONTRADO);
            return;
        }
        System.out.println(describir(actual));
        Cliente datos = new Cliente(actual.getId(), leerTextoOpcional("Nombre", actual.getNombre()));
        System.out.println(personal.actualizarCliente(datos) != null
                ? "Cliente actualizado correctamente." : NO_ENCONTRADO);
    }

    private static void eliminarCliente() {
        int id = leerEntero("Id del cliente: ");
        if (!confirmar("¿Seguro que desea eliminar el cliente " + id + "?")) {
            System.out.println(CANCELADA);
            return;
        }
        System.out.println(personal.eliminarCliente(id) ? "Cliente eliminado." : NO_ENCONTRADO);
    }

    // =====================================================================
    // Descripciones (algunas clases no tienen toString útil)
    // =====================================================================

    private static String describir(Producto p) {
        return "[" + p.getid() + "] " + p.getnombre() + " (" + p.gettipo() + ")";
    }

    private static String describir(Pedido p) {
        String producto = p.getproductopedido() == null ? "?" : p.getproductopedido().getnombre();
        return "[" + p.getid() + "] " + p.getcantidad() + " x " + producto
                + " - Mesa: " + p.getmesa()
                + " - Costo: " + p.getcosto()
                + " - Detalles: " + p.getdetalles()
                + " - Estado: " + p.getEstado();
    }

    private static String describir(Cocinero c) {
        String nombre = c.getCocinero() == null ? "?" : c.getCocinero().getNombre();
        return "[" + idCocinero(c) + "] " + nombre + " " + c.getApellido()
                + " | Usuario: " + c.getUsuario()
                + " | Documento: " + c.getNumeroDoc()
                + " | Celular: " + c.getCelular()
                + " | Correo: " + c.getCorreo();
    }

    private static String describir(Administrador a) {
        return "[" + a.getId() + "] " + a.getNombre() + " " + a.getApellido()
                + " | Usuario: " + a.getUsuario()
                + " | Documento: " + a.getNumeroDoc()
                + " | Celular: " + a.getCelular()
                + " | Correo: " + a.getCorreo();
    }

    private static String describir(Mesero m) {
        return "Mesero " + m.getApellido()
                + " | Usuario: " + m.getUsuario()
                + " | Documento: " + m.getNumeroDoc()
                + " | Celular: " + m.getCelular()
                + " | Correo: " + m.getCorreo();
    }

    private static String describir(Cliente c) {
        return "[" + c.getId() + "] " + c.getNombre();
    }

    private static int idCocinero(Cocinero c) {
        return c.getCocinero() == null ? 0 : c.getCocinero().getId();
    }

    // =====================================================================
    // Utilidades de apoyo
    // =====================================================================

    /** Muestra un menú numerado; la opción 0 siempre es "Volver / Salir". */
    private static void mostrarMenu(String titulo, String... opciones) {
        System.out.println(NL + "----- " + titulo + " -----");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println(" " + (i + 1) + ". " + opciones[i]);
        }
        System.out.println(" 0. Volver / Salir");
        System.out.println(LINEA);
    }

    /** Selecciona un producto existente (lo lista y pide el id). */
    private static Producto seleccionarProducto() {
        if (operaciones.listarProductos().isEmpty()) {
            System.out.println("No hay productos registrados. Cree uno primero.");
            return null;
        }
        listarProductos();
        Producto p = operaciones.buscarProductoPorId(leerEntero("Id del producto: "));
        if (p == null) {
            System.out.println(NO_ENCONTRADO);
        }
        return p;
    }

    /** Imprime una lista usando el toString de cada elemento. */
    private static void imprimirLista(List<?> lista) {
        if (lista.isEmpty()) {
            System.out.println(SIN_REGISTROS);
            return;
        }
        for (Object o : lista) {
            System.out.println(o);
        }
    }

    /** Ejecuta una acción mostrando los errores de los DAO en lugar de cerrar el programa. */
    private static void ejecutar(Runnable accion) {
        try {
            accion.run();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (UnsupportedOperationException e) {
            System.out.println("Operación no soportada por la clase (revise getId/setId en Mesero).");
        }
    }

    // ------------------------------------------------------------- Lectura

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo es obligatorio.");
        }
    }

    private static String leerTextoOpcional(String etiqueta, String valorActual) {
        System.out.print(etiqueta + " [Enter para mantener: " + valorActual + "]: ");
        String texto = sc.nextLine().trim();
        return texto.isEmpty() ? valorActual : texto;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static int leerEnteroNoNegativo(String mensaje) {
        while (true) {
            int n = leerEntero(mensaje);
            if (n >= 0) {
                return n;
            }
            System.out.println("El valor no puede ser negativo.");
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            int n = leerEntero(mensaje);
            if (n > 0) {
                return n;
            }
            System.out.println("El valor debe ser mayor que cero.");
        }
    }

    private static long leerLongNoNegativo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                long n = Long.parseLong(sc.nextLine().trim());
                if (n >= 0) {
                    return n;
                }
                System.out.println("El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double n = Double.parseDouble(sc.nextLine().trim());
                if (n >= 0) {
                    return n;
                }
                System.out.println("El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private static int leerEnteroOpcional(String etiqueta, int actual) {
        while (true) {
            String texto = leerTextoOpcional(etiqueta, String.valueOf(actual));
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static long leerLongOpcional(String etiqueta, long actual) {
        while (true) {
            String texto = leerTextoOpcional(etiqueta, String.valueOf(actual));
            try {
                return Long.parseLong(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static double leerDoubleOpcional(String etiqueta, double actual) {
        while (true) {
            String texto = leerTextoOpcional(etiqueta, String.valueOf(actual));
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private static boolean leerBooleanOpcional(String etiqueta, boolean actual) {
        System.out.print(etiqueta + " (s/n) [Enter para mantener: " + (actual ? "s" : "n") + "]: ");
        String texto = sc.nextLine().trim();
        return texto.isEmpty() ? actual : texto.equalsIgnoreCase("s");
    }

    private static boolean confirmar(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        return sc.nextLine().trim().equalsIgnoreCase("s");
    }
}
