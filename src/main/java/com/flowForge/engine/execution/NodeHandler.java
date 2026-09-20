package com.flowForge.engine.execution;

import com.flowForge.engine.workflow.model.TaskNode;
import tools.jackson.databind.JsonNode;

public interface NodeHandler {

    String getType();

    void execute(JsonNode nodeData, JsonNode contextMap);

}