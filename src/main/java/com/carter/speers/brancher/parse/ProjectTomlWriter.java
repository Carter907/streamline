package com.carter.speers.brancher.parse;

import com.carter.speers.brancher.parse.model.ProjectFileModel;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;

import java.io.File;
import java.io.IOException;

public class ProjectTomlWriter {

    public void writeFile(ProjectFileModel model, File file) {

        TomlMapper mapper = new TomlMapper();
        try {
            mapper.writeValue(file, model);

        } catch (IOException e) {
            System.err.println("IOException Occurred: " + e.getMessage());
            System.exit(1);
        }
    }
}
