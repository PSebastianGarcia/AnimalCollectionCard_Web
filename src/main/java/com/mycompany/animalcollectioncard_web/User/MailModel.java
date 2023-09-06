package com.mycompany.animalcollectioncard_web.User;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class MailModel {

    private final String SENDER_MAIL = "pc.sebastiangarcia@gmail.com";
    private final String SENDER_PASS = "";
    private String mailTo;
    private final String SUBJECT = "E-mail de confirmación";
    private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mMail;

    public MailModel() {
        mProperties = new Properties();
    }

    public void sendEmail(User user, String content) {
        this.mailTo = user.getEmail();
        this.content = content;

        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.starttls.enable", "true");
        mProperties.put("mail.smtp.port", "587");
        mProperties.put("mail.smtp.auth", "true");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.put("mail.smtp.user", SENDER_MAIL);
        mProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_MAIL, SENDER_PASS);
            }
        };

        mSession = Session.getInstance(mProperties, auth);
        try {
            mMail = new MimeMessage(mSession);
            mMail.setFrom(new InternetAddress(SENDER_MAIL));
            mMail.setRecipient(Message.RecipientType.TO, new InternetAddress(this.mailTo));
            mMail.setSubject(SUBJECT);
            mMail.setContent(this.content, "text/html");
            
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(SENDER_MAIL, SENDER_PASS);
            mTransport.sendMessage(mMail, mMail.getRecipients(Message.RecipientType.TO));
            mTransport.close();

        } catch (MessagingException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo enviar el mail para confirmación " + ex.getMessage());
            throw new RuntimeException("Error interno", ex);
        }
    }
}
