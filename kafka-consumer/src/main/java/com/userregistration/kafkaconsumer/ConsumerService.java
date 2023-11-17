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
        final StringBuilder logMsg = new StringBuilder();
        logMsg.append("\n----- >Consumed User reg event <-----\n");
        logMsg.append(String.format("Id: %s\n", usrReg.getUserId()));
        logMsg.append(String.format("UserName: %s\n", usrReg.getUsername()));
        logMsg.append(String.format("Email: %s\n", usrReg.getEmail()));
        logMsg.append(String.format("Date: %s\n", usrReg.getRegistrationDate().toString()));
        logMsg.append("\n\n");
        log.info(logMsg.toString());
    }
}

