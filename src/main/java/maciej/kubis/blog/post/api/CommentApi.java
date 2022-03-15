package maciej.kubis.blog.post.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import maciej.kubis.blog.post.api.request.CommentRequest;
import maciej.kubis.blog.post.api.request.CommentUpdateRequest;
import maciej.kubis.blog.post.api.response.CommentResponse;
import maciej.kubis.blog.post.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Comment")
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentApi {

    private final CommentService commentService;

    @PostMapping
    @ApiOperation("Create comment")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.createComment(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get comment")
    public ResponseEntity<CommentResponse> getPost(@PathVariable Long id) {
        CommentResponse commentResponse = commentService.getComment(id);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponse);
    }

    @PutMapping
    @ApiOperation("Update comment")
    public ResponseEntity<CommentResponse> UpdateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        CommentResponse commentResponse = commentService.updateComment(commentUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponse);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete comment")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/post/{id}")
    @ApiOperation("Get comments from post")
    public ResponseEntity<List<CommentResponse>> getAllPosts(@PathVariable Long id) {
        List<CommentResponse> commentResponses = commentService.getAllPosts(id);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponses);
    }
}
