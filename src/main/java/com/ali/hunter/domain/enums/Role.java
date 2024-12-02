package com.ali.hunter.domain.enums;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(
            Permission.MANAGE_SPECIES,
            Permission.MANAGE_COMPETITIONS,
            Permission.MANAGE_PARTICIPATIONS
    )),
    MEMBER(Set.of(
            Permission.MANAGE_COMPETITIONS
    )),
    JURY(Set.of(
            Permission.MANAGE_PARTICIPATIONS
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
