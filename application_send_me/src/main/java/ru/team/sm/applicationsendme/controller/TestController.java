package ru.team.sm.applicationsendme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.sm.applicationsendme.dao.abstracts.UserDao;
import ru.team.sm.applicationsendme.dao.abstracts.dto.UserDtoDao;
import ru.team.sm.applicationsendme.model.user.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserDtoDao userDtoDao;
    private final UserDao userDao;

    @Autowired
    public TestController(UserDtoDao userDtoDao, UserDao userDao) {
        this.userDtoDao = userDtoDao;
        this.userDao = userDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userDtoDao.getAll());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getOnlyById(@PathVariable Integer id){
        return ResponseEntity.ok(userDtoDao.getUserById(id).orElseThrow());
    }

}
