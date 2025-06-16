package com.example.pedidobackend.service;

public interface EmailService {

    void enviarCorreo(String destinatario, String asunto, String cuerpo);

}
