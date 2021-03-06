package md.endava.review.process;

import md.endava.review.domain.User;
import md.endava.review.service.ReviewService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author apavel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti-test-context.xml")
public class ReviewTest {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    @Rule
    public ActivitiRule activitiRule;
    @Mock
    private ReviewService reviewService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Deployment(resources = {"process/review.bpmn20.xml"})
    public void simpleProcessTest() {
//        Map<String, Object> processVariables = new HashMap<String, Object>();
//        processVariables.put("reviewers", createReviewers());
//        processVariables.put("employee", createEmployee());
//        runtimeService.startProcessInstanceById("reviewProcess", processVariables);
    }

    private List<User> createReviewers() {
        List<User> users = new ArrayList<User>();
        User user = new User();
        user.setId(1L);
        users.add(user);
        return users;
    }

    private User createEmployee() {
        User user = new User();
        user.setId(2L);
        return user;
    }


}
