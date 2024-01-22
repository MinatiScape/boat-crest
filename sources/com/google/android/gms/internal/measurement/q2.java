package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class q2 extends zzjc {

    /* renamed from: a  reason: collision with root package name */
    public int f8923a;
    public int b;
    public int c;

    public /* synthetic */ q2(byte[] bArr, int i, int i2, boolean z, zziz zzizVar) {
        super(null);
        this.c = Integer.MAX_VALUE;
        this.f8923a = 0;
    }

    public final int a(int i) throws zzkj {
        int i2 = this.c;
        this.c = 0;
        int i3 = this.f8923a + this.b;
        this.f8923a = i3;
        if (i3 > 0) {
            this.b = i3;
            this.f8923a = 0;
        } else {
            this.b = 0;
        }
        return i2;
    }
}
