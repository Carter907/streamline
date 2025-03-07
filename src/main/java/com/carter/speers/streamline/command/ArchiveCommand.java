package com.carter.speers.streamline.command;

import com.carter.speers.streamline.command.tools.JarTool;
import com.carter.speers.streamline.parse.model.ProjectFileModel;

import java.io.File;
import java.nio.file.Path;

public final class ArchiveCommand extends ProjectCommand {
    public ArchiveCommand(ProjectFileModel model) {
        super(model);
    }

    @Override
    public void execute(CommandContext ctx) {

        final var outStr = String.format("./%s", model.build().outDir());
        var outDir = new File(outStr);
        File[] files = outDir.listFiles();
        if (files == null) {
            System.err.println("Failed to package jar file\n" +
                    "Make sure you have built your project first");
            System.exit(1);
        }

        String jarName = String.format("%s/lib/%s.jar", model.build().outDir(),
                model.archive().jarName());

        String classes;
        String mainClass;

        if (model.modules() == null) {
            classes = model.build().outDir();
            mainClass = model.project().mainClass();
        } else {
            mainClass = model.project().mainClass();
            classes = Path.of(model.build().outDir())
                    .resolve(model.modules().mainModule()).toString();
        }



        String[] args = {
                "--create",
                "--file",
                jarName,
                "--main-class",
                mainClass, "-C",
                classes,
                "."
        };
        JarTool jar = new JarTool(args);

        if (ctx.loggingEnabled())
            jar.logCommand();

        jar.execute();
    }


}
