package org.bouncycastle.crypto.signers;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SignerWithRecovery;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class ISO9796d2PSSSigner implements SignerWithRecovery {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14838a;
    public AsymmetricBlockCipher b;
    public SecureRandom c;
    public byte[] d;
    public int e;
    public int f;
    public int g;
    public byte[] h;
    public byte[] i;
    public int j;
    public int k;
    public boolean l;
    public byte[] m;
    public byte[] n;
    public byte[] o;
    public int p;
    public int q;

    public ISO9796d2PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i) {
        this(asymmetricBlockCipher, digest, i, false);
    }

    public ISO9796d2PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i, boolean z) {
        int intValue;
        this.b = asymmetricBlockCipher;
        this.f14838a = digest;
        this.e = digest.getDigestSize();
        this.k = i;
        if (z) {
            intValue = 188;
        } else {
            Integer trailer = ISOTrailers.getTrailer(digest);
            if (trailer == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
            intValue = trailer.intValue();
        }
        this.f = intValue;
    }

    public final void a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    public final void b(long j, byte[] bArr) {
        bArr[0] = (byte) (j >>> 56);
        bArr[1] = (byte) (j >>> 48);
        bArr[2] = (byte) (j >>> 40);
        bArr[3] = (byte) (j >>> 32);
        bArr[4] = (byte) (j >>> 24);
        bArr[5] = (byte) (j >>> 16);
        bArr[6] = (byte) (j >>> 8);
        bArr[7] = (byte) (j >>> 0);
    }

    public final void c(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    public final boolean d(byte[] bArr, byte[] bArr2) {
        boolean z = this.j == bArr2.length;
        for (int i = 0; i != bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                z = false;
            }
        }
        return z;
    }

    public final byte[] e(byte[] bArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[this.e];
        byte[] bArr4 = new byte[4];
        this.f14838a.reset();
        int i5 = 0;
        while (true) {
            i4 = this.e;
            if (i5 >= i3 / i4) {
                break;
            }
            a(i5, bArr4);
            this.f14838a.update(bArr, i, i2);
            this.f14838a.update(bArr4, 0, 4);
            this.f14838a.doFinal(bArr3, 0);
            int i6 = this.e;
            System.arraycopy(bArr3, 0, bArr2, i5 * i6, i6);
            i5++;
        }
        if (i4 * i5 < i3) {
            a(i5, bArr4);
            this.f14838a.update(bArr, i, i2);
            this.f14838a.update(bArr4, 0, 4);
            this.f14838a.doFinal(bArr3, 0);
            int i7 = this.e;
            System.arraycopy(bArr3, 0, bArr2, i5 * i7, i3 - (i5 * i7));
        }
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        int digestSize = this.f14838a.getDigestSize();
        byte[] bArr = new byte[digestSize];
        this.f14838a.doFinal(bArr, 0);
        byte[] bArr2 = new byte[8];
        b(this.j * 8, bArr2);
        this.f14838a.update(bArr2, 0, 8);
        this.f14838a.update(this.i, 0, this.j);
        this.f14838a.update(bArr, 0, digestSize);
        byte[] bArr3 = this.d;
        if (bArr3 == null) {
            bArr3 = new byte[this.k];
            this.c.nextBytes(bArr3);
        }
        this.f14838a.update(bArr3, 0, bArr3.length);
        int digestSize2 = this.f14838a.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        this.f14838a.doFinal(bArr4, 0);
        int i = this.f == 188 ? 1 : 2;
        byte[] bArr5 = this.h;
        int length = bArr5.length;
        int i2 = this.j;
        int length2 = ((((length - i2) - bArr3.length) - this.e) - i) - 1;
        bArr5[length2] = 1;
        int i3 = length2 + 1;
        System.arraycopy(this.i, 0, bArr5, i3, i2);
        System.arraycopy(bArr3, 0, this.h, i3 + this.j, bArr3.length);
        byte[] e = e(bArr4, 0, digestSize2, (this.h.length - this.e) - i);
        for (int i4 = 0; i4 != e.length; i4++) {
            byte[] bArr6 = this.h;
            bArr6[i4] = (byte) (bArr6[i4] ^ e[i4]);
        }
        byte[] bArr7 = this.h;
        int length3 = bArr7.length;
        int i5 = this.e;
        System.arraycopy(bArr4, 0, bArr7, (length3 - i5) - i, i5);
        int i6 = this.f;
        if (i6 == 188) {
            byte[] bArr8 = this.h;
            bArr8[bArr8.length - 1] = PSSSigner.TRAILER_IMPLICIT;
        } else {
            byte[] bArr9 = this.h;
            bArr9[bArr9.length - 2] = (byte) (i6 >>> 8);
            bArr9[bArr9.length - 1] = (byte) i6;
        }
        byte[] bArr10 = this.h;
        bArr10[0] = (byte) (bArr10[0] & Byte.MAX_VALUE);
        byte[] processBlock = this.b.processBlock(bArr10, 0, bArr10.length);
        int i7 = this.j;
        byte[] bArr11 = new byte[i7];
        this.m = bArr11;
        byte[] bArr12 = this.i;
        this.l = i7 <= bArr12.length;
        System.arraycopy(bArr12, 0, bArr11, 0, bArr11.length);
        c(this.i);
        c(this.h);
        this.j = 0;
        return processBlock;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public byte[] getRecoveredMessage() {
        return this.m;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public boolean hasFullMessage() {
        return this.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    @Override // org.bouncycastle.crypto.Signer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(boolean r4, org.bouncycastle.crypto.CipherParameters r5) {
        /*
            r3 = this;
            int r0 = r3.k
            boolean r1 = r5 instanceof org.bouncycastle.crypto.params.ParametersWithRandom
            if (r1 == 0) goto L17
            org.bouncycastle.crypto.params.ParametersWithRandom r5 = (org.bouncycastle.crypto.params.ParametersWithRandom) r5
            org.bouncycastle.crypto.CipherParameters r1 = r5.getParameters()
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = (org.bouncycastle.crypto.params.RSAKeyParameters) r1
            if (r4 == 0) goto L44
            java.security.SecureRandom r5 = r5.getRandom()
        L14:
            r3.c = r5
            goto L44
        L17:
            boolean r1 = r5 instanceof org.bouncycastle.crypto.params.ParametersWithSalt
            if (r1 == 0) goto L39
            org.bouncycastle.crypto.params.ParametersWithSalt r5 = (org.bouncycastle.crypto.params.ParametersWithSalt) r5
            org.bouncycastle.crypto.CipherParameters r0 = r5.getParameters()
            r1 = r0
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = (org.bouncycastle.crypto.params.RSAKeyParameters) r1
            byte[] r5 = r5.getSalt()
            r3.d = r5
            int r0 = r5.length
            int r5 = r5.length
            int r2 = r3.k
            if (r5 != r2) goto L31
            goto L44
        L31:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Fixed salt is of wrong length"
            r4.<init>(r5)
            throw r4
        L39:
            r1 = r5
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = (org.bouncycastle.crypto.params.RSAKeyParameters) r1
            if (r4 == 0) goto L44
            java.security.SecureRandom r5 = new java.security.SecureRandom
            r5.<init>()
            goto L14
        L44:
            org.bouncycastle.crypto.AsymmetricBlockCipher r5 = r3.b
            r5.init(r4, r1)
            java.math.BigInteger r4 = r1.getModulus()
            int r4 = r4.bitLength()
            r3.g = r4
            int r4 = r4 + 7
            int r4 = r4 / 8
            byte[] r4 = new byte[r4]
            r3.h = r4
            int r5 = r3.f
            r1 = 188(0xbc, float:2.63E-43)
            int r4 = r4.length
            if (r5 != r1) goto L73
            org.bouncycastle.crypto.Digest r5 = r3.f14838a
            int r5 = r5.getDigestSize()
            int r4 = r4 - r5
            int r4 = r4 - r0
            int r4 = r4 + (-1)
            int r4 = r4 + (-1)
            byte[] r4 = new byte[r4]
            r3.i = r4
            goto L83
        L73:
            org.bouncycastle.crypto.Digest r5 = r3.f14838a
            int r5 = r5.getDigestSize()
            int r4 = r4 - r5
            int r4 = r4 - r0
            int r4 = r4 + (-1)
            int r4 = r4 + (-2)
            byte[] r4 = new byte[r4]
            r3.i = r4
        L83:
            r3.reset()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.signers.ISO9796d2PSSSigner.init(boolean, org.bouncycastle.crypto.CipherParameters):void");
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.f14838a.reset();
        this.j = 0;
        byte[] bArr = this.i;
        if (bArr != null) {
            c(bArr);
        }
        byte[] bArr2 = this.m;
        if (bArr2 != null) {
            c(bArr2);
            this.m = null;
        }
        this.l = false;
        if (this.n != null) {
            this.n = null;
            c(this.o);
            this.o = null;
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        if (this.n == null) {
            int i = this.j;
            byte[] bArr = this.i;
            if (i < bArr.length) {
                this.j = i + 1;
                bArr[i] = b;
                return;
            }
        }
        this.f14838a.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        if (this.n == null) {
            while (i2 > 0 && this.j < this.i.length) {
                update(bArr[i]);
                i++;
                i2--;
            }
        }
        if (i2 > 0) {
            this.f14838a.update(bArr, i, i2);
        }
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public void updateWithRecoveredMessage(byte[] bArr) throws InvalidCipherTextException {
        byte[] processBlock = this.b.processBlock(bArr, 0, bArr.length);
        int length = processBlock.length;
        int i = this.g;
        if (length < (i + 7) / 8) {
            int i2 = (i + 7) / 8;
            byte[] bArr2 = new byte[i2];
            System.arraycopy(processBlock, 0, bArr2, i2 - processBlock.length, processBlock.length);
            c(processBlock);
            processBlock = bArr2;
        }
        int i3 = 2;
        if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
            i3 = 1;
        } else {
            int i4 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
            Integer trailer = ISOTrailers.getTrailer(this.f14838a);
            if (trailer == null) {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            int intValue = trailer.intValue();
            if (i4 != intValue && (intValue != 15052 || i4 != 16588)) {
                throw new IllegalStateException("signer initialised with wrong digest for trailer " + i4);
            }
        }
        this.f14838a.doFinal(new byte[this.e], 0);
        int length2 = processBlock.length;
        int i5 = this.e;
        byte[] e = e(processBlock, (length2 - i5) - i3, i5, (processBlock.length - i5) - i3);
        for (int i6 = 0; i6 != e.length; i6++) {
            processBlock[i6] = (byte) (processBlock[i6] ^ e[i6]);
        }
        processBlock[0] = (byte) (processBlock[0] & Byte.MAX_VALUE);
        int i7 = 0;
        while (i7 != processBlock.length && processBlock[i7] != 1) {
            i7++;
        }
        int i8 = i7 + 1;
        if (i8 >= processBlock.length) {
            c(processBlock);
        }
        this.l = i8 > 1;
        byte[] bArr3 = new byte[(e.length - i8) - this.k];
        this.m = bArr3;
        System.arraycopy(processBlock, i8, bArr3, 0, bArr3.length);
        byte[] bArr4 = this.m;
        System.arraycopy(bArr4, 0, this.i, 0, bArr4.length);
        this.n = bArr;
        this.o = processBlock;
        this.p = i8;
        this.q = i3;
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        int i = this.e;
        byte[] bArr2 = new byte[i];
        this.f14838a.doFinal(bArr2, 0);
        byte[] bArr3 = this.n;
        if (bArr3 == null) {
            try {
                updateWithRecoveredMessage(bArr);
            } catch (Exception unused) {
                return false;
            }
        } else if (!Arrays.areEqual(bArr3, bArr)) {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        }
        byte[] bArr4 = this.o;
        int i2 = this.p;
        int i3 = this.q;
        this.n = null;
        this.o = null;
        byte[] bArr5 = new byte[8];
        b(this.m.length * 8, bArr5);
        this.f14838a.update(bArr5, 0, 8);
        byte[] bArr6 = this.m;
        if (bArr6.length != 0) {
            this.f14838a.update(bArr6, 0, bArr6.length);
        }
        this.f14838a.update(bArr2, 0, i);
        byte[] bArr7 = this.d;
        if (bArr7 != null) {
            this.f14838a.update(bArr7, 0, bArr7.length);
        } else {
            this.f14838a.update(bArr4, i2 + this.m.length, this.k);
        }
        int digestSize = this.f14838a.getDigestSize();
        byte[] bArr8 = new byte[digestSize];
        this.f14838a.doFinal(bArr8, 0);
        int length = (bArr4.length - i3) - digestSize;
        boolean z = true;
        for (int i4 = 0; i4 != digestSize; i4++) {
            if (bArr8[i4] != bArr4[length + i4]) {
                z = false;
            }
        }
        c(bArr4);
        c(bArr8);
        if (!z) {
            this.l = false;
            this.j = 0;
            c(this.m);
            return false;
        } else if (this.j == 0 || d(this.i, this.m)) {
            this.j = 0;
            c(this.i);
            return true;
        } else {
            this.j = 0;
            c(this.i);
            return false;
        }
    }
}
