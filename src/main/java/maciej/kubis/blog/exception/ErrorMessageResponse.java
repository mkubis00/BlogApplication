package maciej.kubis.blog.exception;

public class ErrorMessageResponse {

    private String message;

    public ErrorMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
