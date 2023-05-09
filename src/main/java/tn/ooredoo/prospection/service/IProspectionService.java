package tn.ooredoo.prospection.service;

import java.util.List;

import tn.ooredoo.prospection.entity.Prospection;


public interface IProspectionService {
	
	public Prospection retrieveProspectionById(long id);
	public List<Prospection> retrieveAll();
	public Prospection addProspection(Prospection p);
	public Prospection updateProspection(Prospection p, long id);
	public void deleteProspection(long id);
	public List<Prospection> getAll();
}
