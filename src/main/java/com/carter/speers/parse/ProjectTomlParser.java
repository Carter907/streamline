package com.carter.speers.parse;

import com.carter.speers.parse.model.Project;
import com.carter.speers.parse.model.ProjectFileModel;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

final public class ProjectTomlParser {


    public ProjectFileModel parseBranch(URL file) {
        TomlMapper mapper = new TomlMapper();

        try {
            return mapper.readValue(file, ProjectFileModel.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
