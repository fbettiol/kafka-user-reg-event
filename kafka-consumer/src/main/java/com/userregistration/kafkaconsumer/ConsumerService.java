package com.userregistration.kafkaconsumer;


import com.userregistration.commons.Constants;
import com.userregistration.commons.UserRegistrationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = Constants.USER_REG_TOPIC, groupId = "reg", containerFactory = "userRegListener")
    public void consume(UserRegistrationEvent usrReg) {
        log.info(String.format("Consumed User reg event -> %s", usrReg.toString()));
    }
}

