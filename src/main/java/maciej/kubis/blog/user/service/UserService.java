package maciej.kubis.blog.user.service;

import lombok.RequiredArgsConstructor;
import maciej.kubis.blog.exception.NotFoundException;
import maciej.kubis.blog.user.domain.CurrentUser;
import maciej.kubis.blog.user.domain.User;
import maciej.kubis.blog.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public User isExist(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    public String getCurrentUserId() {
        return currentUser.getId().toString();
    }

}
