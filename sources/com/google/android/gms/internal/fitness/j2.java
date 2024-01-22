package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class j2 extends zzgj {

    /* renamed from: a  reason: collision with root package name */
    public int f8831a;
    public int b;
    public int c;
    public int d;
    public int e;

    public j2(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.e = Integer.MAX_VALUE;
        this.f8831a = i2 + i;
        this.c = i;
        this.d = i;
    }

    @Override // com.google.android.gms.internal.fitness.zzgj
    public final int zzbb() {
        return this.c - this.d;
    }

    @Override // com.google.android.gms.internal.fitness.zzgj
    public final int zzm(int i) throws zzhk {
        if (i >= 0) {
            int zzbb = i + zzbb();
            int i2 = this.e;
            if (zzbb <= i2) {
                this.e = zzbb;
                int i3 = this.f8831a + this.b;
                this.f8831a = i3;
                int i4 = i3 - this.d;
                if (i4 > zzbb) {
                    int i5 = i4 - zzbb;
                    this.b = i5;
                    this.f8831a = i3 - i5;
                } else {
                    this.b = 0;
                }
                return i2;
            }
            throw new zzhk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new zzhk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
}
