package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public final class z extends zzbk {

    /* renamed from: a  reason: collision with root package name */
    public int f8607a;
    public int b;
    public int c;
    public int d;
    public int e;

    public z(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.e = Integer.MAX_VALUE;
        this.f8607a = i2 + i;
        this.c = i;
        this.d = i;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbk
    public final int zzaf() {
        return this.c - this.d;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbk
    public final int zzl(int i) throws zzco {
        if (i >= 0) {
            int zzaf = i + zzaf();
            int i2 = this.e;
            if (zzaf <= i2) {
                this.e = zzaf;
                int i3 = this.f8607a + this.b;
                this.f8607a = i3;
                int i4 = i3 - this.d;
                if (i4 > zzaf) {
                    int i5 = i4 - zzaf;
                    this.b = i5;
                    this.f8607a = i3 - i5;
                } else {
                    this.b = 0;
                }
                return i2;
            }
            throw zzco.zzbl();
        }
        throw new zzco("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
}
