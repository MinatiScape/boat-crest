package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkDatabasePathHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1799a = Logger.tagWithPrefix("WrkDbPathHelper");
    public static final String[] b = {"-journal", "-shm", "-wal"};

    @RequiresApi(23)
    public static File a(@NonNull Context context, @NonNull String str) {
        return new File(context.getNoBackupFilesDir(), str);
    }

    @NonNull
    @VisibleForTesting
    public static File getDatabasePath(@NonNull Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return getDefaultDatabasePath(context);
        }
        return a(context, "androidx.work.workdb");
    }

    @NonNull
    @VisibleForTesting
    public static File getDefaultDatabasePath(@NonNull Context context) {
        return context.getDatabasePath("androidx.work.workdb");
    }

    @NonNull
    public static String getWorkDatabaseName() {
        return "androidx.work.workdb";
    }

    public static void migrateDatabase(@NonNull Context context) {
        String format;
        File defaultDatabasePath = getDefaultDatabasePath(context);
        if (Build.VERSION.SDK_INT < 23 || !defaultDatabasePath.exists()) {
            return;
        }
        Logger.get().debug(f1799a, "Migrating WorkDatabase to the no-backup directory", new Throwable[0]);
        Map<File, File> migrationPaths = migrationPaths(context);
        for (File file : migrationPaths.keySet()) {
            File file2 = migrationPaths.get(file);
            if (file.exists() && file2 != null) {
                if (file2.exists()) {
                    Logger.get().warning(f1799a, String.format("Over-writing contents of %s", file2), new Throwable[0]);
                }
                if (file.renameTo(file2)) {
                    format = String.format("Migrated %s to %s", file, file2);
                } else {
                    format = String.format("Renaming %s to %s failed", file, file2);
                }
                Logger.get().debug(f1799a, format, new Throwable[0]);
            }
        }
    }

    @NonNull
    @VisibleForTesting
    public static Map<File, File> migrationPaths(@NonNull Context context) {
        File defaultDatabasePath;
        File databasePath;
        String[] strArr;
        HashMap hashMap = new HashMap();
        if (Build.VERSION.SDK_INT >= 23) {
            hashMap.put(getDefaultDatabasePath(context), getDatabasePath(context));
            for (String str : b) {
                hashMap.put(new File(defaultDatabasePath.getPath() + str), new File(databasePath.getPath() + str));
            }
        }
        return hashMap;
    }
}
