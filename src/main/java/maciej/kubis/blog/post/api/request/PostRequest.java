package maciej.kubis.blog.post.api.request;

import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Jacksonized
public class PostRequest {

    private Long postOwner;
    private String content;

}
