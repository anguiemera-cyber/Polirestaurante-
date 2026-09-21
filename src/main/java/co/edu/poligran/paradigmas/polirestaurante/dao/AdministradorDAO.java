/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.dao;
import com.mycompany.polirestaurante.modelo.Administrador;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author salaG201
 */
public class AdministradorDAO implements ICrudDAO<Administrador> {

    @Override
    public Administrador crear(List<Administrador> lista, Administrador administrador) {
        validar(lista, administrador);
        if (administrador.getId() <= 0) {
            administrador.setId(siguienteId(lista));
        } else if (buscarPorId(lista, administrador.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una persona con id " + administrador.getId());
        }
        lista.add(administrador);
        return administrador;
    }

    @Override
    public Administrador actualizar(List<Administrador> lista, Administrador administrador) {
         validar(lista, administrador);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == administrador.getId()) {
                lista.set(i, administrador);
                return administrador;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Administrador> lista, int id) {
        return lista.removeIf(p -> p.getId() == id);
    }

    @Override
    public Administrador buscarPorId(List<Administrador> lista, int id) {
        for (Administrador p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Administrador> listar(List<Administrador> lista) {
       return Collections.unmodifiableList(lista);
    }

    private void validar(List<Administrador> lista, Administrador administrador) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de personas no puede ser nula");
        }
        if (administrador == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
    }

    private int siguienteId(List<Administrador> lista) {
         int max = 0;
        for (Administrador p : lista) {
            max = Math.max(max, p.getId());
        }
        return max + 1;
    }
    
}
