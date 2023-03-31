package ru.team.sm.applicationsendme.dao.abstracts;

import ru.team.sm.applicationsendme.model.chat.SingleChat;

import java.util.Optional;

public interface SingleChatDao extends ReadWriteDao<SingleChat,Integer>{

    void deleteByChatId(Integer id, Integer userId);
    Optional<SingleChat> getByUsersIds(Integer userOneId, Integer userTwoId);

    Optional<Integer> getCharIdByUserIds(Integer userOneId, Integer userTwoId);

    int existChatByUsers(Integer userOneId, Integer userTwoId);
}
