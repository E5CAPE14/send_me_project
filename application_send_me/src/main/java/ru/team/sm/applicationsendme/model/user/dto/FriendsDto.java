package ru.team.sm.applicationsendme.model.user.dto;

import lombok.Data;
import ru.team.sm.applicationsendme.model.user.entity.Friends;
import ru.team.sm.applicationsendme.model.user.entity.User;

@Data
public class FriendsDto {

    private UserDto user;


    public FriendsDto(Friends friends, User user) {

        if(user.equals(friends.getUser())){

            this.user = new UserDto(friends.getUserTwo());

        } else if(user.equals(friends.getUserTwo())){

            this.user = new UserDto(friends.getUser());

        }
    }
}
