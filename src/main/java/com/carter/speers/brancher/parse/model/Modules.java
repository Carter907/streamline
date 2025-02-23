package com.carter.speers.brancher.parse.model;

import java.util.Arrays;

public class Modules {
    private String moduleSrc;
    private String mainModule;
    private String[] modulePath;

    public Modules(String moduleSrc, String mainModule, String[] modulePath) {
        this.moduleSrc = moduleSrc;
        this.mainModule = mainModule;
        this.modulePath = modulePath;
    }

    public String moduleSrc() {
        return moduleSrc;
    }

    public void setModuleSrc(String moduleSrc) {
        this.moduleSrc = moduleSrc;
    }

    public String mainModule() {
        return mainModule;
    }

    public void setMainModule(String mainModule) {
        this.mainModule = mainModule;
    }

    public String[] modulePath() {
        return modulePath;
    }

    public void setModulePath(String[] modulePath) {
        this.modulePath = modulePath;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "moduleSrc='" + moduleSrc + '\'' +
                ", mainModule='" + mainModule + '\'' +
                ", modulePath=" + Arrays.toString(modulePath) +
                '}';
    }
}
