package ru.team.sm.applicationsendme.service.abstracts.dto;


import ru.team.sm.applicationsendme.model.chat.dto.PageDto;

import java.util.Map;

public interface PaginationServiceDto<T> {
    PageDto<T> getPageDto(int currentPageNumber, int itemsOnPage, Map<String, Object> param);
}