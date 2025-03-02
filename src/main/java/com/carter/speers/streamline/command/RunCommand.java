package com.carter.speers.streamline.command;

import com.carter.speers.streamline.parse.model.ProjectFileModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

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
                "java",
                "--module-path",
                model.modules().modulePath() != null ?
                        String.join(":", model.modules().modulePath()) :
                        model.build().outDir(),
                "-m",
                String.format("%s/%s", model.modules().mainModule(),
                        model.project().mainClass())
        };
        if (ctx.loggingEnabled())
            System.out.println(Arrays.toString(args));

        ProcessBuilder processBuilder = new ProcessBuilder(args);
        try {

            Process p = processBuilder
                    .inheritIO()
                    .start();

            p.waitFor();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getCause());
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception: " + e.getCause());
            System.exit(1);
        }
    }

    private void runWithUnnamedModule(CommandContext ctx) {

        String[] args = {
                "java",
                String.format("%s.java",
                        model.project().mainClass()),
        };
        if (ctx.loggingEnabled())
            System.out.println(Arrays.toString(args));

        ProcessBuilder processBuilder = new ProcessBuilder(args);

        try {

            Process p = processBuilder
                    .directory(new File(model.build().srcDir()))
                    .inheritIO()
                    .start();

            p.waitFor();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception: " + e.getMessage());
            System.exit(1);
        }
    }

}
