package com.camelsoft.ticketmanagement.project;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMapper {
    public ProjectResponse toProjectResponse(Project project) {
        return ProjectResponse.builder()
                        .id(project.getId())
                        .projectName(project.getProjectName())
                        .domainName(project.getDomainName())
                        .projectDetails(project.getProjectDetails())
                        .projectType(String.valueOf(project.getProjectType()))
                        .projectLanguage(project.getProjectLanguage())
                        .lastModifiedDate(project.getLastModifiedDate())
                        .createdDate(project.getCreatedDate())
                        .createdBy(project.getCreatedBy().getEmail())
                        .assignedTo(project.getAssignedTo().getEmail())
                .build();
    }
    public List<ProjectResponse> toProjectListResponse(List<Project> projects) {
        return projects.stream()
                .map(this::toProjectResponse)
                .collect(Collectors.toList());
    }

}