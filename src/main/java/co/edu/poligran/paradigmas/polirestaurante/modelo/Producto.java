package co.edu.poligran.paradigmas.polirestaurante.modelo;

public class Producto {

    private int id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private double precio;
    private boolean disponible;

    public Producto() {
        this.disponible = true;
    }
    
    public Producto(String nombre, String tipo) {
        this();
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Producto(String nombre, String tipo,
                    String descripcion, double precio) {
        this(nombre, tipo);
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto(int id) {
        this();
        this.id = id;
    }

    public Producto(int id, String nombre, String tipo,
                    String descripcion, double precio,
                    boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getid() {
        return getId();
    }

    public void setid(int id) {
        setId(id);
    }

    public String getnombre() {
        return getNombre();
    }

    public void setnombre(String nombre) {
        setNombre(nombre);
    }

    public String gettipo() {
        return getTipo();
    }

    public void settipo(String tipo) {
        setTipo(tipo);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre
                + " (" + tipo + ") - $"
                + precio;
    }
}
