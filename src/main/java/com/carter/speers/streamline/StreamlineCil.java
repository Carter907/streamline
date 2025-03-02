package com.carter.speers.streamline;

import com.carter.speers.streamline.command.*;
import com.carter.speers.streamline.parse.model.ProjectFileModel;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "stml", mixinStandardHelpOptions = true, version = "streamline 0.0.1",
        description = "A simple Java build tool for simple tasks.")
public class StreamlineCil implements Callable<Void> {

    @Option(names = {"--logging", "-l"}, description = "Enable to show the underlying java tools " +
            "commands invoked.")
    public Boolean logging = false;

    @CommandLine.Command(name = "run")
    public void runCommand() {
        ProjectFileModel projectModel = Command.getDefaultTomlModel();
        new RunCommand(projectModel).execute(new CommandContext(logging));
    }

    @CommandLine.Command(name = "init")
    public void initCommand() {
        ProjectFileModel projectModel = Command.getDefaultTomlModel();
        new InitCommand().execute(new CommandContext(logging));
    }

    @CommandLine.Command(name = "build")
    public void buildCommand() {
        ProjectFileModel projectModel = Command.getDefaultTomlModel();
        new BuildCommand(projectModel).execute(new CommandContext(logging));
    }

    @CommandLine.Command(name = "clean")
    public void cleanCommand() {
        ProjectFileModel projectModel = Command.getDefaultTomlModel();
        new CleanCommand(projectModel).execute(new CommandContext(logging));
    }

    @CommandLine.Command(name = "archive")
    public void archiveCommand() {
        ProjectFileModel projectModel = Command.getDefaultTomlModel();
        new ArchiveCommand(projectModel).execute(new CommandContext(logging));
    }


    @Override
    public Void call() {

        System.err.println("Please enter a subcommand\nTry --help for assistance");

        return null;
    }
}
