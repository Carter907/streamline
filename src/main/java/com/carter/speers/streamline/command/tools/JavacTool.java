package com.carter.speers.streamline.command.tools;

import java.util.spi.ToolProvider;

public final class JavacTool extends Tool {


    public JavacTool(String... args) {
        super(args);
    }
    @Override
    public void execute() {
        ToolProvider javac = ToolProvider.findFirst("javac").orElseThrow();

        javac.run(System.out, System.err, args);
    }

    @Override
    public void logCommand() {

    }
}
