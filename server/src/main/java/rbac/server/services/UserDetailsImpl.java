package rbac.server.services;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.access.PermissionEvaluator;

import rbac.server.models.Role;
import rbac.server.models.User;



@Getter
@Setter
public class UserDetailsImpl implements UserDetails{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;


    private Collection<? extends GrantedAuthority> permissions;
    private Collection<String> roles;

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<String> roles, Collection<? extends GrantedAuthority> permissions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
    }


    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> permissions = user.getRoles().stream()
                .map(Role::getPermissions)
                .flatMap(Set::stream)
                .map(permission -> {
                    return new SimpleGrantedAuthority(permission.getName());
                })
                .collect(Collectors.toList());
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().toString())
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                roles,
                permissions);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private Boolean enabled = true;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
