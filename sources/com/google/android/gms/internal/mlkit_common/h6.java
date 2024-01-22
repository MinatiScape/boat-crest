package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;
/* loaded from: classes8.dex */
public final class h6 extends zzqb {

    /* renamed from: a  reason: collision with root package name */
    public final zzlm f9211a;
    public final String b;
    public final boolean c;
    public final boolean d;
    public final ModelType e;
    public final zzls f;
    public final int g;

    public /* synthetic */ h6(zzlm zzlmVar, String str, boolean z, boolean z2, ModelType modelType, zzls zzlsVar, int i, zzpo zzpoVar) {
        this.f9211a = zzlmVar;
        this.b = str;
        this.c = z;
        this.d = z2;
        this.e = modelType;
        this.f = zzlsVar;
        this.g = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzqb) {
            zzqb zzqbVar = (zzqb) obj;
            if (this.f9211a.equals(zzqbVar.zzc()) && this.b.equals(zzqbVar.zze()) && this.c == zzqbVar.zzg() && this.d == zzqbVar.zzf() && this.e.equals(zzqbVar.zzb()) && this.f.equals(zzqbVar.zzd()) && this.g == zzqbVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((this.f9211a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
        return (((((((((hashCode * 1000003) ^ (true != this.c ? 1237 : 1231)) * 1000003) ^ (true == this.d ? 1231 : 1237)) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g;
    }

    public final String toString() {
        String obj = this.f9211a.toString();
        String str = this.b;
        boolean z = this.c;
        boolean z2 = this.d;
        String obj2 = this.e.toString();
        String obj3 = this.f.toString();
        int i = this.g;
        return "RemoteModelLoggingOptions{errorCode=" + obj + ", tfliteSchemaVersion=" + str + ", shouldLogRoughDownloadTime=" + z + ", shouldLogExactDownloadTime=" + z2 + ", modelType=" + obj2 + ", downloadStatus=" + obj3 + ", failureStatusCode=" + i + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqb
    public final int zza() {
        return this.g;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqb
    public final ModelType zzb() {
        return this.e;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqb
    public final zzlm zzc() {
        return this.f9211a;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqb
    public final zzls zzd() {
        return this.f;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqb
    public final String zze() {
        return this.b;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqb
    public final boolean zzf() {
        return this.d;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqb
    public final boolean zzg() {
        return this.c;
    }
}
