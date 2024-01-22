package com.elvishew.xlog.printer.file.clean;

import java.io.File;
/* loaded from: classes9.dex */
public class FileLastModifiedCleanStrategy implements CleanStrategy {

    /* renamed from: a  reason: collision with root package name */
    public long f7886a;

    public FileLastModifiedCleanStrategy(long j) {
        this.f7886a = j;
    }

    @Override // com.elvishew.xlog.printer.file.clean.CleanStrategy
    public boolean shouldClean(File file) {
        return System.currentTimeMillis() - file.lastModified() > this.f7886a;
    }
}
