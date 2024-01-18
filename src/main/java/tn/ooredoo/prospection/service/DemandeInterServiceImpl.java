package tn.ooredoo.prospection.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.DTO.NotificationMessage;
import tn.ooredoo.prospection.entity.Activation;
import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.entity.NotificationToken;
import tn.ooredoo.prospection.entity.Reservation;
import tn.ooredoo.prospection.entity.UserAdmin;
import tn.ooredoo.prospection.entity.UserSousTraitant;
import tn.ooredoo.prospection.repository.ActivationRepo;
import tn.ooredoo.prospection.repository.DemandeInterRepository;
import tn.ooredoo.prospection.repository.ReservationRepository;
import tn.ooredoo.prospection.repository.UserAdminRepository;
import tn.ooredoo.prospection.repository.UserStRepository;

@Service
public class DemandeInterServiceImpl implements IDemandeInterService{
	
    private static final Logger logger = LoggerFactory.getLogger(DemandeInterServiceImpl.class);

	@Autowired
	UserAdminRepository uaRepo;
	
	@Autowired
	UserStRepository usRepo;

	@Autowired
	DemandeInterRepository dRepo;
	
	@Autowired
	ReservationRepository rRepo;
	
	@Autowired
	ActivationRepo aRepo;
	
	@Autowired
	NotificationMessagingService nms;
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(DemandeInterServiceImpl.class);

	
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
	public List<DemandeInter> retrieveAllDemandeInterByUserSt(Long userId) {
		return dRepo.findByUserstId(userId);
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
	
//	@Override
//	public DemandeInter addDemandeTest(DemandeInterRequest d) {
//		
//		
//		DemandeInter demande = new DemandeInter();
//		
//		demande.setAbonnement(d.getAbonnement());
//		demande.setCategory(d.getCategory());
//		demande.setType(d.getType());
//		demande.setType(d.getType());
//		demande.setDateDemande(LocalDateTime.now());
//
//		
//		
//
//		return dRepo.save(demande);
//	}

//	@Override
//	public DemandeInter addDemandeInterRes(Long adminId, Long ssId,Long  resId, DemandeInter d) {
////		DemandeInter demande = new DemandeInter();
////		
////		demande.setAbonnement(d.getAbonnement());
////		demande.setCategory(d.getCategory());
////		demande.setType(d.getType());
////		demande.setType(d.getType());
//		dRepo.save(d);
//
//		UserAdmin admin = uaRepo.findById(adminId).orElse(null);
//		UserSousTraitant userst = usRepo.findById(ssId).orElse(null);
//		Reservation r = rRepo.findById(resId).orElse(null);
//
////		LOGGER.info("Admin: {}", admin); 
////		LOGGER.info("Sous Traitant: {}", userst); 
////		LOGGER.info("Reservation: {}", r); 
////		
////		System.out.println("Admin: {"+admin+"}");
////		System.out.println("Sous Traitant: {"+userst+"}");
////		System.out.println("Reservation: {"+r+"}");
//
//
//		//rRepo.save(r);
//		r.setDemande_res(d);
//		r.setUsera(admin);
//		r.setStatus("Affecté");
//		d.setReservation(r);
//		d.setUsera(admin);
//		d.setUserst(userst);
//		d.setDateDemande(LocalDateTime.now());	
//		rRepo.save(r);
//		return dRepo.save(d);
//	}
	
	
	@Override
	public DemandeInter affecterDemandeInterRes(Long demandeId, Long resId) {
		DemandeInter demande = dRepo.findById(demandeId).orElse(null);
		Reservation res = rRepo.findById(resId).orElse(null);
		
		return (demande);
	}

//	@Override
//	public DemandeInter addDemandeTest(DemandeInterRequest d) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
    @Transactional
    public DemandeInter updateDatePlanif(Long demandeId, LocalDateTime newDatePlanif) {
        Optional<DemandeInter> demandeOptional = dRepo.findById(demandeId);
        if (demandeOptional.isPresent()) {
            DemandeInter demande = demandeOptional.get();
            demande.setDatePlanif(newDatePlanif);
            demande.getActivation().setStatus("PLANNED");
            
 
                final String title = "Affectation Demande";

                final String body = "Votre contract numéro: "
            			+demande.getActivation().getFix().getContractNum()
            			+"vous à été affecté.\n prière de planifié une date avec le client pendant 48h ou elle sera retiré de votre responsabilité.";
                final List<NotificationToken> tokens = demande.getUserst().getNotTokens();
        
                for (NotificationToken token : tokens) {
                	logger.info("token value: "+token);
                    NotificationMessage not_message = new NotificationMessage();
                    not_message.setRecipientToken(token.getToken());
                    not_message.setTitle(title);
                    not_message.setBody(body);
                    nms.sendNotificationByToken(not_message);
                }

            
            return dRepo.save(demande);
        } else {
            throw new EntityNotFoundException("DemandeInter not found with id: " + demandeId);
        }
    }
    
    

    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void checkDatePlanif() {
        List<DemandeInter> demandes = dRepo.findByStatus("SET");
        final String title = "Dépassement du délai.";

        for (DemandeInter demande : demandes) {
            LocalDateTime datePlanif = demande.getDatePlanif();
            LocalDateTime currentDateTime = LocalDateTime.now();
            
            if (datePlanif != null) {
                long hoursDifference = ChronoUnit.HOURS.between(datePlanif, currentDateTime);
                
                if (hoursDifference > 48) {
                    Activation act = demande.getActivation();
                    if(act != null) {
                        String contractNum = act.getFix().getContractNum();
                        if (contractNum != null) {
                        	List<NotificationToken> notTokens = demande.getUserst().getNotTokens();
                        	final String body = "Le numéro de demande: "
                        			+demande.getActivation().getFix().getContractNum()
                        			+" affecté le "+demande.getDateDemande()+" à votre société a été retiré de votre responsabilité suite au dépassement de l'échéance de planification de 48 heures avec votre client.";
                            if (notTokens != null && !notTokens.isEmpty()) {
                                for (NotificationToken token : notTokens) {
                                	
                                	NotificationMessage not_message = new NotificationMessage();
                                    not_message.setRecipientToken(token.getToken());
                                    not_message.setTitle(title);
                                    not_message.setBody(body);
                                    
                                    nms.sendNotificationByToken(not_message);
                                    demande.setUserst(null);
                                    //demande.setDatePlanif(null);
                                }
                            }                        
                      }
                    }
                }
            }
        }
    }

	@Override
	public DemandeInter addDemandeInterAct(Long adminId, Long ssId, Integer actId, DemandeInter d) {
		dRepo.save(d);

		UserAdmin admin = uaRepo.findById(adminId).orElse(null);
		UserSousTraitant userst = usRepo.findById(ssId).orElse(null);
		Activation a = aRepo.findById(actId).orElse(null);


		a.setDemande_act(d);
		a.setUsera(admin);
		a.setStatus("SET");
		d.setActivation(a);
		d.setUsera(admin);
		d.setUserst(userst);
		d.setDateDemande(LocalDateTime.now());	
		aRepo.save(a);
		return dRepo.save(d);
	}

	@Override
	public DemandeInter addDemandeInterRes(Long adminId, Long ssId, Long resId, DemandeInter d) {
		// TODO Auto-generated method stub
		return null;
	}



    



}
