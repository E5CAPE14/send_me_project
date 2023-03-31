package ru.team.sm.applicationsendme.dao.impl.dto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.dto.MessageDtoDao;
import ru.team.sm.applicationsendme.dao.abstracts.dto.pagination.PaginationDtoElement;
import ru.team.sm.applicationsendme.dao.impl.ReadOnlyDaoImpl;
import ru.team.sm.applicationsendme.model.chat.Chat;
import ru.team.sm.applicationsendme.model.chat.dto.MessageDto;

import java.util.List;
import java.util.Map;

@Repository
public class MessageDtoDaoImpl extends ReadOnlyDaoImpl<MessageDto,Integer> implements MessageDtoDao, PaginationDtoElement<MessageDto> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageDto> getAllMessageByChat(Chat chat) {
        return entityManager.createQuery("select new ru.team.sm.applicationsendme.model.chat.dto.MessageDto(" +
                        "message.id," +
                        "message.message," +
                        "message.userSender.login," +
                        "message.chat.id," +
                        "message.timeRegistration)" +
                        " from Message message where message.chat.id = :chat_id" +
                        " group by message.id order " +
                        "by message.timeRegistration desc ",MessageDto.class)
                .setParameter("chat_id",chat.getId())
                .getResultList();
    }

    @Override
    public List<MessageDto> getItems(Map<String, Object> param) {
        int currentPageNumber = (int) param.get("currentPage");//Текущая страница
        int itemsOnPage = (int) param.get("pageItems");//Элементов на странице
        int chatId = (int) param.get("chatId");//Идентификатор чата

        return entityManager.createQuery(
                        "SELECT  new ru.team.sm.applicationsendme.model.chat.dto.MessageDto" +
                                "(message.id," +
                                "message.message," +
                                "message.userSender.login," +
                                "message.chat.id," +
                                "message.timeRegistration)" +
                                "FROM Message message JOIN FETCH User u ON message.userSender.id = u.id " +
                                "WHERE message.chat.id = :chatId " +
                                "ORDER BY message.timeRegistration DESC", MessageDto.class)
                .setParameter("chatId", chatId)
                .setFirstResult((currentPageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        int chatId = (int) param.get("chatId");
        return ((Long) entityManager.createQuery("SELECT COUNT(m) FROM Message m where m.chat.id = :chatId")
                .setParameter("chatId", chatId)
                .getSingleResult())
                .intValue();
    }

}
