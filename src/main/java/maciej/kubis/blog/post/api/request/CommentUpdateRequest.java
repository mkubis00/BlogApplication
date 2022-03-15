package maciej.kubis.blog.post.api.request;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentUpdateRequest {

    private Long id;
    private Long CommentOwner;
    private String content;
    private Long PostId;

}
