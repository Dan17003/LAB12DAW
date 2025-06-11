package com.tecsup.semana_12_mendoza.repositorio;

import com.tecsup.semana_12_mendoza.modelo.Estudiante;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EstudianteRepositorio {
    private List<Estudiante> estudiantes = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    public List<Estudiante> listar() {
        return estudiantes;
    }

    public void agregar(Estudiante estudiante) {
        estudiante.setId(idCounter.incrementAndGet());
        estudiantes.add(estudiante);
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return estudiantes.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void actualizar(Estudiante estudiante) {
        buscarPorId(estudiante.getId()).ifPresent(e -> {
            e.setNombre(estudiante.getNombre());
            e.setApellido(estudiante.getApellido());
            e.setCorreo(estudiante.getCorreo());
            e.setCarrera(estudiante.getCarrera());
        });
    }

    public void eliminar(Long id) {
        estudiantes.removeIf(e -> e.getId().equals(id));
    }
}
