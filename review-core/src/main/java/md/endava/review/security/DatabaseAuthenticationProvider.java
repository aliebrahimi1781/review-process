package md.endava.review.security;

import md.endava.review.domain.Role;
import md.endava.review.domain.User;
import md.endava.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Authentication provider that authenticates a user
 * against database
 *
 * @author apavel
 */
public class DatabaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(
        final UserDetails aUserDetails, final UsernamePasswordAuthenticationToken aUsernamePasswordAuthenticationToken)
        throws AuthenticationException {
        final String presentedPassword = aUsernamePasswordAuthenticationToken.getCredentials().toString();

        if (!presentedPassword.equals(aUserDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    protected UserDetails retrieveUser(
        final String username, final UsernamePasswordAuthenticationToken aUsernamePasswordAuthenticationToken)
        throws AuthenticationException {
        final UserDetails loadedUser;

        try {
            loadedUser = loadUserByUsername(username);
        } catch (final DataAccessException repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        } catch (final UsernameNotFoundException unf) {
            throw new AuthenticationServiceException(unf.getMessage());
        }

        return loadedUser;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new SessionUser(user.getUsername(), user.getPassword(), true, true, true, true, authorities, user.getId(), user.getFirstname(), user.getLastname(), user.getRoles());
    }
}
