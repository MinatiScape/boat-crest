package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
/* loaded from: classes13.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final Digest f15339a;
    public final int b;

    public b(Digest digest, int i) {
        Objects.requireNonNull(digest, "digest == null");
        this.f15339a = digest;
        this.b = i;
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i = this.b;
        if (length == i) {
            if (bArr2.length == i) {
                return e(0, bArr, bArr2);
            }
            throw new IllegalArgumentException("wrong in length");
        }
        throw new IllegalArgumentException("wrong key length");
    }

    public byte[] b(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i = this.b;
        if (length == i) {
            if (bArr2.length == i * 2) {
                return e(1, bArr, bArr2);
            }
            throw new IllegalArgumentException("wrong in length");
        }
        throw new IllegalArgumentException("wrong key length");
    }

    public byte[] c(byte[] bArr, byte[] bArr2) {
        if (bArr.length == this.b * 3) {
            return e(2, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong key length");
    }

    public byte[] d(byte[] bArr, byte[] bArr2) {
        if (bArr.length == this.b) {
            if (bArr2.length == 32) {
                return e(3, bArr, bArr2);
            }
            throw new IllegalArgumentException("wrong address length");
        }
        throw new IllegalArgumentException("wrong key length");
    }

    public final byte[] e(int i, byte[] bArr, byte[] bArr2) {
        byte[] bytesBigEndian = XMSSUtil.toBytesBigEndian(i, this.b);
        this.f15339a.update(bytesBigEndian, 0, bytesBigEndian.length);
        this.f15339a.update(bArr, 0, bArr.length);
        this.f15339a.update(bArr2, 0, bArr2.length);
        int i2 = this.b;
        byte[] bArr3 = new byte[i2];
        Digest digest = this.f15339a;
        if (digest instanceof Xof) {
            ((Xof) digest).doFinal(bArr3, 0, i2);
        } else {
            digest.doFinal(bArr3, 0);
        }
        return bArr3;
    }
}
