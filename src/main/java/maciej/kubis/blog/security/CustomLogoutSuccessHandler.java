package maciej.kubis.blog.security;

import maciej.kubis.blog.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler  implements LogoutSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        if (authentication != null) {
            User user = (User) authentication.getPrincipal();

            LOG.info("onLogoutSuccess - user logged out:");
            LOG.info(user.getEmail());

            final HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("user");
            }

            redirectStrategy.sendRedirect(request, response, "/login");
        }
    }
}

