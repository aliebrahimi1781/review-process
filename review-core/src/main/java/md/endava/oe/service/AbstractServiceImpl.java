package md.endava.oe.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author apavel
 */
public class AbstractServiceImpl implements AbstractService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
