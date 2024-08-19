package com.flightmanager.UserService.listener;


import com.flightmanager.UserService.dto.DecrementBookCountDto;
import com.flightmanager.UserService.service.IUserService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

@Component
public class DecrementBookCountListener {

    private MessageHelper messageHelper;
    private IUserService userService;

    public DecrementBookCountListener(MessageHelper messageHelper, IUserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.decrementBookCount}", concurrency = "5-10")
    public void decrementReservationCount(Message message) throws JMSException {
        DecrementBookCountDto decrementReservationCountDto = messageHelper.getMessage(message, DecrementBookCountDto.class);
        System.out.println(decrementReservationCountDto);
        userService.decrementReservationCount(decrementReservationCountDto);
    }

}
