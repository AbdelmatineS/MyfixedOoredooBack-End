package tn.ooredoo.prospection.service;


import java.util.List;

import tn.ooredoo.prospection.entity.Activation;

public interface ActivationService {
    public Activation add (Activation activation);
    public Activation getList(Integer id);
    public List<Activation> getAllList();
    public Activation update (Activation activation,Integer id);
    public void delete(Integer id);
}
