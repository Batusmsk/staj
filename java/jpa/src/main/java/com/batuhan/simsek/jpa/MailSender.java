package com.batuhan.simsek.jpa;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailSender {
   public static void main(String[] args) {

      String to = "simsekbatu2004@hotmail.com";
      String from = "simsekbatuhan18@gmail.com";
      String host = "smtp.gmail.com"; // Örnek: gmail SMTP sunucusu

      // SMTP sunucusuna bağlanmak için gerekli bilgileri ayarlayın
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.smtp.port", "465");
      properties.setProperty("mail.smtp.auth", "true");
      properties.setProperty("mail.smtp.starttls.enable", "false");
      properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

      // Oturum açmak için kimlik bilgilerini ayarlayın
      Session session = Session.getDefaultInstance(properties, new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("simsekbatuhan18@gmail.com", "Bmab19970420smsk");
         }
      });

      try {
         // Yeni bir MimeMessage nesnesi oluşturun
         MimeMessage message = new MimeMessage(session);

         // Gönderen, alıcı ve konu bilgilerini ayarlayın
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject("Java ile mail gönderme örneği");

         // Mesaj içeriğini ayarlayın
         message.setText("Bu bir test mesajıdır.");

         // Mesajı gönderin
         Transport.send(message);
         System.out.println("Mesaj başarıyla gönderildi.");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}