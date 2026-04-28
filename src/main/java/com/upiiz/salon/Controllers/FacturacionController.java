package com.upiiz.salon.Controllers;

import com.upiiz.salon.Entities.VentasEntity;
import com.upiiz.salon.Services.ServicioService;
import com.upiiz.salon.Services.VentaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facturacion")
public class FacturacionController {

    @Autowired private VentaService ventaService;
    @Autowired private ServicioService servicioService;

    @GetMapping
    public String listar(Model model, HttpSession session) {
        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "FacturacionListado";
    }

    @GetMapping("/factura/{id}")
    public String generarFactura(@PathVariable Long id, Model model, HttpSession session) {
        VentasEntity venta = ventaService.obtenerVentaPorId(id);
        model.addAttribute("venta", venta);
        model.addAttribute("servicio", servicioService.obtenerServicioPorId(venta.getIdServicio()));
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "invoice";
    }
}