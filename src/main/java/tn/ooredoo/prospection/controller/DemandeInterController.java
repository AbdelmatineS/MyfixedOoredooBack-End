package tn.ooredoo.prospection.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.exception.TwilioException;

import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.paylaod.request.DemandeInterRequest;
import tn.ooredoo.prospection.service.EmailService;
import tn.ooredoo.prospection.service.IDemandeInterService;
import tn.ooredoo.prospection.service.SmsService;

@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8101","http://localhost:4200","http://192.168.1.57:8100"})
@RestController
@RequestMapping("/api/DemandeInter")
public class DemandeInterController {
	
	@Autowired
	IDemandeInterService dService;
	
	@Autowired
	SmsService smsService;
	
	@Autowired
	EmailService emailService;
	
    @GetMapping("/getLastDemandeInter")
    public ResponseEntity<?> getLastDemandeInter() {
    	DemandeInter lastDemandeInter = dService.getLastDemandeInter();
        return ResponseEntity.ok(lastDemandeInter);
    }
	
	@PostMapping("/addDemandeInter/{adminId}/{ssId}/{resId}")
	@ResponseBody
	ResponseEntity<Long> addDemandeInterRes(
	        @PathVariable ("adminId") Long adminId,
	        @PathVariable ("ssId") Long ssId,
	        @PathVariable ("resId") Long resId,
	        @RequestBody DemandeInter d) 
	{
		DemandeInter response = dService.addDemandeInterRes(adminId,ssId,resId, d);	
		
        if (response != null && response.getId() != null) {
            try {
				sendEmailAsync(d);
	            sendSMSAsync(d);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
            return ResponseEntity.ok(response.getId());
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }	}
	
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
	
    @Async
    public void sendEmailAsync(DemandeInter d) throws MessagingException {
        // Send email
		String to = d.getReservation().getEmail();
		String subject = "Demande Assigné";
		String body = "Votre réservation N° "
		+d.getReservation().getContractNum()+
		" a été assigné à la société: "+d.getUser_st().getNomSt()+
		" avec succès. Vous pouvez contacter la société sous traitante via ce numéro: "
		+d.getUser_st().getNumTel();
		emailService.sendMail(to, subject, body);
    }

    @Async
    public void sendSMSAsync(DemandeInter d) {
        try {
            // Send SMS
            String smsMessage = "Votre réservation N° " +d.getReservation().getContractNum()+
            		" a été assigné à la société: "+d.getUser_st().getNomSt()+
            		" avec succès. Vous pouvez contacter la société sous traitante via ce numéro: "
            		+d.getUser_st().getNumTel();
            smsService.sendSMS(smsMessage, "+216" + d.getReservation().getTelOne().toString());
        } catch (TwilioException e) {
            // Handle SMS sending failure
            e.printStackTrace();
        }
    }

}
