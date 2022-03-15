package maciej.kubis.blog.user.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum RoleEnum {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String value;

    public String getValue() {
        return value;
    }
}
