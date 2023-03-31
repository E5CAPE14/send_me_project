package ru.team.sm.applicationsendme.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team.sm.applicationsendme.dao.abstracts.ChatDao;
import ru.team.sm.applicationsendme.dao.abstracts.MessageDao;
import ru.team.sm.applicationsendme.dao.abstracts.SingleChatDao;
import ru.team.sm.applicationsendme.dao.abstracts.UserDao;
import ru.team.sm.applicationsendme.model.chat.Chat;
import ru.team.sm.applicationsendme.model.chat.Message;
import ru.team.sm.applicationsendme.model.chat.SingleChat;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.abstracts.SingleChatService;
import ru.team.sm.applicationsendme.service.exception.ChatException;
import ru.team.sm.applicationsendme.service.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SingleChatServiceImpl extends ReadWriteServiceImpl<SingleChat, Integer> implements SingleChatService {

    private final SingleChatDao singleChatDao;

    private final ChatDao chatDao;

    private final UserDao userDao;
    private final MessageDao messageDao;

    public SingleChatServiceImpl(SingleChatDao singleChatDao, ChatDao chatDao, UserDao userDao, MessageDao messageDao) {
        super(singleChatDao);
        this.singleChatDao = singleChatDao;
        this.chatDao = chatDao;
        this.userDao = userDao;
        this.messageDao = messageDao;
    }

    @Override
    @Transactional
    public void deleteByChatId(Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Optional<SingleChat> singleChat = getById(id);
        if (singleChat.get().getUserOne().getId() == user.getId() && singleChat.get().isDeletedByUserOne() ||
                singleChat.get().getUserTwo().getId() == user.getId() && singleChat.get().isDeletedByUserTwo()) {
            throw new ChatException("Single Chat № " + id + " для данного пользователя уже удален.");
        }
        singleChatDao.deleteByChatId(id, user.getId());
    }

    @Override
    @Transactional (noRollbackFor = ChatException.class)
    public void addSingleChatAndMessage(SingleChat singleChat, String message) {

        User userOne = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTwo = singleChat.getUserTwo();
        singleChat.setUserOne(userOne);
        if(existSingleChatByUsers(userOne,userTwo)){
            throw new ChatException("Данный чат уже создан!");
        }
        singleChatDao.persist(singleChat);
        Message message1 = new Message();
        message1.setMessage(message);
        message1.setChat(singleChat.getChat());
        message1.setUserSender(userOne);
        message1.setTimeRegistration(LocalDateTime.now());
        messageDao.persist(message1);
    }

    @Override
    public Optional<SingleChat> getByUsersIds(Integer userOneId, Integer userTwoId) {
        return singleChatDao.getByUsersIds(userOneId, userTwoId);
    }

    @Override
    public Integer getChatIdByUserIds(Integer userOneId, Integer userTwoId){
        return singleChatDao.getCharIdByUserIds(userOneId, userTwoId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void sendMessageToChatId(String messageText, Integer chat_id){
        User userOne = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Chat chat = chatDao.getById(chat_id).orElseThrow(() -> new ChatException("Данного чата не существует"));
        Message message = new Message();
        message.setMessage(messageText);
        message.setUserSender(userOne);
        message.setChat(chat);
        message.setTimeRegistration(LocalDateTime.now());
        messageDao.persist(message);
    }

    @Override
    public void sendMessage(String messageText, Integer user_id){

        User userOne = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userTwo = userDao.getById(user_id);

        if(userTwo.isEmpty()){
            throw new RuntimeException("Пользователя с таким id не существует");
        }

        List<SingleChat> singleChats = singleChatDao.getAll();
        SingleChat singleChat = null;
        for (var el:singleChats) {
            if(el.getUserTwo().equals(userTwo.get()) && el.getUserOne().equals(userOne)){
                singleChat = el;
                break;
            } else if(el.getUserTwo().equals(userOne) && el.getUserOne().equals(userTwo.get())){
                singleChat = el;
                break;
            }
        }

        if(!(singleChat == null)) {
            Message message = new Message();
            message.setMessage(messageText);
            message.setUserSender(userOne);
            message.setChat(singleChats.get(0).getChat());
            message.setTimeRegistration(LocalDateTime.now());
            messageDao.persist(message);
        } else {
            throw new RuntimeException("Данного чата не существует");
        }
    }

    @Override
    public boolean existSingleChatByUsers(User userOneId, User userTwoId) {
        return singleChatDao.existChatByUsers(userOneId.getId(),userTwoId.getId()) > 0;
    }
}

