package ru.team.sm.applicationsendme.model.user.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class UserDto {
    @NonNull
    private Integer id;
    @NonNull
    private String email;

    private String linkImage;
    @NonNull
    private String login;
    @NonNull
    private LocalDateTime timeOfRegistry;

    public UserDto(@NonNull Integer id, @NonNull String email, @NonNull String login, @NonNull LocalDateTime timeOfRegistry) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.timeOfRegistry = timeOfRegistry;
        this.linkImage = "user_default.jpg";
    }

    public UserDto(@NotNull Integer id,
                   @NonNull String email,
                   String linkImage,
                   @NonNull String login,
                   @NonNull LocalDateTime timeOfRegistry) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.linkImage = linkImage;
        this.timeOfRegistry = timeOfRegistry;
    }

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.linkImage = user.getLinkImage();
        this.timeOfRegistry = user.getTimeOfRegistry();
    }



}
