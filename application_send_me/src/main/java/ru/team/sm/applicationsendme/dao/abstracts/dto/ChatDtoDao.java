package ru.team.sm.applicationsendme.dao.abstracts.dto;

import ru.team.sm.applicationsendme.dao.abstracts.ReadWriteDao;
import ru.team.sm.applicationsendme.model.chat.Chat;
import ru.team.sm.applicationsendme.model.chat.dto.ChatDto;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.util.Optional;

public interface ChatDtoDao extends ReadWriteDao<ChatDto,Integer> {

    Optional<ChatDto> getChatByUser(User user);

}
