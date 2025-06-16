package com.example.pedidobackend.service.impl;

import com.example.pedidobackend.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);

        try {
            javaMailSender.send(mensaje);
            logger.info("Correo enviado correctamente a {}", destinatario);
        } catch (MailException e) {
            logger.error("Error al enviar el correo a {}", destinatario, e);
        }
    }
}
