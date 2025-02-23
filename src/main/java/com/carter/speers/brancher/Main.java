package com.carter.speers.brancher;

import com.carter.speers.brancher.command.Command;
import com.carter.speers.brancher.command.Task;

public class Main {

    public static void main(String[] args) {

        // check if user specified at least one command.
        if (args.length == 0) {
            System.out.println("Please specify a command");
            System.exit(1);
        }

        // iterate through args and execute each command.

        for (String cmdStr : args) {

            Task task = Task.valueOf(cmdStr.toUpperCase());

            Command command = Command.from(task);

            command.execute();
        }


    }
}
