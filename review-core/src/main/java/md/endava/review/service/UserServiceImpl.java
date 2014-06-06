package md.endava.review.service;

import md.endava.review.domain.User;
import md.endava.review.security.SessionUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author apavel
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

    @Override
    public User findById(Long id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        from.fetch("roles", JoinType.LEFT);
        CriteriaQuery<User> select = criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("username"), username));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(select);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<User> findByLmId(Long id) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery<User> select = criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("lm"), id));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(select);
        return typedQuery.getResultList();
    }

    @Override
    public List<User> findReviewers(final Long aEmpId) {
        SessionUser currentUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery<User> select = criteriaQuery.select(from);
        criteriaQuery.where(
            criteriaBuilder.notEqual(from.get("username"), "admin"),
            criteriaBuilder.notEqual(from.get("id"), aEmpId),
            criteriaBuilder.notEqual(from.get("id"), currentUser.getId()));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(select);
        return typedQuery.getResultList();
    }

    @Override
    public List<User> findIn(final List<Long> reviewerIds) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery<User> select = criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.in(from.get("id")).value(reviewerIds));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(select);
        return typedQuery.getResultList();
    }
}
