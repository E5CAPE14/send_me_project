package ru.team.sm.applicationsendme.service.impl.dto;

import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.model.chat.dto.MessageDto;
import ru.team.sm.applicationsendme.model.chat.dto.PageDto;
import ru.team.sm.applicationsendme.service.abstracts.dto.MessageDtoService;

import java.util.Map;

@Service
public class MessageDtoServiceImpl extends PaginationServiceDtoImpl<MessageDto> implements MessageDtoService {
}
