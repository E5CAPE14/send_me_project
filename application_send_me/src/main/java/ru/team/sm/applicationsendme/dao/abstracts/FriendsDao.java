package ru.team.sm.applicationsendme.dao.abstracts;

import ru.team.sm.applicationsendme.model.user.entity.Friends;

import java.util.List;

public interface FriendsDao extends ReadWriteDao<Friends,Integer>{

    List<Friends> getFriends(Integer user_id);

    boolean existFriend(Integer user, Integer userTwo);
}
