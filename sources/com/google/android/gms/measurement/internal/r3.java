package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public abstract class r3 extends q3 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10129a;

    public r3(zzkn zzknVar) {
        super(zzknVar);
        this.zzf.l();
    }

    public final boolean a() {
        return this.f10129a;
    }

    public final void zzY() {
        if (!a()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzZ() {
        if (!this.f10129a) {
            zzb();
            this.zzf.g();
            this.f10129a = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public abstract boolean zzb();
}
