package md.endava.review.web.controller;

import md.endava.review.domain.Review;
import md.endava.review.domain.User;
import md.endava.review.security.SessionUser;
import md.endava.review.service.ReviewService;
import md.endava.review.service.UserService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author apavel
 */
@Controller
@SessionAttributes("review")
public class ReviewController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/employee-list", method = RequestMethod.GET)
    public String employeeList(Model uiModel) {
        SessionUser currentUser = getSessionUser();
        Map<User, Boolean> employeeList = new LinkedHashMap<User, Boolean>();
        for (User emp : userService.findByLmId(currentUser.getId())) {
            employeeList.put(emp, !taskService.createTaskQuery().processDefinitionKey("reviewProcess").processVariableValueEquals(emp).list().isEmpty());
        }
        uiModel.addAttribute("employeeList", employeeList);
        return "employee-list";
    }

    private SessionUser getSessionUser() {
        return (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = "/review-request/{empId}", method = RequestMethod.GET)
    public String createReviewRequestForm(@PathVariable("empId") Long empId, Model uiModel) {
        uiModel.addAttribute("employee", userService.findById(empId));
        uiModel.addAttribute("review", new Review());
        uiModel.addAttribute("reviewers", userService.findReviewers(empId));
        return "review-request";
    }

    @RequestMapping(value = "/review-request", method = RequestMethod.POST)
    public String createReviewRequest(@ModelAttribute Review review, SessionStatus aSessionStatus) {
        Map<String, Object> processVariables = new HashMap<String, Object>();
        processVariables.put("reviewers", userService.findIn(review.getReviewers()));
        processVariables.put("employee", userService.findById(review.getEmployee().getId()));
        runtimeService.startProcessInstanceByKey("reviewProcess", processVariables);
        aSessionStatus.setComplete();
        return "redirect:/employee-list";
    }

    @RequestMapping(value = "/review/{taskId}", method = RequestMethod.GET)
    public String createReviewForm(@PathVariable String taskId, Model uiModel) {
        Review review = (Review) taskService.getVariable(taskId, "review");
        uiModel.addAttribute("review", review);
        uiModel.addAttribute("taskId", taskId);
        if (review.getApproved() != null && !review.getApproved()) {
            uiModel.addAttribute("rejectReason", taskService.getVariable(taskId, "rejectReason"));
        }
        return "review";
    }

    @RequestMapping(value = "/review/{taskId}", method = RequestMethod.POST)
    public String createReview(
        @ModelAttribute Review review,
        @PathVariable String taskId,
        SessionStatus aSessionStatus) {
        reviewService.update(review);
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("review", review);
        taskService.complete(taskId, variables);
        aSessionStatus.setComplete();
        return "redirect:/review-list";
    }

    @RequestMapping(value = "/review-approval/{taskId}", method = RequestMethod.GET)
    public String createReviewApprovalForm(@PathVariable String taskId, Model uiModel) {
        Review review = (Review) taskService.getVariable(taskId, "review");
        uiModel.addAttribute("review", review);
        uiModel.addAttribute("taskId", taskId);
        return "review-approval";
    }

    @RequestMapping(value = "/review-approval/{taskId}", method = RequestMethod.POST)
    public String createReviewApproval(
        @ModelAttribute Review review,
        @PathVariable String taskId,
        @RequestParam String rejectReason,
        SessionStatus aSessionStatus) {
        reviewService.update(review);
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("review", review);
        if (!review.getApproved()) {
        	String executionId = taskService.createTaskQuery().taskId(taskId).singleResult().getExecutionId();
            runtimeService.setVariableLocal(executionId, "rejectReason", rejectReason);
        }
        taskService.complete(taskId, variables);
        aSessionStatus.setComplete();
        return "redirect:/review-list";
    }

    @RequestMapping(value = "/review-list", method = RequestMethod.GET)
    public String reviewList(Model uiModel) {
        SessionUser currentUser = getSessionUser();
        uiModel.addAttribute("taskList", taskService.createTaskQuery().taskAssignee(currentUser.getUsername()).list());
        return "review-list";
    }

}
