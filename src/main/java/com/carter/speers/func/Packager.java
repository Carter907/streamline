package com.carter.speers.func;

import com.carter.speers.parse.model.ProjectFileModel;

import java.io.IOException;
import java.io.File;

public class Packager implements JavacExecutor {
    @Override
    public void executeCommand(ProjectFileModel pfm) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        final var outStr = String.format("./%s", pfm.getBuild().outDir());
        var outDir = new File(outStr);
        File[] files = outDir.listFiles();
        if (files == null) {
            System.err.println("Failed to package jar file. Make sure you have built your project first.");
            System.exit(1);
        }
  
        String jarName = String.format("%s/lib/%s.jar", pfm.getBuild().outDir(), pfm.getaPackage().jarName());
        String mainClass = pfm.getProject().mainClass();
        
        try {
            Process p = processBuilder
                    .command(
                "jar",
                "--create",
                "--file",
                jarName,
                "--main-class",
                mainClass, "-C",
                pfm.getBuild().outDir(),
                "."
            ).inheritIO().start();

            p.waitFor();
            // ProcessBuilder pb = new ProcessBuilder();
            // Process mv = pb.directory(outDir)
            // .command("mv", jarName, "libs/" + jarName)
            // .inheritIO()
            // .start();
            // mv.waitFor();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
