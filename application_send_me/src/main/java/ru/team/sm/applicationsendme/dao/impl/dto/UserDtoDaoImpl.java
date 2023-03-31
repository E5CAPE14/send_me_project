package ru.team.sm.applicationsendme.dao.impl.dto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.dto.UserDtoDao;
import ru.team.sm.applicationsendme.dao.abstracts.dto.pagination.PaginationDtoElement;
import ru.team.sm.applicationsendme.dao.impl.ReadOnlyDaoImpl;
import ru.team.sm.applicationsendme.model.chat.dto.MessageDto;
import ru.team.sm.applicationsendme.model.user.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl extends ReadOnlyDaoImpl<UserDto,Integer> implements UserDtoDao, PaginationDtoElement<UserDto> {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<UserDto> getItems(Map<String, Object> param) {
        int currentPageNumber = (int) param.get("currentPage");//Текущая страница
        int itemsOnPage = (int) param.get("pageItems");//Элементов на странице

        return entityManager.createQuery(
                        "SELECT  new ru.team.sm.applicationsendme.model.user.dto.UserDto" +
                                "(u.id," +
                                "u.email," +
                                "u.linkImage," +
                                "u.login," +
                                "u.timeOfRegistry)" +
                                "FROM User u where u.isEnable = true " +
                                "GROUP BY u.id", UserDto.class)
                .setFirstResult((currentPageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return ((Long) entityManager.createQuery("SELECT COUNT(u) FROM User u")
                .getSingleResult())
                .intValue();
    }

    @Override
    public Optional<UserDto> getUserById(Integer id) {
        return entityManager.createQuery(
                        "SELECT new ru.team.sm.applicationsendme.model.user.dto.UserDto " +
                                "(user.id, " +
                                "user.email, " +
                                "user.linkImage, " +
                                "user.login, " +
                                "user.timeOfRegistry) " +
                                "FROM User user " +
                                "WHERE user.id = :id " +
                                "AND user.isEnable = true " +
                                "GROUP BY user.id",
                        UserDto.class)
                .setParameter("id", id)
                .getResultStream()
                .findAny();
    }

    @Override
    public List<UserDto> getAll() {
        return entityManager.createQuery(
                "SELECT new ru.team.sm.applicationsendme.model.user.dto.UserDto " +
                "(user.id, " +
                "user.email, " +
                "user.linkImage, " +
                "user.login, " +
                "user.timeOfRegistry) " +
                "FROM User user " +
                "WHERE user.isEnable = true " +
                "GROUP BY user.id",
                UserDto.class).getResultList();
    }
}
