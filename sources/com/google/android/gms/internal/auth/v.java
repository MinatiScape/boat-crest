package com.google.android.gms.internal.auth;

import android.content.Context;
import java.util.Objects;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class v extends e0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8542a;
    public final zzdj b;

    public v(Context context, @Nullable zzdj zzdjVar) {
        Objects.requireNonNull(context, "Null context");
        this.f8542a = context;
        this.b = zzdjVar;
    }

    @Override // com.google.android.gms.internal.auth.e0
    public final Context a() {
        return this.f8542a;
    }

    @Override // com.google.android.gms.internal.auth.e0
    @Nullable
    public final zzdj b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        zzdj zzdjVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof e0) {
            e0 e0Var = (e0) obj;
            if (this.f8542a.equals(e0Var.a()) && ((zzdjVar = this.b) != null ? zzdjVar.equals(e0Var.b()) : e0Var.b() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.f8542a.hashCode() ^ 1000003) * 1000003;
        zzdj zzdjVar = this.b;
        return hashCode ^ (zzdjVar == null ? 0 : zzdjVar.hashCode());
    }

    public final String toString() {
        String obj = this.f8542a.toString();
        String valueOf = String.valueOf(this.b);
        return "FlagsContext{context=" + obj + ", hermeticFileOverrides=" + valueOf + "}";
    }
}
