package tn.ooredoo.prospection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import tn.ooredoo.prospection.entity.Prospection;
import tn.ooredoo.prospection.service.IProspectionService;


@RestController
@RequestMapping("/api/Prospection")
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8101","http://localhost:4200","http://192.168.1.57:8100"}, maxAge = 3600, allowCredentials="true")
public class ProspectionController {
	
	@Autowired
	private IProspectionService pService;
	
	
	
    @GetMapping("/search")
    public List<Prospection> searchEntities(
            @RequestParam("attribute") String attribute,
            @RequestParam("query") String query
    ) {
        return pService.searchEntities(attribute, query);
    }
	
	@PreAuthorize("hasRole('ROLE_USERCONSEILLER')")
	@PostMapping("/addProspection/{userId}")
	@ResponseBody
	void addProspection(@PathVariable("userId") Long userId,@RequestBody Prospection p) {
		 pService.addProspection(userId,p);	
	}
	
	
	
	@GetMapping("/getallprospections")
	@ResponseBody
	public List<Prospection> getAll() {
	List<Prospection> listProspections = pService.getAll();
	return listProspections;
	}
	
	/*
	 * 
	 //	@GetMapping("/retrieveallprospections")
//	@ResponseBody
//	public List<Prospection> retrieveAll() {
//	List<Prospection> listProspections = pService.retrieveAll();
//	return listProspections;
//	}
 * 
	 */

	
	
	@GetMapping("retrieveProspection/{id}")
	@ResponseBody
	public Prospection retrieveProspectionById(@PathVariable long id ) {	
		return pService.retrieveProspectionById(id);
	}
	
	
	@GetMapping("/retrieveProspBynumID/{numID}")
	@ResponseBody
	public List<Prospection> getProspectionByNumID(@PathVariable Long numID ) {	
		return pService.getProspectionByNumID(numID);
	}
	
	

	
	
	@DeleteMapping("deleteProspection/{id}")
	@ResponseBody
	void deleteProspection(@PathVariable("id") long id) {	
		 pService.deleteProspection(id);
	}
	
	
	
	@PutMapping("modifyProspection/{id}")
	@ResponseBody
	Prospection updateProspection(@RequestBody Prospection p , @PathVariable long id ) {	
		return  pService.updateProspection(p,id);
	}

	

}
