package maciej.kubis.blog.security;

import lombok.RequiredArgsConstructor;
import maciej.kubis.blog.user.domain.CurrentUser;
import maciej.kubis.blog.user.domain.User;
import maciej.kubis.blog.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final CurrentUser currentUser;
    private final UserRepository userRepository;


    public CustomAuthSuccessHandler(CurrentUser currentUser, UserRepository userRepository) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        redirectStrategy.sendRedirect(request, response, "/swagger-ui/");
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(30 * 60);
            String username;
            if (authentication.getPrincipal() instanceof User) {
                username = ((User) authentication.getPrincipal()).getEmail();
            } else {
                username = authentication.getName();
            }

            User user = userRepository.findByEmail(username);
            currentUser.setId(user.getId());
            currentUser.setEmail(user.getEmail());

            session.setAttribute("user", username);
        }
    }

}
