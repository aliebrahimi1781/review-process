package md.endava.oe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

/**
 * @author apavel
 */
@Entity
@Table(name = "pm_role", uniqueConstraints = {@UniqueConstraint(name = "uk_pm_role_name", columnNames = {"name"})})
public class Role extends GenericEntity {

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(final String aName) {
        name = aName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> aUsers) {
        users = aUsers;
    }
}
