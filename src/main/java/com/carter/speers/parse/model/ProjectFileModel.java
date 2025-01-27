package com.carter.speers.parse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectFileModel {
    private Project project;
    private Build build;

    @JsonProperty("package")
    private Package aPackage;

    public ProjectFileModel() {

    }
    public ProjectFileModel(Project project, Build build, Package aPackage) {
        this.project = project;
        this.build = build;
        this.aPackage = aPackage;
    }

    @Override
    public String toString() {
        return "ProjectFileModel{" +
                "project=" + project +
                ", build=" + build +
                ", aPackage=" + aPackage +
                '}';
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
