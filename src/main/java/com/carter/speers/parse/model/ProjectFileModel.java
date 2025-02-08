package com.carter.speers.parse.model;

public class ProjectFileModel {
    private Project project;
    private Build build;
    private Archive archive;

    public ProjectFileModel() {

    }
    public ProjectFileModel(Project project, Build build, Archive archive) {
        this.project = project;
        this.build = build;
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "ProjectFileModel{" +
                "project=" + project +
                ", build=" + build +
                ", aPackage=" + archive +
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

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }
}
