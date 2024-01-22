package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class l1 extends h1 {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReferenceFieldUpdater<q1, Thread> f9440a;
    public final AtomicReferenceFieldUpdater<q1, q1> b;
    public final AtomicReferenceFieldUpdater<zzec, q1> c;
    public final AtomicReferenceFieldUpdater<zzec, k1> d;
    public final AtomicReferenceFieldUpdater<zzec, Object> e;

    public l1(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        super(null);
        this.f9440a = atomicReferenceFieldUpdater;
        this.b = atomicReferenceFieldUpdater2;
        this.c = atomicReferenceFieldUpdater3;
        this.d = atomicReferenceFieldUpdater4;
        this.e = atomicReferenceFieldUpdater5;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final k1 a(zzec zzecVar, k1 k1Var) {
        return this.d.getAndSet(zzecVar, k1Var);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final q1 b(zzec zzecVar, q1 q1Var) {
        return this.c.getAndSet(zzecVar, q1Var);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final void c(q1 q1Var, @CheckForNull q1 q1Var2) {
        this.b.lazySet(q1Var, q1Var2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final void d(q1 q1Var, Thread thread) {
        this.f9440a.lazySet(q1Var, thread);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean e(zzec zzecVar, @CheckForNull k1 k1Var, k1 k1Var2) {
        return zzed.zza(this.d, zzecVar, k1Var, k1Var2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean f(zzec zzecVar, @CheckForNull Object obj, Object obj2) {
        return zzed.zza(this.e, zzecVar, obj, obj2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean g(zzec zzecVar, @CheckForNull q1 q1Var, @CheckForNull q1 q1Var2) {
        return zzed.zza(this.c, zzecVar, q1Var, q1Var2);
    }
}
