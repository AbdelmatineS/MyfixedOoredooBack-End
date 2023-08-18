package tn.ooredoo.prospection.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.Activation;
import tn.ooredoo.prospection.repository.ActivationRepo;

import java.util.List;

@Service
public class ActivationServiceImp implements ActivationService{
    @Autowired
    private ActivationRepo activationRepo;

    @Override
    public Activation add(Activation activation) {

        return activationRepo.save(activation);
    }

    @Override
    public Activation getList(Integer id) {
        return activationRepo.findById(id).get();

    }

    @Override
    public List<Activation> getAllList() {

        return activationRepo.findAll();
    }

    @Override


    public Activation update(Activation a, Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        activationRepo.deleteById(id);

    }

}
