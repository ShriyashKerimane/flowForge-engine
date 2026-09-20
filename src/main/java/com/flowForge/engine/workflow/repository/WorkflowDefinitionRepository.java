package com.flowForge.engine.workflow.repository;

import com.flowForge.engine.workflow.entity.WorkflowDefinitionEntity;
import com.flowForge.engine.workflow.entity.WorkflowDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowDefinitionRepository
        extends JpaRepository<WorkflowDefinitionEntity, WorkflowDefinitionId> {
}