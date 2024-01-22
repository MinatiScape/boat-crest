package androidx.room;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.room.util.CopyLock;
import androidx.room.util.DBUtil;
import androidx.room.util.FileUtil;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
/* loaded from: classes.dex */
public class d implements SupportSQLiteOpenHelper {
    @NonNull
    public final Context h;
    @Nullable
    public final String i;
    @Nullable
    public final File j;
    public final int k;
    @NonNull
    public final SupportSQLiteOpenHelper l;
    @Nullable
    public DatabaseConfiguration m;
    public boolean n;

    public d(@NonNull Context context, @Nullable String str, @Nullable File file, int i, @NonNull SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        this.h = context;
        this.i = str;
        this.j = file;
        this.k = i;
        this.l = supportSQLiteOpenHelper;
    }

    public final void a(File file) throws IOException {
        ReadableByteChannel channel;
        if (this.i != null) {
            channel = Channels.newChannel(this.h.getAssets().open(this.i));
        } else if (this.j != null) {
            channel = new FileInputStream(this.j).getChannel();
        } else {
            throw new IllegalStateException("copyFromAssetPath and copyFromFile == null!");
        }
        File createTempFile = File.createTempFile("room-copy-helper", ".tmp", this.h.getCacheDir());
        createTempFile.deleteOnExit();
        FileUtil.copy(channel, new FileOutputStream(createTempFile).getChannel());
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Failed to create directories for " + file.getAbsolutePath());
        } else if (createTempFile.renameTo(file)) {
        } else {
            throw new IOException("Failed to move intermediate file (" + createTempFile.getAbsolutePath() + ") to destination (" + file.getAbsolutePath() + ").");
        }
    }

    public void b(@Nullable DatabaseConfiguration databaseConfiguration) {
        this.m = databaseConfiguration;
    }

    public final void c() {
        String databaseName = getDatabaseName();
        File databasePath = this.h.getDatabasePath(databaseName);
        DatabaseConfiguration databaseConfiguration = this.m;
        CopyLock copyLock = new CopyLock(databaseName, this.h.getFilesDir(), databaseConfiguration == null || databaseConfiguration.multiInstanceInvalidation);
        try {
            copyLock.lock();
            if (!databasePath.exists()) {
                try {
                    a(databasePath);
                    copyLock.unlock();
                    return;
                } catch (IOException e) {
                    throw new RuntimeException("Unable to copy database file.", e);
                }
            } else if (this.m == null) {
                copyLock.unlock();
                return;
            } else {
                try {
                    int readVersion = DBUtil.readVersion(databasePath);
                    int i = this.k;
                    if (readVersion == i) {
                        copyLock.unlock();
                        return;
                    } else if (this.m.isMigrationRequired(readVersion, i)) {
                        copyLock.unlock();
                        return;
                    } else {
                        if (this.h.deleteDatabase(databaseName)) {
                            try {
                                a(databasePath);
                            } catch (IOException e2) {
                                Log.w("ROOM", "Unable to copy database file.", e2);
                            }
                        } else {
                            Log.w("ROOM", "Failed to delete database file (" + databaseName + ") for a copy destructive migration.");
                        }
                        copyLock.unlock();
                        return;
                    }
                } catch (IOException e3) {
                    Log.w("ROOM", "Unable to read database version.", e3);
                    copyLock.unlock();
                    return;
                }
            }
        } catch (Throwable th) {
            copyLock.unlock();
            throw th;
        }
        copyLock.unlock();
        throw th;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.l.close();
        this.n = false;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.l.getDatabaseName();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public synchronized SupportSQLiteDatabase getReadableDatabase() {
        if (!this.n) {
            c();
            this.n = true;
        }
        return this.l.getReadableDatabase();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public synchronized SupportSQLiteDatabase getWritableDatabase() {
        if (!this.n) {
            c();
            this.n = true;
        }
        return this.l.getWritableDatabase();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    @RequiresApi(api = 16)
    public void setWriteAheadLoggingEnabled(boolean z) {
        this.l.setWriteAheadLoggingEnabled(z);
    }
}
