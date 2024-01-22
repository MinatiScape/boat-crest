package org.bouncycastle.crypto.signers;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SignerWithRecovery;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class ISO9796d2Signer implements SignerWithRecovery {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14839a;
    public AsymmetricBlockCipher b;
    public int c;
    public int d;
    public byte[] e;
    public byte[] f;
    public int g;
    public boolean h;
    public byte[] i;
    public byte[] j;
    public byte[] k;

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, false);
    }

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean z) {
        int intValue;
        this.b = asymmetricBlockCipher;
        this.f14839a = digest;
        if (z) {
            intValue = 188;
        } else {
            Integer trailer = ISOTrailers.getTrailer(digest);
            if (trailer == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
            intValue = trailer.intValue();
        }
        this.c = intValue;
    }

    public final void a(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    public final boolean b(byte[] bArr, byte[] bArr2) {
        boolean z;
        int i = this.g;
        byte[] bArr3 = this.f;
        if (i > bArr3.length) {
            z = bArr3.length <= bArr2.length;
            for (int i2 = 0; i2 != this.f.length; i2++) {
                if (bArr[i2] != bArr2[i2]) {
                    z = false;
                }
            }
        } else {
            z = i == bArr2.length;
            for (int i3 = 0; i3 != bArr2.length; i3++) {
                if (bArr[i3] != bArr2[i3]) {
                    z = false;
                }
            }
        }
        return z;
    }

    public final boolean c(byte[] bArr) {
        this.g = 0;
        a(this.f);
        a(bArr);
        return false;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        int i;
        int i2;
        byte b;
        int i3;
        int digestSize = this.f14839a.getDigestSize();
        if (this.c == 188) {
            byte[] bArr = this.e;
            i2 = (bArr.length - digestSize) - 1;
            this.f14839a.doFinal(bArr, i2);
            byte[] bArr2 = this.e;
            bArr2[bArr2.length - 1] = PSSSigner.TRAILER_IMPLICIT;
            i = 8;
        } else {
            i = 16;
            byte[] bArr3 = this.e;
            int length = (bArr3.length - digestSize) - 2;
            this.f14839a.doFinal(bArr3, length);
            byte[] bArr4 = this.e;
            int i4 = this.c;
            bArr4[bArr4.length - 2] = (byte) (i4 >>> 8);
            bArr4[bArr4.length - 1] = (byte) i4;
            i2 = length;
        }
        int i5 = this.g;
        int i6 = ((((digestSize + i5) * 8) + i) + 4) - this.d;
        if (i6 > 0) {
            int i7 = i5 - ((i6 + 7) / 8);
            b = 96;
            i3 = i2 - i7;
            System.arraycopy(this.f, 0, this.e, i3, i7);
            this.i = new byte[i7];
        } else {
            b = 64;
            i3 = i2 - i5;
            System.arraycopy(this.f, 0, this.e, i3, i5);
            this.i = new byte[this.g];
        }
        int i8 = i3 - 1;
        if (i8 > 0) {
            for (int i9 = i8; i9 != 0; i9--) {
                this.e[i9] = -69;
            }
            byte[] bArr5 = this.e;
            bArr5[i8] = (byte) (bArr5[i8] ^ 1);
            bArr5[0] = 11;
            bArr5[0] = (byte) (bArr5[0] | b);
        } else {
            byte[] bArr6 = this.e;
            bArr6[0] = 10;
            bArr6[0] = (byte) (bArr6[0] | b);
        }
        AsymmetricBlockCipher asymmetricBlockCipher = this.b;
        byte[] bArr7 = this.e;
        byte[] processBlock = asymmetricBlockCipher.processBlock(bArr7, 0, bArr7.length);
        this.h = (b & 32) == 0;
        byte[] bArr8 = this.f;
        byte[] bArr9 = this.i;
        System.arraycopy(bArr8, 0, bArr9, 0, bArr9.length);
        this.g = 0;
        a(this.f);
        a(this.e);
        return processBlock;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public byte[] getRecoveredMessage() {
        return this.i;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public boolean hasFullMessage() {
        return this.h;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        this.b.init(z, rSAKeyParameters);
        int bitLength = rSAKeyParameters.getModulus().bitLength();
        this.d = bitLength;
        byte[] bArr = new byte[(bitLength + 7) / 8];
        this.e = bArr;
        int i = this.c;
        int length = bArr.length;
        if (i == 188) {
            this.f = new byte[(length - this.f14839a.getDigestSize()) - 2];
        } else {
            this.f = new byte[(length - this.f14839a.getDigestSize()) - 3];
        }
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.f14839a.reset();
        this.g = 0;
        a(this.f);
        byte[] bArr = this.i;
        if (bArr != null) {
            a(bArr);
        }
        this.i = null;
        this.h = false;
        if (this.j != null) {
            this.j = null;
            a(this.k);
            this.k = null;
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.f14839a.update(b);
        int i = this.g;
        byte[] bArr = this.f;
        if (i < bArr.length) {
            bArr[i] = b;
        }
        this.g = i + 1;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0 && this.g < this.f.length) {
            update(bArr[i]);
            i++;
            i2--;
        }
        this.f14839a.update(bArr, i, i2);
        this.g += i2;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public void updateWithRecoveredMessage(byte[] bArr) throws InvalidCipherTextException {
        byte[] processBlock = this.b.processBlock(bArr, 0, bArr.length);
        if (((processBlock[0] & 192) ^ 64) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        if (((processBlock[processBlock.length - 1] & 15) ^ 12) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        int i = 2;
        if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
            i = 1;
        } else {
            int i2 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
            Integer trailer = ISOTrailers.getTrailer(this.f14839a);
            if (trailer == null) {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            int intValue = trailer.intValue();
            if (i2 != intValue && (intValue != 15052 || i2 != 16588)) {
                throw new IllegalStateException("signer initialised with wrong digest for trailer " + i2);
            }
        }
        int i3 = 0;
        while (i3 != processBlock.length && ((processBlock[i3] & 15) ^ 10) != 0) {
            i3++;
        }
        int i4 = i3 + 1;
        int length = ((processBlock.length - i) - this.f14839a.getDigestSize()) - i4;
        if (length <= 0) {
            throw new InvalidCipherTextException("malformed block");
        }
        if ((processBlock[0] & 32) == 0) {
            this.h = true;
            byte[] bArr2 = new byte[length];
            this.i = bArr2;
            System.arraycopy(processBlock, i4, bArr2, 0, bArr2.length);
        } else {
            this.h = false;
            byte[] bArr3 = new byte[length];
            this.i = bArr3;
            System.arraycopy(processBlock, i4, bArr3, 0, bArr3.length);
        }
        this.j = bArr;
        this.k = processBlock;
        Digest digest = this.f14839a;
        byte[] bArr4 = this.i;
        digest.update(bArr4, 0, bArr4.length);
        byte[] bArr5 = this.i;
        this.g = bArr5.length;
        System.arraycopy(bArr5, 0, this.f, 0, bArr5.length);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] processBlock;
        byte[] bArr2 = this.j;
        if (bArr2 == null) {
            try {
                processBlock = this.b.processBlock(bArr, 0, bArr.length);
            } catch (Exception unused) {
                return false;
            }
        } else if (!Arrays.areEqual(bArr2, bArr)) {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        } else {
            processBlock = this.k;
            this.j = null;
            this.k = null;
        }
        if (((processBlock[0] & 192) ^ 64) == 0 && ((processBlock[processBlock.length - 1] & 15) ^ 12) == 0) {
            int i = 2;
            if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
                i = 1;
            } else {
                int i2 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
                Integer trailer = ISOTrailers.getTrailer(this.f14839a);
                if (trailer == null) {
                    throw new IllegalArgumentException("unrecognised hash in signature");
                }
                int intValue = trailer.intValue();
                if (i2 != intValue && (intValue != 15052 || i2 != 16588)) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + i2);
                }
            }
            int i3 = 0;
            while (i3 != processBlock.length && ((processBlock[i3] & 15) ^ 10) != 0) {
                i3++;
            }
            int i4 = i3 + 1;
            int digestSize = this.f14839a.getDigestSize();
            byte[] bArr3 = new byte[digestSize];
            int length = (processBlock.length - i) - digestSize;
            int i5 = length - i4;
            if (i5 <= 0) {
                return c(processBlock);
            }
            if ((processBlock[0] & 32) == 0) {
                this.h = true;
                if (this.g > i5) {
                    return c(processBlock);
                }
                this.f14839a.reset();
                this.f14839a.update(processBlock, i4, i5);
                this.f14839a.doFinal(bArr3, 0);
                boolean z = true;
                for (int i6 = 0; i6 != digestSize; i6++) {
                    int i7 = length + i6;
                    processBlock[i7] = (byte) (processBlock[i7] ^ bArr3[i6]);
                    if (processBlock[i7] != 0) {
                        z = false;
                    }
                }
                if (!z) {
                    return c(processBlock);
                }
                byte[] bArr4 = new byte[i5];
                this.i = bArr4;
                System.arraycopy(processBlock, i4, bArr4, 0, bArr4.length);
            } else {
                this.h = false;
                this.f14839a.doFinal(bArr3, 0);
                boolean z2 = true;
                for (int i8 = 0; i8 != digestSize; i8++) {
                    int i9 = length + i8;
                    processBlock[i9] = (byte) (processBlock[i9] ^ bArr3[i8]);
                    if (processBlock[i9] != 0) {
                        z2 = false;
                    }
                }
                if (!z2) {
                    return c(processBlock);
                }
                byte[] bArr5 = new byte[i5];
                this.i = bArr5;
                System.arraycopy(processBlock, i4, bArr5, 0, bArr5.length);
            }
            if (this.g == 0 || b(this.f, this.i)) {
                a(this.f);
                a(processBlock);
                this.g = 0;
                return true;
            }
            return c(processBlock);
        }
        return c(processBlock);
    }
}
