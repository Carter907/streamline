package com.carter.speers.brancher.parse.model;

public class Project {
    private String name;
    private String mainClass;

    public Project(String name, String mainClass) {
        this.name = name;
        this.mainClass = mainClass;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String mainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", mainClass='" + mainClass + '\'' +
                '}';
    }
}
