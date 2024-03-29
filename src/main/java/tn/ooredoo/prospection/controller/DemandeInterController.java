package tn.ooredoo.prospection.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.exception.TwilioException;

import tn.ooredoo.prospection.DTO.NotificationMessage;
import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.service.EmailService;
import tn.ooredoo.prospection.service.IDemandeInterService;
import tn.ooredoo.prospection.service.NotificationMessagingService;
import tn.ooredoo.prospection.service.SmsService;

//@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8101","http://localhost:4200","http://192.168.1.57:8100"})
@RestController
@RequestMapping("/api/DemandeInter")
public class DemandeInterController {
	
	@Autowired
	IDemandeInterService dService;
	
	@Autowired
	SmsService smsService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	NotificationMessagingService nms;
	
    @GetMapping("/getLastDemandeInter")
    public ResponseEntity<?> getLastDemandeInter() {
    	DemandeInter lastDemandeInter = dService.getLastDemandeInter();
        return ResponseEntity.ok(lastDemandeInter);
    }
    
    /////////////////////////ADDDDDDDHHHEEEERRRREEEEE///////////////
	
	@PostMapping("/addDemandeInter/{adminId}/{ssId}/{actId}")
	@ResponseBody
	ResponseEntity<Long> addDemandeInterAct(
	        @PathVariable ("adminId") Long adminId,
	        @PathVariable ("ssId") Long ssId,
	        @PathVariable ("actId") Integer actId,
	        @RequestBody DemandeInter d) 
	{
		DemandeInter response = dService.addDemandeInterAct(adminId,ssId,actId, d);	

        if (response != null && response.getId() != null) {
            try {
				sendEmailAffect(d);
	            sendSMSAffect(d);

	            
			} catch (MessagingException e) {
				e.printStackTrace();
			}
            return ResponseEntity.ok(response.getId());
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }	
        
	}
	
	
	
	
	
	
	
	@PostMapping("/affecterDemandeInter/{demandeId}/{resId}")
	@ResponseBody
	ResponseEntity<Long> affecterDemandeInterRes(
	        @PathVariable ("demandeId") Long demandeId,
	        @PathVariable ("resId") Long resId) 
	{
		DemandeInter response = dService.affecterDemandeInterRes(demandeId,resId);	
		
        if (response != null && response.getId() != null) {

            return ResponseEntity.ok(response.getId());
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }	
        
	}
	
	
	
//	@PostMapping("/addDemandeTest")
//	@ResponseBody
//	ResponseEntity<Long> addDemandeTest(@RequestBody DemandeInterRequest d) 
//	{
//		DemandeInter response = dService.addDemandeTest(d);	
//		return ResponseEntity.ok(response.getId());
//	}
	
	
	@PostMapping("/addPlanDemandeInter")
	@ResponseBody
	ResponseEntity<Long> addPlanDemandeInter(@RequestBody Long id, LocalDateTime date ) {
		DemandeInter response = dService.addPlanDemandeInter(id, date);	
		return ResponseEntity.ok(response.getId());
	}
	
	
	@GetMapping("/retrieveallDemandeInter")
	@ResponseBody
	public List<DemandeInter> retrieveAll() {
	List<DemandeInter> listDemandeInters = dService.retrieveAllDemandeInter();
	return listDemandeInters;
	}
	
	
	@GetMapping("/retrieveallDemandeInterByUserSt/{userId}")
	@ResponseBody
	public List<DemandeInter> retrieveAllDemandeInterByUserSt(@PathVariable ("userId") Long userId) {
	List<DemandeInter> listDemandeInters = dService.retrieveAllDemandeInterByUserSt(userId);
	return listDemandeInters;
	}
	
	
	@GetMapping("retrieveDemandeInter/{id}")
	@ResponseBody
	DemandeInter retrieveDemandeInterById(@PathVariable Long id ) {	
		return dService.retrieveDemandeInterById(id);
	}
	
	
	@DeleteMapping("deleteDemandeInter/{id}")
	@ResponseBody
	void deleteDemandeInter(@PathVariable("id") Long id) {	
		 dService.deleteDemandeInter(id);
	}
	
    @PutMapping("/updateDatePlanif/{demandeId}")
    public ResponseEntity<DemandeInter> updateDatePlanif(
            @PathVariable Long demandeId,
            @RequestParam("newDatePlanif") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDatePlanif
    ) {
        DemandeInter updatedDemande = dService.updateDatePlanif(demandeId, newDatePlanif);
        if(updatedDemande!= null && updatedDemande.getDatePlanif() != null)
        {
        	
            try {
				sendEmailPlan(updatedDemande);
	            sendSMSPlan(updatedDemande);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
            return ResponseEntity.ok(updatedDemande);
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PostMapping("/not")
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
    	return nms.sendNotificationByToken(notificationMessage);
    }
    
    
    /*
		DemandeInter response = dService.addDemandeInterRes(adminId,ssId,resId, d);	
		
        if (response != null && response.getId() != null) {
            try {
				sendEmailAffect(d);
	            sendSMSAffect(d);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
            return ResponseEntity.ok(response.getId());
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
     */
	
    @Async
    public void sendEmailAffect(DemandeInter d) throws MessagingException {
        // Send email
		String to = d.getActivation().getEmail();
		String subject = "Demande Attribuée";
		String body = "Votre réservation N° "
		+d.getActivation().getFix().getContractNum()+
		" a été attribuée à la société: "+d.getUserst().getNomSt()+
		" avec succès. Vous pouvez contacter la société sous traitante via ce numéro: "
		+d.getUserst().getNumTel();
		emailService.sendMail(to, subject, body);
    }

    @Async
    public void sendSMSAffect(DemandeInter d) {
        try {
            // Send SMS
            String smsMessage = "Votre réservation N° " +d.getActivation().getFix().getContractNum()+
            		" a été attribuée à la société: "+d.getUserst().getNomSt()+
            		" avec succès. Vous pouvez contacter la société sous traitante via ce numéro: "
            		+d.getUserst().getNumTel();
            smsService.sendSMS(smsMessage, "+216" + d.getActivation().getNumContact().toString());
        } catch (TwilioException e) {
            // Handle SMS sending failure
            e.printStackTrace();
        }
    }
    
    
    
    @Async
    public void sendEmailPlan(DemandeInter d) throws MessagingException {
        // Send email
		String to = d.getActivation().getEmail();
		String subject = "Demande Planifiée";
		String body = "Votre réservation N° "
		+d.getActivation().getFix().getContractNum()+
		" a été planifiée pour la date: "+d.getDatePlanif()+
		" Vous pouvez contacter la société sous traitante via ce numéro: "
		+d.getUserst().getNumTel()+" pour plus d'information.";
		emailService.sendMail(to, subject, body);
    }

    @Async
    public void sendSMSPlan(DemandeInter d) {
        try {
            // Send SMS
            String smsMessage = "Votre réservation N° " +d.getActivation().getFix().getContractNum()+
            		" a été planifiée pour la date: "+d.getDatePlanif()+
            		"  Vous pouvez contacter la société sous traitante via ce numéro: "
            		+d.getUserst().getNumTel()+"  pour plus d'information.";
            smsService.sendSMS(smsMessage, "+216" + d.getActivation().getNumContact().toString());
        } catch (TwilioException e) {
            // Handle SMS sending failure
            e.printStackTrace();
        }
    }
    
    

}
