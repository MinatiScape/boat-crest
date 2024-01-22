package com.google.android.gms.common;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.errorprone.annotations.CheckReturnValue;
import javax.annotation.Nullable;
@CheckReturnValue
/* loaded from: classes6.dex */
public class o {
    public static final o e = new o(true, 3, 1, null, null);

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8364a;
    @Nullable
    public final String b;
    @Nullable
    public final Throwable c;
    public final int d;

    public o(boolean z, int i, int i2, @Nullable String str, @Nullable Throwable th) {
        this.f8364a = z;
        this.d = i;
        this.b = str;
        this.c = th;
    }

    @Deprecated
    public static o b() {
        return e;
    }

    public static o c(@NonNull String str) {
        return new o(false, 1, 5, str, null);
    }

    public static o d(@NonNull String str, @NonNull Throwable th) {
        return new o(false, 1, 5, str, th);
    }

    public static o f(int i) {
        return new o(true, i, 1, null, null);
    }

    public static o g(int i, int i2, @NonNull String str, @Nullable Throwable th) {
        return new o(false, i, i2, str, th);
    }

    @Nullable
    public String a() {
        return this.b;
    }

    public final void e() {
        if (this.f8364a || !Log.isLoggable("GoogleCertificatesRslt", 3)) {
            return;
        }
        if (this.c != null) {
            Log.d("GoogleCertificatesRslt", a(), this.c);
        } else {
            Log.d("GoogleCertificatesRslt", a());
        }
    }
}
