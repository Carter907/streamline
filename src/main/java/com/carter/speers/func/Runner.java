package com.carter.speers.func;

import com.carter.speers.parse.model.ProjectFileModel;

import java.io.IOException;

public class Runner implements JavacExecutor {
    @Override
    public void executeCommand(ProjectFileModel projectFileModel) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                "-cp",
                projectFileModel.getBuild().outDir(),
                String.format("%s",
                        projectFileModel.getProject().mainClass()));
        try {
            Process p = processBuilder.inheritIO().start();

            p.waitFor();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
