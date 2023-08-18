package tn.ooredoo.prospection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.ooredoo.prospection.entity.Activation;
import tn.ooredoo.prospection.service.ActivationService;

import java.util.List;

@RestController
@RequestMapping("Activation")
@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})

public class ActivationController {
    @Autowired
    ActivationService activationService;
    @PostMapping("/add")
    public Activation add (@RequestBody Activation activation){

        return activationService.add(activation);
    }
    @GetMapping("/list")
    public List<Activation> finAllActivation(){

        return activationService.getAllList();
    }
    @PutMapping("update/{id}")
    @ResponseBody
    Activation update(@RequestBody Activation a , @PathVariable Integer id ) {
        return  activationService.update(a,id);
    }
    @DeleteMapping("delete/{id}")
    public void delete (@PathVariable Integer id ){
        activationService.delete(id);
    }


}


