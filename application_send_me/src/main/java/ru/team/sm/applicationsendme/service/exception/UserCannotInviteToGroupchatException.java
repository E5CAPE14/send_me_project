package ru.team.sm.applicationsendme.service.exception;

public class UserCannotInviteToGroupchatException extends RuntimeException{
    public UserCannotInviteToGroupchatException(String message) {
        super(message);
    }
}
