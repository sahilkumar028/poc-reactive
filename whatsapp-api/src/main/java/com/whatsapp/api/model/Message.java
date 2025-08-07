package com.whatsapp.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime timestamp;

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp=(timestamp==null)?LocalDateTime.now():timestamp;
    }
}
