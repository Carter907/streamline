package com.carter.speers;

import com.carter.speers.command.BrancherCommand;
import com.carter.speers.parse.ProjectTomlParser;
import com.carter.speers.parse.model.ProjectFileModel;

import java.io.File;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please specify a task");
            System.exit(1);
        }

        String task = args[0];

        String[] remainingArgs = new String[args.length - 1];
        System.arraycopy(args, 1, remainingArgs, 0, args.length - 1);

        BrancherCommand brancherCommand;
        ProjectFileModel projectConfig = new ProjectFileModel(null,null,null);
        try {
            projectConfig = new ProjectTomlParser()
                    .parseBranch(new File("branch.toml").toURI().toURL());

        } catch (MalformedURLException ex) {
            System.err.println("Failed to parse branch.toml: " + ex.getCause());
            System.exit(1);
        }

        brancherCommand = new BrancherCommand(projectConfig);

        switch (task) {
            case "run" -> brancherCommand.run();

            case "build" -> brancherCommand.build();

            case "archive" -> brancherCommand.archive();

            default -> {
                System.err.println("Unknown task: " + task);
                System.exit(1);
            }
        }

    }


}
