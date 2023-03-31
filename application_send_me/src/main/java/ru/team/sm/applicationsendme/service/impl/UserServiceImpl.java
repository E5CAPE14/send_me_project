package ru.team.sm.applicationsendme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team.sm.applicationsendme.dao.abstracts.UserDao;
import ru.team.sm.applicationsendme.model.user.entity.User;
import ru.team.sm.applicationsendme.service.abstracts.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends ReadWriteServiceImpl<User, Integer> implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        super(userDao);
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> getAllByIds(Iterable<Integer> ids) {
        return userDao.getAllByIds(ids);
    }

    @Override
    public void persist(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.persist(user);
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
    }

    @Override
    public void deleteByEmail(String email) {
        userDao.deleteByEmail(email);
    }

    @Override
    public void updatePasswordByEmail(String email, String password) {
        userDao.updatePasswordByEmail(email, password);
    }
}