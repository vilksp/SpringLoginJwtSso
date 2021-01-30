package ksp.vilius.enums;

import lombok.Getter;

import static ksp.vilius.constants.AuthorityConstant.*;

@Getter
public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_MODERATOR(MODERATOR_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authority) {
        this.authorities = authority;
    }

}
