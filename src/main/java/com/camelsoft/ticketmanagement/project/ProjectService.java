package com.camelsoft.ticketmanagement.project;

import com.camelsoft.ticketmanagement.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Long countProjects() {
        return projectRepository.count();
    }

    public void deleteProjectById(Integer id) {
        projectRepository.deleteById(id);
    }


    public List<Project> findByProjectName(String projectName) {
        return projectRepository.findByProjectName(projectName);
    }

    public List<Project> findByProjectNameContaining(String keyword) {
        return projectRepository.findByProjectNameContaining(keyword);
    }

    public List<Project> findByProjectType(ProjectType projectType) {
        return projectRepository.findByProjectType(projectType);
    }

    public List<Project> findByAssignedTo(User assignedTo) {
        return projectRepository.findByAssignedTo(assignedTo);
    }

    public List<Project> findByCreatedBy(User createdBy) {
        return projectRepository.findByCreatedBy(createdBy);
    }

    public Project updateProject(Integer id, Project updatedProject) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setProjectName(updatedProject.getProjectName());
                    project.setDomainName(updatedProject.getDomainName());
                    project.setImage(updatedProject.getImage());
                    project.setProjectLanguage(updatedProject.getProjectLanguage());
                    project.setProjectDetails(updatedProject.getProjectDetails());
                    project.setProjectType(updatedProject.getProjectType());
                    project.setAssignedTo(updatedProject.getAssignedTo());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    public void createProject(Project requestedProject) {
        var project = Project.builder()
                .projectName(requestedProject.getProjectName())
                .domainName(requestedProject.getDomainName())
                .image(requestedProject.getImage())
                .projectLanguage(requestedProject.getProjectLanguage())
                .projectDetails(requestedProject.getProjectDetails())
                .projectType(requestedProject.getProjectType())
                .assignedTo(requestedProject.getAssignedTo())
                .createdBy(requestedProject.getCreatedBy())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        projectRepository.save(project);
    }

    /*public List<Project> getProjectByNameAndUserId(String projectName, Integer userId) {
        return projectRepository.findByProjectNameAndUserId(projectName, userId);
    }*/

}