package com.upiiz.salon.Controllers;

import com.upiiz.salon.Entities.ServiciosEntity;
import com.upiiz.salon.Services.ServicioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servicios")
public class ServiciosController {

    @Autowired private ServicioService servicioService;

    @GetMapping
    public String listar(Model model, HttpSession session) {
        model.addAttribute("servicios", servicioService.listarServicios());
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "ServiciosListado";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model, HttpSession session) {
        model.addAttribute("servicio", new ServiciosEntity());
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "ServiciosFormulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("servicio") ServiciosEntity servicio) {
        servicioService.guardarServicio(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {
        model.addAttribute("servicio", servicioService.obtenerServicioPorId(id));
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "ServiciosFormulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return "redirect:/servicios";
    }
}