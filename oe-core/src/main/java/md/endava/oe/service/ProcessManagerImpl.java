package md.endava.oe.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author apavel
 */
@Service
public class ProcessManagerImpl implements ProcessManager {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oe-reporting");
    }
}
