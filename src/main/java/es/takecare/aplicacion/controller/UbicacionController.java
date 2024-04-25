package es.takecare.aplicacion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.takecare.aplicacion.model.Paciente;
import es.takecare.aplicacion.model.Ubicacion;
import es.takecare.aplicacion.repository.PacienteRepository;
import es.takecare.aplicacion.repository.UbicacionRepository;

@Controller
public class UbicacionController {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/")
    public String inicio(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Paciente> paciente = pacienteRepository.findByNombre((String) authentication.getPrincipal());

        if (paciente.isEmpty()) {
            return "redirect:/ubicaciones";
        } else {
            return "redirect:/personal/medicacion";
        }

    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/ubicaciones")
    public String getAllUbicaciones(Model model) {
        try {
            List<Ubicacion> ubicaciones = ubicacionRepository.findAll();
            model.addAttribute("ubicaciones", ubicaciones);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "ubicaciones";
    }

    @GetMapping("/ubicaciones/alta")
    public String altaUbicacion(Model model) {
        Ubicacion ubicacion = new Ubicacion();

        model.addAttribute("ubicacion", ubicacion);
        model.addAttribute("paginaTitulo", "Crear nueva ubicación");

        return "ubicacion_form";

    }

    @PostMapping("/ubicaciones/guardar")
    public String saveUbicacion(Ubicacion ubicacion, RedirectAttributes redirectAttributes) {
        try {
            ubicacionRepository.save(ubicacion);

            redirectAttributes.addFlashAttribute("message", "La ubicación se ha guardado correctamente");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/ubicaciones";
    }

    @GetMapping("/ubicaciones/{id}")
    public String editUbicacion(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Ubicacion Ubicacion = ubicacionRepository.findById(id).get();

            model.addAttribute("ubicacion", Ubicacion);
            model.addAttribute("paginaTitulo", "Editar Ubicacion");

            return "Ubicacion_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/ubicaciones";
        }
    }

    @GetMapping("/ubicaciones/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            ubicacionRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "La ubicación ha sido borrada");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "No se puede borrar una ubicación si tiene pacientes. Elimine primero los pacientes");
        }

        return "redirect:/ubicaciones";
    }

}
