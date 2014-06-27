package md.endava.review.web.controller;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author apavel
 */
@Controller
public class ReportController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/review-report", method = RequestMethod.GET)
    public String showTaskDurationInformation(Model uiModel) {
        final ProcessDefinition reviewProcess = repositoryService.createProcessDefinitionQuery().processDefinitionKey("reviewProcess").singleResult();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("processDefinition", reviewProcess);
        final ProcessInstance processInstance =
            runtimeService.startProcessInstanceByKey("task-duration-report", variables);
        final byte[] reportData = (byte[]) historyService.createHistoricVariableInstanceQuery().processInstanceId(
            processInstance.getId()).variableName("reportData").singleResult().getValue();
        uiModel.addAttribute("reportData", new String(reportData));
        return "review-report";
    }
}
