package md.endava.review.activiti.identity;

import md.endava.review.domain.Role;
import md.endava.review.service.UserService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author apavel
 */
public class ActivitiUserManager extends UserEntityManager {

    @Autowired
    private UserService userService;

    @Override
    public User createNewUser(final String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertUser(final User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(final User updatedUser) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findUserById(final String userId) {
        final md.endava.review.domain.User pmUser = userService.findById(Long.parseLong(userId));
        return pmUserToActivitiUser(pmUser);
    }

    @Override
    public void deleteUser(final String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUserByQueryCriteria(final UserQueryImpl query, final Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findUserCountByQueryCriteria(final UserQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByUser(final String userId) {
        md.endava.review.domain.User pmUser = userService.findById(Long.parseLong(userId));
        List<Group> groups = new ArrayList<Group>();

        for (Role pmRole : pmUser.getRoles())
            groups.add(ActivitiGroupManager.pmRoleToActivitiGroup(pmRole));

        return groups;
    }

    @Override
    public UserQuery createNewUserQuery() {
        return super.createNewUserQuery();
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(final String userId, final String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(final String userId, final String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean checkPassword(final String userId, final String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findPotentialStarterUsers(final String proceDefId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUsersByNativeQuery(
        final Map<String, Object> parameterMap, final int firstResult, final int maxResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findUserCountByNativeQuery(final Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNewUser(final User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Picture getUserPicture(final String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUserPicture(final String userId, final Picture picture) {
        throw new UnsupportedOperationException();
    }

    private UserEntity pmUserToActivitiUser(md.endava.review.domain.User user) {
        UserEntity activitiUser = new UserEntity();
        activitiUser.setId(user.getId().toString());
        activitiUser.setPassword(user.getPassword());
        activitiUser.setFirstName(user.getFirstname());
        activitiUser.setLastName(user.getLastname());
        activitiUser.setEmail(user.getEmail());
        return activitiUser;
    }
}
