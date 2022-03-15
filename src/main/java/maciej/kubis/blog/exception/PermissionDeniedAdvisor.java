package maciej.kubis.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PermissionDeniedAdvisor {

    @ExceptionHandler(PermissionDenied.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessageResponse errorFound(PermissionDenied exception) {
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }
}
