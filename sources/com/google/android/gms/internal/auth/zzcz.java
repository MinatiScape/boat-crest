package com.google.android.gms.internal.auth;

import android.net.Uri;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class zzcz {

    /* renamed from: a  reason: collision with root package name */
    public final String f8558a;
    public final Uri b;
    public final String c;
    public final String d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    @Nullable
    public final zzdg i;

    public zzcz(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    public zzcz(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzdg zzdgVar) {
        this.f8558a = null;
        this.b = uri;
        this.c = "";
        this.d = "";
        this.e = z;
        this.f = false;
        this.g = z3;
        this.h = false;
        this.i = null;
    }

    public final zzcz zza() {
        return new zzcz(null, this.b, this.c, this.d, this.e, false, true, false, null);
    }

    public final zzcz zzb() {
        if (this.c.isEmpty()) {
            return new zzcz(null, this.b, this.c, this.d, true, false, this.g, false, null);
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public final zzdc zzc(String str, double d) {
        return new c0(this, str, Double.valueOf(0.0d), true);
    }

    public final zzdc zzd(String str, long j) {
        return new a0(this, str, Long.valueOf(j), true);
    }

    public final zzdc zze(String str, boolean z) {
        return new b0(this, str, Boolean.valueOf(z), true);
    }

    public final zzdc zzf(String str, Object obj, zzhu zzhuVar) {
        return new d0(this, "getTokenRefactor__blocked_packages", obj, true, zzhuVar, null);
    }
}
