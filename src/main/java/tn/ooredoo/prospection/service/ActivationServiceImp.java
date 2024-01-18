package tn.ooredoo.prospection.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.Activation;
import tn.ooredoo.prospection.entity.FixeJdid;
import tn.ooredoo.prospection.entity.Raccordement;
import tn.ooredoo.prospection.entity.Reglement;
import tn.ooredoo.prospection.entity.UserConseiller;
import tn.ooredoo.prospection.repository.ActivationRepo;
import tn.ooredoo.prospection.repository.FixeJdidRepo;
import tn.ooredoo.prospection.repository.RaccordementRepo;
import tn.ooredoo.prospection.repository.ReglementRepo;
import tn.ooredoo.prospection.repository.UserConRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivationServiceImp implements ActivationService{
    @Autowired
    private ActivationRepo activationRepo;
    
	@Autowired
	UserConRepository uRepo;
	
	@Autowired
	ReglementRepo regRepo;
	
	@Autowired
	RaccordementRepo raccRepo;
	
	@Autowired
	FixeJdidRepo fixeRepo;

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
    
    @Override
    public Activation addActivation(Long id, Activation a) {
		UserConseiller user = uRepo.findById(id).orElse(null);
		a.setUserc(user);
		a.setDateCreation(LocalDateTime.now());
		a.setDateDernièreModification(LocalDateTime.now());
		a.setStatus("NEW");
		return activationRepo.save(a);
    }

	@Override
	public Reglement addReg(Integer id, Reglement reg) {
		Activation a = activationRepo.findById(id).orElse(null);
		reg.setActivation(a);
		//a.setReg(reg);
		activationRepo.save(a);
		
		return regRepo.save(reg);
	}

	@Override
	public Raccordement addRacc(Integer id, Raccordement r) {
		Activation a = activationRepo.findById(id).orElse(null);
		r.setActivation(a);
		//a.setRacc(r);
		activationRepo.save(a);
		
		return raccRepo.save(r);
	}

	@Override
	public FixeJdid addfixe(Integer id, FixeJdid f) {
		fixeRepo.save(f);
		Activation a = activationRepo.findById(id).orElse(null);
		f.setActivation(a);
		//a.setFix(f);
		activationRepo.save(a);
		
		
		return fixeRepo.save(f);
	}
	
	
    @Override
    public void deleteF(Integer id) {
    	fixeRepo.deleteById(id);
    }

}
