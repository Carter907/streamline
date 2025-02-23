package com.carter.speers.brancher.parse;

import com.carter.speers.brancher.parse.model.ProjectFileModel;
import com.moandjiezana.toml.Toml;

import java.io.File;

final public class ProjectTomlReader {

    public ProjectFileModel parseFile(File file) {
        try {

            Toml toml = new Toml().read(file);

            return toml.to(ProjectFileModel.class);

        } catch (RuntimeException e) {
            return null;
        }
    }

}
