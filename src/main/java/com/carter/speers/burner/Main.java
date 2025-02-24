package com.carter.speers.burner;

import picocli.CommandLine;

public class Main {

    public static void main(String[] args) {

        int exitCode = new CommandLine(new BurnerCli()).execute(args);
        System.exit(exitCode);
    }
}
