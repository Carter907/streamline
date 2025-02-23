package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.ProjectTomlWriter;
import com.carter.speers.brancher.parse.model.*;

import java.io.*;
import java.util.Scanner;

public final class InitCommand extends FreeCommand {
    public InitCommand() {
        super();
    }

    private ProjectFileModel promptDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("project name: ");
        String projectName = scanner.nextLine();

        return new ProjectFileModel(
                new Project(projectName, "com.example.Main"),
                new Build("src", "build"),
                new Archive(projectName),
                new Modules(null, "com.example", new String[]{"build", "libs"})
        );
    }

    @Override
    public void execute() {
        this.model = promptDetails();

        var projectDir = new File(model.project().name());
        var sourceDir = new File(model.project().name()).toPath().resolve("src").toFile();
        var libs = new File(model.project().name()).toPath().resolve("libs").toFile();

        projectDir.mkdir();
        sourceDir.mkdir();
        libs.mkdir();

        var sourceFile = new File(model.project().name()).toPath().resolve("src/com.example/com" +
                "/example/Main.java").toFile();
        var moduleinfoFile = new File(model.project().name()).toPath().resolve("src/com.example" +
                "/module-info.java").toFile();

        sourceFile.getParentFile().mkdirs();
        moduleinfoFile.getParentFile().mkdirs();
        try {
            if (!sourceFile.createNewFile()) {
                System.err.println("Could not create new source file");
                System.exit(1);
            }
            if (!moduleinfoFile.createNewFile()) {
                System.err.println("Could not create new source file");
                System.exit(1);
            }
            // write source code

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(sourceFile))
            )) {
                String source = """
                        package com.example;
                        
                        public class Main {
                            public static void main(String... args) {
                        
                                System.out.println("Hello World");
                            }
                        }
                        """;

                writer.write(source);
            }
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(moduleinfoFile))
            )) {
                String source = """
                        module com.example {
                        
                        }
                        """;

                writer.write(source);
            }


        } catch (IOException e) {
            System.err.println("Failed to create a source file: ");
            System.exit(1);
        }


        System.out.printf("Created project at %s%n", model.project().name());

        new ProjectTomlWriter().writeFile(model,
                projectDir.toPath().resolve("branch.toml").toFile());
    }
}
