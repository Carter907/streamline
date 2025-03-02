package com.carter.speers.streamline;

import picocli.CommandLine;

public class Main {

    public static void main(String[] args) {

        int exitCode = new CommandLine(new StreamlineCil()).execute(args);
        System.exit(exitCode);
    }
}
