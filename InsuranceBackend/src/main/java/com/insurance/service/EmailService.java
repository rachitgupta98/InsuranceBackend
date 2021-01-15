<<<<<<< HEAD
package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private MailSender mailSender;
    public void sendEmail(String email,String text,String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("InsuranceGroup13@outlook.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }

}
=======
package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private MailSender mailSender;
    public void sendEmail(String email,String text,String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("InsuranceGroup13@outlook.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }

}
>>>>>>> branch 'master' of https://github.com/rachitgupta98/InsuranceBackend.git
