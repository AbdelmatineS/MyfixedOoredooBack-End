package tn.ooredoo.prospection.service;

import java.util.List;

import tn.ooredoo.prospection.DTO.ProspectionDTO;
import tn.ooredoo.prospection.entity.Prospection;


public interface IProspectionService {
	
	public Prospection retrieveProspectionById(long id);
	public List<Prospection> retrieveAllByUser(long id);
	public Prospection addProspection(Long id, Prospection p);
	public Prospection updateProspection(Prospection p, long id);
	public void deleteProspection(long id);
	public List<Prospection> getAll();
	public List<Prospection> getProspectionByNumID(Long numID);
	 public List<Prospection> searchEntities(String attribute, String query);
}
