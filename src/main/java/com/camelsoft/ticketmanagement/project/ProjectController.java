package com.camelsoft.ticketmanagement.project;

import com.camelsoft.ticketmanagement.user.User;
import com.camelsoft.ticketmanagement.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectMapper projectMapper;

    @GetMapping("/all-projects")
    public List<ProjectResponse> getAllProjects() {
        return projectMapper.toProjectListResponse(projectService.getAllProjects());
    }

    @GetMapping("/project/{id}")
    public ProjectResponse getProjectById(@PathVariable("id") Integer id) {
        return projectMapper.toProjectResponse(projectService.getProjectById(id));
    }

    /*
    @GetMapping("/project/{name}/{id}")
    public ResponseEntity<ProjectResponse> getProjectByNameAndClientId(@PathVariable("name") String name, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(projectService.getProjectByNameAndUserId(name,id));
    }

    @GetMapping("/project/{name}/{id}")
    public ResponseEntity<ProjectResponse> getProjectByNameAndAgentId(@PathVariable("name") String name, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(projectService.getProjectByNameAndUserId(name,id));
    }
    */

    @GetMapping("/Client-projects/{id}")
    public List<ProjectResponse> getProjectByClient(@PathVariable User id) {
        return projectMapper.toProjectListResponse(projectService.findByCreatedBy(id));
    }

    @GetMapping("/Agent-projects/{id}")
    public List<ProjectResponse> getProjectByAgent(@PathVariable User id) {
        return projectMapper.toProjectListResponse(projectService.findByAssignedTo(id));
    }

    @PostMapping("/create")
    public String createProject(@RequestBody ProjectRequest projectRequest) {
        System.out.println("assigned to : " +userService.getUserById(projectRequest.getAssignedTo()));
        System.out.println("assigned to : " +userService.getUserById(projectRequest.getCreatedBy()));
        User assignedTo = userService.getUserById(projectRequest.getAssignedTo());
        User createdBy = userService.getUserById(projectRequest.getCreatedBy());
        Project project = Project.builder()
                .projectName(projectRequest.getProjectName())
                .domainName(projectRequest.getDomainName())
                .projectLanguage(projectRequest.getProjectLanguage())
                .projectDetails(projectRequest.getProjectDetails())
                .projectType(projectRequest.getProjectType())
                .assignedTo(assignedTo)
                .createdBy(createdBy)
                .build();

        projectService.createProject(project);
        return "Project created successfully";
    }


    @PutMapping("/update/{id}")
    public  String updateProject(@PathVariable Integer id, @RequestBody ProjectRequest projectRequest) {
        User assignedTo = userService.getUserById(projectRequest.getAssignedTo());

        Project updatedProject = Project.builder()
                .projectName(projectRequest.getProjectName())
                .domainName(projectRequest.getDomainName())
                .projectLanguage(projectRequest.getProjectLanguage())
                .projectDetails(projectRequest.getProjectDetails())
                .projectType(projectRequest.getProjectType())
                .assignedTo(assignedTo)
                .build();
        projectService.updateProject(id, updatedProject);
        return "Project updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer id) {
        try {
            projectService.deleteProjectById(id);
            return ResponseEntity.ok("Project deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal error, please contact the admin");
        }
    }

    @GetMapping("/count/id")
    public Long countProjects() {
        return projectService.countProjects();
    }


}
