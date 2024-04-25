package es.takecare.aplicacion.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.takecare.aplicacion.bean.AltaMedicacion;
import es.takecare.aplicacion.model.Medicamento;
import es.takecare.aplicacion.model.Paciente;
import es.takecare.aplicacion.model.Toma;
import es.takecare.aplicacion.repository.MedicamentoRepository;
import es.takecare.aplicacion.repository.PacienteRepository;
import es.takecare.aplicacion.repository.TomaRepository;

@Controller
public class TomaController {

    @Autowired
    private TomaRepository tomaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @GetMapping("/tomas/{id}")
    public String getAlltomaes(@PathVariable("id") Integer id, Model model) {
        try {
            Optional<Paciente> paciente = pacienteRepository.findById(id);
            if (paciente.isEmpty()) {
                model.addAttribute("message", "El código de paciente proporcionado no existe");
            } else {
                List<Toma> tomas = tomaRepository.findByPacienteOrderByFecha(paciente.get());
                model.addAttribute("tomas", tomas);
                model.addAttribute("codigoPaciente", id);
                model.addAttribute("codigoUbicacion", paciente.get().getUbicacion().getCodigo());
            }

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        
        return "tomas";
    }

    @GetMapping("/tomas/{id}/alta")
    public String altatoma(@PathVariable("id") Integer id, Model model) {
        AltaMedicacion altaMedicacion = new AltaMedicacion();
        altaMedicacion.setInicio(new Date());

        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            model.addAttribute("message", "El código de paciente proporcionado no existe");
        } else {
            altaMedicacion.setPaciente(paciente.get());
        }

        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        List<String> frecuencias = new ArrayList<String>();
        frecuencias.add("Mañana");
        frecuencias.add("Tarde");
        frecuencias.add("Noche");

        model.addAttribute("frecuencias", frecuencias);
        model.addAttribute("altaMedicacion", altaMedicacion);
        model.addAttribute("medicamentos", medicamentos);
        model.addAttribute("paginaTitulo", "Crear nueva tomas de medicación");

        return "toma_form";

    }

    @PostMapping("/tomas/guardar")
    public String savetoma(AltaMedicacion altaMedicacion, RedirectAttributes redirectAttributes) {
        try {
            for (int dia = 0; dia < altaMedicacion.getDuracion(); dia++) {
                for (String frecuencia : altaMedicacion.getFrecuencia()) {
                    Toma toma = new Toma();
                    Calendar cal = Calendar.getInstance();

                    cal.setTime(altaMedicacion.getInicio());
                    cal.add(Calendar.DATE, dia);
                    toma.setFecha(cal.getTime());
                    toma.setDosis(altaMedicacion.getDosis());
                    toma.setHorario(frecuencia);
                    toma.setTomada(false);
                    toma.setPaciente(altaMedicacion.getPaciente());
                    toma.setMedicamento(altaMedicacion.getMedicamento());
                    tomaRepository.save(toma);
                }
            }

            redirectAttributes.addFlashAttribute("message", "La nueva medicación se ha guardado correctamente");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/tomas/" + altaMedicacion.getPaciente().getCodigo();
    }

    @GetMapping("/cambiarToma/{id}")
    public String cambiarToma(@PathVariable("id") Integer id, Model model) {
        Optional<Toma> tomaOptional = tomaRepository.findById(id);

        if (tomaOptional.isEmpty()) {
            model.addAttribute("message", "La toma proporcionada no existe");
            return "redirect:/";
        } else {
            Toma toma = tomaOptional.get();
            toma.setTomada(!toma.isTomada());
            tomaRepository.save(toma);
            return "redirect:/tomas/" + toma.getPaciente().getCodigo();
        }

    }

}
