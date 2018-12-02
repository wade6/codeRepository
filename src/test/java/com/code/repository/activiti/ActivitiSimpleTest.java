package com.code.repository.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiSimpleTest {

    @Test
    public void test(){
         System.out.println("=== ActivitiSimpleTest ok!");
    }


    // 1. 部署流程定义 RepositoryService
    @Test
    public void deploy() {
        // 加载配置文件，创建基础表
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti/activiti.cfg.xml").buildProcessEngine();
        System.out.println("创建表成功!");
        RepositoryService repositoryService = processEngine.getRepositoryService();// 获得部署服务
        repositoryService.createDeployment().addClasspathResource("activiti/myActiviti.bpmn").deploy();// 部署流程模型
        System.out.println("流程部署成功!");
    }

    public static ProcessEngine getProcessEngine(){
        return ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti/activiti.cfg.xml").buildProcessEngine();
    }

    // 2. 生成一个流程实例   ExecutionService
    @Test
    public void createInstance() {
        // 开始一个流程实例
        ProcessEngine processEngine = getProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();// 获得运行时服务
        System.out.println("执行服务成功!");
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey("Process_1");
        Map<String, Object> map = new HashMap<String, Object>(); // 添加变量
        map.put("staff","hm2员工");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("Process_3",map);
        System.out.println("开始一个实例，processId=" + instance.getId()+",activitiId:"+instance.getActivityId());// processId=2504,7501
    }

    // 查询任务列表 TaskService
    @Test
    public void findMyTaskList() {
        ProcessEngine processEngine = getProcessEngine();
        // 查询任务
        TaskService taskService = processEngine.getTaskService();// 获得任务服务
        List<Task> taskList = taskService.createTaskQuery().list();


        // 显示任务
        System.out.println("=========未办理的任务列表 ========="+taskList.size());
        for (Task task : taskList) {
            System.out.println("id=" + task.getId()//
                    + ", name=" + task.getName()//
                    + ", assignee=" + task.getAssignee()//
                    + ", createTime=" + task.getCreateTime());
        }
    }

    // 完成当前任务  TaskService
    @Test
    public void completeTask() {
        ProcessEngine processEngine = getProcessEngine();
        String taskId = "2504";
        TaskService taskService = processEngine.getTaskService();
        taskService.complete(taskId);
    }

}
