package md.endava.oe.service;

import md.endava.oe.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apavel
 */
@Service
public interface UserService extends AbstractService {
    User findById(Long id);

    User findByUsername(String username);

    List<User> findByLmId(Long id);

    List<User> findReviewers(Long aEmpId);

    List<User> findIn(List<Long> reviewerIds);
}
