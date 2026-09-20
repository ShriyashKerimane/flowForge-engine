package com.flowForge.engine.workflow.service;

import com.flowForge.engine.workflow.repository.WorkflowDefinitionRepository;
import com.flowForge.engine.workflow.entity.WorkflowDefinitionEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class WorkflowDefinitionService {

    private final WorkflowDefinitionRepository workflowDefinitionRepository;

    private final ObjectMapper objectMapper;

    public WorkflowDefinitionService(
            WorkflowDefinitionRepository workflowDefinitionRepository,
            ObjectMapper objectMapper) {

        this.workflowDefinitionRepository = workflowDefinitionRepository;
        this.objectMapper = objectMapper;
    }

    public List<WorkflowDefinitionEntity> getAllWorkflowDefinitions() {
        return workflowDefinitionRepository.findAll();
    }

    public WorkflowDefinitionEntity saveWorkflowDefinition(
            JsonNode workflowJson) {

        WorkflowDefinitionEntity workflowDefinition =
                objectMapper.convertValue(
                        workflowJson,
                        WorkflowDefinitionEntity.class
                );

        OffsetDateTime currentTime = OffsetDateTime.now();

        if (workflowDefinition.getCreatedAt() == null) {
            workflowDefinition.setCreatedAt(currentTime);
        }

        if (workflowDefinition.getUpdatedAt() == null) {
            workflowDefinition.setUpdatedAt(currentTime);
        }

        return workflowDefinitionRepository.save(workflowDefinition);
    }
}