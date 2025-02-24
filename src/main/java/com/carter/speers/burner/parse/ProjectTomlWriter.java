package com.carter.speers.burner.parse;

import com.carter.speers.burner.parse.model.ProjectFileModel;
import com.moandjiezana.toml.TomlWriter;

import java.io.File;
import java.io.IOException;

public class ProjectTomlWriter {

    public void writeFile(ProjectFileModel model, File file) {

        TomlWriter tomlWriter = new TomlWriter();

        try {
            tomlWriter.write(model, file);
        } catch (IOException e) {
            System.err.println("Failed to write to toml file");
            System.exit(1);
        }

    }
}
