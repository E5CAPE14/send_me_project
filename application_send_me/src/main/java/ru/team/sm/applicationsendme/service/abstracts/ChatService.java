package ru.team.sm.applicationsendme.service.abstracts;

import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.model.chat.Chat;

@Service
public interface ChatService extends ReadWriteService<Chat, Integer>{

}
