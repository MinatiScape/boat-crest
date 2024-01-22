package com.elvishew.xlog.printer.file.clean;

import java.io.File;
/* loaded from: classes9.dex */
public class NeverCleanStrategy implements CleanStrategy {
    @Override // com.elvishew.xlog.printer.file.clean.CleanStrategy
    public boolean shouldClean(File file) {
        return false;
    }
}
