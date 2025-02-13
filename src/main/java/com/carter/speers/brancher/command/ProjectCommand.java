package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.model.ProjectFileModel;

public sealed abstract class ProjectCommand extends Command permits ArchiveCommand, RunCommand,
        BuildCommand {
    protected ProjectCommand(ProjectFileModel model) {
        super(model);
    }

    public abstract void execute();
}
