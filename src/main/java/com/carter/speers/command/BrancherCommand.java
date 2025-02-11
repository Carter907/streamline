package com.carter.speers.command;

import com.carter.speers.parse.model.ProjectFileModel;

import java.io.File;
import java.io.IOException;
import java.util.spi.ToolProvider;

public class BrancherCommand implements JavacCommand {

    private ProjectFileModel model;

    public BrancherCommand(ProjectFileModel model) {

        this.model = model;
    }

    public void getInfo() {

    }

    @Override
    public void build() {

        ToolProvider javac = ToolProvider.findFirst("javac").orElseThrow();

        javac.run(System.out, System.err,
                "-cp",
                model.build().sourceDir(),
                "-d",
                model.build().outDir(),
                String.format("%s/%s.java", model.build().sourceDir(),
                        model.project().mainClass()));
    }

    @Override
    public void archive() {
        final var outStr = String.format("./%s", model.build().outDir());
        var outDir = new File(outStr);
        File[] files = outDir.listFiles();
        if (files == null) {
            System.err.println("Failed to package jar file. Make sure you have built your project first.");
            System.exit(1);
        }

        String jarName = String.format("%s/lib/%s.jar", model.build().outDir(),
                model.archive().jarName());
        String mainClass = model.project().mainClass();

        ToolProvider jar = ToolProvider.findFirst("jar").orElseThrow();
        jar.run(System.out, System.err,
                "--create",
                "--file",
                jarName,
                "--main-class",
                mainClass, "-C",
                model.build().outDir(),
                ".");
    }

    @Override
    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                String.format("%s.java",
                        model.project().mainClass()));
        try {

            Process p = processBuilder
                    .directory(new File(model.build().sourceDir()))
                    .inheritIO()
                    .start();

            p.waitFor();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getCause());
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception: " + e.getCause());
            System.exit(1);
        }
    }

}
