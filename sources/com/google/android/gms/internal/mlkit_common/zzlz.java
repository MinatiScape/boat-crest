package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes8.dex */
public final class zzlz {

    /* renamed from: a  reason: collision with root package name */
    public final String f9350a;
    public final String b;
    public final zzlx c;
    public final String d;
    public final String e;
    public final zzlw f;
    public final Long g;
    public final Boolean h;
    public final Boolean i;

    public /* synthetic */ zzlz(zzlv zzlvVar, zzly zzlyVar) {
        String str;
        zzlx zzlxVar;
        String str2;
        zzlw zzlwVar;
        str = zzlvVar.f9349a;
        this.f9350a = str;
        this.b = null;
        zzlxVar = zzlvVar.b;
        this.c = zzlxVar;
        this.d = null;
        str2 = zzlvVar.c;
        this.e = str2;
        zzlwVar = zzlvVar.d;
        this.f = zzlwVar;
        this.g = null;
        this.h = null;
        this.i = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzlz) {
            zzlz zzlzVar = (zzlz) obj;
            return Objects.equal(this.f9350a, zzlzVar.f9350a) && Objects.equal(null, null) && Objects.equal(this.c, zzlzVar.c) && Objects.equal(null, null) && Objects.equal(this.e, zzlzVar.e) && Objects.equal(this.f, zzlzVar.f) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f9350a, null, this.c, null, this.e, this.f, null, null, null);
    }

    @Nullable
    @zzbo(zza = 6)
    public final zzlw zza() {
        return this.f;
    }

    @Nullable
    @zzbo(zza = 3)
    public final zzlx zzb() {
        return this.c;
    }

    @Nullable
    @zzbo(zza = 5)
    public final String zzc() {
        return this.e;
    }

    @Nullable
    @zzbo(zza = 1)
    public final String zzd() {
        return this.f9350a;
    }
}
