package ru.team.sm.applicationsendme.dao.abstracts.dto.pagination;

import java.util.List;
import java.util.Map;

//Интерфейс, который нужно имплементировать при реализации конкретной пагинации
public interface PaginationDtoElement<T> {
    List<T> getItems(Map<String, Object> param);
    int getTotalResultCount(Map<String, Object> param);
}