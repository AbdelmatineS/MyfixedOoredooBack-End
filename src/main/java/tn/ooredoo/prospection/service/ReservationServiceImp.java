package tn.ooredoo.prospection.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.Reservation;
import tn.ooredoo.prospection.entity.UserConseiller;
import tn.ooredoo.prospection.repository.ReservationRepository;
import tn.ooredoo.prospection.repository.UserConRepository;

@Service
public class ReservationServiceImp implements IReservationService {
	
	
	@Autowired
	ReservationRepository rRepo;

	@Autowired
	UserConRepository uRepo;
	
	@Override
	public Reservation retrieveReservationById(Long id) {
		return rRepo.findById(id).get();
	}

	@Override
	public List<Reservation> retrieveAllReservation() {
		return (List<Reservation>) rRepo.findAll();
	}
	
	/*
	 	@Override
	public Prospection addProspection(Long userId, Prospection p) {
		pRepo.save(p);
		UserConseiller user = uRepo.findById(userId).orElse(null);

			p.setDateCreation(LocalDateTime.now());
			p.setDateDernièreModification(LocalDateTime.now());
			p.setUserc(user);
		

		return pRepo.save(p);
	}
	 */

	@Override
	public Reservation addReservation(Long userId, Reservation r) {
		rRepo.save(r);
		UserConseiller user = uRepo.findById(userId).orElse(null);
		
		r.setDateCreation(LocalDateTime.now());
		r.setDateDernièreModification(LocalDateTime.now());
		r.setUserc(user);
		return rRepo.save(r);
	}

	@Override
	public Reservation updateReservation(Reservation r, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReservation(Long id) {
		rRepo.deleteById(id);		
	}

	@Override
	public Reservation getLastReservation() {
		return rRepo.findTopByOrderByIdDesc();
	}

}





