package com.tecsup.semana_12_mendoza.controlador;

import com.tecsup.semana_12_mendoza.modelo.Estudiante;
import com.tecsup.semana_12_mendoza.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteControlador {

    @Autowired
    private EstudianteRepositorio repositorio;

    @GetMapping
    public String listar(Model model) {
        List<Estudiante> estudiantes = repositorio.listar();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("estudiante") Estudiante estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return "estudiantes/formulario";
        }
        repositorio.agregar(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Estudiante estudiante = repositorio.buscarPorId(id).orElse(null);
        if (estudiante == null) {
            return "redirect:/estudiantes";
        }
        model.addAttribute("estudiante", estudiante);
        return "estudiantes/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("estudiante") Estudiante estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return "estudiantes/formulario";
        }
        repositorio.actualizar(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repositorio.eliminar(id);
        return "redirect:/estudiantes";
    }
}
