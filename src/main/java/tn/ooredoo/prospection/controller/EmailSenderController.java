package tn.ooredoo.prospection.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import tn.ooredoo.prospection.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailSenderController {
    private EmailService emailService;
    public EmailSenderController(EmailService emailService){
        this.emailService=emailService;
    }
    @PostMapping("/send")
    public String sendEmail(@RequestParam(value = "file", required = false) MultipartFile file,
                            @RequestParam("to") String to,
                            @RequestParam("cc") String[] cc,
                            @RequestParam("subject") String subject,
                            @RequestParam("body") String body) {
        return emailService.sendMail(file, to, cc, subject, body);
    }


}
