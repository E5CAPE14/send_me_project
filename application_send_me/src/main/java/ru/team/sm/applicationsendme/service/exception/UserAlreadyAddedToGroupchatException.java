package ru.team.sm.applicationsendme.service.exception;

public class UserAlreadyAddedToGroupchatException extends RuntimeException{
    public UserAlreadyAddedToGroupchatException(String message) {
        super(message);
    }
}
