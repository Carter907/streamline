package com.carter.speers.streamline.parse.model;

public class Build {
    private String srcDir;
    private String outDir;

    public Build(String srcDir, String outDir) {
        this.srcDir = srcDir;
        this.outDir = outDir;
    }

    public String srcDir() {
        return srcDir;
    }

    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

    public String outDir() {
        return outDir;
    }

    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }

    @Override
    public String toString() {
        return "Build{" +
                "srcDir='" + srcDir + '\'' +
                ", outDir='" + outDir + '\'' +
                '}';
    }
}
