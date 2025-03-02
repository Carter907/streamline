package com.carter.speers.streamline.command.tools;

// an abstraction layer over the Java ToolProvider API
public sealed abstract class Tool permits JarTool, JavaTool, JavacTool {

    protected String[] args;

    protected Tool(String... args) {

        this.args = args;

        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Cannot run Java tool with no arguments");
        }
    }

    public abstract void execute();

    public abstract void logCommand();
}
