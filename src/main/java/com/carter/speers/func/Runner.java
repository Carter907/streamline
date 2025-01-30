package com.carter.speers.func;

import com.carter.speers.parse.model.ProjectFileModel;

import java.io.File;
import java.io.IOException;

public class Runner implements JavacExecutor {
    @Override
    public void executeCommand(ProjectFileModel projectFileModel) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                String.format("%s.java",
                        projectFileModel.getProject().mainClass()));
        try {

            Process p = processBuilder
                    .directory(new File(projectFileModel.getBuild().sourceDir()))
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
