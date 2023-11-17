package com.userregistration.kafkaproducer;


import com.userregistration.commons.Constants;
import com.userregistration.commons.UserRegistrationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherService {

    private final KafkaTemplate<String, UserRegistrationEvent> kafkaTemplate;

    @Autowired
    public PublisherService(KafkaTemplate<String, UserRegistrationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserRegEvent(UserRegistrationEvent userReg) {
        log.info(String.format("Producing user reg event --> %s", userReg.toString()));
        this.kafkaTemplate.send(Constants.USER_REG_TOPIC, userReg);
    }
}

