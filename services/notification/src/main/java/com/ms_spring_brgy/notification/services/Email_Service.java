package com.ms_spring_brgy.notification.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class Email_Service {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendEmail() throws MessagingException, InterruptedException {
        //create thymeleaf context this will contain the variable that will be use in html
        Context context = new Context();
        context.setVariable("name", "jia");

        //process the html template
        String htmlContent = templateEngine.process("email-template", context);

        //send email
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo("renzvanni.dev@gmail.com");
        helper.setSubject("Notification from brgy api");
        helper.setText(htmlContent, true);
        helper.setFrom("renzvanni626@gmail.com");

        javaMailSender.send(message);

        Thread.sleep(5000L);
        System.out.println("Second Message 002");
    }
}
