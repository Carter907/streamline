package com.carter.speers.streamline.command;

import com.carter.speers.streamline.parse.ProjectTomlWriter;
import com.carter.speers.streamline.parse.model.*;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public final class InitCommand extends FreeCommand {
    public InitCommand() {
        super();
    }

    private ProjectFileModel promptDetails() {

        Scanner scanner = new Scanner(System.in);
        String currDirName = Path.of(System.getProperty("user.dir")).getFileName().toString();

        System.out.printf("project name: (%s) ", currDirName);
        String projectName = scanner.nextLine();
        if (projectName.isBlank()) {
            projectName = currDirName;
        }
        String defaultModule = "com.example";
        System.out.printf("module name: (%s) ", defaultModule);
        String moduleName = scanner.nextLine();
        if (moduleName.isBlank()) {
            moduleName = defaultModule;
        }

        return new ProjectFileModel(
                new Project(projectName, String.format("%s.Main", moduleName)),
                new Build("src", "build"),
                new Archive(projectName),
                new Modules(null, moduleName, new String[]{"build", "libs"})
        );
    }

    @Override
    public void execute(CommandContext ctx) {
        this.model = promptDetails();

        var moduleFolder = this.model.modules().mainModule().replaceAll("\\.", "/");
        var projectDir = new File(model.project().name());
        var sourceDir = new File(model.project().name()).toPath().resolve("src").toFile();
        var libs = new File(model.project().name()).toPath().resolve("libs").toFile();

        projectDir.mkdir();
        sourceDir.mkdir();
        libs.mkdir();


        var sourceFile = new File(
                model.project().name()
        ).toPath().resolve(
                "src/" + model.modules().mainModule() + "/" + moduleFolder + "/Main.java"
        ).toFile();

        var moduleinfoFile = new File(
                model.project().name()
        ).toPath().resolve(
                "src/"+ model.modules().mainModule() + "/module-info.java"
        ).toFile();

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
                String source = String.format("""
                        package %s;
                        
                        public class Main {
                            public static void main(String... args) {
                        
                                System.out.println("Hello World");
                            }
                        }
                        """, model.modules().mainModule());

                writer.write(source);
            }
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(moduleinfoFile))
            )) {
                String source = String.format("""
                        module %s {
                        
                        }
                        """, model.modules().mainModule());

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
