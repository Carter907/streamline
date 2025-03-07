package com.carter.speers.streamline.command;

import com.carter.speers.streamline.command.tools.JavaTool;
import com.carter.speers.streamline.parse.model.ProjectFileModel;

import java.nio.file.Files;
import java.nio.file.Path;

public final class RunCommand extends ProjectCommand {
    public RunCommand(ProjectFileModel model) {
        super(model);
    }

    @Override
    public void execute(CommandContext ctx) {
        if (!Files.exists(Path.of(model.build().outDir()))) {
            System.err.println("Output directory " + model.build().outDir() + " not found\n" +
                    "Make sure you build your project first");
            System.exit(1);
        }

        if (model.modules() == null) {
            runWithUnnamedModule(ctx);
        } else {
            runWithModules(ctx);
        }
    }

    private void runWithModules(CommandContext ctx) {

        String[] args = {
                "--module-path",
                model.modules().modulePath() != null ?
                        String.join(":", model.modules().modulePath()) :
                        model.build().outDir(),
                "--module",
                String.format("%s/%s", model.modules().mainModule(),
                        model.project().mainClass())
        };

        JavaTool java = new JavaTool(args);

        if (ctx.loggingEnabled())
            java.logCommand();

        java.execute();
    }

    private void runWithUnnamedModule(CommandContext ctx) {

        String[] args = {
                String.format("%s.java",
                        model.project().mainClass()),
        };
        JavaTool java = new JavaTool(Path.of(model.build().srcDir()), args);

        if (ctx.loggingEnabled())
            java.logCommand();

        java.execute();


    }

}
