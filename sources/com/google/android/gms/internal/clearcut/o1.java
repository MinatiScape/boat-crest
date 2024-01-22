package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
/* loaded from: classes7.dex */
public final class o1 implements e1 {

    /* renamed from: a  reason: collision with root package name */
    public final zzdo f8593a;
    public final p1 b;

    public o1(zzdo zzdoVar, String str, Object[] objArr) {
        this.f8593a = zzdoVar;
        this.b = new p1(zzdoVar.getClass(), str, objArr);
    }

    @Override // com.google.android.gms.internal.clearcut.e1
    public final int a() {
        int i;
        i = this.b.d;
        return (i & 1) == 1 ? zzcg.zzg.zzkl : zzcg.zzg.zzkm;
    }

    @Override // com.google.android.gms.internal.clearcut.e1
    public final boolean b() {
        int i;
        i = this.b.d;
        return (i & 2) == 2;
    }

    public final int c() {
        int i;
        i = this.b.e;
        return i;
    }

    public final p1 d() {
        return this.b;
    }

    public final int e() {
        int i;
        i = this.b.h;
        return i;
    }

    public final int f() {
        int i;
        i = this.b.i;
        return i;
    }

    public final int g() {
        int i;
        i = this.b.j;
        return i;
    }

    public final int h() {
        int i;
        i = this.b.m;
        return i;
    }

    public final int[] i() {
        int[] iArr;
        iArr = this.b.n;
        return iArr;
    }

    public final int j() {
        int i;
        i = this.b.l;
        return i;
    }

    public final int k() {
        int i;
        i = this.b.k;
        return i;
    }

    @Override // com.google.android.gms.internal.clearcut.e1
    public final zzdo zzch() {
        return this.f8593a;
    }
}
