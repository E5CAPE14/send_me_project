package ru.team.sm.applicationsendme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.dao.abstracts.MessageDao;
import ru.team.sm.applicationsendme.model.chat.Message;
import ru.team.sm.applicationsendme.service.abstracts.MessageService;

@Service
public class MessageServiceImpl extends ReadWriteServiceImpl<Message, Integer> implements MessageService {

    @Autowired
    public MessageServiceImpl(MessageDao messageDao, MessageDao messageDao1) {
        super(messageDao);
    }

}
