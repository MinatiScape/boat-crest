package com.google.android.gms.internal.mlkit_vision_text_common;
/* loaded from: classes6.dex */
public final class x5 extends zznx {

    /* renamed from: a  reason: collision with root package name */
    public final String f9916a;
    public final boolean b;
    public final int c;

    public /* synthetic */ x5(String str, boolean z, int i, zznt zzntVar) {
        this.f9916a = str;
        this.b = z;
        this.c = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zznx) {
            zznx zznxVar = (zznx) obj;
            if (this.f9916a.equals(zznxVar.zzb()) && this.b == zznxVar.zzc() && this.c == zznxVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f9916a.hashCode() ^ 1000003) * 1000003) ^ (true != this.b ? 1237 : 1231)) * 1000003) ^ this.c;
    }

    public final String toString() {
        String str = this.f9916a;
        boolean z = this.b;
        int i = this.c;
        return "MLKitLoggingOptions{libraryName=" + str + ", enableFirelog=" + z + ", firelogEventType=" + i + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznx
    public final int zza() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznx
    public final String zzb() {
        return this.f9916a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznx
    public final boolean zzc() {
        return this.b;
    }
}
