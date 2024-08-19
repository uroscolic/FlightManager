package com.flightmanager.UserService.listener;


import com.flightmanager.UserService.dto.IncrementBookCountDto;
import com.flightmanager.UserService.service.IUserService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class IncrementBookCountListener {

    private MessageHelper messageHelper;
    private IUserService userService;

    public IncrementBookCountListener(MessageHelper messageHelper, IUserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.incrementBookCount}", concurrency = "5-10")
    public void incrementReservationCount(Message message) throws JMSException {
        IncrementBookCountDto incrementReservationCountDto = messageHelper.getMessage(message, IncrementBookCountDto.class);
        System.out.println(incrementReservationCountDto);
        userService.incrementReservationCount(incrementReservationCountDto);
    }

}
