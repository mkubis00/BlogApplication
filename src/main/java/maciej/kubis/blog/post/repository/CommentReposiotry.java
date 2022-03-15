package maciej.kubis.blog.post.repository;

import maciej.kubis.blog.post.domain.Comment;
import maciej.kubis.blog.post.domain.Post;
import maciej.kubis.blog.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentReposiotry extends JpaRepository<Comment, Long> {

    @Transactional
    List<Comment> findAllByPost(Post post);

}
