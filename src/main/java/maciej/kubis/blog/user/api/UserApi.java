package maciej.kubis.blog.user.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import maciej.kubis.blog.user.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "User")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> getCurrentUserId() {
        String id = userService.getCurrentUserId();
        return  ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
