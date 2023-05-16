package com.company;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class JavaMailDemo {
    public static void main(String[] args) {
        // 1. add external libraries to project

        // Sender's email ID needs to be mentioned
        String from = "alexovmaxim24@gmail.com";

        // Recipient's email ID needs to be mentioned.
        //String to = "yyy@gmail.com";
        String to = "alizanovmarat1@gmail.com";

//        alizanovmarat1@gmail.com
        // Get system properties
        Properties properties = new Properties();

        // Setup mail server
        // Assuming you are sending email from through gmails smtp
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(from, "xskpzuztchvoixhi");

                    }

                });

        // Used to debug SMTP issues
        // session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            String[] split = to.split("\\s+");
            Address[] addresses = new Address[split.length];
            for (int i = 0; i < split.length; i++) {
                addresses[i] = new InternetAddress(split[i]);
            }
            message.addRecipients(Message.RecipientType.TO, addresses);

            // Set Subject: header field
            message.setSubject("Salom xat");

            // Now set the actual message
            message.setText("Bugun G24 uchun boshqacha kun");

            // Send the actual HTML message.
/*
            message.setContent(
                    "<h1>This is actual message embedded in HTML tags</h1>",
                    "text/html");
*/

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f = new File("pom.xml");

                attachmentPart.attachFile(f);

                textPart.setText("This is list of students");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

