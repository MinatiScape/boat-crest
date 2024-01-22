package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes7.dex */
public final class t0 extends zzia {

    /* renamed from: a  reason: collision with root package name */
    public final zzte f8730a;

    public t0(zzig zzigVar, zzte zzteVar) {
        this.f8730a = zzteVar;
        zzteVar.setLenient(true);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void flush() throws IOException {
        this.f8730a.flush();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void writeBoolean(boolean z) throws IOException {
        this.f8730a.zzaw(z);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void writeString(String str) throws IOException {
        this.f8730a.zzcg(str);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zza(BigInteger bigInteger) throws IOException {
        this.f8730a.zza(bigInteger);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzah(int i) throws IOException {
        this.f8730a.zzu(i);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzan(String str) throws IOException {
        this.f8730a.zzcf(str);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zze(long j) throws IOException {
        this.f8730a.zzu(j);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzgv() throws IOException {
        this.f8730a.zzri();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzgw() throws IOException {
        this.f8730a.zzrj();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzgx() throws IOException {
        this.f8730a.zzrk();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzgy() throws IOException {
        this.f8730a.zzrl();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzgz() throws IOException {
        this.f8730a.zzrn();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzha() throws IOException {
        this.f8730a.setIndent("  ");
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zzj(float f) throws IOException {
        this.f8730a.zzb(f);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zza(double d) throws IOException {
        this.f8730a.zzb(d);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzia
    public final void zza(BigDecimal bigDecimal) throws IOException {
        this.f8730a.zza(bigDecimal);
    }
}
