package org.bouncycastle.crypto.engines;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class DESedeWrapEngine implements Wrapper {
    public static final byte[] h = {com.htsmart.wristband2.a.a.a.Y0, -35, -94, 44, com.htsmart.wristband2.a.a.a.U1, -24, 33, 5};

    /* renamed from: a  reason: collision with root package name */
    public CBCBlockCipher f14674a;
    public KeyParameter b;
    public ParametersWithIV c;
    public byte[] d;
    public boolean e;
    public Digest f = DigestFactory.createSHA1();
    public byte[] g = new byte[20];

    public static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            bArr2[i] = bArr[bArr.length - i2];
            i = i2;
        }
        return bArr2;
    }

    public final byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        this.f.update(bArr, 0, bArr.length);
        this.f.doFinal(this.g, 0);
        System.arraycopy(this.g, 0, bArr2, 0, 8);
        return bArr2;
    }

    public final boolean b(byte[] bArr, byte[] bArr2) {
        return Arrays.constantTimeAreEqual(a(bArr), bArr2);
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "DESede";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        this.e = z;
        this.f14674a = new CBCBlockCipher(new DESedeEngine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            CipherParameters parameters = parametersWithRandom.getParameters();
            SecureRandom random = parametersWithRandom.getRandom();
            cipherParameters = parameters;
            secureRandom = random;
        } else {
            secureRandom = new SecureRandom();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.b = (KeyParameter) cipherParameters;
            if (this.e) {
                byte[] bArr = new byte[8];
                this.d = bArr;
                secureRandom.nextBytes(bArr);
                this.c = new ParametersWithIV(this.b, this.d);
            }
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.c = parametersWithIV;
            this.d = parametersWithIV.getIV();
            this.b = (KeyParameter) this.c.getParameters();
            if (!this.e) {
                throw new IllegalArgumentException("You should not supply an IV for unwrapping");
            }
            byte[] bArr2 = this.d;
            if (bArr2 == null || bArr2.length != 8) {
                throw new IllegalArgumentException("IV is not 8 octets");
            }
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.e) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (bArr != null) {
            int blockSize = this.f14674a.getBlockSize();
            if (i2 % blockSize != 0) {
                throw new InvalidCipherTextException("Ciphertext not multiple of " + blockSize);
            }
            this.f14674a.init(false, new ParametersWithIV(this.b, h));
            byte[] bArr2 = new byte[i2];
            for (int i3 = 0; i3 != i2; i3 += blockSize) {
                this.f14674a.processBlock(bArr, i + i3, bArr2, i3);
            }
            byte[] c = c(bArr2);
            byte[] bArr3 = new byte[8];
            this.d = bArr3;
            int length = c.length - 8;
            byte[] bArr4 = new byte[length];
            System.arraycopy(c, 0, bArr3, 0, 8);
            System.arraycopy(c, 8, bArr4, 0, c.length - 8);
            ParametersWithIV parametersWithIV = new ParametersWithIV(this.b, this.d);
            this.c = parametersWithIV;
            this.f14674a.init(false, parametersWithIV);
            byte[] bArr5 = new byte[length];
            for (int i4 = 0; i4 != length; i4 += blockSize) {
                this.f14674a.processBlock(bArr4, i4, bArr5, i4);
            }
            int i5 = length - 8;
            byte[] bArr6 = new byte[i5];
            byte[] bArr7 = new byte[8];
            System.arraycopy(bArr5, 0, bArr6, 0, i5);
            System.arraycopy(bArr5, i5, bArr7, 0, 8);
            if (b(bArr6, bArr7)) {
                return bArr6;
            }
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
        }
        throw new InvalidCipherTextException("Null pointer as ciphertext");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i, int i2) {
        if (this.e) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            byte[] a2 = a(bArr2);
            int length = a2.length + i2;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr2, 0, bArr3, 0, i2);
            System.arraycopy(a2, 0, bArr3, i2, a2.length);
            int blockSize = this.f14674a.getBlockSize();
            if (length % blockSize == 0) {
                this.f14674a.init(true, this.c);
                byte[] bArr4 = new byte[length];
                for (int i3 = 0; i3 != length; i3 += blockSize) {
                    this.f14674a.processBlock(bArr3, i3, bArr4, i3);
                }
                byte[] bArr5 = this.d;
                byte[] bArr6 = new byte[bArr5.length + length];
                System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
                System.arraycopy(bArr4, 0, bArr6, this.d.length, length);
                byte[] c = c(bArr6);
                this.f14674a.init(true, new ParametersWithIV(this.b, h));
                for (int i4 = 0; i4 != c.length; i4 += blockSize) {
                    this.f14674a.processBlock(c, i4, c, i4);
                }
                return c;
            }
            throw new IllegalStateException("Not multiple of block length");
        }
        throw new IllegalStateException("Not initialized for wrapping");
    }
}
