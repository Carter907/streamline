package com.carter.speers.command;

import com.carter.speers.parse.model.ProjectFileModel;

import java.io.File;
import java.io.IOException;

public class BrancherCommand implements JavacCommand {

    private ProjectFileModel model;

    public BrancherCommand(ProjectFileModel model) {

        this.model = model;
    }

    public void getInfo() {
        
    }


    @Override
    public void build() {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "javac",
                "-cp",
                model.getBuild().sourceDir(),
                "-d",
                model.getBuild().outDir(),
                String.format("%s/%s.java", model.getBuild().sourceDir(),
                        model.getProject().mainClass()));
        try {
            Process p = processBuilder.inheritIO().start();

            p.waitFor();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void archive() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        final var outStr = String.format("./%s", model.getBuild().outDir());
        var outDir = new File(outStr);
        File[] files = outDir.listFiles();
        if (files == null) {
            System.err.println("Failed to package jar file. Make sure you have built your project first.");
            System.exit(1);
        }

        String jarName = String.format("%s/lib/%s.jar", model.getBuild().outDir(),
                model.getArchive().jarName());
        String mainClass = model.getProject().mainClass();

        try {
            Process p = processBuilder
                    .command(
                            "jar",
                            "--create",
                            "--file",
                            jarName,
                            "--main-class",
                            mainClass, "-C",
                            model.getBuild().outDir(),
                            "."
                    ).inheritIO().start();

            p.waitFor();


        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                String.format("%s.java",
                        model.getProject().mainClass()));
        try {

            Process p = processBuilder
                    .directory(new File(model.getBuild().sourceDir()))
                    .inheritIO()
                    .start();

            p.waitFor();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
