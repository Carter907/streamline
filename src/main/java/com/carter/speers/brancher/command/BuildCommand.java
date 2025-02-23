package com.carter.speers.brancher.command;

import com.carter.speers.brancher.io.SourceFileWalker;
import com.carter.speers.brancher.parse.model.ProjectFileModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.spi.ToolProvider;

public final class BuildCommand extends ProjectCommand {
    public BuildCommand(ProjectFileModel model) {
        super(model);
    }
    @Override
    public void execute() {

        if (model.modules() == null) {
            ToolProvider javac = ToolProvider.findFirst("javac").orElseThrow();

            javac.run(System.out, System.err,
                    "-cp",
                    model.build().srcDir(),
                    "-d",
                    model.build().outDir(),
                    String.format("%s/%s.java", model.build().srcDir(),
                            model.project().mainClass()));
        } else {
            buildWithModules();
        }
    }

    private void buildWithModules() {

        ToolProvider javac = ToolProvider.findFirst("javac").orElseThrow();

        SourceFileWalker srcFiles = new SourceFileWalker();
        try {
            Path src = Path.of(model.build().srcDir());
            Files.walkFileTree(src,
                    srcFiles);
        } catch (IOException e) {
            System.err.println("Walk source file tree failed: " + e.getMessage());
            System.exit(1);
        }

        String outPath = model.build().outDir();
        String moduleSourcePath = model.modules().moduleSrc() != null ? model.modules().moduleSrc() : model.build().srcDir();

        List<String> source = srcFiles.getSourceFiles().stream()
                .map(Path::toString)
                .toList();

        List<String> args = new ArrayList<>(List.of(
                "-d",
                outPath,
                "--module-source-path",
                moduleSourcePath
        ));

        args.addAll(source);
        System.out.println("javac\n" + args);
        javac.run(System.out, System.err, args.toArray(String[]::new));
    }
}
