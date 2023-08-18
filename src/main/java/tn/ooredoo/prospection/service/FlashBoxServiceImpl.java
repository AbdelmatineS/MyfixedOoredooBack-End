package tn.ooredoo.prospection.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.FlashBox;
import tn.ooredoo.prospection.repository.FlashBoxRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlashBoxServiceImpl implements FlashBoxService{
    @Autowired
    private FlashBoxRepo flashBoxRepo;
    @Override
    public FlashBox add(FlashBox flashBox) {

        return flashBoxRepo.save(flashBox);
    }
    @Override
    public List<FlashBox> findByMsisdnStartingWith(String prefix) {
        return flashBoxRepo.findByMsisdnStartsWith(prefix);
    }
    @Override
    public FlashBox findByMsisdn(String msisdn) {
        return flashBoxRepo.findByMsisdn(msisdn);
    }

    @Override
    public List<String> getAvailableMsisdns() {
        List<FlashBox> allFixeJdids = flashBoxRepo.findAll();
        List<String> availableMsisdns = new ArrayList<>();

        for (FlashBox flashBox : allFixeJdids) {
            if (isMsisdnAvailable(flashBox.getMsisdn())) {
                availableMsisdns.add(flashBox.getMsisdn());
            }
        }

        return availableMsisdns;
    }

    private boolean isMsisdnAvailable(String msisdn) {
        return msisdn != null && !msisdn.isEmpty();

    }
}
