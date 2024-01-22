package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class zzyc<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public final i7<K, V> f8816a;
    public final K b;
    public final V c;

    public zzyc(zzaan zzaanVar, K k, zzaan zzaanVar2, V v) {
        this.f8816a = new i7<>(zzaanVar, k, zzaanVar2, v);
        this.b = k;
        this.c = v;
    }

    public static <K, V> int a(i7<K, V> i7Var, K k, V v) {
        return p6.e(i7Var.f8691a, 1, k) + p6.e(i7Var.c, 2, v);
    }

    public static <K, V> void b(zzwi zzwiVar, i7<K, V> i7Var, K k, V v) throws IOException {
        p6.h(zzwiVar, i7Var.f8691a, 1, k);
        p6.h(zzwiVar, i7Var.c, 2, v);
    }

    public static <K, V> zzyc<K, V> zza(zzaan zzaanVar, K k, zzaan zzaanVar2, V v) {
        return new zzyc<>(zzaanVar, k, zzaanVar2, v);
    }

    public final i7<K, V> c() {
        return this.f8816a;
    }

    public final int zzc(int i, K k, V v) {
        return zzwi.zzdf(i) + zzwi.g(a(this.f8816a, k, v));
    }
}
