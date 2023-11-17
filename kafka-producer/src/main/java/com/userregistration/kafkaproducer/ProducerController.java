package com.userregistration.kafkaproducer;

import com.userregistration.commons.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class ProducerController {
    private final PublisherService producer;

    @Autowired
    public ProducerController(PublisherService producer) {
        this.producer = producer;
    }

    @PostMapping("/register")
    public void sendMessageToKafkaTopic(@RequestBody UserRegistrationEvent usrRegEvent) {
        this.producer.publishUserRegEvent(usrRegEvent);
    }


}