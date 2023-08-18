package tn.ooredoo.prospection.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.service.IDemandeInterService;

@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})
@RestController
@RequestMapping("DemandeInter")
public class DemandeInterController {
	
	@Autowired
	IDemandeInterService dService;
	
	
    @GetMapping("/getLastDemandeInter")
    public ResponseEntity<?> getLastDemandeInter() {
    	DemandeInter lastDemandeInter = dService.getLastDemandeInter();
        return ResponseEntity.ok(lastDemandeInter);
    }
	
	@PostMapping("/addDemandeInter")
	@ResponseBody
	ResponseEntity<Long> addDemandeInter(@RequestBody DemandeInter d) {
		DemandeInter response = dService.addDemandeInter(d);	
		return ResponseEntity.ok(response.getId());
	}
	
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

}
