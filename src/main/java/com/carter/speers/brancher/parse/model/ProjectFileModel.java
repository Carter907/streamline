package com.carter.speers.brancher.parse.model;

public class ProjectFileModel {
        private Project project;
        private Build build;
        private Archive archive;
        private Modules modules;


    public ProjectFileModel(Project project, Build build, Archive archive, Modules modules) {
        this.project = project;
        this.build = build;
        this.archive = archive;
        this.modules = modules;
    }

    public Project project() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Build build() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public Archive archive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public Modules modules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "ProjectFileModel{" +
                "project=" + project +
                ", build=" + build +
                ", archive=" + archive +
                ", modules=" + modules +
                '}';
    }
}

