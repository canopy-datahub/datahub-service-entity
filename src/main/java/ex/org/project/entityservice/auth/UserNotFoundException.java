package ex.org.project.entityservice.auth;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) { super(message); }

}
