package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.model.ProjectFileModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public final class RunCommand extends ProjectCommand {
    public RunCommand(ProjectFileModel model) {
        super(model);
    }

    @Override
    public void execute() {
        if (!Files.exists(Path.of(model.build().outDir()))) {
            System.err.println("Output directory " + model.build().outDir() + " not found\n" +
                    "Make sure you build your project first");
            System.exit(1);
        }

        if (model.modules() == null) {
            runWithUnnamedModule();
        } else {
            runWithModules();
        }
    }

    private void runWithModules() {

        String[] args = {
                "java",
                "--module-path",
                model.modules().modulePath() != null ?
                        String.join(":", model.modules().modulePath()) :
                        model.build().outDir(),
                "-m",
                String.format("%s/%s", model.modules().mainModule(),
                        model.project().mainClass())
        };
        System.out.println(Arrays.toString(args));

        ProcessBuilder processBuilder = new ProcessBuilder(args);
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

        String[] args = {
                "java",
                String.format("%s.java",
                        model.project().mainClass()),
        };
        System.out.println(Arrays.toString(args));

        ProcessBuilder processBuilder = new ProcessBuilder(args);

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
