package com.elvishew.xlog.printer.file.backup;
/* loaded from: classes9.dex */
public interface BackupStrategy2 extends BackupStrategy {
    public static final int NO_LIMIT = 0;

    String getBackupFileName(String str, int i);

    int getMaxBackupIndex();
}
