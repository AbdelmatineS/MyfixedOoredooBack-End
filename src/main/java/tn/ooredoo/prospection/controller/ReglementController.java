package tn.ooredoo.prospection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.ooredoo.prospection.entity.Activation;
import tn.ooredoo.prospection.entity.Reglement;
import tn.ooredoo.prospection.service.ActivationService;
import tn.ooredoo.prospection.service.ReglementService;
@RestController
@RequestMapping("/api/Reglement")
@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})

public class ReglementController {
    @Autowired
    private ReglementService reglementService;
    
    @Autowired
    ActivationService activationService;
    
    
    @PostMapping("/add")
    Reglement add (@RequestBody Reglement reglement) {
        return reglementService.add(reglement);
    }
    
    
	@PreAuthorize("hasRole('ROLE_USERCONSEILLER')")
	@PostMapping("/addReg/{actId}")
	@ResponseBody
	ResponseEntity<Integer> addReg(@PathVariable("actId") Integer actId,@RequestBody Reglement reg) {
		Reglement response = activationService.addReg(actId,reg);	
		return ResponseEntity.ok(response.getId());
	}
	

}
