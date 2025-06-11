package com.tecsup.semana_12_mendoza.controlador;

import com.tecsup.semana_12_mendoza.modelo.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class PersonaControlador {

    @GetMapping("/personas")
    public String mostrarPersonas(Model model) {
        List<Persona> lista = List.of(
            new Persona("Ana", 28),
            new Persona("Luis", 35),
            new Persona("Maria", 22)
        );
        model.addAttribute("personas", lista);
        return "personas";
    }
}
