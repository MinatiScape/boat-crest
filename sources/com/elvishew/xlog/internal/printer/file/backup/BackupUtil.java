package com.elvishew.xlog.internal.printer.file.backup;

import com.elvishew.xlog.printer.file.backup.BackupStrategy2;
import java.io.File;
/* loaded from: classes9.dex */
public class BackupUtil {
    public static void backup(File file, BackupStrategy2 backupStrategy2) {
        String name = file.getName();
        String parent = file.getParent();
        int maxBackupIndex = backupStrategy2.getMaxBackupIndex();
        if (maxBackupIndex <= 0) {
            if (maxBackupIndex == 0) {
                for (int i = 1; i < Integer.MAX_VALUE; i++) {
                    File file2 = new File(parent, backupStrategy2.getBackupFileName(name, i));
                    if (!file2.exists()) {
                        file.renameTo(file2);
                        return;
                    }
                }
                return;
            }
            return;
        }
        File file3 = new File(parent, backupStrategy2.getBackupFileName(name, maxBackupIndex));
        if (file3.exists()) {
            file3.delete();
        }
        for (int i2 = maxBackupIndex - 1; i2 > 0; i2--) {
            File file4 = new File(parent, backupStrategy2.getBackupFileName(name, i2));
            if (file4.exists()) {
                file4.renameTo(new File(parent, backupStrategy2.getBackupFileName(name, i2 + 1)));
            }
        }
        file.renameTo(new File(parent, backupStrategy2.getBackupFileName(name, 1)));
    }

    public static void verifyBackupStrategy(BackupStrategy2 backupStrategy2) {
        int maxBackupIndex = backupStrategy2.getMaxBackupIndex();
        if (maxBackupIndex < 0) {
            throw new IllegalArgumentException("Max backup index should not be less than 0");
        }
        if (maxBackupIndex != Integer.MAX_VALUE) {
            return;
        }
        throw new IllegalArgumentException("Max backup index too big: " + maxBackupIndex);
    }
}
