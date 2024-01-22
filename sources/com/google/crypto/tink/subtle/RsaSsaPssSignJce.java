package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.subtle.EngineWrapper;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import org.bouncycastle.crypto.signers.PSSSigner;
/* loaded from: classes10.dex */
public final class RsaSsaPssSignJce implements PublicKeySign {

    /* renamed from: a  reason: collision with root package name */
    public final RSAPrivateCrtKey f11045a;
    public final RSAPublicKey b;
    public final Enums.HashType c;
    public final Enums.HashType d;
    public final int e;

    public RsaSsaPssSignJce(RSAPrivateCrtKey rSAPrivateCrtKey, Enums.HashType hashType, Enums.HashType hashType2, int i) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPrivateCrtKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPrivateCrtKey.getPublicExponent());
        this.f11045a = rSAPrivateCrtKey;
        this.b = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent()));
        this.c = hashType;
        this.d = hashType2;
        this.e = i;
    }

    public final byte[] a(byte[] bArr, int i) throws GeneralSecurityException {
        Validators.validateSignatureHash(this.c);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.c));
        byte[] digest = engineFactory.digest(bArr);
        int digestLength = engineFactory.getDigestLength();
        int i2 = ((i - 1) / 8) + 1;
        int i3 = this.e;
        if (i2 >= digestLength + i3 + 2) {
            byte[] randBytes = Random.randBytes(i3);
            int i4 = digestLength + 8;
            byte[] bArr2 = new byte[this.e + i4];
            System.arraycopy(digest, 0, bArr2, 8, digestLength);
            System.arraycopy(randBytes, 0, bArr2, i4, randBytes.length);
            byte[] digest2 = engineFactory.digest(bArr2);
            int i5 = (i2 - digestLength) - 1;
            byte[] bArr3 = new byte[i5];
            int i6 = this.e;
            bArr3[((i2 - i6) - digestLength) - 2] = 1;
            System.arraycopy(randBytes, 0, bArr3, ((i2 - i6) - digestLength) - 1, randBytes.length);
            byte[] mgf1 = SubtleUtil.mgf1(digest2, i5, this.d);
            byte[] bArr4 = new byte[i5];
            for (int i7 = 0; i7 < i5; i7++) {
                bArr4[i7] = (byte) (bArr3[i7] ^ mgf1[i7]);
            }
            for (int i8 = 0; i8 < (i2 * 8) - i; i8++) {
                int i9 = i8 / 8;
                bArr4[i9] = (byte) ((~(1 << (7 - (i8 % 8)))) & bArr4[i9]);
            }
            int i10 = digestLength + i5;
            byte[] bArr5 = new byte[i10 + 1];
            System.arraycopy(bArr4, 0, bArr5, 0, i5);
            System.arraycopy(digest2, 0, bArr5, i5, digest2.length);
            bArr5[i10] = PSSSigner.TRAILER_IMPLICIT;
            return bArr5;
        }
        throw new GeneralSecurityException("encoding error");
    }

    public final byte[] b(byte[] bArr) throws GeneralSecurityException {
        EngineFactory<EngineWrapper.TCipher, Cipher> engineFactory = EngineFactory.CIPHER;
        Cipher engineFactory2 = engineFactory.getInstance("RSA/ECB/NOPADDING");
        engineFactory2.init(2, this.f11045a);
        byte[] doFinal = engineFactory2.doFinal(bArr);
        Cipher engineFactory3 = engineFactory.getInstance("RSA/ECB/NOPADDING");
        engineFactory3.init(1, this.b);
        if (new BigInteger(1, bArr).equals(new BigInteger(1, engineFactory3.doFinal(doFinal)))) {
            return doFinal;
        }
        throw new RuntimeException("Security bug: RSA signature computation error");
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        return b(a(bArr, this.b.getModulus().bitLength() - 1));
    }
}
