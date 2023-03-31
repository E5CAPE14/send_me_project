package ru.team.sm.applicationsendme.service.impl.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team.sm.applicationsendme.dao.abstracts.dto.pagination.PaginationDtoElement;
import ru.team.sm.applicationsendme.model.chat.dto.PageDto;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.abstracts.dto.PaginationServiceDto;
import ru.team.sm.applicationsendme.service.exception.PageException;

import java.util.Map;

@Service
public class PaginationServiceDtoImpl<T> implements PaginationServiceDto<T> {

    private Map<String, PaginationDtoElement<?>> map;
    @Autowired
    public void setMap(Map<String, PaginationDtoElement<?>> map) {
        this.map = map;
    }

    @Override
    @Transactional
    public PageDto<T> getPageDto(int currentPage, int items, Map<String, Object> param) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Здесь мы достаем нужную пагинацию, которая была помещена в контроллере по ключу class, и собираем PageDto
        if (currentPage < 0 || items < 0) {
            throw new PageException("currentPage or items less than 0");
        }
        if (!map.containsKey(param.get("class"))) {
            throw new PageException(param.get("class") + " is not a PaginationDto");
        }
        if (!param.containsKey("currentPage") || !param.containsKey("pageItems")) {
            throw new PageException("PaginationMap is not contain key currentPage or items");
        }
        param.put("userId", user.getId());
        PaginationDtoElement<T> dtoEl = (PaginationDtoElement<T>) map.get(param.get("class"));
        int totalResultCount = dtoEl.getTotalResultCount(param);
        int totalPageCount = totalResultCount%items == 0 ? totalResultCount/items : totalResultCount/items + 1;
        return new PageDto<> (currentPage, totalPageCount, totalResultCount, dtoEl.getItems(param), items);
    }
}