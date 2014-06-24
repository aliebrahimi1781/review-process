package md.endava.review.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author apavel
 */
@Entity
@Table(name = "pm_review")
public class Review extends GenericEntity {

    @Enumerated(value = EnumType.STRING)
    private ReviewType reviewType;
    
    private String content;
    
    private Integer mark;
    
    private Boolean approved;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;
    @ManyToOne
    @JoinColumn(name = "submitter_id")
    private User submitter;
    @Transient
    private List<Long> reviewers;

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(final ReviewType aReviewType) {
        reviewType = aReviewType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String aContent) {
        content = aContent;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(final Integer aMark) {
        mark = aMark;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(final Boolean aApproved) {
        approved = aApproved;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(final User aEmployee) {
        employee = aEmployee;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(final User aSubmitter) {
        submitter = aSubmitter;
    }

    public List<Long> getReviewers() {
        return reviewers;
    }

    public void setReviewers(final List<Long> aReviewers) {
        reviewers = aReviewers;
    }
}
