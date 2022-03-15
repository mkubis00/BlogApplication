package maciej.kubis.blog.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciej.kubis.blog.user.domain.User;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "comment_owner_id")
    private User commentOwner;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "content", length = 1000)
    private String content;
}
