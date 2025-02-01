package com.carter.speers.func;

import com.carter.speers.parse.model.ProjectFileModel;

import java.io.IOException;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Packager implements JavacExecutor {
    @Override
    public void executeCommand(ProjectFileModel pfm) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        final var outStr = String.format("./%s", pfm.getBuild().outDir());
        var outDir = new File(outStr);
        var libsDir = new File(outDir, "libs");
        File[] files = outDir.listFiles();
        if (files == null) {
            System.err.println("Failed to package jar file. make sure you have built your project" +
                    " first");
            System.exit(1);
        }
        if (!libsDir.exists() && !libsDir.mkdirs()) {
            System.err.println("Failed to create libs directory");
        }
        List<String> classFiles = Stream.of(files)
                .map(File::getName)
                .filter(name -> name.endsWith(".class"))
                .toList();

        String jarName =
                String.format("%s.jar", pfm.getaPackage().jarName());
        String mainClass =
                pfm.getProject().mainClass();

        ArrayList<String> command = new ArrayList<>(List.of("jar",
                "cfe",
                jarName,
                mainClass
                ));
        command.addAll(classFiles);
        try {
            Process p = processBuilder
                    .directory(outDir)
                    .command(command.toArray(new String[0]))
                    .inheritIO().start();

            p.waitFor();
            ProcessBuilder pb = new ProcessBuilder();
            Process mv = pb.directory(outDir)
                    .command("mv", "app.jar", "libs/app.jar")
                    .inheritIO()
                    .start();
            mv.waitFor();


        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
