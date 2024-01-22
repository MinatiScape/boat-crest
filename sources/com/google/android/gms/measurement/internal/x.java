package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public abstract class x extends m {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10133a;

    public x(zzfs zzfsVar) {
        super(zzfsVar);
        this.zzs.c();
    }

    public final boolean a() {
        return this.f10133a;
    }

    public final void zza() {
        if (!a()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzb() {
        if (!this.f10133a) {
            if (zzf()) {
                return;
            }
            this.zzs.b();
            this.f10133a = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public final void zzc() {
        if (!this.f10133a) {
            zzd();
            this.zzs.b();
            this.f10133a = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public void zzd() {
    }

    public abstract boolean zzf();
}
