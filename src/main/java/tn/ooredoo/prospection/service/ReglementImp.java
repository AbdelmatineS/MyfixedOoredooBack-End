package tn.ooredoo.prospection.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.Reglement;
import tn.ooredoo.prospection.repository.ReglementRepo;
@Service
public class ReglementImp implements ReglementService {
    @Autowired
     private ReglementRepo reglementRepo;
    @Override
    public Reglement add(Reglement reglement) {
        return reglementRepo.save(reglement);
    }
}

