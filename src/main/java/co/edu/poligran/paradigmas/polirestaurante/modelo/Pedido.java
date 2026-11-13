
package co.edu.poligran.paradigmas.polirestaurante.modelo;

public class Pedido {

    private int id;
    private int cantidad;
    private Producto productopedido;
    private long costo;
    private String mesa;
    private String detalles;
    private EstadoPedido estado;

    public Pedido(int id) {
        this.id = id;
        this.estado = EstadoPedido.PENDIENTE;
    }
    
    public Pedido(Producto productopedido, int cantidad,
                  long costo, String mesa, String detalles) {

        this.productopedido = productopedido;
        this.cantidad = cantidad;
        this.costo = costo;
        this.mesa = mesa;
        this.detalles = detalles;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Pedido(int id, Producto productopedido,
                  int cantidad, long costo,
                  String mesa, String detalles,
                  EstadoPedido estado) {

        this.id = id;
        this.productopedido = productopedido;
        this.cantidad = cantidad;
        this.costo = costo;
        this.mesa = mesa;
        this.detalles = detalles;

        this.estado = estado == null
                ? EstadoPedido.PENDIENTE : estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProductoPedido() {
        return productopedido;
    }

    public void setProductoPedido(Producto producto) {
        this.productopedido = producto;
    }

    public long getCosto() {
        return costo;
    }

    public void setCosto(long costo) {
        this.costo = costo;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        if (estado == null) {
            throw new IllegalArgumentException(
                    "El estado no puede ser nulo."
            );
        }

        this.estado = estado;
    }

    public int getid() {
        return getId();
    }

    public void setid(int id) {
        setId(id);
    }

    public int getcantidad() {
        return getCantidad();
    }

    public void setcantidad(int cantidad) {
        setCantidad(cantidad);
    }

    public Producto getproductopedido() {
        return getProductoPedido();
    }

    public void setproductopedido(Producto producto) {
        setProductoPedido(producto);
    }

    public long getcosto() {
        return getCosto();
    }

    public void setcosto(long costo) {
        setCosto(costo);
    }

    public String getmesa() {
        return getMesa();
    }

    public void setmesa(String mesa) {
        setMesa(mesa);
    }

    public String getdetalles() {
        return getDetalles();
    }

    public void setdetalles(String detalles) {
        setDetalles(detalles);
    }

    @Override
    public String toString() {
        return "[" + id + "] "
                + cantidad + " "
                + productopedido
                + " - Mesa: " + mesa
                + " - Costo: " + costo
                + " - Detalles: " + detalles
                + " - Estado: " + estado;
    }
}
