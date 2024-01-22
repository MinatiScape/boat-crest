package com.coveiot.repository;
/* loaded from: classes9.dex */
public enum RepositoryModuleNames {
    REPOSITORY("repository");
    
    private final String moduleName;

    RepositoryModuleNames(String str) {
        this.moduleName = str;
    }

    public String getModuleName() {
        return this.moduleName;
    }
}
