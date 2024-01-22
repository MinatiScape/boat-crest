package com.elvishew.xlog.printer.file.backup;

import java.io.File;
/* loaded from: classes9.dex */
public class FileSizeBackupStrategy2 extends AbstractBackupStrategy {

    /* renamed from: a  reason: collision with root package name */
    public long f7885a;
    public int b;

    public FileSizeBackupStrategy2(long j, int i) {
        this.f7885a = j;
        this.b = i;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy2
    public int getMaxBackupIndex() {
        return this.b;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy
    public boolean shouldBackup(File file) {
        return file.length() > this.f7885a;
    }
}
