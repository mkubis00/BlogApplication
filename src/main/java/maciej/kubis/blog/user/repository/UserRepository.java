package maciej.kubis.blog.user.repository;

import maciej.kubis.blog.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    User findByEmail(String email);

    @Override
    void delete(User user);
}
