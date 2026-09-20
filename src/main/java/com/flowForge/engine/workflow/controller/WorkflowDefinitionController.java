package com.flowForge.engine.workflow.controller;


import com.flowForge.engine.workflow.entity.WorkflowDefinitionEntity;
import com.flowForge.engine.workflow.service.WorkflowDefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowDefinitionController {

    private final WorkflowDefinitionService workflowDefinitionService;
    private final ObjectMapper objectMapper= new ObjectMapper();

    public WorkflowDefinitionController(
            WorkflowDefinitionService workflowDefinitionService) {

        this.workflowDefinitionService = workflowDefinitionService;
    }

    @GetMapping
    public ResponseEntity<List<WorkflowDefinitionEntity>> getAllWorkflows() {

        List<WorkflowDefinitionEntity> workflows = workflowDefinitionService.getAllWorkflowDefinitions();

        return ResponseEntity.ok(workflows);
    }

    @PostMapping
    public ResponseEntity<WorkflowDefinitionEntity> saveWorkflow(
            @RequestBody JsonNode workflowJson) {

        WorkflowDefinitionEntity savedWorkflow =
                workflowDefinitionService.saveWorkflowDefinition(
                        workflowJson
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedWorkflow);
    }
}