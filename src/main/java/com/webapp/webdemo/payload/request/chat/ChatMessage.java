package com.webapp.webdemo.payload.request.chat;

import com.webapp.webdemo.constants.enums.MessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
}
