package com.carter.speers.parse;

import com.carter.speers.parse.model.ProjectFileModel;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;

import java.io.IOException;
import java.net.URL;

final public class ProjectTomlParser {

    public ProjectFileModel parseBranch(URL file) {
        TomlMapper mapper = new TomlMapper();

        try {
            return mapper.readValue(file, ProjectFileModel.class);

        } catch (IOException e) {
            System.err.println("IOException Occured: " + e.getCause());
            System.exit(1);
        }
        return null;
    }
}
