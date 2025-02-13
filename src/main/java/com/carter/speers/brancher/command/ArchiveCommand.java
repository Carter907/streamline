package com.carter.speers.brancher.command;

import com.carter.speers.brancher.parse.model.ProjectFileModel;

import java.io.File;
import java.nio.file.Path;
import java.util.spi.ToolProvider;

public final class ArchiveCommand extends ProjectCommand {
    public ArchiveCommand(ProjectFileModel model) {
        super(model);
    }

    @Override
    public void execute() {

        final var outStr = String.format("./%s", model.build().outDir());
        var outDir = new File(outStr);
        File[] files = outDir.listFiles();
        if (files == null) {
            System.err.println("Failed to package jar file. Make sure you have built your project first.");
            System.exit(1);
        }

        String jarName = String.format("%s/lib/%s.jar", model.build().outDir(),
                model.archive().jarName());
        String mainClass = model.project().mainClass();

        String classes = model.modules() == null ? model.build().outDir() :
                Path.of(model.build().outDir()).resolve(Path.of(model.modules().mainModule())).toString();

        ToolProvider jar = ToolProvider.findFirst("jar").orElseThrow();


        jar.run(System.out, System.err,
                "--create",
                "--file",
                jarName,
                "--main-class",
                mainClass, "-C",
                classes,
                ".");
    }


}
