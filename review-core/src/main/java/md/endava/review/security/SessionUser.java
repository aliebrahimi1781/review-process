package md.endava.review.security;

import md.endava.review.domain.Role;
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
    private final String lastname;

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
        final String aFirstname,
        final String aLastname,
        final Set<Role> aRoles) {
        super(aUsername, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        id = aId;
        firstname = aFirstname;
        lastname = aLastname;
        roles = aRoles;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

}
