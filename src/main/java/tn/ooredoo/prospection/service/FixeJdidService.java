package tn.ooredoo.prospection.service;


import java.util.List;

import tn.ooredoo.prospection.entity.FixeJdid;
import tn.ooredoo.prospection.entity.Reservation;

public interface FixeJdidService {
    public FixeJdid addFixeJdid (FixeJdid fixeJdid);
    FixeJdid add(FixeJdid fixeJdid);

    FixeJdid findByMsisdn(String msisdn);

    List<FixeJdid> findByMsisdnStartingWith(String prefix);

    List<String> getAvailableMsisdns();
    FixeJdid getById(Integer id);
	void deleteF(Integer id);
	public FixeJdid getLastFix();

}


