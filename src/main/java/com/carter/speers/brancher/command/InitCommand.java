package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.ProjectTomlWriter;
import com.carter.speers.brancher.parse.model.*;

import java.io.File;
import java.util.Scanner;

public final class InitCommand extends FreeCommand {
    public InitCommand() {
        super();
    }

    private ProjectFileModel promptDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("project name: ");
        String projectName = scanner.nextLine();

        return new ProjectFileModel(
                new Project(projectName, null),
                new Build(null, null),
                new Archive(null),
                new Modules(null, null, null)
        );
    }

    @Override
    public void execute() {
        this.model = promptDetails();

        var projectDir = new File(model.project().name());

        if (projectDir.mkdir()) {
            System.err.println("Failed to initialize brancher project for unknown cause.");
        }

        new ProjectTomlWriter().writeFile(model,
                projectDir.toPath().resolve("branch.toml").toFile());
    }
}
