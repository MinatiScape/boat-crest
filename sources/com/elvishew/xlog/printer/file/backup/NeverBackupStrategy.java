package com.elvishew.xlog.printer.file.backup;

import java.io.File;
/* loaded from: classes9.dex */
public class NeverBackupStrategy implements BackupStrategy {
    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy
    public boolean shouldBackup(File file) {
        return false;
    }
}
