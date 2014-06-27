package md.endava.review.service;

import org.activiti.engine.history.HistoricTaskInstance;

/**
 * @author apavel
 */
public interface ReportService extends AbstractService {

    java.util.List<HistoricTaskInstance> getTaskDurationInformation();
}
