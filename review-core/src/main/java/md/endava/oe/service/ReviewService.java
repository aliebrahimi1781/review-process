package md.endava.oe.service;

import md.endava.oe.domain.Review;
import md.endava.oe.domain.User;

import java.util.List;

/**
 * @author apavel
 */
public interface ReviewService extends AbstractService {
    void persist(Review aReview);

    List<Review> createReviewList(User employee, List<User> employeeList);

    Review findById(Long id);

    void update(Review aReview);

    Review createFinalReview(User employee, List<Review> reviewList);
}
