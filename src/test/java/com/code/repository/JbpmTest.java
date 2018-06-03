package com.code.repository;
import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    // 一、部署流程定义
    @Test
    public void deployProcessDefinition() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addResourceFromClasspath("jbpm.jpdl.xml").deploy();
    }

    @Test
    public void startProcessInstance() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        processEngine.getExecutionService().startProcessInstanceByKey("test");
    }

    @Test
    public void findMyTaskList() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        // 查询任务
        String userId = "员工";
        List<Task> list = processEngine.getTaskService().findPersonalTasks(userId);

        // 显示任务
        System.out.println("========= 【"+userId+"】的未办理的任务列表 =========");
        for (Task task : list) {
            System.out.println("id=" + task.getId()//
                    + ", name=" + task.getName()//
                    + ", assignee=" + task.getAssignee()//
                    + ", createTime=" + task.getCreateTime());
        }
    }

    @Test
    public void completeTask() {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        String taskId = "20002";
        processEngine.getTaskService().completeTask(taskId);
    }



}
