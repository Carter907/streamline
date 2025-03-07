package com.carter.speers.streamline.command.tools;

import java.util.spi.ToolProvider;

public final class JarTool extends Tool {


    public JarTool(String... args) {
        super(args);
    }

    @Override
    public void execute() {
        ToolProvider jar = ToolProvider.findFirst("jar").orElseThrow();

        jar.run(System.out, System.err, args);
    }

    @Override
    public String getLogCommandString() {

        return "Running: jar " + String.join(" ", args);
    }

    @Override
    public void logCommand() {
        System.out.println(getLogCommandString());
    }
}
