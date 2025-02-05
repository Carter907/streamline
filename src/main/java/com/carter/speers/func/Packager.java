package com.carter.speers.func;

import com.carter.speers.parse.model.ProjectFileModel;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        List<String> classFiles = Stream.of(files)
                .map(File::getName)
                .filter(name -> name.endsWith(".class"))
                .toList();

        String jarName = String.format("%s/lib/%s.jar", pfm.getBuild().outDir(), pfm.getaPackage().jarName());
        String mainClass = pfm.getProject().mainClass();

        ArrayList<String> command = new ArrayList<>(List.of("jar",
                "--create",
                "--file",
                jarName,
                "--main-class",
                mainClass, "-C", pfm.getBuild().outDir(), "."));
        try {
            Process p = processBuilder
                    .command(command.toArray(new String[0]))
                    .inheritIO().start();

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
