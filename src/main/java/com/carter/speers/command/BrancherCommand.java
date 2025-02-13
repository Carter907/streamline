package com.carter.speers.command;

import com.carter.speers.io.SourceFileWalker;
import com.carter.speers.parse.model.ProjectFileModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.spi.ToolProvider;

public class BrancherCommand {

    private ProjectFileModel model;

    public BrancherCommand(ProjectFileModel model) {

        this.model = model;
    }

    public void getInfo() {

    }

    public void build() {

        if (model.modules() == null) {
            ToolProvider javac = ToolProvider.findFirst("javac").orElseThrow();

            javac.run(System.out, System.err,
                    "-cp",
                    model.build().srcDir(),
                    "-d",
                    model.build().outDir(),
                    String.format("%s/%s.java", model.build().srcDir(),
                            model.project().mainClass()));
        } else {
            buildWithModules();
        }
    }

    private void buildWithModules() {
        ToolProvider javac = ToolProvider.findFirst("javac").orElseThrow();

        SourceFileWalker srcFiles = new SourceFileWalker();
        try {
            Path src = Path.of(model.build().srcDir());
            Files.walkFileTree(src,
                    srcFiles);
        } catch (IOException e) {
            System.err.println("Walk source file tree failed: " + e.getMessage());
            System.exit(1);
        }

        String outPath = model.build().outDir();
        String moduleSourcePath = model.modules().moduleSrc() != null ? model.modules().moduleSrc() : model.build().srcDir();
        List<String> source = srcFiles.getSourceFiles().stream()
                .map(Path::toString)
                .toList();
        List<String> args = new ArrayList<>(List.of(
                "-d",
                outPath,
                "--module-source-path",
                moduleSourcePath
        ));
        args.addAll(source);
        javac.run(System.out, System.err, args.toArray(String[]::new));
    }

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

    public void run() {
        if (model.modules() == null) {
            runWithUnnamedModule();
        } else {
            runWithModules();
        }

    }

    private void runWithModules() {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                "--module-path",
                model.modules().modulePath() != null ? model.modules().modulePath() : model.build().outDir(),
                "-m",
                String.format("%s/%s", model.modules().mainModule(),
                        model.project().mainClass()));
        try {

            Process p = processBuilder
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

    private void runWithUnnamedModule() {

        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                String.format("%s.java",
                        model.project().mainClass()));
        try {

            Process p = processBuilder
                    .directory(new File(model.build().srcDir()))
                    .inheritIO()
                    .start();

            p.waitFor();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception: " + e.getMessage());
            System.exit(1);
        }
    }

}
