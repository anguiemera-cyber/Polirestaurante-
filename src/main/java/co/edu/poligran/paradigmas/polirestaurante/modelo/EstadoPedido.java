
package co.edu.poligran.paradigmas.polirestaurante.modelo;

public class EstadoPedido {
    private int idEstado;
    Pedido pedido;
    private String estado;

    public EstadoPedido(Pedido pedido, String estado) {
        this.pedido = pedido;
        this.estado = estado;
    }

    public EstadoPedido(int idEstado, Pedido pedido, String estado) {
        this.idEstado = idEstado;
        this.pedido = pedido;
        this.estado = estado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "[" + idEstado + "] Pedido: " + pedido
                + " - Estado: " + estado;
    }
}
