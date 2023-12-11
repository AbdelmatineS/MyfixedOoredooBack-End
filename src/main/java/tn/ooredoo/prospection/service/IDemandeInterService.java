package tn.ooredoo.prospection.service;

import java.time.LocalDateTime;
import java.util.List;

import tn.ooredoo.prospection.entity.DemandeInter;
import tn.ooredoo.prospection.paylaod.request.DemandeInterRequest;


public interface IDemandeInterService {
	
	public DemandeInter addDemandeInterRes(Long adminId, Long ssId,Long resId, DemandeInter d);

	
	public DemandeInter addDemandeTest(DemandeInterRequest d);
	
	public DemandeInter retrieveDemandeInterById(Long id);
	public List<DemandeInter> retrieveAllDemandeInter();
	public DemandeInter addPlanDemandeInter(Long id, LocalDateTime date);
	public DemandeInter updateDemandeInter(DemandeInter d, Long id);
	public void deleteDemandeInter(Long id);
	public DemandeInter getLastDemandeInter();
}
