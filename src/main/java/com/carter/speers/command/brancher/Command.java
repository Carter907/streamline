package com.carter.speers.command.brancher;

import com.carter.speers.command.Task;
import com.carter.speers.parse.model.ProjectFileModel;

public sealed abstract class Command permits RunCommand, BuildCommand, ArchiveCommand, InitCommand {

    protected ProjectFileModel model;

    protected Command(ProjectFileModel model) {

        this.model = model;
    }

    public abstract void execute();


    public static Command from(Task task, ProjectFileModel model) {
        return switch (task) {
            case RUN -> new RunCommand(model);
            case BUILD -> new BuildCommand(model);
            case ARCHIVE -> new ArchiveCommand(model);
            case INIT -> new InitCommand(model);
        };
    }
}
