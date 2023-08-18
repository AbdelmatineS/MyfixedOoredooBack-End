package tn.ooredoo.prospection.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Service
@Slf4j
 public class EmailServiceImpl implements  EmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override

    public String sendMail(MultipartFile file, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            if (file != null && !file.isEmpty()) {
                mimeMessageHelper.addAttachment(
                        file.getOriginalFilename(),
                        new ByteArrayResource(file.getBytes())
                );
            }

            javaMailSender.send(mimeMessage);
            return "Mail sent";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
