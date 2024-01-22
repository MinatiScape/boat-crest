package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.e;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class SPHINCS256Signer implements MessageSigner {

    /* renamed from: a  reason: collision with root package name */
    public final a f15318a;
    public byte[] b;

    public SPHINCS256Signer(Digest digest, Digest digest2) {
        if (digest.getDigestSize() != 32) {
            throw new IllegalArgumentException("n-digest needs to produce 32 bytes of output");
        }
        if (digest2.getDigestSize() != 64) {
            throw new IllegalArgumentException("2n-digest needs to produce 64 bytes of output");
        }
        this.f15318a = new a(digest, digest2);
    }

    public static void a(a aVar, byte[] bArr, byte[] bArr2, int i, e.a aVar2, byte[] bArr3, byte[] bArr4, int i2) {
        e.a aVar3 = new e.a(aVar2);
        byte[] bArr5 = new byte[2048];
        byte[] bArr6 = new byte[1024];
        byte[] bArr7 = new byte[68608];
        aVar3.c = 0L;
        while (true) {
            long j = aVar3.c;
            if (j >= 32) {
                break;
            }
            d.a(aVar, bArr6, (int) (j * 32), bArr3, aVar3);
            aVar3.c++;
        }
        f fVar = new f();
        aVar3.c = 0L;
        while (true) {
            long j2 = aVar3.c;
            if (j2 >= 32) {
                break;
            }
            fVar.d(aVar, bArr7, (int) (67 * j2 * 32), bArr6, (int) (j2 * 32), bArr4, 0);
            aVar3.c++;
        }
        aVar3.c = 0L;
        while (true) {
            long j3 = aVar3.c;
            if (j3 >= 32) {
                break;
            }
            e.b(aVar, bArr5, (int) ((j3 * 32) + 1024), bArr7, (int) (j3 * 67 * 32), bArr4, 0);
            aVar3.c++;
        }
        int i3 = 0;
        for (int i4 = 32; i4 > 0; i4 >>>= 1) {
            for (int i5 = 0; i5 < i4; i5 += 2) {
                aVar.c(bArr5, ((i4 >>> 1) * 32) + ((i5 >>> 1) * 32), bArr5, (i4 * 32) + (i5 * 32), bArr4, (i3 + 7) * 2 * 32);
            }
            i3++;
        }
        int i6 = (int) aVar2.c;
        for (int i7 = 0; i7 < i2; i7++) {
            System.arraycopy(bArr5, ((32 >>> i7) * 32) + (((i6 >>> i7) ^ 1) * 32), bArr2, i + (i7 * 32), 32);
        }
        System.arraycopy(bArr5, 32, bArr, 0, 32);
    }

    public static void c(a aVar, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if ((i & 1) != 0) {
            for (int i4 = 0; i4 < 32; i4++) {
                bArr5[i4 + 32] = bArr2[i4];
            }
            for (int i5 = 0; i5 < 32; i5++) {
                bArr5[i5] = bArr3[i2 + i5];
            }
        } else {
            for (int i6 = 0; i6 < 32; i6++) {
                bArr5[i6] = bArr2[i6];
            }
            for (int i7 = 0; i7 < 32; i7++) {
                bArr5[i7 + 32] = bArr3[i2 + i7];
            }
        }
        int i8 = i2 + 32;
        int i9 = 0;
        int i10 = i;
        while (i9 < i3 - 1) {
            int i11 = i10 >>> 1;
            if ((i11 & 1) != 0) {
                aVar.c(bArr5, 32, bArr5, 0, bArr4, (i9 + 7) * 2 * 32);
                for (int i12 = 0; i12 < 32; i12++) {
                    bArr5[i12] = bArr3[i8 + i12];
                }
            } else {
                aVar.c(bArr5, 0, bArr5, 0, bArr4, (i9 + 7) * 2 * 32);
                for (int i13 = 0; i13 < 32; i13++) {
                    bArr5[i13 + 32] = bArr3[i8 + i13];
                }
            }
            i8 += 32;
            i9++;
            i10 = i11;
        }
        aVar.c(bArr, 0, bArr5, 0, bArr4, ((i3 + 7) - 1) * 2 * 32);
    }

    public byte[] b(a aVar, byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[41000];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[64];
        long[] jArr = new long[8];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[32];
        byte[] bArr8 = new byte[1024];
        byte[] bArr9 = new byte[1088];
        for (int i = 0; i < 1088; i++) {
            bArr9[i] = bArr2[i];
        }
        System.arraycopy(bArr9, 1056, bArr3, 40968, 32);
        Digest a2 = aVar.a();
        byte[] bArr10 = new byte[a2.getDigestSize()];
        a2.update(bArr3, 40968, 32);
        a2.update(bArr, 0, bArr.length);
        a2.doFinal(bArr10, 0);
        e(bArr3, 40968, 32);
        for (int i2 = 0; i2 != 8; i2++) {
            jArr[i2] = Pack.littleEndianToLong(bArr10, i2 * 8);
        }
        long j = jArr[0] & 1152921504606846975L;
        System.arraycopy(bArr10, 16, bArr4, 0, 32);
        System.arraycopy(bArr4, 0, bArr3, 39912, 32);
        e.a aVar2 = new e.a();
        aVar2.f15320a = 11;
        aVar2.b = 0L;
        aVar2.c = 0L;
        System.arraycopy(bArr9, 32, bArr3, 39944, 1024);
        e.c(aVar, bArr3, 40968, 5, bArr9, aVar2, bArr3, 39944);
        Digest a3 = aVar.a();
        a3.update(bArr3, 39912, 1088);
        a3.update(bArr, 0, bArr.length);
        a3.doFinal(bArr5, 0);
        e.a aVar3 = new e.a();
        aVar3.f15320a = 12;
        aVar3.c = (int) (j & 31);
        aVar3.b = j >>> 5;
        for (int i3 = 0; i3 < 32; i3++) {
            bArr3[i3] = bArr4[i3];
        }
        byte[] bArr11 = bArr8;
        System.arraycopy(bArr9, 32, bArr11, 0, 1024);
        for (int i4 = 0; i4 < 8; i4++) {
            bArr3[32 + i4] = (byte) ((j >>> (i4 * 8)) & 255);
        }
        d.a(aVar, bArr7, 0, bArr9, aVar3);
        new b();
        byte[] bArr12 = bArr9;
        f fVar = new f();
        int b = 40 + b.b(aVar, bArr3, 40, bArr6, bArr7, bArr11, bArr5);
        int i5 = 0;
        for (int i6 = 12; i5 < i6; i6 = 12) {
            aVar3.f15320a = i5;
            byte[] bArr13 = bArr12;
            d.a(aVar, bArr7, 0, bArr13, aVar3);
            int i7 = b;
            int i8 = b;
            byte[] bArr14 = bArr11;
            fVar.e(aVar, bArr3, i7, bArr6, bArr7, bArr14);
            int i9 = i8 + 2144;
            a(aVar, bArr6, bArr3, i9, aVar3, bArr13, bArr14, 5);
            b = i9 + 160;
            long j2 = aVar3.b;
            aVar3.c = (int) (j2 & 31);
            aVar3.b = j2 >>> 5;
            i5++;
            bArr12 = bArr13;
            bArr11 = bArr11;
        }
        e(bArr12, 0, 1088);
        return bArr3;
    }

    public boolean d(a aVar, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[2144];
        byte[] bArr5 = new byte[32];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[41000];
        byte[] bArr8 = new byte[1056];
        if (bArr2.length == 41000) {
            byte[] bArr9 = new byte[64];
            for (int i = 0; i < 1056; i++) {
                bArr8[i] = bArr3[i];
            }
            byte[] bArr10 = new byte[32];
            for (int i2 = 0; i2 < 32; i2++) {
                bArr10[i2] = bArr2[i2];
            }
            System.arraycopy(bArr2, 0, bArr7, 0, 41000);
            Digest a2 = aVar.a();
            a2.update(bArr10, 0, 32);
            a2.update(bArr8, 0, 1056);
            a2.update(bArr, 0, bArr.length);
            a2.doFinal(bArr9, 0);
            long j = 0;
            for (int i3 = 0; i3 < 8; i3++) {
                j ^= (bArr7[32 + i3] & 255) << (i3 * 8);
            }
            new b();
            b.c(aVar, bArr6, bArr7, 40, bArr8, bArr9);
            f fVar = new f();
            int i4 = 0;
            int i5 = 13352;
            while (i4 < 12) {
                byte[] bArr11 = bArr8;
                fVar.f(aVar, bArr4, bArr7, i5, bArr6, bArr8);
                int i6 = i5 + 2144;
                e.b(aVar, bArr5, 0, bArr4, 0, bArr11, 0);
                byte[] bArr12 = bArr7;
                c(aVar, bArr6, bArr5, (int) (31 & j), bArr12, i6, bArr11, 5);
                j >>= 5;
                i5 = i6 + 160;
                i4++;
                bArr7 = bArr12;
                bArr8 = bArr11;
            }
            byte[] bArr13 = bArr8;
            boolean z = true;
            for (int i7 = 0; i7 < 32; i7++) {
                if (bArr6[i7] != bArr13[i7 + 1024]) {
                    z = false;
                }
            }
            return z;
        }
        throw new IllegalArgumentException("signature wrong size");
    }

    public final void e(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 != i2; i3++) {
            bArr[i + i3] = 0;
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        return b(this.f15318a, bArr, this.b);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (z) {
            this.b = ((SPHINCSPrivateKeyParameters) cipherParameters).getKeyData();
        } else {
            this.b = ((SPHINCSPublicKeyParameters) cipherParameters).getKeyData();
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return d(this.f15318a, bArr, bArr2, this.b);
    }
}
