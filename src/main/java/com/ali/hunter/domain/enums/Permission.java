package com.ali.hunter.domain.enums;

public enum Permission {
    CAN_PARTICIPATE("CAN_PARTICIPATE"),
    CAN_VIEW_RANKINGS("CAN_VIEW_RANKINGS"),
    CAN_VIEW_COMPETITIONS("CAN_VIEW_COMPETITIONS"),
    CAN_SCORE("CAN_SCORE"),
    CAN_MANAGE_COMPETITIONS("CAN_MANAGE_COMPETITIONS"),
    CAN_MANAGE_USERS("CAN_MANAGE_USERS"),
    CAN_MANAGE_SPECIES("CAN_MANAGE_SPECIES"),
    CAN_MANAGE_SETTINGS("CAN_MANAGE_SETTINGS");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
