package org.bouncycastle.crypto.engines;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class RC2WrapEngine implements Wrapper {
    public static final byte[] i = {com.htsmart.wristband2.a.a.a.Y0, -35, -94, 44, com.htsmart.wristband2.a.a.a.U1, -24, 33, 5};

    /* renamed from: a  reason: collision with root package name */
    public CBCBlockCipher f14692a;
    public CipherParameters b;
    public ParametersWithIV c;
    public byte[] d;
    public boolean e;
    public SecureRandom f;
    public Digest g = DigestFactory.createSHA1();
    public byte[] h = new byte[20];

    public final byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        this.g.update(bArr, 0, bArr.length);
        this.g.doFinal(this.h, 0);
        System.arraycopy(this.h, 0, bArr2, 0, 8);
        return bArr2;
    }

    public final boolean b(byte[] bArr, byte[] bArr2) {
        return Arrays.constantTimeAreEqual(a(bArr), bArr2);
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.e = z;
        this.f14692a = new CBCBlockCipher(new RC2Engine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            this.f = new SecureRandom();
        }
        if (!(cipherParameters instanceof ParametersWithIV)) {
            this.b = cipherParameters;
            if (this.e) {
                byte[] bArr = new byte[8];
                this.d = bArr;
                this.f.nextBytes(bArr);
                this.c = new ParametersWithIV(this.b, this.d);
                return;
            }
            return;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        this.c = parametersWithIV;
        this.d = parametersWithIV.getIV();
        this.b = this.c.getParameters();
        if (!this.e) {
            throw new IllegalArgumentException("You should not supply an IV for unwrapping");
        }
        byte[] bArr2 = this.d;
        if (bArr2 == null || bArr2.length != 8) {
            throw new IllegalArgumentException("IV is not 8 octets");
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.e) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (bArr != null) {
            if (i3 % this.f14692a.getBlockSize() != 0) {
                throw new InvalidCipherTextException("Ciphertext not multiple of " + this.f14692a.getBlockSize());
            }
            this.f14692a.init(false, new ParametersWithIV(this.b, i));
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i2, bArr2, 0, i3);
            for (int i4 = 0; i4 < i3 / this.f14692a.getBlockSize(); i4++) {
                int blockSize = this.f14692a.getBlockSize() * i4;
                this.f14692a.processBlock(bArr2, blockSize, bArr2, blockSize);
            }
            byte[] bArr3 = new byte[i3];
            int i5 = 0;
            while (i5 < i3) {
                int i6 = i5 + 1;
                bArr3[i5] = bArr2[i3 - i6];
                i5 = i6;
            }
            byte[] bArr4 = new byte[8];
            this.d = bArr4;
            int i7 = i3 - 8;
            byte[] bArr5 = new byte[i7];
            System.arraycopy(bArr3, 0, bArr4, 0, 8);
            System.arraycopy(bArr3, 8, bArr5, 0, i7);
            ParametersWithIV parametersWithIV = new ParametersWithIV(this.b, this.d);
            this.c = parametersWithIV;
            this.f14692a.init(false, parametersWithIV);
            byte[] bArr6 = new byte[i7];
            System.arraycopy(bArr5, 0, bArr6, 0, i7);
            for (int i8 = 0; i8 < i7 / this.f14692a.getBlockSize(); i8++) {
                int blockSize2 = this.f14692a.getBlockSize() * i8;
                this.f14692a.processBlock(bArr6, blockSize2, bArr6, blockSize2);
            }
            int i9 = i7 - 8;
            byte[] bArr7 = new byte[i9];
            byte[] bArr8 = new byte[8];
            System.arraycopy(bArr6, 0, bArr7, 0, i9);
            System.arraycopy(bArr6, i9, bArr8, 0, 8);
            if (b(bArr7, bArr8)) {
                if (i9 - ((bArr7[0] & 255) + 1) <= 7) {
                    int i10 = bArr7[0];
                    byte[] bArr9 = new byte[i10];
                    System.arraycopy(bArr7, 1, bArr9, 0, i10);
                    return bArr9;
                }
                throw new InvalidCipherTextException("too many pad bytes (" + (i9 - ((bArr7[0] & 255) + 1)) + ")");
            }
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
        }
        throw new InvalidCipherTextException("Null pointer as ciphertext");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i2, int i3) {
        if (this.e) {
            int i4 = i3 + 1;
            int i5 = i4 % 8;
            int i6 = i5 != 0 ? (8 - i5) + i4 : i4;
            byte[] bArr2 = new byte[i6];
            bArr2[0] = (byte) i3;
            System.arraycopy(bArr, i2, bArr2, 1, i3);
            int i7 = (i6 - i3) - 1;
            byte[] bArr3 = new byte[i7];
            if (i7 > 0) {
                this.f.nextBytes(bArr3);
                System.arraycopy(bArr3, 0, bArr2, i4, i7);
            }
            byte[] a2 = a(bArr2);
            int length = a2.length + i6;
            byte[] bArr4 = new byte[length];
            System.arraycopy(bArr2, 0, bArr4, 0, i6);
            System.arraycopy(a2, 0, bArr4, i6, a2.length);
            byte[] bArr5 = new byte[length];
            System.arraycopy(bArr4, 0, bArr5, 0, length);
            int blockSize = length / this.f14692a.getBlockSize();
            if (length % this.f14692a.getBlockSize() == 0) {
                this.f14692a.init(true, this.c);
                for (int i8 = 0; i8 < blockSize; i8++) {
                    int blockSize2 = this.f14692a.getBlockSize() * i8;
                    this.f14692a.processBlock(bArr5, blockSize2, bArr5, blockSize2);
                }
                byte[] bArr6 = this.d;
                int length2 = bArr6.length + length;
                byte[] bArr7 = new byte[length2];
                System.arraycopy(bArr6, 0, bArr7, 0, bArr6.length);
                System.arraycopy(bArr5, 0, bArr7, this.d.length, length);
                byte[] bArr8 = new byte[length2];
                int i9 = 0;
                while (i9 < length2) {
                    int i10 = i9 + 1;
                    bArr8[i9] = bArr7[length2 - i10];
                    i9 = i10;
                }
                this.f14692a.init(true, new ParametersWithIV(this.b, i));
                for (int i11 = 0; i11 < blockSize + 1; i11++) {
                    int blockSize3 = this.f14692a.getBlockSize() * i11;
                    this.f14692a.processBlock(bArr8, blockSize3, bArr8, blockSize3);
                }
                return bArr8;
            }
            throw new IllegalStateException("Not multiple of block length");
        }
        throw new IllegalStateException("Not initialized for wrapping");
    }
}
