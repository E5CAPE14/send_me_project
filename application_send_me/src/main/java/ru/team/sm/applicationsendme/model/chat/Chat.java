package ru.team.sm.applicationsendme.model.chat;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(generator = "Chat_seq")
    private Integer id;

    @Column
    private String title;

    @Column(name = "persist_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime persistDate;

    @Enumerated
    @Column(columnDefinition = "int2")
    private ChatType chatType;

    @Column(name = "is_chat_pin")
    private Boolean isChatPin;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<>();


    public Chat(ChatType chatType) {
        this.chatType = chatType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id) && Objects.equals(title, chat.title) &&
                Objects.equals(persistDate, chat.persistDate) && chatType == chat.chatType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, persistDate, chatType);
    }
}