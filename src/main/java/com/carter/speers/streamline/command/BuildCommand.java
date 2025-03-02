package com.carter.speers.streamline.command;

import com.carter.speers.streamline.command.tools.JavacTool;
import com.carter.speers.streamline.io.SourceFileWalker;
import com.carter.speers.streamline.parse.model.ProjectFileModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class BuildCommand extends ProjectCommand {

    public BuildCommand(ProjectFileModel model) {
        super(model);
    }
    @Override
    public void execute(CommandContext ctx) {

        if (model.modules() == null) {
            String[] args = {
                    "-cp",
                    model.build().srcDir(),
                    "-d",
                    model.build().outDir(),
                    String.format("%s/%s.java", model.build().srcDir(),
                            model.project().mainClass())
            };
            JavacTool javac = new JavacTool(args);

            javac.execute();


        } else {
            buildWithModules(ctx);
        }
    }

    private void buildWithModules(CommandContext ctx) {

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
        String[] modulePath = model.modules().modulePath();


        List<String> source = srcFiles.getSourceFiles().stream()
                .map(Path::toString)
                .toList();

        List<String> args = new ArrayList<>(List.of(
                "-d",
                outPath,
                "--module-path",
                String.join(":", modulePath),
                "--module-source-path",
                moduleSourcePath
        ));

        args.addAll(source);
        JavacTool javac = new JavacTool(args.toArray(String[]::new));

        if (ctx.loggingEnabled())
            javac.logCommand();

        javac.execute();
    }
}
