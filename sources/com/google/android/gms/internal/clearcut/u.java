package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public final class u {

    /* renamed from: a  reason: collision with root package name */
    public final zzbn f8601a;
    public final byte[] b;

    public u(int i) {
        byte[] bArr = new byte[i];
        this.b = bArr;
        this.f8601a = zzbn.zzc(bArr);
    }

    public /* synthetic */ u(int i, q qVar) {
        this(i);
    }

    public final zzbb a() {
        if (this.f8601a.zzag() == 0) {
            return new w(this.b);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzbn b() {
        return this.f8601a;
    }
}
