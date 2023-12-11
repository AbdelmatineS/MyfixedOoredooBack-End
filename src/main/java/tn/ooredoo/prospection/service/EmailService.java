package tn.ooredoo.prospection.service;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendMailCC(MultipartFile file, String to, String[] cc, String subject, String body);
    String sendMailFile(MultipartFile file, String to, String subject, String body);
    void sendMail(String to, String subject, String body) throws MessagingException;

}