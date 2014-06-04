package md.endava.oe.service;

import javax.persistence.EntityManager;

/**
 * @author apavel
 */
public interface AbstractService {
    EntityManager getEntityManager();
}
