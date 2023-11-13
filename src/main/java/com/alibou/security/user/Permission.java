package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CONTENT_CREATOR_READ("content_creator:read"),  // Updated from MANAGER_READ to CONTENT_CREATOR_READ
    CONTENT_CREATOR_UPDATE("content_creator:update"),  // Updated from MANAGER_UPDATE to CONTENT_CREATOR_UPDATE
    CONTENT_CREATOR_CREATE("content_creator:create"),  // Updated from MANAGER_CREATE to CONTENT_CREATOR_CREATE
    CONTENT_CREATOR_DELETE("content_creator:delete")  // Updated from MANAGER_DELETE to CONTENT_CREATOR_DELETE
    ;

    @Getter
    private final String permission;
}
