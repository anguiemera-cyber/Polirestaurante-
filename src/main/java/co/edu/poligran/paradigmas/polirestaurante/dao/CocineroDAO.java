package co.edu.poligran.paradigmas.polirestaurante.dao;

import co.edu.poligran.paradigmas.polirestaurante.modelo.Cocinero;
import java.util.Collections;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad {@link Cocinero}.
 * Implementa las operaciones CRUD sobre listas en memoria.
 */
public class CocineroDAO implements ICrudDAO<Cocinero> {

    @Override
    public Cocinero crear(List<Cocinero> lista, Cocinero cocinero) {
        validar(lista, cocinero);
        
        // Se valida el ID usando la Persona que compone al Cocinero
        if (cocinero.getCocinero().getId() <= 0) {
            cocinero.getCocinero().setId(siguienteId(lista));
        } else if (buscarPorId(lista, cocinero.getCocinero().getId()) != null) {
            throw new IllegalArgumentException("Ya existe un cocinero con id " + cocinero.getCocinero().getId());
        }
        
        lista.add(cocinero);
        return cocinero;
    }

    @Override
    public Cocinero actualizar(List<Cocinero> lista, Cocinero cocinero) {
        validar(lista, cocinero);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCocinero().getId() == cocinero.getCocinero().getId()) {
                lista.set(i, cocinero);
                return cocinero;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Cocinero> lista, int id) {
        if (lista == null) return false;
        return lista.removeIf(c -> c.getCocinero() != null && c.getCocinero().getId() == id);
    }

    @Override
    public Cocinero buscarPorId(List<Cocinero> lista, int id) {
        if (lista == null) return null;
        for (Cocinero c : lista) {
            if (c.getCocinero() != null && c.getCocinero().getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca un cocinero por su nombre de usuario en el sistema.
     */
    public Cocinero buscarPorUsuario(List<Cocinero> lista, String usuario) {
        if (lista == null || usuario == null) return null;
        for (Cocinero c : lista) {
            if (c.getUsuario() != null && c.getUsuario().trim().equalsIgnoreCase(usuario.trim())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Cocinero> listar(List<Cocinero> lista) {
        if (lista == null) return Collections.emptyList();
        return Collections.unmodifiableList(lista);
    }

    private int siguienteId(List<Cocinero> lista) {
        int max = 0;
        for (Cocinero c : lista) {
            if (c.getCocinero() != null) {
                max = Math.max(max, c.getCocinero().getId());
            }
        }
        return max + 1;
    }

    private void validar(List<Cocinero> lista, Cocinero cocinero) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de cocineros no puede ser nula");
        }
        if (cocinero == null) {
            throw new IllegalArgumentException("El cocinero no puede ser nulo");
        }
        if (cocinero.getCocinero() == null) {
            throw new IllegalArgumentException("La persona asociada al cocinero no puede ser nula");
        }
    }
}
