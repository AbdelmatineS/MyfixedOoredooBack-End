package tn.ooredoo.prospection.service;



import java.util.List;

import tn.ooredoo.prospection.entity.FlashBox;

public interface FlashBoxService {
    public FlashBox add (FlashBox flashBox);
    FlashBox findByMsisdn(String msisdn);

    List<FlashBox> findByMsisdnStartingWith(String prefix);


    List<String> getAvailableMsisdns();
}
