package com.upiiz.salon.Services;

import com.upiiz.salon.Entities.UsuarioEntity;
import java.util.Optional;

public interface UsuarioService {
    UsuarioEntity guardarUsuario(UsuarioEntity usuario);
    Optional<UsuarioEntity> buscarPorEmail(String email);
}