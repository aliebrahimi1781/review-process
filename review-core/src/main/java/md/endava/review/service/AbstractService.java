package md.endava.review.service;

import javax.persistence.EntityManager;

/**
 * @author apavel
 */
public interface AbstractService {
    EntityManager getEntityManager();
}
