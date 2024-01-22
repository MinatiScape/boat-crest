package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public final class zzhr {

    /* renamed from: a  reason: collision with root package name */
    public final String f8957a;
    public final Uri b;
    public final String c;
    public final String d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    @Nullable
    public final zzhy<Context, Boolean> i;

    public zzhr(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    public zzhr(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzhy<Context, Boolean> zzhyVar) {
        this.f8957a = null;
        this.b = uri;
        this.c = "";
        this.d = "";
        this.e = z;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = null;
    }

    public final zzhr zza() {
        if (this.c.isEmpty()) {
            return new zzhr(null, this.b, this.c, this.d, true, false, false, false, null);
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public final zzhu<Double> zzb(String str, double d) {
        return new w1(this, "measurement.test.double_flag", Double.valueOf(-3.0d), true);
    }

    public final zzhu<Long> zzc(String str, long j) {
        return new u1(this, str, Long.valueOf(j), true);
    }

    public final zzhu<String> zzd(String str, String str2) {
        return new x1(this, str, str2, true);
    }

    public final zzhu<Boolean> zze(String str, boolean z) {
        return new v1(this, str, Boolean.valueOf(z), true);
    }
}
