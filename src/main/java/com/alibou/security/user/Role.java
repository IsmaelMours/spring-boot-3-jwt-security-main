package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alibou.security.user.Permission.ADMIN_CREATE;
import static com.alibou.security.user.Permission.ADMIN_DELETE;
import static com.alibou.security.user.Permission.ADMIN_READ;
import static com.alibou.security.user.Permission.ADMIN_UPDATE;
import static com.alibou.security.user.Permission.CONTENT_CREATOR_CREATE;
import static com.alibou.security.user.Permission.CONTENT_CREATOR_DELETE;
import static com.alibou.security.user.Permission.CONTENT_CREATOR_READ;
import static com.alibou.security.user.Permission.CONTENT_CREATOR_UPDATE;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  CONTENT_CREATOR_READ,
                  CONTENT_CREATOR_UPDATE,
                  CONTENT_CREATOR_DELETE,
                  CONTENT_CREATOR_CREATE
          )
  ),
  CONTENT_CREATOR( // Updated name from MANAGER to CONTENT_CREATOR
          Set.of(
                  CONTENT_CREATOR_READ,
                  CONTENT_CREATOR_UPDATE,
                  CONTENT_CREATOR_DELETE,
                  CONTENT_CREATOR_CREATE
          )
  );

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}

