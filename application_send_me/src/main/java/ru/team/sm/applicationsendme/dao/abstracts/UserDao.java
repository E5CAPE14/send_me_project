package ru.team.sm.applicationsendme.dao.abstracts;

import ru.team.sm.applicationsendme.model.user.entity.User;

import java.util.Optional;

public interface UserDao extends ReadWriteDao<User,Integer>{

    Optional<User> getByEmail(String email);

    void deleteByEmail(String email);

    void updatePasswordByEmail(String email, String password);

    Boolean isPresentByEmail(String email);

}
