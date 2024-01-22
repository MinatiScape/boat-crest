package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class j6 extends zzwh {

    /* renamed from: a  reason: collision with root package name */
    public int f8695a;
    public int b;
    public int c;
    public int d;
    public int e;

    public j6(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.e = Integer.MAX_VALUE;
        this.f8695a = i2 + i;
        this.c = i;
        this.d = i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzwh
    public final int zzcz(int i) throws zzxk {
        if (i >= 0) {
            int zztx = i + zztx();
            int i2 = this.e;
            if (zztx <= i2) {
                this.e = zztx;
                int i3 = this.f8695a + this.b;
                this.f8695a = i3;
                int i4 = i3 - this.d;
                if (i4 > zztx) {
                    int i5 = i4 - zztx;
                    this.b = i5;
                    this.f8695a = i3 - i5;
                } else {
                    this.b = 0;
                }
                return i2;
            }
            throw zzxk.zzve();
        }
        throw zzxk.zzvf();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzwh
    public final int zztx() {
        return this.c - this.d;
    }
}
