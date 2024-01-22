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
/* loaded from: classes13.dex */
public class McEliecePointchevalCipher implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.2";

    /* renamed from: a  reason: collision with root package name */
    public Digest f15304a;
    public SecureRandom b;
    public int c;
    public int d;
    public int e;
    public McElieceCCA2KeyParameters f;
    public boolean g;

    public final void a(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.f15304a = c.a(mcElieceCCA2PrivateKeyParameters.getDigest());
        this.c = mcElieceCCA2PrivateKeyParameters.getN();
        this.d = mcElieceCCA2PrivateKeyParameters.getK();
        this.e = mcElieceCCA2PrivateKeyParameters.getT();
    }

    public final void b(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        SecureRandom secureRandom = this.b;
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        this.b = secureRandom;
        this.f15304a = c.a(mcElieceCCA2PublicKeyParameters.getDigest());
        this.c = mcElieceCCA2PublicKeyParameters.getN();
        this.d = mcElieceCCA2PublicKeyParameters.getK();
        this.e = mcElieceCCA2PublicKeyParameters.getT();
    }

    public int decryptOutputSize(int i) {
        return 0;
    }

    public int encryptOutputSize(int i) {
        return 0;
    }

    public int getKeySize(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) throws IllegalArgumentException {
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
            this.f = mcElieceCCA2PrivateKeyParameters;
            a(mcElieceCCA2PrivateKeyParameters);
        } else if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.b = new SecureRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters = (McElieceCCA2PublicKeyParameters) cipherParameters;
            this.f = mcElieceCCA2PublicKeyParameters;
            b(mcElieceCCA2PublicKeyParameters);
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.b = parametersWithRandom.getRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters2 = (McElieceCCA2PublicKeyParameters) parametersWithRandom.getParameters();
            this.f = mcElieceCCA2PublicKeyParameters2;
            b(mcElieceCCA2PublicKeyParameters2);
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public byte[] messageDecrypt(byte[] bArr) throws InvalidCipherTextException {
        if (this.g) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int i = (this.c + 7) >> 3;
        int length = bArr.length - i;
        byte[][] split = ByteUtils.split(bArr, i);
        byte[] bArr2 = split[0];
        byte[] bArr3 = split[1];
        GF2Vector[] a2 = b.a((McElieceCCA2PrivateKeyParameters) this.f, GF2Vector.OS2VP(this.c, bArr2));
        byte[] encoded = a2[0].getEncoded();
        GF2Vector gF2Vector = a2[1];
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(encoded);
        byte[] bArr4 = new byte[length];
        digestRandomGenerator.nextBytes(bArr4);
        for (int i2 = 0; i2 < length; i2++) {
            bArr4[i2] = (byte) (bArr4[i2] ^ bArr3[i2]);
        }
        this.f15304a.update(bArr4, 0, length);
        byte[] bArr5 = new byte[this.f15304a.getDigestSize()];
        this.f15304a.doFinal(bArr5, 0);
        if (a.b(this.c, this.e, bArr5).equals(gF2Vector)) {
            return ByteUtils.split(bArr4, length - (this.d >> 3))[0];
        }
        throw new InvalidCipherTextException("Bad Padding: Invalid ciphertext.");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public byte[] messageEncrypt(byte[] bArr) {
        if (this.g) {
            int i = this.d >> 3;
            byte[] bArr2 = new byte[i];
            this.b.nextBytes(bArr2);
            GF2Vector gF2Vector = new GF2Vector(this.d, this.b);
            byte[] encoded = gF2Vector.getEncoded();
            byte[] concatenate = ByteUtils.concatenate(bArr, bArr2);
            this.f15304a.update(concatenate, 0, concatenate.length);
            byte[] bArr3 = new byte[this.f15304a.getDigestSize()];
            this.f15304a.doFinal(bArr3, 0);
            byte[] encoded2 = b.b((McElieceCCA2PublicKeyParameters) this.f, gF2Vector, a.b(this.c, this.e, bArr3)).getEncoded();
            DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
            digestRandomGenerator.addSeedMaterial(encoded);
            byte[] bArr4 = new byte[bArr.length + i];
            digestRandomGenerator.nextBytes(bArr4);
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr4[i2] = (byte) (bArr4[i2] ^ bArr[i2]);
            }
            for (int i3 = 0; i3 < i; i3++) {
                int length = bArr.length + i3;
                bArr4[length] = (byte) (bArr4[length] ^ bArr2[i3]);
            }
            return ByteUtils.concatenate(encoded2, bArr4);
        }
        throw new IllegalStateException("cipher initialised for decryption");
    }
}
