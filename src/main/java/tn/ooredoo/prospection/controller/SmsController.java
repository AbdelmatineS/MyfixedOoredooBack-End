package tn.ooredoo.prospection.controller;


import lombok.extern.slf4j.Slf4j;
import tn.ooredoo.prospection.entity.SMSrequest;
import tn.ooredoo.prospection.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ooredoo")
@Slf4j
public class SmsController {
    @Autowired
    private SmsService smsService;
    @PostMapping("/SMS")

    public String processSMS(@RequestBody SMSrequest smsrequest) {
        log.info("processSMS started sendRequest: " + smsrequest.toString());
        return smsService.sendSMS(smsrequest.getSmsMessages(), smsrequest.getDestinationSMSNumber());
    }

}
