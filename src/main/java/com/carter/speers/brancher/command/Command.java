package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.ProjectTomlReader;
import com.carter.speers.brancher.parse.model.ProjectFileModel;

import java.io.File;

public sealed abstract class Command permits FreeCommand, ProjectCommand {

    protected ProjectFileModel model;

    protected Command(ProjectFileModel model) {
        this.model = model;
    }

    public abstract void execute();


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
