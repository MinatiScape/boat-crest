package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public final class q8 extends zztp {

    /* renamed from: a  reason: collision with root package name */
    public final String f9485a;
    public final boolean b;
    public final int c;

    public /* synthetic */ q8(String str, boolean z, int i, zztk zztkVar) {
        this.f9485a = str;
        this.b = z;
        this.c = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zztp) {
            zztp zztpVar = (zztp) obj;
            if (this.f9485a.equals(zztpVar.zzb()) && this.b == zztpVar.zzc() && this.c == zztpVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f9485a.hashCode() ^ 1000003) * 1000003) ^ (true != this.b ? 1237 : 1231)) * 1000003) ^ this.c;
    }

    public final String toString() {
        String str = this.f9485a;
        boolean z = this.b;
        int i = this.c;
        return "MLKitLoggingOptions{libraryName=" + str + ", enableFirelog=" + z + ", firelogEventType=" + i + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztp
    public final int zza() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztp
    public final String zzb() {
        return this.f9485a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztp
    public final boolean zzc() {
        return this.b;
    }
}
