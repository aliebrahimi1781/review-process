package md.endava.review.web.controller;

import md.endava.review.service.ReviewService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author apavel
 */
@Controller
@SessionAttributes(value = {"report"})
public class AdminController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/admin/process-list", method = RequestMethod.GET)
    public String processList(Model uiModel) {
        uiModel.addAttribute("processList", repositoryService.createProcessDefinitionQuery().list());
        return "admin/process-list";
    }

    @RequestMapping(value = "/admin/start-process/{key}", method = RequestMethod.GET)
    public String startProcess(@PathVariable("key") String key) {
        runtimeService.startProcessInstanceByKey(key);
        return "admin/process-list";
    }


}
