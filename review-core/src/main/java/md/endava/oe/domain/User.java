package md.endava.oe.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author apavel
 */
@Entity
@Table(name = "pm_user")
public class User extends GenericEntity {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "lm_id")
    private User lm;

    @ManyToMany
    @JoinTable(
        name = "pm_user_to_role",
        joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<Role>();

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User getLm() {
        return lm;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
