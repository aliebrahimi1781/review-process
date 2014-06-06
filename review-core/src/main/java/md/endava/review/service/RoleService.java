package md.endava.review.service;

import md.endava.review.domain.Role;
import org.springframework.stereotype.Service;

/**
 * @author apavel
 */
@Service
public interface RoleService extends AbstractService {
    Role findById(Long id);
}
