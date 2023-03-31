package ru.team.sm.applicationsendme.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.SingleChatDao;
import ru.team.sm.applicationsendme.dao.impl.util.SingleResultUtil;
import ru.team.sm.applicationsendme.model.chat.SingleChat;

import java.util.Optional;
@Repository
public class SingleChatDaoImpl extends ReadWriteDaoImpl<SingleChat,Integer> implements SingleChatDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void deleteByChatId(Integer id, Integer userId) {
        entityManager.createQuery(
                        "UPDATE SingleChat SET " +
                                "deletedByUserOne = CASE WHEN userOne.id = :userId THEN true ELSE deletedByUserOne END, " +
                                "deletedByUserTwo = CASE WHEN userTwo.id = :userId THEN true ELSE deletedByUserTwo END " +
                                "WHERE chat.id = :id")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public Optional<SingleChat> getByUsersIds(Integer userOneId, Integer userTwoId) {
        TypedQuery<SingleChat> singleChat = entityManager.createQuery("SELECT sc FROM SingleChat sc JOIN FETCH sc.userOne,sc.userTwo" +
                        " WHERE " +
                        "(sc.userOne.id = :userOneId and sc.userTwo.id = :userTwoId)" +
                        "OR " +
                        "(sc.userOne.id = :userTwoId and sc.userTwo.id = :userOneId)" +
                        "GROUP BY sc.id",SingleChat.class)
                .setParameter("userOneId",userOneId)
                .setParameter("userTwoId",userTwoId);

        return SingleResultUtil.getSingleResultOrNull(singleChat);
    }
    @Override
    public Optional<Integer> getCharIdByUserIds(Integer userOneId, Integer userTwoId){
        return Optional.of((Integer) entityManager.createNativeQuery("SELECT sc.chat_id FROM single_chat as sc " +
                "WHERE (sc.user_one_id = :userOne AND sc.user_two_id = :userTwo) or (sc.user_one_id = :userTwo and sc.user_two_id = :userOne)",Integer.class)
                .setParameter("userOne", userOneId)
                .setParameter("userTwo", userTwoId)
                .getSingleResult());
    }

    @Override
    public int existChatByUsers(Integer userOneId, Integer userTwoId) {
        return ((Long) entityManager.createNativeQuery("SELECT count(sc) FROM single_chat as sc " +
                "WHERE (sc.user_one_id = :userOne AND sc.user_two_id = :userTwo) or (sc.user_one_id = :userTwo and sc.user_two_id = :userOne)")
                .setParameter("userOne", userOneId)
                .setParameter("userTwo", userTwoId)
                .getSingleResult())
                .intValue();
    }
}
