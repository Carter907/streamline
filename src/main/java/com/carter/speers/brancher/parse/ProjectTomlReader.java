package com.carter.speers.brancher.parse;

import com.carter.speers.brancher.parse.model.ProjectFileModel;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;

import java.io.File;
import java.io.IOException;

final public class ProjectTomlReader {

    public ProjectFileModel parseFile(File file) {
        TomlMapper mapper = new TomlMapper();

        try {
            return mapper.readValue(file, ProjectFileModel.class);

        } catch (IOException e) {
            return null;
        }
    }

}
