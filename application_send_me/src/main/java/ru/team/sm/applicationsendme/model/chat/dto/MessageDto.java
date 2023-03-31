package ru.team.sm.applicationsendme.model.chat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.team.sm.applicationsendme.model.chat.Chat;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class MessageDto {

    private Integer id;
    private String message;
    private String userSender;
    private Integer chat_id;
    private LocalDateTime timeRegistration;

    public MessageDto(Integer id, String message, String userSender, Integer chat, LocalDateTime timeRegistration) {
        this.id = id;
        this.message = message;
        this.userSender = userSender;
        this.chat_id = chat;
        this.timeRegistration = timeRegistration;
    }
}
