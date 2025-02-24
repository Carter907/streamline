package com.carter.speers.burner.command;

import com.carter.speers.burner.parse.model.ProjectFileModel;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.spi.ToolProvider;

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

        ToolProvider jar = ToolProvider.findFirst("jar").orElseThrow();

        String[] args = {
                "--create",
                "--file",
                jarName,
                "--main-class",
                mainClass, "-C",
                classes,
                "."
        };

        if (ctx.loggingEnabled())
            System.out.println("jar\n" + Arrays.toString(args));

        jar.run(System.out, System.err, args);
    }


}
