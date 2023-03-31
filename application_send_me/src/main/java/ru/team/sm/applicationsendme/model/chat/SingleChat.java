package ru.team.sm.applicationsendme.model.chat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.exception.ApiRequestException;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "single_chat")
public class SingleChat{
    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @MapsId
    private Chat chat = new Chat(ChatType.SINGLE);

    @ManyToOne(fetch = FetchType.LAZY)
    private User userOne;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userTwo;

    @NotNull
    @Column(name = "deleted_by_user_one")
    private boolean deletedByUserOne;

    @NotNull
    @Column(name = "deleted_by_user_two")
    private boolean deletedByUserTwo;

    @PrePersist
    private void prePersistFunction() {
        checkConstraints();
    }

    @PreUpdate
    private void preUpdateFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
        if (this.chat.getChatType() != ChatType.SINGLE) {
            throw new ApiRequestException("У экземпляра Chat, связанного с SingleChat, " +
                    "поле chatType должно принимать значение ChatType.SINGLE");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleChat that = (SingleChat) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(chat, that.chat) &&
                Objects.equals(userOne, that.userOne) &&
                Objects.equals(userTwo, that.userTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chat, userOne, userTwo);
    }
}