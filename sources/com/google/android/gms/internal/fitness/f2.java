package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class f2 {

    /* renamed from: a  reason: collision with root package name */
    public final zzgk f8825a;
    public final byte[] b;

    public f2(int i) {
        byte[] bArr = new byte[i];
        this.b = bArr;
        this.f8825a = zzgk.zza(bArr);
    }

    public final zzfx a() {
        if (this.f8825a.zzbc() == 0) {
            return new h2(this.b);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzgk b() {
        return this.f8825a;
    }

    public /* synthetic */ f2(int i, x1 x1Var) {
        this(i);
    }
}
