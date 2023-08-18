package tn.ooredoo.prospection.service;


import java.util.List;

import tn.ooredoo.prospection.entity.Maps;


public interface MapsService {
    public Maps addMaps(Maps maps);
    public Maps getMaps(Integer id);
    public List<Maps> getAllMaps();
    public Maps update (Maps maps,Integer id);
    public void delete(Integer id);

}
