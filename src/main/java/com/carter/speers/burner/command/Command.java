package com.carter.speers.burner.command;

import com.carter.speers.burner.parse.ProjectTomlReader;
import com.carter.speers.burner.parse.model.ProjectFileModel;

import java.io.File;

public sealed abstract class Command permits FreeCommand, ProjectCommand {

    protected ProjectFileModel model;

    protected Command(ProjectFileModel model) {
        this.model = model;
    }

    public abstract void execute(CommandContext ctx);

    public static ProjectFileModel getDefaultTomlModel() {

        return new ProjectTomlReader().parseFile(new File("branch.toml"));
    }

    public static Command from(Task task) {


        ProjectFileModel model = new ProjectTomlReader().parseFile(new File("branch.toml"));

        return switch (task) {
            case RUN -> new RunCommand(model);
            case BUILD -> new BuildCommand(model);
            case ARCHIVE -> new ArchiveCommand(model);
            case CLEAN -> new CleanCommand(model);
            case INIT -> new InitCommand();
        };
    }
}
