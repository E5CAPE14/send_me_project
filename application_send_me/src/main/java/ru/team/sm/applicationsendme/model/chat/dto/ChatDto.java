package ru.team.sm.applicationsendme.model.chat.dto;

import lombok.*;
import ru.team.sm.applicationsendme.model.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private Integer id;

    private String title;

    private List<UserDto> users;

    private LocalDateTime time_creation;

}
