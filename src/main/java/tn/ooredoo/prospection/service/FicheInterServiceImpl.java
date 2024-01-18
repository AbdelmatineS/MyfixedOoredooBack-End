package tn.ooredoo.prospection.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.entity.FicheInter;
import tn.ooredoo.prospection.entity.UserSousTraitant;
import tn.ooredoo.prospection.repository.DemandeInterRepository;
import tn.ooredoo.prospection.repository.FicheInterRepository;
import tn.ooredoo.prospection.repository.UserStRepository;

@Service
public class FicheInterServiceImpl implements IFicheInterService{
	
	
	@Autowired
	UserStRepository usRepo;
	
	@Autowired
	DemandeInterRepository dRepo;
	
	@Autowired
	FicheInterRepository fRepo;

	@Override
	public FicheInter addFicheInter(Long ssId, Long demandeId, FicheInter f) {
		
		//fRepo.save(f);

		//byte[] imgByte = Base64.getDecoder().decode(f.getImagePvInter());
		//f.setImagePvInter(imgByte);
		UserSousTraitant userst = usRepo.findById(ssId).orElse(null);
		DemandeInter demande = dRepo.findById(demandeId).orElse(null);
		
		demande.getFiches().add(f);
		demande.setStatus(f.getAction());
		//demande.setDatePlanif(null);
		f.setDemande(demande);
		f.setUserst(userst);
		dRepo.save(demande);
		return fRepo.save(f);
	}

	@Override
	public List<FicheInter> retrieveAllFicheByDemande(Long demandeId) {
		return fRepo.findByDemandeId(demandeId);
	}

	@Override
	public List<FicheInter> retrieveAll() {
		return fRepo.findAll();
	}

	@Override
	public List<FicheInter> retireveAllFicheByUserst(Long userId) {
		return fRepo.findByUserstId(userId);
	}

}
