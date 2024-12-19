package com.camelsoft.ticketmanagement.project;

import com.camelsoft.ticketmanagement.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByProjectName(String projectName);
    List<Project> findByProjectNameContaining(String keyword);
    List<Project> findByProjectType(ProjectType projectType);
    List<Project> findByAssignedTo(User assignedTo);
    List<Project> findByCreatedBy(User createdBy);
    //List<Project> findByProjectNameAndUserId(String projectName, Integer userId);
}
