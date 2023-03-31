package ru.team.sm.applicationsendme.model.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "message",nullable = false,length = 2048)
    private String message;
    @OneToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    private User userSender;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Chat.class)
    private Chat chat;
    @Column(nullable = false,updatable = false,name = "time_registration")
    @CreatedDate
    private LocalDateTime timeRegistration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Message message = (Message) o;
        return id != null && Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
