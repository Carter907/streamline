package com.carter.speers;

import com.carter.speers.func.Compiler;
import com.carter.speers.parse.ProjectTomlParser;
import com.carter.speers.parse.model.ProjectFileModel;
import org.apache.commons.cli.*;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        // Exit if no arguments provided
        if (args.length == 0) {
            System.out.println("Please provide a subcommand (add|remove)");
            System.exit(1);
        }

        // Get the subcommand (first argument)
        String subCommand = args[0];


        Options runOptions = new Options();
        Options buildOptions = new Options();
        Options packageOptions = new Options();


        try {
            // Create command line parser
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd;

            // Remove the subcommand from args
            String[] remainingArgs = new String[args.length - 1];
            System.arraycopy(args, 1, remainingArgs, 0, args.length - 1);

            // Parse based on subcommand
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
            };
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
        System.out.println("Building...");
        ProjectTomlParser parser = new ProjectTomlParser();
        try {
            ProjectFileModel projectConfig =
                    parser.parseBranch(new File("branch.toml").toURI().toURL());
            Compiler compiler = new Compiler();
            assert projectConfig != null;
            compiler.executeCommand(projectConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void handlePackage(CommandLine cmd) {
        System.out.println("Packaging...");

        ProjectTomlParser parser = new ProjectTomlParser();
        try {
            ProjectFileModel projectConfig =
                    parser.parseBranch(new File("branch.toml").toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleRun(CommandLine cmd) {
        System.out.println("Running...");
        ProjectTomlParser parser = new ProjectTomlParser();
        try {
            ProjectFileModel projectConfig =
                    parser.parseBranch(new File("branch.toml").toURI().toURL());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
