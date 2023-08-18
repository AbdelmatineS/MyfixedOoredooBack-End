package tn.ooredoo.prospection.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.entity.Reservation;
import tn.ooredoo.prospection.repository.DemandeInterRepository;

@Service
public class DemandeInterServiceImpl implements IDemandeInterService{

	@Autowired
	DemandeInterRepository dRepo;
	@Override
	public DemandeInter retrieveDemandeInterById(Long id) {
		// TODO Auto-generated method stub
		return dRepo.findById(id).get();
	}

	@Override
	public List<DemandeInter> retrieveAllDemandeInter() {
		// TODO Auto-generated method stub
		return (List<DemandeInter>) dRepo.findAll();
	}

	@Override
	public DemandeInter addPlanDemandeInter(Long id, LocalDateTime date) {
		DemandeInter d = dRepo.findById(id).get();
		d.setDatePlanif(date);
		return dRepo.save(d);
	}

	@Override
	public DemandeInter updateDemandeInter(DemandeInter d, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDemandeInter(Long id) {
		dRepo.deleteById(id);
		
	}

	@Override
	public DemandeInter getLastDemandeInter() {
		return dRepo.findTopByOrderByIdDesc();
	}

	@Override
	public DemandeInter addDemandeInter(DemandeInter d) {
		return dRepo.save(d);
	}

}
