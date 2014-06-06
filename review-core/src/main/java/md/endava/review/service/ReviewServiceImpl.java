package md.endava.review.service;

import md.endava.review.domain.Review;
import md.endava.review.domain.ReviewType;
import md.endava.review.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author apavel
 */
@Service(value = "reviewService")
public class ReviewServiceImpl extends AbstractServiceImpl implements ReviewService {

    @Override
    @Transactional
    public void persist(Review aReview) {
        getEntityManager().persist(aReview);
    }

    @Override
    @Transactional
    public List<Review> createReviewList(User employee, List<User> reviewerList) {
        List<Review> reviewList = new ArrayList<Review>();
        reviewList.add(createSelfReviewRequest(employee));
        reviewList.addAll(createRegularReviewRequest(employee, reviewerList));
        return reviewList;
    }

    private Review createSelfReviewRequest(final User employee) {
        Review review = new Review();
        review.setEmployee(employee);
        review.setSubmitter(employee);
        review.setReviewType(ReviewType.SELF);
        getEntityManager().persist(review);
        return review;
    }

    private List<Review> createRegularReviewRequest(final User employee, final List<User> reviewerList) {
        List<Review> reviewList = new ArrayList<Review>();
        for (User user : reviewerList) {
            Review review = new Review();
            review.setEmployee(employee);
            review.setSubmitter(user);
            review.setReviewType(ReviewType.REGULAR);
            getEntityManager().persist(review);
            reviewList.add(review);
        }
        return reviewList;
    }

    @Override
    public Review findById(Long id) {
        return getEntityManager().find(Review.class, id);
    }

    @Override
    @Transactional
    public void update(Review aReview) {
        getEntityManager().merge(aReview);
    }

    @Override
    @Transactional
    public Review createFinalReview(User employee, final List<Review> reviewList) {
        Review review = new Review();
        float markSum = 0;
        StringBuilder stringBuilder = new StringBuilder();
        final String newLine = "\n\n";
        for (Review r : reviewList) {
            r = findById(r.getId());
            stringBuilder.append(r.getContent());
            stringBuilder.append(newLine);
            markSum += r.getMark();
        }
        stringBuilder.delete(stringBuilder.lastIndexOf(newLine), stringBuilder.length()-1);

        review.setContent(stringBuilder.toString());
        review.setMark(Math.round(markSum/reviewList.size()));
        review.setEmployee(employee);
        User submitter = employee.getLm();
        review.setSubmitter(submitter);
        review.setReviewType(ReviewType.FINAL);
        getEntityManager().persist(review);
        return review;
    }
}
