package ru.team.sm.applicationsendme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.team.sm.applicationsendme.model.req.EditPasswordRequestDto;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.model.user.dto.UserDto;
import ru.team.sm.applicationsendme.service.abstracts.UserService;
import ru.team.sm.applicationsendme.service.abstracts.dto.UserDtoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;
    private final UserDtoService userDtoService;

    @Autowired
    public UserController(UserService userService, UserDtoService userDtoService) {
        this.userService = userService;
        this.userDtoService = userDtoService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        return ResponseEntity.ok(new UserDto((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false, defaultValue = "20") int items,
                                                     @RequestParam(required = false,defaultValue = "1") int currentPage){
        Map<String, Object> paginationMap = new HashMap<>();
        paginationMap.put("class", "userDtoDaoImpl");
        paginationMap.put("pageItems", items);
        paginationMap.put("currentPage", currentPage);

        return ResponseEntity.ok(userDtoService.getPageDto(items, currentPage, paginationMap).getItems());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(userDtoService.getUserById(userId));
    }

    @PutMapping("/edit/profile")
    public ResponseEntity<?> editCurrentUser(@RequestBody EditPasswordRequestDto editPasswordRequestDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getPassword().equals(editPasswordRequestDto.getCurrentPassword())) {
            return ResponseEntity.badRequest().body("Текущий пароль не верен");
        }
        userService.updatePasswordByEmail(user.getEmail(), editPasswordRequestDto.getEditPassword());
        return ResponseEntity.ok("Пароль изменен");
    }

}
