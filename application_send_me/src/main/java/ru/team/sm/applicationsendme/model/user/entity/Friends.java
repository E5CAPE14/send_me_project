package ru.team.sm.applicationsendme.model.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friends_entity")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private User userTwo;

    private LocalDateTime time_registration;

    public Friends(User user_id, User user_id_two) {
        this.user = user_id;
        this.userTwo = user_id_two;
        time_registration = LocalDateTime.now();
    }
}
