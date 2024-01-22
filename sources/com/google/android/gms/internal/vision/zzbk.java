package com.google.android.gms.internal.vision;

import android.content.Context;
import android.net.Uri;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public final class zzbk {

    /* renamed from: a  reason: collision with root package name */
    public final String f10016a;
    public final Uri b;
    public final String c;
    public final String d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    @Nullable
    public final zzcl<Context, Boolean> i;

    public zzbk(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    public final zzbe<Long> zza(String str, long j) {
        zzbe<Long> a2;
        a2 = zzbe.a(this, str, j);
        return a2;
    }

    public final zzbk zzf(String str) {
        boolean z = this.e;
        if (!z) {
            return new zzbk(this.f10016a, this.b, str, this.d, z, this.f, this.g, this.h, this.i);
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public zzbk(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzcl<Context, Boolean> zzclVar) {
        this.f10016a = str;
        this.b = uri;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = z2;
        this.g = z3;
        this.h = z4;
        this.i = zzclVar;
    }

    public final zzbe<Boolean> zza(String str, boolean z) {
        zzbe<Boolean> c;
        c = zzbe.c(this, str, z);
        return c;
    }

    public final <T> zzbe<T> zza(String str, T t, zzbh<T> zzbhVar) {
        zzbe<T> b;
        b = zzbe.b(this, str, t, zzbhVar);
        return b;
    }
}
