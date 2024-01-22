package com.google.android.gms.internal.mlkit_code_scanner;
/* loaded from: classes8.dex */
public final class u5 extends zznt {

    /* renamed from: a  reason: collision with root package name */
    public final String f9107a;
    public final boolean b;
    public final int c;

    public /* synthetic */ u5(String str, boolean z, int i, zzno zznoVar) {
        this.f9107a = str;
        this.b = z;
        this.c = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zznt) {
            zznt zzntVar = (zznt) obj;
            if (this.f9107a.equals(zzntVar.zzb()) && this.b == zzntVar.zzc() && this.c == zzntVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f9107a.hashCode() ^ 1000003) * 1000003) ^ (true != this.b ? 1237 : 1231)) * 1000003) ^ this.c;
    }

    public final String toString() {
        String str = this.f9107a;
        boolean z = this.b;
        int i = this.c;
        return "MLKitLoggingOptions{libraryName=" + str + ", enableFirelog=" + z + ", firelogEventType=" + i + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznt
    public final int zza() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznt
    public final String zzb() {
        return this.f9107a;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznt
    public final boolean zzc() {
        return this.b;
    }
}
