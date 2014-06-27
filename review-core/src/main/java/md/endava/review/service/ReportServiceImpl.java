package md.endava.review.service;

import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author apavel
 */
@Service(value = "reportService")
public class ReportServiceImpl extends AbstractServiceImpl implements ReportService {

    @Autowired
    private org.activiti.engine.HistoryService historyService;

    @Override
    public java.util.List<HistoricTaskInstance> getTaskDurationInformation() {
        return historyService.createHistoricTaskInstanceQuery().processDefinitionKey("reviewProcess").list();
    }
}
