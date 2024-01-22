package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public abstract class x0 extends w0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10134a;

    public x0(zzfs zzfsVar) {
        super(zzfsVar);
        this.zzs.c();
    }

    public final boolean a() {
        return this.f10134a;
    }

    public void zzaA() {
    }

    public abstract boolean zzf();

    public final void zzu() {
        if (!a()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzv() {
        if (!this.f10134a) {
            if (zzf()) {
                return;
            }
            this.zzs.b();
            this.f10134a = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public final void zzw() {
        if (!this.f10134a) {
            zzaA();
            this.zzs.b();
            this.f10134a = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
