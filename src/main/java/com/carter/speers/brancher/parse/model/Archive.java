package com.carter.speers.brancher.parse.model;

public class Archive {
    private String jarName;

    public Archive(String jarName) {
        this.jarName = jarName;
    }

    public String jarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "jarName='" + jarName + '\'' +
                '}';
    }
}
