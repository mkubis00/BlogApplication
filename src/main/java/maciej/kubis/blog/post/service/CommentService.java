package maciej.kubis.blog.post.service;

import lombok.RequiredArgsConstructor;
import maciej.kubis.blog.exception.NotFoundException;
import maciej.kubis.blog.exception.PermissionDenied;
import maciej.kubis.blog.post.CommentMapper;
import maciej.kubis.blog.post.api.request.CommentRequest;
import maciej.kubis.blog.post.api.request.CommentUpdateRequest;

import maciej.kubis.blog.post.api.response.CommentResponse;

import maciej.kubis.blog.post.domain.Comment;

import maciej.kubis.blog.post.domain.Post;
import maciej.kubis.blog.post.repository.CommentReposiotry;
import maciej.kubis.blog.user.domain.CurrentUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentReposiotry commentReposiotry;
    private final CommentMapper commentMapper;
    private final CurrentUser currentUser;
    private final PostService postService;

    @PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
    public CommentResponse createComment(CommentRequest commentRequest) {
        Comment comment = commentReposiotry.save(commentMapper.toComment(commentRequest));
        return commentMapper.toCommentResponse(comment);
    }

    @PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
    public CommentResponse getComment(Long id) {
        Comment comment = isCommentExists(id);
        return commentMapper.toCommentResponse(comment);
    }

    @PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
    public CommentResponse updateComment(CommentUpdateRequest commentUpdateRequest) {
        int isEqual = commentUpdateRequest.getCommentOwner().compareTo(currentUser.getId());
        if (isEqual != 0) {
            throw new PermissionDenied();
        }
        Comment comment = isCommentExists(commentUpdateRequest.getId());
        comment = commentMapper.toUpdate(comment, commentUpdateRequest);
        commentReposiotry.save(comment);
        return commentMapper.toCommentResponse(comment);
    }

    @PreAuthorize("hasRole('ROLE_MODIFY_PRIVILAGE')")
    public void deleteComment(Long id) {
        isCommentExists(id);
        commentReposiotry.deleteById(id);
    }

    public List<CommentResponse> getAllPosts(Long id) {
        Post post = postService.isPostExists(id);
        return commentReposiotry.findAllByPost(post).stream().map(commentMapper::toCommentResponse).collect(Collectors.toList());
    }

    private Comment isCommentExists(Long id) {
        return commentReposiotry.findById(id).orElseThrow(() -> new NotFoundException());
    }
}
