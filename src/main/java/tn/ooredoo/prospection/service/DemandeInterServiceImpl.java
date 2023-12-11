package tn.ooredoo.prospection.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.entity.Prospection;
import tn.ooredoo.prospection.entity.Reservation;
import tn.ooredoo.prospection.entity.UserAdmin;
import tn.ooredoo.prospection.entity.UserConseiller;
import tn.ooredoo.prospection.entity.UserSousTraitant;
import tn.ooredoo.prospection.paylaod.request.DemandeInterRequest;
import tn.ooredoo.prospection.repository.DemandeInterRepository;
import tn.ooredoo.prospection.repository.ReservationRepository;
import tn.ooredoo.prospection.repository.UserAdminRepository;
import tn.ooredoo.prospection.repository.UserStRepository;

@Service
public class DemandeInterServiceImpl implements IDemandeInterService{
	

	@Autowired
	UserAdminRepository uaRepo;
	
	@Autowired
	UserStRepository usRepo;

	@Autowired
	DemandeInterRepository dRepo;
	
	@Autowired
	ReservationRepository rRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemandeInterServiceImpl.class);

	
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

	/*@Override
	public DemandeInter addDemandeInter(DemandeInter d) {
		return dRepo.save(d);
	}*/
	
	@Override
	public DemandeInter addDemandeTest(DemandeInterRequest d) {
		
		
		DemandeInter demande = new DemandeInter();
		
		demande.setAbonnement(d.getAbonnement());
		demande.setCategory(d.getCategory());
		demande.setType(d.getType());
		demande.setType(d.getType());
		demande.setDateDemande(LocalDateTime.now());

		
		

		return dRepo.save(demande);
	}

	@Override
	public DemandeInter addDemandeInterRes(Long adminId, Long ssId,Long  resId, DemandeInter d) {
//		DemandeInter demande = new DemandeInter();
//		
//		demande.setAbonnement(d.getAbonnement());
//		demande.setCategory(d.getCategory());
//		demande.setType(d.getType());
//		demande.setType(d.getType());
		dRepo.save(d);

		UserAdmin admin = uaRepo.findById(adminId).orElse(null);
		UserSousTraitant userst = usRepo.findById(ssId).orElse(null);
		Reservation r = rRepo.findById(resId).orElse(null);

//		LOGGER.info("Admin: {}", admin); 
//		LOGGER.info("Sous Traitant: {}", userst); 
//		LOGGER.info("Reservation: {}", r); 
//		
//		System.out.println("Admin: {"+admin+"}");
//		System.out.println("Sous Traitant: {"+userst+"}");
//		System.out.println("Reservation: {"+r+"}");


		//rRepo.save(r);
		r.setDemande_res(d);
		r.setUser_a(admin);
		r.setStatus("Phase 2");
		d.setReservation(r);
		d.setUser_a(admin);
		d.setUser_st(userst);
		d.setDateDemande(LocalDateTime.now());	
		rRepo.save(r);
		return dRepo.save(d);
	}



}
