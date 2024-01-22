package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes8.dex */
public final class y4 extends zzme {

    /* renamed from: a  reason: collision with root package name */
    public final String f9776a;
    public final boolean b;
    public final int c;

    public /* synthetic */ y4(String str, boolean z, int i, zzlz zzlzVar) {
        this.f9776a = str;
        this.b = z;
        this.c = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzme) {
            zzme zzmeVar = (zzme) obj;
            if (this.f9776a.equals(zzmeVar.zzb()) && this.b == zzmeVar.zzc() && this.c == zzmeVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f9776a.hashCode() ^ 1000003) * 1000003) ^ (true != this.b ? 1237 : 1231)) * 1000003) ^ this.c;
    }

    public final String toString() {
        String str = this.f9776a;
        boolean z = this.b;
        int i = this.c;
        return "MLKitLoggingOptions{libraryName=" + str + ", enableFirelog=" + z + ", firelogEventType=" + i + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final int zza() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final String zzb() {
        return this.f9776a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final boolean zzc() {
        return this.b;
    }
}
