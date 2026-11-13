package co.edu.poligran.paradigmas.polirestaurante.modelo;

public class DetallePedido {

    private int idDetalle;
    private int idPedido;
    private int idProducto;
    private double precioUnitario;
    private int cantidad;

    public DetallePedido() {
    }

    // Constructor compatible con registros antiguos.
    public DetallePedido(int idPedido,
                         double precioUnitario,
                         int cantidad) {

        this.idPedido = idPedido;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    // Constructor nuevo con identificación del producto.
    public DetallePedido(int idPedido, int idProducto,
                         double precioUnitario, int cantidad) {

        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    // Constructor completo para actualizaciones.
    public DetallePedido(int idDetalle, int idPedido,
                         int idProducto, double precioUnitario,
                         int cantidad) {

        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "idDetalle=" + idDetalle +
                ", idPedido=" + idPedido +
                ", idProducto=" + idProducto +
                ", precioUnitario=" + precioUnitario +
                ", cantidad=" + cantidad +
                ", subtotal=" + getSubtotal() +
                '}';
    }
}

