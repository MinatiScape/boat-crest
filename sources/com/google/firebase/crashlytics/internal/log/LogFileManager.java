package com.google.firebase.crashlytics.internal.log;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.File;
import java.util.Set;
/* loaded from: classes10.dex */
public class LogFileManager {
    public static final b d = new b();

    /* renamed from: a  reason: collision with root package name */
    public final Context f11164a;
    public final DirectoryProvider b;
    public com.google.firebase.crashlytics.internal.log.a c;

    /* loaded from: classes10.dex */
    public interface DirectoryProvider {
        File getLogFileDir();
    }

    /* loaded from: classes10.dex */
    public static final class b implements com.google.firebase.crashlytics.internal.log.a {
        public b() {
        }

        @Override // com.google.firebase.crashlytics.internal.log.a
        public void a() {
        }

        @Override // com.google.firebase.crashlytics.internal.log.a
        public String b() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.log.a
        public byte[] c() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.log.a
        public void d() {
        }

        @Override // com.google.firebase.crashlytics.internal.log.a
        public void e(long j, String str) {
        }
    }

    public LogFileManager(Context context, DirectoryProvider directoryProvider) {
        this(context, directoryProvider, null);
    }

    public final String a(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".temp");
        return lastIndexOf == -1 ? name : name.substring(20, lastIndexOf);
    }

    public final File b(String str) {
        return new File(this.b.getLogFileDir(), "crashlytics-userlog-" + str + ".temp");
    }

    public void c(File file, int i) {
        this.c = new com.google.firebase.crashlytics.internal.log.b(file, i);
    }

    public void clearLog() {
        this.c.d();
    }

    public void discardOldLogFiles(Set<String> set) {
        File[] listFiles = this.b.getLogFileDir().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (!set.contains(a(file))) {
                    file.delete();
                }
            }
        }
    }

    public byte[] getBytesForLog() {
        return this.c.c();
    }

    @Nullable
    public String getLogString() {
        return this.c.b();
    }

    public final void setCurrentSession(String str) {
        this.c.a();
        this.c = d;
        if (str == null) {
            return;
        }
        if (!CommonUtils.getBooleanResourceValue(this.f11164a, "com.crashlytics.CollectCustomLogs", true)) {
            Logger.getLogger().d("Preferences requested no custom logs. Aborting log file creation.");
        } else {
            c(b(str), 65536);
        }
    }

    public void writeToLog(long j, String str) {
        this.c.e(j, str);
    }

    public LogFileManager(Context context, DirectoryProvider directoryProvider, String str) {
        this.f11164a = context;
        this.b = directoryProvider;
        this.c = d;
        setCurrentSession(str);
    }
}
