package ru.team.sm.applicationsendme.service.abstracts;

import ru.team.sm.applicationsendme.model.user.entity.User;

import java.util.Optional;

public interface UserService extends ReadWriteService<User, Integer> {
    void updatePasswordByEmail(String email, String password);
    Optional<User> getByEmail(String email);
    void deleteByEmail(String email);
}