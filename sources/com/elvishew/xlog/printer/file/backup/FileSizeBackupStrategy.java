package com.elvishew.xlog.printer.file.backup;

import java.io.File;
@Deprecated
/* loaded from: classes9.dex */
public class FileSizeBackupStrategy implements BackupStrategy {

    /* renamed from: a  reason: collision with root package name */
    public long f7884a;

    public FileSizeBackupStrategy(long j) {
        this.f7884a = j;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy
    public boolean shouldBackup(File file) {
        return file.length() > this.f7884a;
    }
}
