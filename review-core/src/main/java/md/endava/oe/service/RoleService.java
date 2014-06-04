package md.endava.oe.service;

import md.endava.oe.domain.Role;
import org.springframework.stereotype.Service;

/**
 * @author apavel
 */
@Service
public interface RoleService extends AbstractService {
    Role findById(Long id);
}
