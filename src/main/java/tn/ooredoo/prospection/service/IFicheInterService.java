package tn.ooredoo.prospection.service;

import java.util.List;

import tn.ooredoo.prospection.entity.FicheInter;

public interface IFicheInterService {
	
	public FicheInter addFicheInter(Long ssId, Long demandeId, FicheInter f);
	public List<FicheInter> retrieveAllFicheByDemande(Long demandeId);
	public List<FicheInter> retrieveAll();
	public List<FicheInter> retireveAllFicheByUserst(Long userId);
	

}
