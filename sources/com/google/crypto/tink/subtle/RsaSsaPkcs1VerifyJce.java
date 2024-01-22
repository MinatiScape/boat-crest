package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
/* loaded from: classes10.dex */
public final class RsaSsaPkcs1VerifyJce implements PublicKeyVerify {

    /* renamed from: a  reason: collision with root package name */
    public final RSAPublicKey f11043a;
    public final Enums.HashType b;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11044a;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            f11044a = iArr;
            try {
                iArr[Enums.HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11044a[Enums.HashType.SHA512.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public RsaSsaPkcs1VerifyJce(RSAPublicKey rSAPublicKey, Enums.HashType hashType) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPublicKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPublicKey.getPublicExponent());
        this.f11043a = rSAPublicKey;
        this.b = hashType;
    }

    public final byte[] a(byte[] bArr, int i, Enums.HashType hashType) throws GeneralSecurityException {
        int length;
        Validators.validateSignatureHash(hashType);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.b));
        engineFactory.update(bArr);
        byte[] digest = engineFactory.digest();
        byte[] b = b(hashType);
        if (i >= b.length + digest.length + 11) {
            byte[] bArr2 = new byte[i];
            bArr2[0] = 0;
            int i2 = 2;
            bArr2[1] = 1;
            int i3 = 0;
            while (i3 < (i - length) - 3) {
                bArr2[i2] = -1;
                i3++;
                i2++;
            }
            int i4 = i2 + 1;
            bArr2[i2] = 0;
            System.arraycopy(b, 0, bArr2, i4, b.length);
            System.arraycopy(digest, 0, bArr2, i4 + b.length, digest.length);
            return bArr2;
        }
        throw new GeneralSecurityException("intended encoded message length too short");
    }

    public final byte[] b(Enums.HashType hashType) throws GeneralSecurityException {
        int i = a.f11044a[hashType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return Hex.decode("3051300d060960864801650304020305000440");
            }
            throw new GeneralSecurityException("Unsupported hash " + hashType);
        }
        return Hex.decode("3031300d060960864801650304020105000420");
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        BigInteger publicExponent = this.f11043a.getPublicExponent();
        BigInteger modulus = this.f11043a.getModulus();
        int bitLength = (modulus.bitLength() + 7) / 8;
        if (bitLength == bArr.length) {
            BigInteger bytes2Integer = SubtleUtil.bytes2Integer(bArr);
            if (bytes2Integer.compareTo(modulus) < 0) {
                if (!Bytes.equal(SubtleUtil.integer2Bytes(bytes2Integer.modPow(publicExponent, modulus), bitLength), a(bArr2, bitLength, this.b))) {
                    throw new GeneralSecurityException("invalid signature");
                }
                return;
            }
            throw new GeneralSecurityException("signature out of range");
        }
        throw new GeneralSecurityException("invalid signature's length");
    }
}
