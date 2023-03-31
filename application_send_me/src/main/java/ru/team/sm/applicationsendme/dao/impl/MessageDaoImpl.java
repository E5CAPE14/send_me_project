package ru.team.sm.applicationsendme.dao.impl;

import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.MessageDao;
import ru.team.sm.applicationsendme.model.chat.Message;

@Repository
public class MessageDaoImpl extends ReadWriteDaoImpl<Message,Integer> implements MessageDao {
}
