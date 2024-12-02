package com.ali.hunter.domain.enums;

public enum Permission {
    MANAGE_SPECIES("PERMISSION_MANAGE_SPECIES"),
    MANAGE_COMPETITIONS("PERMISSION_MANAGE_COMPETITIONS"),
    MANAGE_PARTICIPATIONS("PERMISSION_MANAGE_PARTICIPATIONS");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}