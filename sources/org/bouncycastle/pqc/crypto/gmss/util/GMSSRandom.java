package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public class GMSSRandom {

    /* renamed from: a  reason: collision with root package name */
    public Digest f15296a;

    public GMSSRandom(Digest digest) {
        this.f15296a = digest;
    }

    public final void a(byte[] bArr, byte[] bArr2) {
        byte b = 0;
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] & 255) + (bArr2[i] & 255) + b;
            bArr[i] = (byte) i2;
            b = (byte) (i2 >> 8);
        }
    }

    public final void b(byte[] bArr) {
        byte b = 1;
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] & 255) + b;
            bArr[i] = (byte) i2;
            b = (byte) (i2 >> 8);
        }
    }

    public byte[] nextSeed(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        this.f15296a.update(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.f15296a.getDigestSize()];
        this.f15296a.doFinal(bArr3, 0);
        a(bArr, bArr3);
        b(bArr);
        return bArr3;
    }
}
