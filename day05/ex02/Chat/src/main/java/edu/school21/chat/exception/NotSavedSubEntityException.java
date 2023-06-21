package edu.school21.chat.exception;

public class NotSavedSubEntityException extends RuntimeException {
    public NotSavedSubEntityException(String message) {
        super(message);
    }
}
