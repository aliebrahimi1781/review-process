package md.endava.review.activiti.identity;

import md.endava.review.domain.Role;
import md.endava.review.domain.User;
import md.endava.review.service.UserService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author apavel
 */
public class ActivitiGroupManager extends GroupEntityManager {

    @Autowired
    private UserService userService;

    @Override
    public Group createNewGroup(final String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertGroup(final Group group) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateGroup(final Group updatedGroup) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteGroup(final String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupQuery createNewGroupQuery() {
        return super.createNewGroupQuery();
    }

    @Override
    public List<Group> findGroupByQueryCriteria(final GroupQueryImpl query, final Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByQueryCriteria(final GroupQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByUser(final String userId) {
        User pmUser = userService.findById(Long.parseLong(userId));
        List<Group> groups = new ArrayList<Group>();

        for (Role pmRole : pmUser.getRoles())
            groups.add(pmRoleToActivitiGroup(pmRole));

        return groups;
    }

    @Override
    public List<Group> findGroupsByNativeQuery(
        final Map<String, Object> parameterMap, final int firstResult, final int maxResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByNativeQuery(final Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNewGroup(final Group group) {
        throw new UnsupportedOperationException();
    }

    public static GroupEntity pmRoleToActivitiGroup(Role role) {
        GroupEntity group = new GroupEntity();
        group.setId(role.getId().toString());
        group.setName(role.getName());
        group.setType("");
        return group;
    }
}
