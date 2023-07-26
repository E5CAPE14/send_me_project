package ru.team.sm.applicationsendme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ru.team.sm.applicationsendme.model.chat.SingleChat;
import ru.team.sm.applicationsendme.model.chat.dto.MessageDto;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.abstracts.SingleChatService;
import ru.team.sm.applicationsendme.service.abstracts.UserService;
import ru.team.sm.applicationsendme.service.abstracts.dto.MessageDtoService;
import ru.team.sm.applicationsendme.service.exception.ChatException;
import ru.team.sm.applicationsendme.service.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final SingleChatService singleChatService;
    private final MessageDtoService messageDtoService;
    private final UserService userService;

    @Autowired
    public ChatController(SingleChatService singleChatService, MessageDtoService messageDtoService, UserService userService) {
        this.singleChatService = singleChatService;
        this.messageDtoService = messageDtoService;
        this.userService = userService;
    }

    @GetMapping("/single/all")
    public ResponseEntity<?> getAllSingleCharDto(){
        return ResponseEntity.ok(singleChatService.getAllChatDto());
    }

    @PostMapping("/single/create/")
    public ResponseEntity<?> createSingleChatByUser(@RequestBody Integer id){

        User user = userService.getById(id)
                .orElseThrow(UserNotFoundException::new);

        SingleChat singleChat = new SingleChat();
        singleChat.setUserTwo(user);
        singleChatService.addSingleChatAndMessage(singleChat,"Пользователь создал одиночный чат.");

        return ResponseEntity.ok("Чат создан.");
    }

    @PostMapping("/single/sendToUser/{user_id}")
    public ResponseEntity<?> sendMessageToUser(@RequestBody String message,
                                         @PathVariable Integer user_id){
        singleChatService.sendMessage(message,user_id);
        return ResponseEntity.ok("Сообщение отправлено");
    }

    @PostMapping("/single/sendToChat/{chat_id}")
    public ResponseEntity<?> sendMessageToChat(@RequestBody String message,
                                         @PathVariable Integer chat_id){
        singleChatService.sendMessageToChatId(message,chat_id);
        return ResponseEntity.ok("Сообщение отправлено");
    }



    @GetMapping("/single/{id}")
    public ResponseEntity<List<MessageDto>> getMessagePageDtoByChatId
            (@RequestParam(required = false, defaultValue = "20") int items,
             @RequestParam(required = false,defaultValue = "1") int currentPage,
             @PathVariable("id") int chatId) {

        SingleChat singleChat = singleChatService.getById(chatId).orElseThrow(
                () -> new ChatException("Одиночный чат не найден!")
        );
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(singleChat.getUserOne().equals(user) || singleChat.getUserTwo().equals(user))){
            throw new ChatException("Вы не состоите в чате.");
        }

        Map<String, Object> paginationMap = new HashMap<>();
        paginationMap.put("class", "messageDtoDaoImpl");
        paginationMap.put("chatId", chatId);
        paginationMap.put("pageItems", items);
        paginationMap.put("currentPage", currentPage);

        return ResponseEntity.ok(messageDtoService.getPageDto(items, currentPage, paginationMap).getItems());
    }

    @GetMapping("/check-single-chat/{id}")
    public ResponseEntity<?> checkSingleChat(@PathVariable("id") Integer userId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(singleChatService.existSingleChatByUsers(user,userService.getById(userId).orElseThrow()));
    }


    @GetMapping("single/find/{userId}")
    public ResponseEntity<?> getChatIdByUserIds(@PathVariable Integer userId,@AuthenticationPrincipal User userB){
        return ResponseEntity.ok(singleChatService.getChatIdByUserIds(userB.getId(),userId));
    }


}
