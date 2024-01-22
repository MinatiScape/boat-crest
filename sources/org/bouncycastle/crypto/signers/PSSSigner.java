package org.bouncycastle.crypto.signers;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
/* loaded from: classes13.dex */
public class PSSSigner implements Signer {
    public static final byte TRAILER_IMPLICIT = -68;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14841a;
    public Digest b;
    public AsymmetricBlockCipher c;
    public SecureRandom d;
    public int e;
    public int f;
    public boolean g;
    public int h;
    public int i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public byte m;

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i) {
        this(asymmetricBlockCipher, digest, i, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i, byte b) {
        this(asymmetricBlockCipher, digest, digest, i, b);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i) {
        this(asymmetricBlockCipher, digest, digest2, i, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i, byte b) {
        this.c = asymmetricBlockCipher;
        this.f14841a = digest;
        this.b = digest2;
        this.e = digest.getDigestSize();
        this.f = digest2.getDigestSize();
        this.g = false;
        this.h = i;
        this.j = new byte[i];
        this.k = new byte[i + 8 + this.e];
        this.m = b;
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest2, bArr, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr, byte b) {
        this.c = asymmetricBlockCipher;
        this.f14841a = digest;
        this.b = digest2;
        this.e = digest.getDigestSize();
        this.f = digest2.getDigestSize();
        this.g = true;
        int length = bArr.length;
        this.h = length;
        this.j = bArr;
        this.k = new byte[length + 8 + this.e];
        this.m = b;
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest, bArr, (byte) TRAILER_IMPLICIT);
    }

    public final void a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    public final void b(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    public final byte[] c(byte[] bArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[this.f];
        byte[] bArr4 = new byte[4];
        this.b.reset();
        int i5 = 0;
        while (true) {
            i4 = this.f;
            if (i5 >= i3 / i4) {
                break;
            }
            a(i5, bArr4);
            this.b.update(bArr, i, i2);
            this.b.update(bArr4, 0, 4);
            this.b.doFinal(bArr3, 0);
            int i6 = this.f;
            System.arraycopy(bArr3, 0, bArr2, i5 * i6, i6);
            i5++;
        }
        if (i4 * i5 < i3) {
            a(i5, bArr4);
            this.b.update(bArr, i, i2);
            this.b.update(bArr4, 0, 4);
            this.b.doFinal(bArr3, 0);
            int i7 = this.f;
            System.arraycopy(bArr3, 0, bArr2, i5 * i7, i3 - (i5 * i7));
        }
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException, DataLengthException {
        Digest digest = this.f14841a;
        byte[] bArr = this.k;
        digest.doFinal(bArr, (bArr.length - this.e) - this.h);
        if (this.h != 0) {
            if (!this.g) {
                this.d.nextBytes(this.j);
            }
            byte[] bArr2 = this.j;
            byte[] bArr3 = this.k;
            int length = bArr3.length;
            int i = this.h;
            System.arraycopy(bArr2, 0, bArr3, length - i, i);
        }
        int i2 = this.e;
        byte[] bArr4 = new byte[i2];
        Digest digest2 = this.f14841a;
        byte[] bArr5 = this.k;
        digest2.update(bArr5, 0, bArr5.length);
        this.f14841a.doFinal(bArr4, 0);
        byte[] bArr6 = this.l;
        int length2 = bArr6.length;
        int i3 = this.h;
        int i4 = this.e;
        bArr6[(((length2 - i3) - 1) - i4) - 1] = 1;
        System.arraycopy(this.j, 0, bArr6, ((bArr6.length - i3) - i4) - 1, i3);
        byte[] c = c(bArr4, 0, i2, (this.l.length - this.e) - 1);
        for (int i5 = 0; i5 != c.length; i5++) {
            byte[] bArr7 = this.l;
            bArr7[i5] = (byte) (bArr7[i5] ^ c[i5]);
        }
        byte[] bArr8 = this.l;
        bArr8[0] = (byte) (bArr8[0] & (255 >> ((bArr8.length * 8) - this.i)));
        int length3 = bArr8.length;
        int i6 = this.e;
        System.arraycopy(bArr4, 0, bArr8, (length3 - i6) - 1, i6);
        byte[] bArr9 = this.l;
        bArr9[bArr9.length - 1] = this.m;
        byte[] processBlock = this.c.processBlock(bArr9, 0, bArr9.length);
        b(this.l);
        return processBlock;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        RSAKeyParameters rSAKeyParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            cipherParameters2 = parametersWithRandom.getParameters();
            this.d = parametersWithRandom.getRandom();
        } else {
            if (z) {
                this.d = new SecureRandom();
            }
            cipherParameters2 = cipherParameters;
        }
        if (cipherParameters2 instanceof RSABlindingParameters) {
            rSAKeyParameters = ((RSABlindingParameters) cipherParameters2).getPublicKey();
            this.c.init(z, cipherParameters);
        } else {
            rSAKeyParameters = (RSAKeyParameters) cipherParameters2;
            this.c.init(z, cipherParameters2);
        }
        int bitLength = rSAKeyParameters.getModulus().bitLength() - 1;
        this.i = bitLength;
        if (bitLength < (this.e * 8) + (this.h * 8) + 9) {
            throw new IllegalArgumentException("key too small for specified hash and salt lengths");
        }
        this.l = new byte[(bitLength + 7) / 8];
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.f14841a.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.f14841a.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.f14841a.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        Digest digest = this.f14841a;
        byte[] bArr2 = this.k;
        digest.doFinal(bArr2, (bArr2.length - this.e) - this.h);
        try {
            byte[] processBlock = this.c.processBlock(bArr, 0, bArr.length);
            byte[] bArr3 = this.l;
            System.arraycopy(processBlock, 0, bArr3, bArr3.length - processBlock.length, processBlock.length);
            byte[] bArr4 = this.l;
            if (bArr4[bArr4.length - 1] != this.m) {
                b(bArr4);
                return false;
            }
            int length = bArr4.length;
            int i = this.e;
            byte[] c = c(bArr4, (length - i) - 1, i, (bArr4.length - i) - 1);
            for (int i2 = 0; i2 != c.length; i2++) {
                byte[] bArr5 = this.l;
                bArr5[i2] = (byte) (bArr5[i2] ^ c[i2]);
            }
            byte[] bArr6 = this.l;
            bArr6[0] = (byte) (bArr6[0] & (255 >> ((bArr6.length * 8) - this.i)));
            int i3 = 0;
            while (true) {
                byte[] bArr7 = this.l;
                int length2 = bArr7.length;
                int i4 = this.e;
                int i5 = this.h;
                if (i3 != ((length2 - i4) - i5) - 2) {
                    if (bArr7[i3] != 0) {
                        b(bArr7);
                        return false;
                    }
                    i3++;
                } else if (bArr7[((bArr7.length - i4) - i5) - 2] != 1) {
                    b(bArr7);
                    return false;
                } else {
                    if (this.g) {
                        byte[] bArr8 = this.j;
                        byte[] bArr9 = this.k;
                        System.arraycopy(bArr8, 0, bArr9, bArr9.length - i5, i5);
                    } else {
                        byte[] bArr10 = this.k;
                        System.arraycopy(bArr7, ((bArr7.length - i5) - i4) - 1, bArr10, bArr10.length - i5, i5);
                    }
                    Digest digest2 = this.f14841a;
                    byte[] bArr11 = this.k;
                    digest2.update(bArr11, 0, bArr11.length);
                    Digest digest3 = this.f14841a;
                    byte[] bArr12 = this.k;
                    digest3.doFinal(bArr12, bArr12.length - this.e);
                    int length3 = this.l.length;
                    int i6 = this.e;
                    int i7 = (length3 - i6) - 1;
                    int length4 = this.k.length - i6;
                    while (true) {
                        byte[] bArr13 = this.k;
                        if (length4 == bArr13.length) {
                            b(bArr13);
                            b(this.l);
                            return true;
                        } else if ((this.l[i7] ^ bArr13[length4]) != 0) {
                            b(bArr13);
                            b(this.l);
                            return false;
                        } else {
                            i7++;
                            length4++;
                        }
                    }
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }
}
