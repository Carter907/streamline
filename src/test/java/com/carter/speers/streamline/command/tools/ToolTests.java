package com.carter.speers.streamline.command.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolTests {

    @Test
    public void testJarToolLogging() {
        JarTool jarTool = new JarTool(
                "--create",
                "--file",
                "build/lib/test.jar",
                "--main-class",
                "com.example.Main",
                "-C",
                "build/com.example",
                "."
        );

        Assertions.assertEquals(
                "Running: jar --create --file build/lib/test.jar --main-class com.example.Main -C build/com.example .",
                jarTool.getLogCommandString()
        );
    }

    @Test
    public void testJavacToolLogging() {
        JavacTool javacTool = new JavacTool(
                "-d",
                "build",
                "--module-path",
                "build:libs",
                "--module-source-path",
                "src",
                "src/com.example/module-info.java",
                "src/com.example/com/example/Main.java"
        );

        Assertions.assertEquals(
                "Running: javac -d build --module-path build:libs --module-source-path src " +
                        "src/com.example/module-info.java src/com.example/com/example/Main.java",
                javacTool.getLogCommandString()
        );
    }

    @Test
    public void testJavaToolLogging() {
        JavaTool javaTool = new JavaTool(
                "--module-path",
                "build:libs",
                "--module",
                "com.example/com.example.Main"
        );

        Assertions.assertEquals(
                "Running: java --module-path build:libs --module com.example/com.example.Main",
                javaTool.getLogCommandString()
        );
    }
}
