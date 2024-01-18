package tn.ooredoo.prospection.service;

import java.time.LocalDateTime;
import java.util.List;

import tn.ooredoo.prospection.entity.DemandeInter;


public interface IDemandeInterService {
	
	public DemandeInter addDemandeInterRes(Long adminId, Long ssId,Long resId, DemandeInter d);
	public DemandeInter addDemandeInterAct(Long adminId, Long ssId,Integer actId, DemandeInter d);

	public DemandeInter retrieveDemandeInterById(Long id);
	public List<DemandeInter> retrieveAllDemandeInter();
	public DemandeInter addPlanDemandeInter(Long id, LocalDateTime date);
	public DemandeInter updateDemandeInter(DemandeInter d, Long id);
	public void deleteDemandeInter(Long id);
	public DemandeInter getLastDemandeInter();
    DemandeInter updateDatePlanif(Long demandeId, LocalDateTime newDatePlanif);
	public DemandeInter affecterDemandeInterRes(Long demandeId,Long  resId);
	public List<DemandeInter> retrieveAllDemandeInterByUserSt(Long userId);


}
