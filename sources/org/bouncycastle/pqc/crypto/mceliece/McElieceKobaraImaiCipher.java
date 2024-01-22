package org.bouncycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.pqc.crypto.MessageEncryptor;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;
/* loaded from: classes13.dex */
public class McElieceKobaraImaiCipher implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.3";
    public static final byte[] PUBLIC_CONSTANT = "a predetermined public constant".getBytes();

    /* renamed from: a  reason: collision with root package name */
    public Digest f15303a;
    public SecureRandom b;
    public McElieceCCA2KeyParameters c;
    public int d;
    public int e;
    public int f;
    public boolean g;

    public final void a(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.f15303a = c.a(mcElieceCCA2PrivateKeyParameters.getDigest());
        this.d = mcElieceCCA2PrivateKeyParameters.getN();
        this.e = mcElieceCCA2PrivateKeyParameters.getK();
        this.f = mcElieceCCA2PrivateKeyParameters.getT();
    }

    public final void b(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        this.f15303a = c.a(mcElieceCCA2PublicKeyParameters.getDigest());
        this.d = mcElieceCCA2PublicKeyParameters.getN();
        this.e = mcElieceCCA2PublicKeyParameters.getK();
        this.f = mcElieceCCA2PublicKeyParameters.getT();
    }

    public int getKeySize(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) {
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public void init(boolean z, CipherParameters cipherParameters) {
        this.g = z;
        if (!z) {
            McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters = (McElieceCCA2PrivateKeyParameters) cipherParameters;
            this.c = mcElieceCCA2PrivateKeyParameters;
            a(mcElieceCCA2PrivateKeyParameters);
        } else if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.b = new SecureRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters = (McElieceCCA2PublicKeyParameters) cipherParameters;
            this.c = mcElieceCCA2PublicKeyParameters;
            b(mcElieceCCA2PublicKeyParameters);
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.b = parametersWithRandom.getRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters2 = (McElieceCCA2PublicKeyParameters) parametersWithRandom.getParameters();
            this.c = mcElieceCCA2PublicKeyParameters2;
            b(mcElieceCCA2PublicKeyParameters2);
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public byte[] messageDecrypt(byte[] bArr) throws InvalidCipherTextException {
        byte[] bArr2;
        if (this.g) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int i = this.d >> 3;
        if (bArr.length >= i) {
            int digestSize = this.f15303a.getDigestSize();
            int i2 = this.e >> 3;
            int length = bArr.length - i;
            if (length > 0) {
                byte[][] split = ByteUtils.split(bArr, length);
                bArr2 = split[0];
                bArr = split[1];
            } else {
                bArr2 = new byte[0];
            }
            GF2Vector[] a2 = b.a((McElieceCCA2PrivateKeyParameters) this.c, GF2Vector.OS2VP(this.d, bArr));
            byte[] encoded = a2[0].getEncoded();
            GF2Vector gF2Vector = a2[1];
            if (encoded.length > i2) {
                encoded = ByteUtils.subArray(encoded, 0, i2);
            }
            byte[] concatenate = ByteUtils.concatenate(ByteUtils.concatenate(bArr2, a.a(this.d, this.f, gF2Vector)), encoded);
            int length2 = concatenate.length - digestSize;
            byte[][] split2 = ByteUtils.split(concatenate, digestSize);
            byte[] bArr3 = split2[0];
            byte[] bArr4 = split2[1];
            byte[] bArr5 = new byte[this.f15303a.getDigestSize()];
            this.f15303a.update(bArr4, 0, bArr4.length);
            this.f15303a.doFinal(bArr5, 0);
            for (int i3 = digestSize - 1; i3 >= 0; i3--) {
                bArr5[i3] = (byte) (bArr5[i3] ^ bArr3[i3]);
            }
            DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
            digestRandomGenerator.addSeedMaterial(bArr5);
            byte[] bArr6 = new byte[length2];
            digestRandomGenerator.nextBytes(bArr6);
            for (int i4 = length2 - 1; i4 >= 0; i4--) {
                bArr6[i4] = (byte) (bArr6[i4] ^ bArr4[i4]);
            }
            byte[] bArr7 = PUBLIC_CONSTANT;
            byte[][] split3 = ByteUtils.split(bArr6, length2 - bArr7.length);
            byte[] bArr8 = split3[0];
            if (ByteUtils.equals(split3[1], bArr7)) {
                return bArr8;
            }
            throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
        }
        throw new InvalidCipherTextException("Bad Padding: Ciphertext too short.");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public byte[] messageEncrypt(byte[] bArr) {
        if (this.g) {
            int digestSize = this.f15303a.getDigestSize();
            int i = this.e >> 3;
            int bitLength = (IntegerFunctions.binomial(this.d, this.f).bitLength() - 1) >> 3;
            byte[] bArr2 = PUBLIC_CONSTANT;
            int length = ((i + bitLength) - digestSize) - bArr2.length;
            if (bArr.length > length) {
                length = bArr.length;
            }
            int length2 = bArr2.length + length;
            int i2 = ((length2 + digestSize) - i) - bitLength;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, length, bArr2.length);
            byte[] bArr4 = new byte[digestSize];
            this.b.nextBytes(bArr4);
            DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
            digestRandomGenerator.addSeedMaterial(bArr4);
            byte[] bArr5 = new byte[length2];
            digestRandomGenerator.nextBytes(bArr5);
            for (int i3 = length2 - 1; i3 >= 0; i3--) {
                bArr5[i3] = (byte) (bArr5[i3] ^ bArr3[i3]);
            }
            byte[] bArr6 = new byte[this.f15303a.getDigestSize()];
            this.f15303a.update(bArr5, 0, length2);
            this.f15303a.doFinal(bArr6, 0);
            for (int i4 = digestSize - 1; i4 >= 0; i4--) {
                bArr6[i4] = (byte) (bArr6[i4] ^ bArr4[i4]);
            }
            byte[] concatenate = ByteUtils.concatenate(bArr6, bArr5);
            byte[] bArr7 = new byte[0];
            if (i2 > 0) {
                bArr7 = new byte[i2];
                System.arraycopy(concatenate, 0, bArr7, 0, i2);
            }
            byte[] bArr8 = new byte[bitLength];
            System.arraycopy(concatenate, i2, bArr8, 0, bitLength);
            byte[] bArr9 = new byte[i];
            System.arraycopy(concatenate, bitLength + i2, bArr9, 0, i);
            byte[] encoded = b.b((McElieceCCA2PublicKeyParameters) this.c, GF2Vector.OS2VP(this.e, bArr9), a.b(this.d, this.f, bArr8)).getEncoded();
            return i2 > 0 ? ByteUtils.concatenate(bArr7, encoded) : encoded;
        }
        throw new IllegalStateException("cipher initialised for decryption");
    }
}
