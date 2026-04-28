package com.upiiz.salon.Services;


public interface EmailService {
    void enviarCorreo(String destinatario, String asunto, String cuerpo);
}
