package maciej.kubis.blog.post.service;

import lombok.RequiredArgsConstructor;
import maciej.kubis.blog.exception.NotFoundException;
import maciej.kubis.blog.exception.PermissionDenied;
import maciej.kubis.blog.post.PostMapper;
import maciej.kubis.blog.post.api.request.PostRequest;
import maciej.kubis.blog.post.api.request.PostUpdateRequest;
import maciej.kubis.blog.post.api.response.PostResponse;
import maciej.kubis.blog.post.domain.Post;
import maciej.kubis.blog.post.repository.PostRepository;
import maciej.kubis.blog.user.domain.CurrentUser;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final CurrentUser currentUser;


    @PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
    public PostResponse createPost(PostRequest postRequest) {
        Post post = postRepository.save(postMapper.toPost(postRequest));
        return postMapper.toPostResponse(post);
    }

    @PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
    public PostResponse getPost(Long id) {
        Post post = isPostExists(id);
        return postMapper.toPostResponse(post);
    }

    @PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
    public PostResponse updatePost(PostUpdateRequest postUpdateRequest) {
        int isEqual = postUpdateRequest.getPostOwner().compareTo(currentUser.getId());
        if (isEqual != 0) {
            throw new PermissionDenied();
        }
        Post post = isPostExists(postUpdateRequest.getId());
        post = postMapper.toUpdate(post, postUpdateRequest);
        postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    @PreAuthorize("hasRole('ROLE_MODIFY_PRIVILAGE')")
    public void deletePost(Long id) {
        isPostExists(id);
        postRepository.deleteById(id);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(postMapper::toPostResponse).collect(Collectors.toList());
    }

    public Post isPostExists(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }
}
