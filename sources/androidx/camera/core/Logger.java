package androidx.camera.core;

import android.os.Build;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class Logger {

    /* renamed from: a  reason: collision with root package name */
    public static int f646a = 3;

    public static void a() {
        f646a = 3;
    }

    public static void b(@IntRange(from = 3, to = 6) int i) {
        f646a = i;
    }

    @NonNull
    public static String c(@NonNull String str) {
        return (23 >= str.length() || Build.VERSION.SDK_INT >= 24) ? str : str.substring(0, 23);
    }

    public static void d(@NonNull String str, @NonNull String str2) {
        d(str, str2, null);
    }

    public static void e(@NonNull String str, @NonNull String str2) {
        e(str, str2, null);
    }

    public static void i(@NonNull String str, @NonNull String str2) {
        i(str, str2, null);
    }

    public static boolean isDebugEnabled(@NonNull String str) {
        return f646a <= 3 || Log.isLoggable(c(str), 3);
    }

    public static boolean isErrorEnabled(@NonNull String str) {
        return f646a <= 6 || Log.isLoggable(c(str), 6);
    }

    public static boolean isInfoEnabled(@NonNull String str) {
        return f646a <= 4 || Log.isLoggable(c(str), 4);
    }

    public static boolean isWarnEnabled(@NonNull String str) {
        return f646a <= 5 || Log.isLoggable(c(str), 5);
    }

    public static void w(@NonNull String str, @NonNull String str2) {
        w(str, str2, null);
    }

    public static void d(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isDebugEnabled(str)) {
            Log.d(c(str), str2, th);
        }
    }

    public static void e(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isErrorEnabled(str)) {
            Log.e(c(str), str2, th);
        }
    }

    public static void i(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isInfoEnabled(str)) {
            Log.i(c(str), str2, th);
        }
    }

    public static void w(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isWarnEnabled(str)) {
            Log.w(c(str), str2, th);
        }
    }
}
