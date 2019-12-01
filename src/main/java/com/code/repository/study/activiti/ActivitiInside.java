package com.code.repository.study.activiti;

import de.odysseus.el.util.SimpleContext;
import org.activiti.bpmn.converter.BaseBpmnXMLConverter;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.apache.el.ExpressionFactoryImpl;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内存嵌入式acitivi流程引擎
 */
public class ActivitiInside {

    public static void main(String[] args) throws XMLStreamException {

        String filePath="";
        String processId="test1";
        Map<String,Object> variables = new HashMap<>();
        variables.put("name","tt");
        // 加载流程文件 BPMN标准
        InputStream fileinputStream = loadFile(filePath);
        // 装载BPMN模型
        XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlFactory.createXMLStreamReader(fileinputStream);
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(reader);

        // 获取开始节点,子流程
        StartEvent startEvent = null;
        SubProcess subProcess = null;
        Process process = bpmnModel.getProcessById(processId);
        Collection<FlowElement> flowList =  process.getFlowElements();
        for(FlowElement flowElement : flowList){
            if(flowElement instanceof StartEvent){ // 开始节点
                startEvent = (StartEvent) flowElement;
            }
            if(flowElement instanceof SubProcess){ // 子流程
                subProcess = (SubProcess) flowElement;
            }
        }

        // 获取后续节点
        getNextEvent(startEvent.getId(),process,variables);
    }

    // 获取后续节点
    private static void getNextEvent(String eventId,Process process,Map<String,Object> variables){
        FlowElement nowflowElement = process.getFlowElement(eventId);
        FlowNode flowNode = (FlowNode) nowflowElement;
        List<SequenceFlow> nextFlows = flowNode.getOutgoingFlows();// 连接线
        for(SequenceFlow sequenceFlow : nextFlows){
            String express = sequenceFlow.getConditionExpression(); // 条件表达式
            if(express!=null){ // 判断表达式
                ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
                SimpleContext context = new SimpleContext();
                context.setVariable("name",expressionFactory.createValueExpression(variables.get("name"),String.class));
                ValueExpression vel = expressionFactory.createValueExpression(context,express,Boolean.class);
                Boolean result = (Boolean) vel.getValue(context);
                if(!result){
                    continue;
                }
            }
            String elementId = sequenceFlow.getTargetRef(); // 任务节点，打印任务参数
            FlowElement flowElement = process.getFlowElement(elementId);
            if(flowElement instanceof Task){
                //捞取节点参数
//                flowElement.getExtensionElements().get("property");
                String name = flowElement.getAttributeValue(null,"name");
                String value =flowElement.getAttributeValue(null,"value");
                System.out.println("name:"+name+",value:"+value);
            }
        }
    }

    private static InputStream loadFile(String filePath){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        if(inputStream == null){ // 不在jar文件，从系统文件取
            try {
                inputStream = new FileInputStream(filePath);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return inputStream;
    }
}
