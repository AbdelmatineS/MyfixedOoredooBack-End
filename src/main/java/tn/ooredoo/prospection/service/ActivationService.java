package tn.ooredoo.prospection.service;


import java.util.List;

import tn.ooredoo.prospection.entity.Activation;
import tn.ooredoo.prospection.entity.FixeJdid;
import tn.ooredoo.prospection.entity.Raccordement;
import tn.ooredoo.prospection.entity.Reglement;

public interface ActivationService {
    public Activation add (Activation activation);
    public Activation getList(Integer id);
    public List<Activation> getAllList();
    public Activation update (Activation activation,Integer id);
    public Activation addActivation(Long id, Activation a);
    public Reglement addReg(Integer id, Reglement reg);
    public Raccordement addRacc(Integer id, Raccordement r);
    public FixeJdid addfixe(Integer id, FixeJdid f);
    public void delete(Integer id);
	void deleteF(Integer id);
}
