package es.takecare.aplicacion.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.takecare.aplicacion.model.Paciente;
import es.takecare.aplicacion.model.Toma;
import es.takecare.aplicacion.repository.MedicamentoRepository;
import es.takecare.aplicacion.repository.PacienteRepository;
import es.takecare.aplicacion.repository.TomaRepository;

@Controller
public class PacienteMedicacionController {

    @Autowired
    private TomaRepository tomaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @GetMapping("/personal/medicacion")
    public String getAlltomaes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Paciente> paciente = pacienteRepository.findByNombre((String) authentication.getPrincipal());

        if (paciente.isEmpty())  {
            return "redirect:/";
        }

        List<Toma> tomas = tomaRepository.findByPacienteAndFecha(paciente.get(), new Date());
        model.addAttribute("tomas", tomas);
        model.addAttribute("codigoPaciente", paciente.get().getCodigo());
        model.addAttribute("nombrePaciente", paciente.get().getNombre());
        model.addAttribute("fechaActual", new Date());

        return "personalMedicacion";
    }

    @GetMapping("/personal/cambiarToma/{id}")
    public String cambiarToma(@PathVariable("id") Integer id, Model model) {
        Optional<Toma> tomaOptional = tomaRepository.findById(id);

        if (tomaOptional.isEmpty()) {
            model.addAttribute("message", "La toma proporcionada no existe");
            return "redirect:/";
        } else {
            Toma toma = tomaOptional.get();
            toma.setTomada(!toma.isTomada());
            tomaRepository.save(toma);
            return "redirect:/personal/medicacion";
        }

    }

}
