package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes8.dex */
public final class f6 extends zzpt {

    /* renamed from: a  reason: collision with root package name */
    public final String f9197a;
    public final boolean b;
    public final int c;

    public /* synthetic */ f6(String str, boolean z, int i, zzpl zzplVar) {
        this.f9197a = str;
        this.b = z;
        this.c = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzpt) {
            zzpt zzptVar = (zzpt) obj;
            if (this.f9197a.equals(zzptVar.zzb()) && this.b == zzptVar.zzc() && this.c == zzptVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f9197a.hashCode() ^ 1000003) * 1000003) ^ (true != this.b ? 1237 : 1231)) * 1000003) ^ this.c;
    }

    public final String toString() {
        String str = this.f9197a;
        boolean z = this.b;
        int i = this.c;
        return "MLKitLoggingOptions{libraryName=" + str + ", enableFirelog=" + z + ", firelogEventType=" + i + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpt
    public final int zza() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpt
    public final String zzb() {
        return this.f9197a;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpt
    public final boolean zzc() {
        return this.b;
    }
}
