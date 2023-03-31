package ru.team.sm.applicationsendme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.dao.abstracts.MessageDao;
import ru.team.sm.applicationsendme.dao.abstracts.RoleDao;
import ru.team.sm.applicationsendme.dao.abstracts.SingleChatDao;
import ru.team.sm.applicationsendme.dao.abstracts.UserDao;
import ru.team.sm.applicationsendme.model.chat.Chat;
import ru.team.sm.applicationsendme.model.chat.ChatType;
import ru.team.sm.applicationsendme.model.chat.Message;
import ru.team.sm.applicationsendme.model.chat.SingleChat;
import ru.team.sm.applicationsendme.model.user.entity.Role;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.abstracts.SingleChatService;

import java.time.LocalDateTime;
@Service
public class InitDB {

    private final RoleDao roleDao;
    private final UserDao userDao;

    private final MessageDao messageDao;

    private final SingleChatService chatService;

    private final SingleChatDao singleChatDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitDB(RoleDao roleDao, UserDao userDao, MessageDao messageDao, SingleChatService chatService, SingleChatDao singleChatDao, PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.messageDao = messageDao;
        this.chatService = chatService;
        this.singleChatDao = singleChatDao;
        this.passwordEncoder = passwordEncoder;
//        initRole();
//        initUser();
//        addTwoUser();
    }
    private void initRole(){
        Role[] roles = {new Role("ROLE_ADMIN"),new Role("ROLE_USER")};
        roleDao.persist(roles[0]);
        roleDao.persist(roles[1]);
    }

    private void initUser(){
        User admin = new User("admin@mail.ru",passwordEncoder.encode("123"),"admin", LocalDateTime.now(),
                roleDao.getRoleByName("ROLE_ADMIN").orElseThrow());
        User user = new User("user@mail.ru",passwordEncoder.encode("321"),"user",LocalDateTime.now(),
                roleDao.getRoleByName("ROLE_USER").orElseThrow());
        User user2 = new User("user2@mail.ru",passwordEncoder.encode("321"),"user2",LocalDateTime.now(),
                roleDao.getRoleByName("ROLE_USER").orElseThrow());
        userDao.persist(admin);
        userDao.persist(user);
        userDao.persist(user2);
        Chat chat = new Chat(ChatType.SINGLE);
        SingleChat singleChat = new SingleChat();
        singleChat.setChat(chat);
        singleChat.setUserOne(admin);
        singleChat.setUserTwo(user);
        singleChat.setId(199999);
        singleChatDao.persist(singleChat);
        Message[] messages = new Message[20];
        for(int i = 0; i < 20;i += 2){
            Message message = new Message();
            Message message1 = new Message();
            message.setMessage("Я хочу сосать." + i + "- аж вот столько раз");
            message.setUserSender(admin);
            message.setChat(chat);
            message.setTimeRegistration(LocalDateTime.now());
            messages[i] = message;
            message1.setMessage("Ну так пососи " + i + "раз");
            message1.setUserSender(user);
            message1.setChat(chat);
            message1.setTimeRegistration(LocalDateTime.now());
            messages[i+1] = message1;
        }
        messageDao.persistAll(messages);
    }

    private void addTwoUser(){
        User admin = new User("client1@mail.ru",passwordEncoder.encode("1234"),"Sashok123", LocalDateTime.now(),
                roleDao.getRoleByName("ROLE_USER").orElseThrow());
        User user = new User("Clien2@mail.ru",passwordEncoder.encode("4321"),"Egorik331",LocalDateTime.now(),
                roleDao.getRoleByName("ROLE_USER").orElseThrow());
        userDao.persist(admin);
        userDao.persist(user);
    }

    private void createdChat(){

        SingleChat singleChat = new SingleChat();
        singleChat.setUserOne(userDao.getByEmail("admin@mail.ru").get());
        singleChat.setUserTwo(userDao.getByEmail("user@mail.ru").get());
        chatService.addSingleChatAndMessage(singleChat,"Hello User!");
    }
}
