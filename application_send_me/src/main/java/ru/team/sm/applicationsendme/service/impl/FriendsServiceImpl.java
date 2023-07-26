package ru.team.sm.applicationsendme.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.dao.abstracts.FriendsDao;
import ru.team.sm.applicationsendme.dao.abstracts.UserDao;
import ru.team.sm.applicationsendme.model.user.dto.FriendsDto;
import ru.team.sm.applicationsendme.model.user.entity.Friends;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.abstracts.FriendsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class FriendsServiceImpl extends ReadWriteServiceImpl<Friends,Integer> implements FriendsService {

    private final FriendsDao friendsDao;
    private final UserDao userDao;
    public FriendsServiceImpl(FriendsDao friendsDao, UserDao userDao) {

        super(friendsDao);

        this.friendsDao = friendsDao;
        this.userDao = userDao;
    }

    @Override
    public List<FriendsDto> getFriendsDto() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Friends> friends = friendsDao.getFriends(user.getId());
        List<FriendsDto> friendsDto = new ArrayList<>();

        for (Friends el:friends) {
            friendsDto.add(new FriendsDto(el,user));
        }

        return friendsDto;
    }
    @Override
    public void addFriendsForCurrentUser(User userTwo){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!userDao.existsById(userTwo.getId())){
            throw new UsernameNotFoundException("Пользователя которого вы хотите добавить не существует");
        }

        if(friendsDao.existFriend(user.getId(),userTwo.getId())) {
            throw new RuntimeException("Друг уже добавлен!");
        }

        Friends friends = new Friends();
        friends.setUser(user);
        friends.setUserTwo(userTwo);
        friends.setTime_registration(LocalDateTime.now());
        friendsDao.persist(friends);
    }

}
