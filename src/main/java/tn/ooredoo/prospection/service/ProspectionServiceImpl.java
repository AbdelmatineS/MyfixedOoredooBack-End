package tn.ooredoo.prospection.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.Prospection;
import tn.ooredoo.prospection.entity.UserConseiller;
import tn.ooredoo.prospection.repository.ProspectionRepository;
import tn.ooredoo.prospection.repository.UserConRepository;


@Service
public class ProspectionServiceImpl implements IProspectionService {
	
	
	@Autowired
	ProspectionRepository pRepo;
	
	@Autowired
	UserConRepository uRepo;

	@Override
	public Prospection retrieveProspectionById(long id) {
		return pRepo.findById(id).get();		
	}

//	@Override
//	public List<Prospection> retrieveAllByUser(long id) {
//		return (List<Prospection>) pRepo.findByOrderByIdByUserConseillerDesc(id);
//	}

	@Override
	public Prospection addProspection(Long userId, Prospection p) {
		pRepo.save(p);
		UserConseiller user = uRepo.findById(userId).orElse(null);

			p.setDateCreation(LocalDateTime.now());
			p.setDateDernièreModification(LocalDateTime.now());
			p.setUserc(user);
		

		return pRepo.save(p);
	}
	
	/*
	 * 	public Prospection addProspection(Long id, Prospection p) {
		UserConseiller user = uRepo.findById(id).orElse(null);
		
		p.setDateCreation(LocalDateTime.now());
		p.setDateDernièreModification(LocalDateTime.now());
		pRepo.save(p);
		user.setProspections((Set<Prospection>) p);
		uRepo.save(user);
		return p;
	}
	 */

	@Override
	public Prospection updateProspection(Prospection p, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProspection(long id) {
		pRepo.deleteById(id);
	}

	@Override
	public List<Prospection> getAll() {
		return (List<Prospection>) pRepo.findByOrderByIdDesc();
	}

	@Override
	public List<Prospection> getProspectionByNumID(Long numID) {
		return pRepo.findByNumIDContaining(numID);
	}

	@Override
	public List<Prospection> searchEntities(String attribute, String query) {
        if ("fullName".equals(attribute)) {
            return pRepo.findByFullNameContainingIgnoreCase(query);
        } else if ("zone".equals(attribute)) {
            return pRepo.findByZoneContainingIgnoreCase(query);
        } else if ("adresse".equals(attribute)) {
            return pRepo.findByAdresseContainingIgnoreCase(query);
        } else if ("numID".equals(attribute)) {
            //return pRepo.findByNumIDContaining(Long.parseLong(query));
        }

        return Collections.emptyList();
    }

	@Override
	public List<Prospection> retrieveAllByUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
