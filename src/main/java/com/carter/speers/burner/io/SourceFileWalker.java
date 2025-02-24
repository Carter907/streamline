package com.carter.speers.burner.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SourceFileWalker extends SimpleFileVisitor<Path> {

    private final List<Path> sourceFiles;

    public SourceFileWalker() {
        this.sourceFiles = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);

        if (attrs.isRegularFile() && file.toString().endsWith(".java")) {
            sourceFiles.add(file);
        }

        return FileVisitResult.CONTINUE;
    }

    public List<Path> getSourceFiles() {
        return sourceFiles;
    }

}
