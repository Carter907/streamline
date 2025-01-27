package com.carter.speers;

import com.carter.speers.func.Compiler;
import com.carter.speers.func.Packager;
import com.carter.speers.func.Runner;
import com.carter.speers.parse.ProjectTomlParser;
import com.carter.speers.parse.model.ProjectFileModel;
import org.apache.commons.cli.*;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please specifiy a task");
            System.exit(1);
        }

        String subCommand = args[0];

        Options runOptions = new Options();
        Options buildOptions = new Options();
        Options packageOptions = new Options();

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd;

            String[] remainingArgs = new String[args.length - 1];
            System.arraycopy(args, 1, remainingArgs, 0, args.length - 1);

            switch (subCommand) {
                case "run" -> {
                    cmd = parser.parse(runOptions, remainingArgs);
                    handleRun(cmd);
                }
                case "build" -> {
                    cmd = parser.parse(buildOptions, remainingArgs);
                    handleBuild(cmd);
                }
                case "package" -> {
                    cmd = parser.parse(packageOptions, remainingArgs);
                    handlePackage(cmd);
                }
                default -> {
                    System.out.println("Unknown subcommand: " + subCommand);
                    System.exit(1);
                }
            }
            ;
        } catch (ParseException e) {
            System.out.println("Error parsing command line: " + e.getMessage());

            // Show appropriate help based on subcommand
            HelpFormatter formatter = new HelpFormatter();
            if ("run".equals(subCommand)) {
                formatter.printHelp("program run", runOptions);
            } else if ("build".equals(subCommand)) {
                formatter.printHelp("program build", buildOptions);
            }
            System.exit(1);
        }
    }

    private static void handleBuild(CommandLine cmd) {
        ProjectTomlParser parser = new ProjectTomlParser();
        try {
            ProjectFileModel projectConfig = parser.parseBranch(new File("branch.toml").toURI().toURL());
            Compiler compiler = new Compiler();
            assert projectConfig != null;
            compiler.executeCommand(projectConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handlePackage(CommandLine cmd) {
        ProjectTomlParser parser = new ProjectTomlParser();
        try {
            ProjectFileModel projectConfig = parser.parseBranch(new File("branch.toml").toURI().toURL());
            var compiler = new Packager();
            assert projectConfig != null;
            compiler.executeCommand(projectConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleRun(CommandLine cmd) {
        ProjectTomlParser parser = new ProjectTomlParser();
        try {
            ProjectFileModel projectConfig = parser.parseBranch(new File("branch.toml").toURI().toURL());
            var compiler = new Runner();
            assert projectConfig != null;
            compiler.executeCommand(projectConfig);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
