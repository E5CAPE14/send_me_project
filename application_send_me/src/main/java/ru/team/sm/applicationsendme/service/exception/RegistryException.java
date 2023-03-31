package ru.team.sm.applicationsendme.service.exception;

public class RegistryException extends RuntimeException {

    public RegistryException() {
        super("Данная почта уже используется!");
    }
}
