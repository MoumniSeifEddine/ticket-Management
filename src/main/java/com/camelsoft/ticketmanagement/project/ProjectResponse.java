package com.camelsoft.ticketmanagement.project;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private Integer id;
    private String projectName;
    private String domainName;
    private String image;
    private String projectLanguage;
    private String projectDetails;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String projectType;
    private String createdBy;
    private String assignedTo;
}
