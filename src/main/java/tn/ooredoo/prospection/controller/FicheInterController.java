package tn.ooredoo.prospection.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.exception.TwilioException;

import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.entity.FicheInter;
import tn.ooredoo.prospection.service.EmailService;
import tn.ooredoo.prospection.service.IFicheInterService;
import tn.ooredoo.prospection.service.SmsService;

@RestController
@RequestMapping("/api/FicheInter")
public class FicheInterController {
	@Autowired
	IFicheInterService fService;
	
	@Autowired
	SmsService smsService;
	
	@Autowired
	EmailService emailService;
	
	
	
	@PostMapping("/addFicheInter/{userId}/{demandeId}")
	@ResponseBody
	ResponseEntity<Long> addFicheInter(
	        @PathVariable ("userId") Long userId,
	        @PathVariable ("demandeId") Long demandeId,
	        @RequestBody FicheInter f) 
	{
		FicheInter response = fService.addFicheInter(userId, demandeId, f);	
		
        if (response != null && response.getId() != null) {
            return ResponseEntity.ok(response.getId());
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }	
        
	}
	
	
	

	
	
}
