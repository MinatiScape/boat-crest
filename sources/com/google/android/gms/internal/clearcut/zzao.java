package com.google.android.gms.internal.clearcut;

import android.net.Uri;
/* loaded from: classes7.dex */
public final class zzao {

    /* renamed from: a  reason: collision with root package name */
    public final String f8613a;
    public final Uri b;
    public final String c;
    public final String d;
    public final boolean e;
    public final boolean f;

    public zzao(Uri uri) {
        this(null, uri, "", "", false, false);
    }

    public zzao(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
        this.f8613a = str;
        this.b = uri;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = z2;
    }

    public final <T> zzae<T> zza(String str, T t, zzan<T> zzanVar) {
        return zzae.f(this, str, t, zzanVar);
    }

    public final zzae<String> zza(String str, String str2) {
        return zzae.g(this, str, null);
    }

    public final zzae<Boolean> zzc(String str, boolean z) {
        return zzae.h(this, str, false);
    }

    public final zzao zzc(String str) {
        boolean z = this.e;
        if (z) {
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        }
        return new zzao(this.f8613a, this.b, str, this.d, z, this.f);
    }

    public final zzao zzd(String str) {
        return new zzao(this.f8613a, this.b, this.c, str, this.e, this.f);
    }
}
