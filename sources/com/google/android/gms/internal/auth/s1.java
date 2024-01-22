package com.google.android.gms.internal.auth;

import java.io.IOException;
/* loaded from: classes6.dex */
public final class s1 implements z1 {

    /* renamed from: a  reason: collision with root package name */
    public final zzfw f8538a;
    public final m2 b;
    public final y0 c;

    public s1(m2 m2Var, y0 y0Var, zzfw zzfwVar) {
        this.b = m2Var;
        this.c = y0Var;
        this.f8538a = zzfwVar;
    }

    public static s1 a(m2 m2Var, y0 y0Var, zzfw zzfwVar) {
        return new s1(m2Var, y0Var, zzfwVar);
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final boolean d(Object obj) {
        this.c.a(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final boolean e(Object obj, Object obj2) {
        return this.b.a(obj).equals(this.b.a(obj2));
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final void f(Object obj, byte[] bArr, int i, int i2, m0 m0Var) throws IOException {
        zzeu zzeuVar = (zzeu) obj;
        if (zzeuVar.zzc == zzgz.zza()) {
            zzeuVar.zzc = zzgz.b();
        }
        zzet zzetVar = (zzet) obj;
        throw null;
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final void g(Object obj, Object obj2) {
        b2.f(this.b, obj, obj2);
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final int zza(Object obj) {
        return this.b.a(obj).hashCode();
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final Object zzd() {
        return ((zzes) ((zzeu) this.f8538a).zzi(5, null, null)).zzg();
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final void zze(Object obj) {
        this.b.e(obj);
        this.c.b(obj);
    }
}
