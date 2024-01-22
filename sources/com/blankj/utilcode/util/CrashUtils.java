package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import com.blankj.utilcode.util.b;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.File;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CrashUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2248a = System.getProperty("file.separator");
    public static final Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();

    /* loaded from: classes.dex */
    public static final class CrashInfo {

        /* renamed from: a  reason: collision with root package name */
        public b.a f2249a;
        public Throwable b;

        public /* synthetic */ CrashInfo(String str, Throwable th, a aVar) {
            this(str, th);
        }

        public final void addExtraHead(Map<String, String> map) {
            this.f2249a.c(map);
        }

        public final Throwable getThrowable() {
            return this.b;
        }

        public String toString() {
            return this.f2249a.toString() + b.T(this.b);
        }

        public CrashInfo(String str, Throwable th) {
            this.b = th;
            b.a aVar = new b.a("Crash");
            this.f2249a = aVar;
            aVar.a("Time Of Crash", str);
        }

        public final void addExtraHead(String str, String str2) {
            this.f2249a.b(str, str2);
        }
    }

    /* loaded from: classes.dex */
    public interface OnCrashListener {
        void onCrash(CrashInfo crashInfo);
    }

    /* loaded from: classes.dex */
    public static class a implements Thread.UncaughtExceptionHandler {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnCrashListener f2250a;
        public final /* synthetic */ String b;

        public a(OnCrashListener onCrashListener, String str) {
            this.f2250a = onCrashListener;
            this.b = str;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
            Objects.requireNonNull(thread, "Argument 't' of type Thread (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Objects.requireNonNull(th, "Argument 'e' of type Throwable (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            String format = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date());
            CrashInfo crashInfo = new CrashInfo(format, th, null);
            OnCrashListener onCrashListener = this.f2250a;
            if (onCrashListener != null) {
                onCrashListener.onCrash(crashInfo);
            }
            b.i1(this.b + format + ".txt", crashInfo.toString(), true);
            if (CrashUtils.b != null) {
                CrashUtils.b.uncaughtException(thread, th);
            }
        }
    }

    public CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Thread.UncaughtExceptionHandler b(String str, OnCrashListener onCrashListener) {
        return new a(onCrashListener, str);
    }

    public static void init() {
        init("");
    }

    public static void init(@NonNull File file) {
        Objects.requireNonNull(file, "Argument 'crashDir' of type File (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        init(file.getAbsolutePath(), (OnCrashListener) null);
    }

    public static void init(String str) {
        init(str, (OnCrashListener) null);
    }

    public static void init(OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    public static void init(@NonNull File file, OnCrashListener onCrashListener) {
        Objects.requireNonNull(file, "Argument 'crashDir' of type File (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        init(file.getAbsolutePath(), onCrashListener);
    }

    public static void init(String str, OnCrashListener onCrashListener) {
        if (b.C0(str)) {
            if (b.z0() && Utils.getApp().getExternalFilesDir(null) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(Utils.getApp().getExternalFilesDir(null));
                String str2 = f2248a;
                sb.append(str2);
                sb.append(AppMeasurement.CRASH_ORIGIN);
                sb.append(str2);
                str = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Utils.getApp().getFilesDir());
                String str3 = f2248a;
                sb2.append(str3);
                sb2.append(AppMeasurement.CRASH_ORIGIN);
                sb2.append(str3);
                str = sb2.toString();
            }
        } else {
            String str4 = f2248a;
            if (!str.endsWith(str4)) {
                str = str + str4;
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(b(str, onCrashListener));
    }
}
