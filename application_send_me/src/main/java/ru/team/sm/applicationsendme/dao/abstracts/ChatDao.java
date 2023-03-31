package ru.team.sm.applicationsendme.dao.abstracts;

import ru.team.sm.applicationsendme.model.chat.Chat;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface ChatDao extends ReadWriteDao<Chat,Integer>{
}