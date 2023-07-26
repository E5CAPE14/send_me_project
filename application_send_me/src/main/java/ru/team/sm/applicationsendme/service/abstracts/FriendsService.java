package ru.team.sm.applicationsendme.service.abstracts;

import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.model.user.dto.FriendsDto;
import ru.team.sm.applicationsendme.model.user.entity.Friends;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.util.List;

@Service
public interface FriendsService extends ReadWriteService<Friends,Integer>{
    List<FriendsDto> getFriendsDto();

    void addFriendsForCurrentUser(User userTwo);
}
