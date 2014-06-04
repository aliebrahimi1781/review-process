package md.endava.oe.security;

import md.endava.oe.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

/**
 * @author apavel
 */
public final class SessionUser extends User {

    private final Long id;

    private final String firstname;

    private final Set<Role> roles;


    public SessionUser(
        final String aUsername,
        final String password,
        final boolean enabled,
        final boolean accountNonExpired,
        final boolean credentialsNonExpired,
        final boolean accountNonLocked,
        final Collection<? extends GrantedAuthority> authorities,
        final Long aId,
        final String aAFirstname,
        final Set<Role> aRoles) {
        super(aUsername, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        id = aId;
        firstname = aAFirstname;
        roles = aRoles;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

}
