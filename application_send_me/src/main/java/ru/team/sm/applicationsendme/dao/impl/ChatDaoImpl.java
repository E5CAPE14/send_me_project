package ru.team.sm.applicationsendme.dao.impl;

import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.ChatDao;
import ru.team.sm.applicationsendme.dao.impl.util.SingleResultUtil;
import ru.team.sm.applicationsendme.model.chat.Chat;

import java.util.Optional;


@Repository
public class ChatDaoImpl extends ReadWriteDaoImpl<Chat,Integer> implements ChatDao {

}
