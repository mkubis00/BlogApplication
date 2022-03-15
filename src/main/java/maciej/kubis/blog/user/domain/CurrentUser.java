package maciej.kubis.blog.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@SessionScope
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {

    private Long id;
    private String email;
//    private Long id;
//    private String firstName;
//    private String lastName;

}
