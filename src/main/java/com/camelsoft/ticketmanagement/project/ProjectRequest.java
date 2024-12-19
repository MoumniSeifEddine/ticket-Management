package com.camelsoft.ticketmanagement.project;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProjectRequest {
    @NotEmpty
    private String projectName;

    @NotEmpty
    private String domainName;

    private String image;

    @NotEmpty
    private String projectLanguage;

    @NotEmpty
    private String projectDetails;

    @NotNull
    private ProjectType projectType;

    @NotNull
    private Integer createdBy;

    @NotNull
    private Integer assignedTo;
}
