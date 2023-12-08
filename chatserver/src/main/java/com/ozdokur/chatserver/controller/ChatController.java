package com.ozdokur.chatserver.controller;

import com.ozdokur.chatserver.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

@MessageMapping("/message")
@SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-massage")
    public Message receivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        return message;
    }
}
