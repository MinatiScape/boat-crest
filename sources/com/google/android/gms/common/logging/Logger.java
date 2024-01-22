package com.google.android.gms.common.logging;

import android.util.Log;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;
@KeepForSdk
/* loaded from: classes6.dex */
public class Logger {

    /* renamed from: a  reason: collision with root package name */
    public final String f8350a;
    public final String b;
    public final int c;

    @KeepForSdk
    public Logger(@NonNull String str, @NonNull String... strArr) {
        String sb;
        if (strArr.length == 0) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('[');
            for (String str2 : strArr) {
                if (sb2.length() > 1) {
                    sb2.append(Constants.SEPARATOR_COMMA);
                }
                sb2.append(str2);
            }
            sb2.append("] ");
            sb = sb2.toString();
        }
        this.b = sb;
        this.f8350a = str;
        new GmsLogger(str);
        int i = 2;
        while (i <= 7 && !Log.isLoggable(this.f8350a, i)) {
            i++;
        }
        this.c = i;
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.f8350a, format(str, objArr));
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull Throwable th, @NonNull Object... objArr) {
        Log.e(this.f8350a, format(str, objArr), th);
    }

    @NonNull
    @KeepForSdk
    public String format(@NonNull String str, @NonNull Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.b.concat(str);
    }

    @NonNull
    @KeepForSdk
    public String getTag() {
        return this.f8350a;
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull Object... objArr) {
        Log.i(this.f8350a, format(str, objArr));
    }

    @KeepForSdk
    public boolean isLoggable(int i) {
        return this.c <= i;
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull Throwable th, @NonNull Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.f8350a, format(str, objArr), th);
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull Object... objArr) {
        Log.w(this.f8350a, format(str, objArr));
    }

    @KeepForSdk
    public void wtf(@NonNull String str, @NonNull Throwable th, @NonNull Object... objArr) {
        Log.wtf(this.f8350a, format(str, objArr), th);
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull Object... objArr) {
        Log.e(this.f8350a, format(str, objArr));
    }

    @KeepForSdk
    public void wtf(@NonNull Throwable th) {
        Log.wtf(this.f8350a, th);
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.f8350a, format(str, objArr));
        }
    }
}
