package ru.team.sm.applicationsendme.service.abstracts;

import ru.team.sm.applicationsendme.model.chat.SingleChat;
import ru.team.sm.applicationsendme.model.chat.dto.ChatDto;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.util.List;
import java.util.Optional;



public interface SingleChatService extends ReadWriteService<SingleChat, Integer>{
    List<ChatDto> getAllChatDto();

    void deleteByChatId(Integer id);
    void addSingleChatAndMessage(SingleChat singleChat, String message);
    Optional<SingleChat> getByUsersIds(Integer userOneId,Integer userTwoId);

    Integer getChatIdByUserIds(Integer userOneId, Integer userTwoId);

    void sendMessageToChatId(String messageText, Integer chat_id);

    void sendMessage(String messageText, Integer user_id);

    boolean existSingleChatByUsers(User userOneId, User userTwoId);
}
