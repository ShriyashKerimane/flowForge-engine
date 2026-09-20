package com.flowForge.engine.workflow.model;

import com.flowForge.engine.execution.NodeHandler;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class TaskNode {

    private String nodeId;

    private JsonNode data;

    private List<String> nextNodes;

    private NodeHandler handler;

    public TaskNode() {
        this.nextNodes = new ArrayList<>();
    }

    public TaskNode(
            String nodeId,
            JsonNode data,
            NodeHandler handler
    ) {
        this.nodeId = nodeId;
        this.data = data;
        this.handler = handler;
        this.nextNodes = new ArrayList<>();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public List<String> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<String> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public void addNextNode(String nodeId) {
        this.nextNodes.add(nodeId);
    }

    public NodeHandler getHandler() {
        return handler;
    }

    public void setHandler(NodeHandler handler) {
        this.handler = handler;
    }


}
