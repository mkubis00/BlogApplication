package maciej.kubis.blog.post.api.request;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostUpdateRequest {

    private Long id;
    private Long postOwner;
    private String content;


}
