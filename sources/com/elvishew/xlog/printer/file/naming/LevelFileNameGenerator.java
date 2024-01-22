package com.elvishew.xlog.printer.file.naming;

import com.elvishew.xlog.LogLevel;
/* loaded from: classes9.dex */
public class LevelFileNameGenerator implements FileNameGenerator {
    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public String generateFileName(int i, long j) {
        return LogLevel.getLevelName(i);
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public boolean isFileNameChangeable() {
        return true;
    }
}
