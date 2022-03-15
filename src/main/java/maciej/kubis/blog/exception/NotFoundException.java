package maciej.kubis.blog.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(String.format("NO DATA"));
    }
}
