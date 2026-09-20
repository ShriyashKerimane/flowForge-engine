package com.flowForge.engine.workflow.initializer;

import com.flowForge.engine.execution.NodeHandler;
import com.flowForge.engine.workflow.model.TaskNode;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WorkflowInitializer {

    private final Map<String, String> workflows = new HashMap<>();
    private final Map<String, TaskNode> nodes = new HashMap<>();
    private final Map<String, NodeHandler> handlers = new HashMap<>();

    public WorkflowInitializer(List<NodeHandler> nodeHandlers) {

        for (NodeHandler handler : nodeHandlers) {
            handlers.put(handler.getType(), handler);
        }
    }

    public void initializeWorkflow(JsonNode workflow) {

        String workflowId = workflow.get("id").asString();
        int version = workflow.get("version").asInt();
        String workflowKey = workflowId + ":" + version;

        JsonNode definition = workflow.get("definition");
        JsonNode workflowNodes = definition.get("nodes");
        JsonNode edges = definition.get("edges");
        String startNodeId = null;
        String firstExecutionNodeId = null;

        for (JsonNode node : workflowNodes) {

            String nodeId = node.get("id").asString();

            JsonNode data = node.get("data");
            String nodeType = data.get("type").asString();

            if ("start".equalsIgnoreCase(data.get("type").asString())) {
                startNodeId = node.get("id").asString();
                continue;
            }

            NodeHandler handler = handlers.get(nodeType);

            if (handler == null) {
                throw new IllegalStateException("No handler found for node type: " + nodeType);
            }

            TaskNode taskNode = new TaskNode(nodeId, data, handler);
            nodes.put(nodeId, taskNode);
        }

        for (JsonNode edge : edges) {

            String sourceNodeId = edge.get("source").asString();
            String targetNodeId = edge.get("target").asString();

            TaskNode sourceNode = nodes.get(sourceNodeId);

            if (sourceNode == null && sourceNodeId.equalsIgnoreCase(startNodeId)) {
                firstExecutionNodeId = targetNodeId;
                continue;
            }
            if(sourceNode != null){
                sourceNode.addNextNode(targetNodeId);
            }
        }

        if (startNodeId == null) {
            throw new IllegalStateException("Start node not found for workflow: " + workflowKey);
        }   

        workflows.put(workflowKey, firstExecutionNodeId);
    }

    public String getStartNodeId(String workflowId, int version) {

        return workflows.get(workflowId + ":" + version);
    }

    public TaskNode getNode(String nodeId) {

        return nodes.get(nodeId);
    }
}