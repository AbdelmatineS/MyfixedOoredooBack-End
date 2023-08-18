package tn.ooredoo.prospection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("Prospection")
@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})
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
	
	
	@PostMapping("/addProspection")
	@ResponseBody
	Prospection addProspection(@RequestBody Prospection p) {
		return pService.addProspection(p);	
	}
	
	
	
	@GetMapping("/getallprospections")
	@ResponseBody
	public List<Prospection> getAll() {
	List<Prospection> listProspections = pService.getAll();
	return listProspections;
	}
	
	
	@GetMapping("/retrieveallprospections")
	@ResponseBody
	public List<Prospection> retrieveAll() {
	List<Prospection> listProspections = pService.retrieveAll();
	return listProspections;
	}
	
	
	@GetMapping("retrieveProspection/{id}")
	@ResponseBody
	Prospection retrieveProspectionById(@PathVariable long id ) {	
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
