package co.edu.poligran.paradigmas.polirestaurante.negocio;

import co.edu.poligran.paradigmas.polirestaurante.dao.AdministradorDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.ClienteDAO;
import co.edu.poligran.paradigmas.polirestaurante.dao.MeseroDAO;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Administrador;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Cliente;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Mesero;

import java.util.ArrayList;
import java.util.List;

/**
 * Administra las operaciones de administradores, meseros y clientes.
 *
 */
public class JuanManager {

    // Listas en memoria de cada entidad.
    private final List<Administrador> administradores;
    private final List<Mesero> meseros;
    private final List<Cliente> clientes;

    // DAO encargados de las operaciones CRUD.
    private final AdministradorDAO administradorDAO;
    private final MeseroDAO meseroDAO;
    private final ClienteDAO clienteDAO;

    // Inicializa las listas y los DAO.
    public JuanManager() {
        this.administradores = new ArrayList<>();
        this.meseros = new ArrayList<>();
        this.clientes = new ArrayList<>();

        this.administradorDAO = new AdministradorDAO();
        this.meseroDAO = new MeseroDAO();
        this.clienteDAO = new ClienteDAO();
    }

    // Métodos para administrar administradores.

    public Administrador crearAdministrador(Administrador administrador) {
        return administradorDAO.crear(administradores, administrador);
    }

    public Administrador actualizarAdministrador(Administrador administrador) {
        return administradorDAO.actualizar(administradores, administrador);
    }

    public boolean eliminarAdministrador(int id) {
        return administradorDAO.eliminar(administradores, id);
    }

    public Administrador buscarAdministradorPorId(int id) {
        return administradorDAO.buscarPorId(administradores, id);
    }
    
     public Administrador buscarAdministradorPorUsuario(String usuario) {
        return administradorDAO.buscarPorUsuario(administradores, usuario);
    }
     
    public List<Administrador> listarAdministradores() {
        return administradorDAO.listar(administradores);
    }

    // Métodos para administrar meseros.

    public Mesero crearMesero(Mesero mesero) {
        return meseroDAO.crear(meseros, mesero);
    }

    public Mesero actualizarMesero(Mesero mesero) {
        return meseroDAO.actualizar(meseros, mesero);
    }

    public boolean eliminarMesero(int id) {
        return meseroDAO.eliminar(meseros, id);
    }

    public Mesero buscarMeseroPorId(int id) {
        return meseroDAO.buscarPorId(meseros, id);
    }
    
    public Mesero buscarMeseroPorUsuario(String usuario) {
        return meseroDAO.buscarPorUsuario(meseros, usuario);
    }

    public List<Mesero> listarMeseros() {
        return meseroDAO.listar(meseros);
    }

    // Métodos para administrar clientes.

    public Cliente crearCliente(Cliente cliente) {
        return clienteDAO.crear(clientes, cliente);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteDAO.actualizar(clientes, cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteDAO.eliminar(clientes, id);
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDAO.buscarPorId(clientes, id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar(clientes);
    }
}
