package maciej.kubis.blog.user.domain;

import lombok.Data;
import maciej.kubis.blog.post.domain.Comment;
import maciej.kubis.blog.post.domain.Post;
import maciej.kubis.blog.user.api.UserResponseDto;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")

public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(length = 60)
    private String password;

    private boolean enabled;

    @Column(name = "description", length = 500)
    private String Description;

    @OneToMany(mappedBy = "postOwner", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany(mappedBy = "commentOwner", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<Comment>();

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    protected List<Role> roles = new ArrayList<Role>();

    public UserResponseDto toDto() {
        return new UserResponseDto(firstName, secondName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!getEmail().equals(user.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(id)
                .append(", firstName=").append(firstName)
                .append(", email=").append(email)
                .append(", enabled=").append(enabled)
                .append("]");
        return builder.toString();
    }
}
