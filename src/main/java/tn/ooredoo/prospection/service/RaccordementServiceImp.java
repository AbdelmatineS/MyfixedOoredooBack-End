package tn.ooredoo.prospection.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.Raccordement;
import tn.ooredoo.prospection.repository.RaccordementRepo;

import java.util.List;

@Service
public class RaccordementServiceImp implements RaccordementService {

    @Autowired
    private RaccordementRepo raccordementRepo;

    @Override
    public Raccordement add(Raccordement raccordement) {
        return raccordementRepo.save(raccordement);
    }

    @Override
    public Raccordement getList(Integer id) {
        return raccordementRepo.findById(id).orElse(null);
    }

    @Override
    public Raccordement getById(Integer id) {
        return raccordementRepo.findById(id).orElse(null);

    }

    @Override
    public List<Raccordement> getAllList() {
        return raccordementRepo.findAll();
    }

    @Override
    public Raccordement update(Raccordement raccordement, Integer id) {
        // Implement your update logic here
        // Retrieve the existing entity by ID, update its properties, and save it
        Raccordement existingRaccordement = raccordementRepo.findById(id).orElse(null);
        if (existingRaccordement != null) {
            // Update the properties of the existing entity
            existingRaccordement.setOffres(raccordement.getOffres());
            existingRaccordement.setDebit(raccordement.getDebit());
            existingRaccordement.setMondatTT(raccordement.getMondatTT());

            // Save the updated entity
            return raccordementRepo.save(existingRaccordement);
        }
        return null; // Return null if the entity does not exist
    }

    @Override
    public void delete(Integer id) {
        raccordementRepo.deleteById(id);
    }

    @Override
    public Raccordement findById(Integer id) {
        return null;
    }
}
