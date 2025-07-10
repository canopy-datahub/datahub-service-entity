package ex.org.project.entityservice.exception.custom;


public class StudyNotFoundException extends RuntimeException {
    public StudyNotFoundException(String message) {
        super(message);
    }

}