package maciej.kubis.blog.post;

import lombok.RequiredArgsConstructor;

import maciej.kubis.blog.exception.NotFoundException;
import maciej.kubis.blog.post.api.request.PostRequest;
import maciej.kubis.blog.post.api.request.PostUpdateRequest;
import maciej.kubis.blog.post.api.response.PostResponse;
import maciej.kubis.blog.post.domain.Post;
import maciej.kubis.blog.user.domain.User;
import maciej.kubis.blog.user.service.UserService;

import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class PostMapper {

    private final UserService userService;

    public Post toPost(PostRequest postRequest) {
        User user = isUserExist(postRequest);
        return Post.builder()
                .postOwner(user)
                .content(postRequest.getContent())
                .build();
    }

    public PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .postOwner(post.getPostOwner().getId())
                .content(post.getContent())
                .build();
    }

    public Post toUpdate(Post post, PostUpdateRequest postUpdateRequest) {
        post.setContent(postUpdateRequest.getContent());
        return post;
    }



    private User isUserExist(PostRequest postRequest) {
        return userService.isExist(postRequest.getPostOwner());
    }
}
