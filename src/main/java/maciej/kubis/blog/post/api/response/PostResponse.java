package maciej.kubis.blog.post.api.response;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponse {

    private Long id;
    private Long postOwner;
    private String content;
}
