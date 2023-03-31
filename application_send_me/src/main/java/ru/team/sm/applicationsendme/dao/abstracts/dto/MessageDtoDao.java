package ru.team.sm.applicationsendme.dao.abstracts.dto;

import ru.team.sm.applicationsendme.dao.abstracts.ReadOnlyDao;
import ru.team.sm.applicationsendme.model.chat.Chat;
import ru.team.sm.applicationsendme.model.chat.Message;
import ru.team.sm.applicationsendme.model.chat.dto.MessageDto;

import java.util.List;

public interface MessageDtoDao extends ReadOnlyDao<MessageDto,Integer> {

    List<MessageDto> getAllMessageByChat(Chat chat);

}
