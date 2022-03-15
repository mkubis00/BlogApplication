package maciej.kubis.blog.post;

import lombok.RequiredArgsConstructor;
import maciej.kubis.blog.post.api.request.CommentRequest;
import maciej.kubis.blog.post.api.request.CommentUpdateRequest;
import maciej.kubis.blog.post.api.response.CommentResponse;
import maciej.kubis.blog.post.domain.Comment;
import maciej.kubis.blog.post.domain.Post;
import maciej.kubis.blog.post.service.PostService;
import maciej.kubis.blog.user.domain.User;
import maciej.kubis.blog.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserService userService;
    private final PostService postService;

    public Comment toComment(CommentRequest commentRequest) {
        User user = isUserExist(commentRequest);
        Post post = postService.isPostExists(commentRequest.getPostId());
        return Comment.builder()
                .commentOwner(user)
                .content(commentRequest.getContent())
                .post(post).build();
    }

    public CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .CommentOwner(comment.getCommentOwner().getId())
                .PostId(comment.getPost().getId())
                .build();
    }

    public Comment toUpdate(Comment comment, CommentUpdateRequest commentUpdateRequest) {
        comment.setContent(commentUpdateRequest.getContent());
        return comment;
    }


    private User isUserExist(CommentRequest commentRequest) {
        return userService.isExist(commentRequest.getCommentOwner());
    }


}
