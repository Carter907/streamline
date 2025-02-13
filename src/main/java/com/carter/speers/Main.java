package com.carter.speers;

import com.carter.speers.command.Task;
import com.carter.speers.command.brancher.Command;
import com.carter.speers.parse.ProjectTomlParser;
import com.carter.speers.parse.model.ProjectFileModel;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please specify a task");
            System.exit(1);
        }

        String taskStr = args[0];

        Task task = Task.valueOf(taskStr.toUpperCase());


        URL projectFile = null;
        try {
            projectFile = new File("branch.toml").toURI().toURL();

        } catch (MalformedURLException ex) {
            System.err.println("Failed to find branch.toml: " + ex.getMessage());
            System.exit(1);
        }
        ProjectFileModel projectConfig = new ProjectTomlParser().parseBranch(projectFile);

        Command command = Command.from(task, projectConfig);

        command.execute();
    }


}
