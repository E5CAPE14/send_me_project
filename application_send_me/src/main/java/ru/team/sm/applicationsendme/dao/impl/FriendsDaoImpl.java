package ru.team.sm.applicationsendme.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.FriendsDao;
import ru.team.sm.applicationsendme.model.user.entity.Friends;

import java.util.List;
@Repository
public class FriendsDaoImpl extends ReadWriteDaoImpl<Friends,Integer> implements FriendsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Friends> getFriends(Integer user_id) {
        return entityManager.createQuery("SELECT f FROM Friends f WHERE f.user.id" +
                " = :user_id or f.userTwo.id = :user_id",Friends.class)
                .setParameter("user_id",user_id)
                .getResultList();
    }
    @Override
    public boolean existFriend(Integer user,Integer userTwo){
        return entityManager.createQuery("SELECT (count(f) > 0) FROM Friends f WHERE" +
                "(f.user.id = :user and f.userTwo.id = :userTwo) or " +
                "(f.user.id = :userTwo and f.userTwo.id = :user)",Boolean.class)
                .setParameter("user",user)
                .setParameter("userTwo",userTwo)
                .getSingleResult();
    }
}
