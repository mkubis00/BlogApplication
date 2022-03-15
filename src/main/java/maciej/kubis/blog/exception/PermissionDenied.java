package maciej.kubis.blog.exception;

public class PermissionDenied extends RuntimeException  {
    public PermissionDenied() {
        super(String.format("Permission Denied"));
    }
}
