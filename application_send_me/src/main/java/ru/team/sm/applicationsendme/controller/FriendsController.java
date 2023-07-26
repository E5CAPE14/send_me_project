package ru.team.sm.applicationsendme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.team.sm.applicationsendme.model.user.entity.Friends;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.abstracts.FriendsService;
import ru.team.sm.applicationsendme.service.abstracts.UserService;

@RestController
@RequestMapping("/api/friends/")
public class FriendsController {

    private final FriendsService friendsService;
    private final UserService userService;


    public FriendsController(FriendsService friendsService, UserService userService) {
        this.friendsService = friendsService;
        this.userService = userService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestBody Integer userId){
        User user = userService.getById(userId).orElseThrow( () -> new UsernameNotFoundException("Пользователь не найден!"));

        friendsService.addFriendsForCurrentUser(user);
        return ResponseEntity.ok("Пользователь добавлен в ваш список друзей!");
    }

    @GetMapping()
    public ResponseEntity<?> getFriends(){
        return ResponseEntity.ok(friendsService.getFriendsDto());
    }



}
