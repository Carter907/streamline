package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.model.ProjectFileModel;

import java.io.File;
import java.io.IOException;

public final class RunCommand extends ProjectCommand {
    public RunCommand(ProjectFileModel model) {
        super(model);
    }

    @Override
    public void execute() {
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
