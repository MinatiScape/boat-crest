package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class a {
    public static final byte[] d = Strings.toByteArray("expand 32-byte to 64-byte state!");

    /* renamed from: a  reason: collision with root package name */
    public final Digest f15319a;
    public final Digest b;
    public final c c;

    public a(Digest digest) {
        this(digest, null);
    }

    public a(Digest digest, Digest digest2) {
        this.c = new c();
        this.f15319a = digest;
        this.b = digest2;
    }

    public Digest a() {
        return this.b;
    }

    public int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[] bArr3 = new byte[64];
        for (int i3 = 0; i3 < 32; i3++) {
            bArr3[i3] = bArr2[i2 + i3];
            bArr3[i3 + 32] = d[i3];
        }
        this.c.a(bArr3, bArr3);
        for (int i4 = 0; i4 < 32; i4++) {
            bArr3[i4] = (byte) (bArr3[i4] ^ bArr2[(i2 + i4) + 32]);
        }
        this.c.a(bArr3, bArr3);
        for (int i5 = 0; i5 < 32; i5++) {
            bArr[i + i5] = bArr3[i5];
        }
        return 0;
    }

    public int c(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        byte[] bArr4 = new byte[64];
        for (int i4 = 0; i4 < 64; i4++) {
            bArr4[i4] = (byte) (bArr2[i2 + i4] ^ bArr3[i3 + i4]);
        }
        return b(bArr, i, bArr4, 0);
    }

    public int d(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[] bArr3 = new byte[64];
        for (int i3 = 0; i3 < 32; i3++) {
            bArr3[i3] = bArr2[i2 + i3];
            bArr3[i3 + 32] = d[i3];
        }
        this.c.a(bArr3, bArr3);
        for (int i4 = 0; i4 < 32; i4++) {
            bArr[i + i4] = bArr3[i4];
        }
        return 0;
    }

    public int e(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        byte[] bArr4 = new byte[32];
        for (int i4 = 0; i4 < 32; i4++) {
            bArr4[i4] = (byte) (bArr2[i2 + i4] ^ bArr3[i3 + i4]);
        }
        return d(bArr, i, bArr4, 0);
    }

    public int f(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.f15319a.update(bArr2, 0, i2);
        this.f15319a.doFinal(bArr, i);
        return 0;
    }
}
