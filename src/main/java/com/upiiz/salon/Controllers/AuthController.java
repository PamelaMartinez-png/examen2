package com.upiiz.salon.Controllers;

import com.upiiz.salon.Entities.UsuarioEntity;
import com.upiiz.salon.Services.EmailService;
import com.upiiz.salon.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UsuarioService usuarioService;
    @Autowired private EmailService emailService;

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String rol,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {


        Optional<UsuarioEntity> usuarioOpt = usuarioService.buscarPorEmail(username);

        if (usuarioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/auth/login";
        }

        UsuarioEntity usuario = usuarioOpt.get();


        if (!usuario.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("error", "Contraseña incorrecta");
            return "redirect:/auth/login";
        }


        if (!usuario.getRol().equals(rol)) {
            redirectAttributes.addFlashAttribute("error",
                    "No tienes acceso como " + rol + ". Tu rol es: " + usuario.getRol());
            return "redirect:/auth/login";
        }


        session.setAttribute("usuarioLogueado", usuario.getNombre());
        session.setAttribute("rolUsuario", usuario.getRol());
        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String register() { return "register"; }

    @PostMapping("/register")
    public String processRegister(@RequestParam String nombre,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam(required = false) String cpassword,
                                  @RequestParam String rol,
                                  RedirectAttributes redirectAttributes) {

        if (cpassword != null && !password.equals(cpassword)) {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/auth/register";
        }

        if (usuarioService.buscarPorEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Ya existe una cuenta con ese email");
            return "redirect:/auth/register";
        }

        UsuarioEntity nuevo = new UsuarioEntity();
        nuevo.setNombre(nombre);
        nuevo.setEmail(email);
        nuevo.setPassword(password);
        nuevo.setRol(rol);
        usuarioService.guardarUsuario(nuevo);

        redirectAttributes.addFlashAttribute("success", "Cuenta creada. Ya puedes iniciar sesión.");
        return "redirect:/auth/login";
    }

    @GetMapping("/forgot")
    public String forgot() { return "forgot-password"; }

    @PostMapping("/forgot")
    public String processForgot(@RequestParam String email,
                                RedirectAttributes redirectAttributes) {

        Optional<UsuarioEntity> usuarioOpt = usuarioService.buscarPorEmail(email);

        if (usuarioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No encontramos una cuenta con ese email");
            return "redirect:/auth/forgot";
        }

        try {
            UsuarioEntity usuario = usuarioOpt.get();
            emailService.enviarCorreo(
                    email,
                    "Recuperación de contraseña - Salón de Belleza",
                    "Hola " + usuario.getNombre() + ",\n\n" +
                            "Tu contraseña es: " + usuario.getPassword() + "\n\n" +
                            "Por seguridad, cámbiala después de iniciar sesión.\n\n" +
                            "— Salón de Belleza"
            );
            redirectAttributes.addFlashAttribute("success",
                    "Te enviamos tu contraseña al correo. Revisa tu bandeja.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al enviar el correo.");
        }
        return "redirect:/auth/forgot";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}