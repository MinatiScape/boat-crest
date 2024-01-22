package com.google.android.gms.internal.measurement;

import android.content.Context;
import java.util.Objects;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public final class p1 extends y1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8920a;
    public final zzib<zzhz<zzhi>> b;

    public p1(Context context, @Nullable zzib<zzhz<zzhi>> zzibVar) {
        Objects.requireNonNull(context, "Null context");
        this.f8920a = context;
        this.b = zzibVar;
    }

    @Override // com.google.android.gms.internal.measurement.y1
    public final Context a() {
        return this.f8920a;
    }

    @Override // com.google.android.gms.internal.measurement.y1
    @Nullable
    public final zzib<zzhz<zzhi>> b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        zzib<zzhz<zzhi>> zzibVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof y1) {
            y1 y1Var = (y1) obj;
            if (this.f8920a.equals(y1Var.a()) && ((zzibVar = this.b) != null ? zzibVar.equals(y1Var.b()) : y1Var.b() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.f8920a.hashCode() ^ 1000003) * 1000003;
        zzib<zzhz<zzhi>> zzibVar = this.b;
        return hashCode ^ (zzibVar == null ? 0 : zzibVar.hashCode());
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f8920a);
        String valueOf2 = String.valueOf(this.b);
        StringBuilder sb = new StringBuilder(valueOf.length() + 46 + valueOf2.length());
        sb.append("FlagsContext{context=");
        sb.append(valueOf);
        sb.append(", hermeticFileOverrides=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
