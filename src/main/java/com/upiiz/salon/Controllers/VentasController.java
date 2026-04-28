package com.upiiz.salon.Controllers;

import com.upiiz.salon.Entities.VentasEntity;
import com.upiiz.salon.Services.ServicioService;
import com.upiiz.salon.Services.VentaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/ventas")
public class VentasController {

    @Autowired private VentaService ventaService;
    @Autowired private ServicioService servicioService;

    @GetMapping
    public String listar(Model model, HttpSession session) {
        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "VentasListado";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model, HttpSession session) {
        model.addAttribute("venta", new VentasEntity());
        model.addAttribute("servicios", servicioService.listarServicios());
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "VentasFormulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("venta") VentasEntity venta) {
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }
        // Calcular total automáticamente
        servicioService.obtenerServicioPorId(venta.getIdServicio());
        var servicio = servicioService.obtenerServicioPorId(venta.getIdServicio());
        if (servicio != null && venta.getCantidad() != null) {
            venta.setTotal(servicio.getPrecio().multiply(
                    java.math.BigDecimal.valueOf(venta.getCantidad())));
        }
        ventaService.guardarVenta(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {
        model.addAttribute("venta", ventaService.obtenerVentaPorId(id));
        model.addAttribute("servicios", servicioService.listarServicios());
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "VentasFormulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return "redirect:/ventas";
    }
}