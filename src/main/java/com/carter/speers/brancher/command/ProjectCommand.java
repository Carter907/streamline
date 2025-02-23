package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.model.ProjectFileModel;

public sealed abstract class ProjectCommand extends Command permits ArchiveCommand, RunCommand,
        BuildCommand, CleanCommand {

    protected ProjectCommand(ProjectFileModel model) {
        super(model);
        if (model == null) {
            throw new IllegalArgumentException("Cannot call a project task without a branch.toml " +
                    "file");
        }

    }

    public abstract void execute();
}
