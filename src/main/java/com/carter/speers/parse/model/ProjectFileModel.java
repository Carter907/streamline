package com.carter.speers.parse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProjectFileModel(
    
    @JsonProperty("project") Project project,
    @JsonProperty("build") Build build,
    @JsonProperty("archive") Archive archive,
    @JsonProperty("modules") Modules modules
) {
    
}
