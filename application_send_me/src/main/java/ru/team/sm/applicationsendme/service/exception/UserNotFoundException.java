package ru.team.sm.applicationsendme.service.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Пользователь не найден.");
    }
}
