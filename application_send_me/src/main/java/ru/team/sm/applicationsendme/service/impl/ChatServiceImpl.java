package ru.team.sm.applicationsendme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.dao.abstracts.ChatDao;
import ru.team.sm.applicationsendme.model.chat.Chat;
import ru.team.sm.applicationsendme.service.abstracts.ChatService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl extends ReadWriteServiceImpl<Chat,Integer> implements ChatService {

    public final ChatDao chatDao;

    @Autowired
    public ChatServiceImpl(ChatDao chatDao) {
        super(chatDao);
        this.chatDao = chatDao;
    }
}
