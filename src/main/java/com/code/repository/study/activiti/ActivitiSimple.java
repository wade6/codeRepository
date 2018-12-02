package com.code.repository.study.activiti;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;

/**
 * activiti-engine 5.16
 *
 * activiti 部署实例，具体流程操作在 ActivitiSimpleTest.java 中进行
 *
 */
public class ActivitiSimple {
    
    public static void main(String[] args){  
          System.out.println("===activiti test");

        // 加载配置文件，创建基础表
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti/activiti.cfg.xml").buildProcessEngine();
        System.out.println("创建表成功!");
        RepositoryService repositoryService = processEngine.getRepositoryService();// 持久服务
        repositoryService.createDeployment().addClasspathResource("activiti/myActiviti.bpmn").deploy();// 部署流程模型

        // 开始一个流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        String processId = runtimeService.startProcessInstanceByKey("Process_1").getId();
//        System.out.println("开始一个实例，processId=" + processId);// processId=2504

    }  
}
