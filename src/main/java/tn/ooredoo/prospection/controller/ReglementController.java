package tn.ooredoo.prospection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.ooredoo.prospection.entity.Reglement;
import tn.ooredoo.prospection.service.ReglementService;
@RestController
@RequestMapping("Reglement")
@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})

public class ReglementController {
    @Autowired
    private ReglementService reglementService;
    @PostMapping("/add")
    Reglement add (@RequestBody Reglement reglement) {
        return reglementService.add(reglement);
    }

}
