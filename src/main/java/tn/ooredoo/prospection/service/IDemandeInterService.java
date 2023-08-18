package tn.ooredoo.prospection.service;

import java.time.LocalDateTime;
import java.util.List;

import tn.ooredoo.prospection.entity.DemandeInter;


public interface IDemandeInterService {
	
	public DemandeInter addDemandeInter(DemandeInter d);
	public DemandeInter retrieveDemandeInterById(Long id);
	public List<DemandeInter> retrieveAllDemandeInter();
	public DemandeInter addPlanDemandeInter(Long id, LocalDateTime date);
	public DemandeInter updateDemandeInter(DemandeInter d, Long id);
	public void deleteDemandeInter(Long id);
	public DemandeInter getLastDemandeInter();
}