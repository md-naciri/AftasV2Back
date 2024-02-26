package com.app.fishcompetition.services.email;

public interface EmailServiceSender {
    void sendEmail(String toEmail,String subject,String body);
}
