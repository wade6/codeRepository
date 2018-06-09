package com.code.repository;
import org.jbpm.api.*;
import org.jbpm.api.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JbpmTest {

    @Test
    public void test(){
         System.out.println("test");
    }

    @Test
    public void createSchema() { // hbm2ddl.auto=update
        new org.hibernate.cfg.Configuration().configure("jbpm.hibernate.cfg.xml").buildSessionFactory();
    }

    // 一、部署流程定义 RepositoryService
    @Test
    public void deploy() {
        ProcessEngine processEngine = Configuration.getProcessEngine();// 创建流程引擎
        RepositoryService repositoryService = processEngine.getRepositoryService();// 获取流程资源服务接口
        repositoryService.createDeployment().addResourceFromClasspath("test.jpdl.xml").deploy();// 部署流程
    }

    // 生成一个流程实例   ExecutionService
    @Test
    public void createInstance() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        String processKey = "test";
        ExecutionService executionService = processEngine.getExecutionService();//执行服务
        Map<String, String> map = new HashMap<String, String>(); // 添加变量
        map.put("staff","hm员工");
        ProcessInstance processInstance = executionService.startProcessInstanceByKey(processKey,map);
        String id = processInstance.getId();
        System.out.println("流程实例id："+id);
    }

    // 查询任务列表 TaskService
    @Test
    public void findMyTaskList() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        // 查询任务
        String userId = "员工";
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.findPersonalTasks(userId);

        // 显示任务
        System.out.println("========= 【"+userId+"】的未办理的任务列表 =========");
        for (Task task : list) {
            System.out.println("id=" + task.getId()//
                    + ", name=" + task.getName()//
                    + ", assignee=" + task.getAssignee()//
                    + ", createTime=" + task.getCreateTime());
        }
    }

    // 获取流程变量  TaskService
    @Test
    public void fetchTaskVariable() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        String taskId = "40003";
        String instanceId= "test.40001";
        TaskService taskService = processEngine.getTaskService();
        ExecutionService executionService = processEngine.getExecutionService();
        // 取出当前的所有任务
        Set<String> activityNames = executionService.createProcessInstanceQuery().processInstanceId(instanceId).uniqueResult().findActiveActivityNames();
        String name = (String)activityNames.toArray()[0];
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).activityName(name).uniqueResult();
        // 根据任务取出变量
        Set<String> vars =  taskService.getVariableNames(task.getId());
        System.out.println(vars.toString());
        String staff = (String)taskService.getVariable(taskId,"staff");
        System.out.println("staff:"+staff);
    }

    // 完成当前任务  TaskService
    @Test
    public void completeTask() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        String taskId = "60001";
        TaskService taskService = processEngine.getTaskService();
        taskService.completeTask(taskId);
    }

}
