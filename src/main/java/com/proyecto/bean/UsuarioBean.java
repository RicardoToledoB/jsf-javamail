package com.proyecto.bean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ricardotoledo
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    public UsuarioBean() {
    }

    public void sendEmail() {
        final String username = "sidramagallanes@gmail.com";
        final String password = "sidra123sidra";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sidra.ssmagallanes@redsalud.gov.cl"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("sidra.ssmagallanes@redsalud.gov.cl"));
            message.setSubject("Envio desde JAVA");
            message.setText("Prueba Coreo enviado desde Sistema Local,"
                    + "\n\n Prueba correo enviado desde Sistema Local!");
           

            Transport.send(message);

            System.out.println("Correo Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
