package maciej.kubis.blog.post.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import maciej.kubis.blog.post.api.request.PostRequest;
import maciej.kubis.blog.post.api.request.PostUpdateRequest;
import maciej.kubis.blog.post.api.response.PostResponse;
import maciej.kubis.blog.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "Post")
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostApi {

    private final PostService postService;

    @PostMapping
    @ApiOperation("Create post")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.createPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get post")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        PostResponse postResponse = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postResponse);
    }

    @PutMapping
    @ApiOperation("Update post")
    public ResponseEntity<PostResponse> UpdatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
        PostResponse postResponse = postService.updatePost(postUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(postResponse);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete post")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @ApiOperation("Get all posts")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> postResponses = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(postResponses);
    }
}
