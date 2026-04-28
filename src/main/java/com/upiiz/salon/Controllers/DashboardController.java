package com.upiiz.salon.Controllers;

import com.upiiz.salon.Services.ServicioService;
import com.upiiz.salon.Services.VentaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired private ServicioService servicioService;
    @Autowired private VentaService ventaService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        model.addAttribute("totalServicios", servicioService.listarServicios().size());
        model.addAttribute("totalVentas", ventaService.listarVentas().size());
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "dashboard";
    }
}