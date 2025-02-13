package com.carter.speers.brancher;

import com.carter.speers.brancher.command.Command;
import com.carter.speers.brancher.command.Task;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please specify a task");
            System.exit(1);
        }

        String taskStr = args[0];

        Task task = Task.valueOf(taskStr.toUpperCase());

        Command command = Command.from(task);

        command.execute();
    }
}
