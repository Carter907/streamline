package com.carter.speers.streamline.command.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class JavaTool extends Tool {

    private Path currDir;

    public JavaTool(String... args) {
        super(args);
    }

    public JavaTool(Path currDir, String... args) {
        super(args);
        this.currDir = currDir;
    }

    @Override
    public void execute() {

        var psArgs = new ArrayList<>(List.of(args));
        psArgs.addFirst("java");

        ProcessBuilder processBuilder = new ProcessBuilder(psArgs.toArray(String[]::new));

        try {

            Process p = processBuilder
                    .directory(
                            currDir == null ? new File(System.getProperty("user.dir")) :
                                    currDir.toFile()
                    )
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

    @Override
    public String getLogCommandString() {

        return "Running: java " + String.join(" ", args);
    }

    @Override
    public void logCommand() {
        System.out.println(getLogCommandString());
    }
}
