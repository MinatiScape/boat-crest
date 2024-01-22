package com.google.android.gms.common.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;
@KeepForSdk
/* loaded from: classes6.dex */
public final class GmsLogger {

    /* renamed from: a  reason: collision with root package name */
    public final String f8327a;
    @Nullable
    public final String b;

    @KeepForSdk
    public GmsLogger(@NonNull String str) {
        this(str, null);
    }

    public final String a(String str) {
        String str2 = this.b;
        return str2 == null ? str : str2.concat(str);
    }

    @FormatMethod
    public final String b(String str, Object... objArr) {
        String format = String.format(str, objArr);
        String str2 = this.b;
        return str2 == null ? format : str2.concat(format);
    }

    @KeepForSdk
    public boolean canLog(int i) {
        return Log.isLoggable(this.f8327a, i);
    }

    @KeepForSdk
    public boolean canLogPii() {
        return false;
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull String str2) {
        if (canLog(3)) {
            Log.d(str, a(str2));
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull String str2) {
        if (canLog(6)) {
            Log.e(str, a(str2));
        }
    }

    @KeepForSdk
    @FormatMethod
    public void efmt(@NonNull String str, @NonNull @FormatString String str2, @NonNull Object... objArr) {
        if (canLog(6)) {
            Log.e(str, b(str2, objArr));
        }
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull String str2) {
        if (canLog(4)) {
            Log.i(str, a(str2));
        }
    }

    @KeepForSdk
    public void pii(@NonNull String str, @NonNull String str2) {
    }

    @KeepForSdk
    public void pii(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull String str2) {
        if (canLog(2)) {
            Log.v(str, a(str2));
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull String str2) {
        if (canLog(5)) {
            Log.w(str, a(str2));
        }
    }

    @KeepForSdk
    @FormatMethod
    public void wfmt(@NonNull String str, @NonNull @FormatString String str2, @NonNull Object... objArr) {
        if (canLog(5)) {
            Log.w(this.f8327a, b(str2, objArr));
        }
    }

    @KeepForSdk
    public void wtf(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(7)) {
            Log.e(str, a(str2), th);
            Log.wtf(str, a(str2), th);
        }
    }

    @KeepForSdk
    public GmsLogger(@NonNull String str, @Nullable String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.f8327a = str;
        if (str2 == null || str2.length() <= 0) {
            this.b = null;
        } else {
            this.b = str2;
        }
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(3)) {
            Log.d(str, a(str2), th);
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(6)) {
            Log.e(str, a(str2), th);
        }
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(4)) {
            Log.i(str, a(str2), th);
        }
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(2)) {
            Log.v(str, a(str2), th);
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (canLog(5)) {
            Log.w(str, a(str2), th);
        }
    }
}
