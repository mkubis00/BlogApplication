package maciej.kubis.blog.post.api.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentResponse {

    private Long id;
    private Long CommentOwner;
    private String content;
    private Long PostId;
}
