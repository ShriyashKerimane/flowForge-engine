package com.flowForge.engine.execution;

import com.flowForge.engine.workflow.model.TaskNode;
import tools.jackson.databind.JsonNode;

public interface NodeHandler {

    void execute(JsonNode nodeData, JsonNode contextMap);

}