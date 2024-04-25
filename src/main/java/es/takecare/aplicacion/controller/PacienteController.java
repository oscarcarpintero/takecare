package es.takecare.aplicacion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.takecare.aplicacion.model.Paciente;
import es.takecare.aplicacion.model.Ubicacion;
import es.takecare.aplicacion.repository.PacienteRepository;
import es.takecare.aplicacion.repository.TomaRepository;
import es.takecare.aplicacion.repository.UbicacionRepository;

@Controller
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private TomaRepository tomaRepository;

    @GetMapping("/pacientes/{id}")
    public String getAllpacientees(@PathVariable("id") Integer id, Model model) {
        try {
            Optional<Ubicacion> ubicacion = ubicacionRepository.findById(id);
            if (ubicacion.isEmpty()) {
                model.addAttribute("message", "El código de ubicación proporcionado no existe");
            } else {
                List<Paciente> pacientes = pacienteRepository.findByUbicacion(ubicacion.get());
                model.addAttribute("pacientes", pacientes);
                model.addAttribute("codigoUbicacion", id);
            }

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "pacientes";
    }

    @GetMapping("/pacientes/{id}/alta")
    public String altapaciente(@PathVariable("id") Integer id, Model model) {
        Paciente paciente = new Paciente();

        Optional<Ubicacion> ubicacion = ubicacionRepository.findById(id);

        if (ubicacion.isEmpty()) {
            model.addAttribute("message", "El código de ubicación proporcionado no existe");
        } else {
            paciente.setUbicacion(ubicacion.get());
        }

        model.addAttribute("paciente", paciente);
        model.addAttribute("paginaTitulo", "Crear nuevo paciente");

        return "paciente_form";

    }

    @PostMapping("/pacientes/guardar")
    public String savepaciente(Paciente paciente, RedirectAttributes redirectAttributes) {
        try {
            pacienteRepository.save(paciente);

            redirectAttributes.addFlashAttribute("message", "El paciente se ha guardado correctamente");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/pacientes/" + paciente.getUbicacion().getCodigo();
    }

    @GetMapping("/paciente/{id}")
    public String editpaciente(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Paciente paciente = pacienteRepository.findById(id).get();

            model.addAttribute("paciente", paciente);
            model.addAttribute("paginaTitulo", "Editar paciente");

            return "paciente_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/pacientes";
        }
    }
    @Transactional
    @GetMapping("/pacientes/delete/{idUbicacion}/{id}")
    public String deleteTutorial(@PathVariable("idUbicacion") Integer idUbicacion, @PathVariable("id") Integer id,
            Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<Paciente> paciente = pacienteRepository.findById(id);
            if (paciente.isPresent()) {
                tomaRepository.deleteByPaciente(paciente.get());
                pacienteRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("message", "El paciente ha sido borrado");
            } else {
                redirectAttributes.addFlashAttribute("message", "El ID proporcionado de paciente no existe");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/pacientes/" + idUbicacion;
    }

}
