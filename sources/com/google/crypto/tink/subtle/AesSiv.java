package com.google.crypto.tink.subtle;

import com.google.crypto.tink.DeterministicAead;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes10.dex */
public final class AesSiv implements DeterministicAead {
    public static final Collection<Integer> c = Arrays.asList(64);
    public static final byte[] d = new byte[16];
    public static final byte[] e = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

    /* renamed from: a  reason: collision with root package name */
    public final PrfAesCmac f11019a;
    public final byte[] b;

    public AesSiv(byte[] bArr) throws GeneralSecurityException {
        if (c.contains(Integer.valueOf(bArr.length))) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
            this.b = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
            this.f11019a = new PrfAesCmac(copyOfRange);
            return;
        }
        throw new InvalidKeyException("invalid key size: " + bArr.length + " bytes; key must have 64 bytes");
    }

    public final byte[] a(byte[]... bArr) throws GeneralSecurityException {
        byte[] xor;
        if (bArr.length == 0) {
            return this.f11019a.compute(e, 16);
        }
        byte[] compute = this.f11019a.compute(d, 16);
        for (int i = 0; i < bArr.length - 1; i++) {
            compute = Bytes.xor(a.b(compute), this.f11019a.compute(bArr[i] == null ? new byte[0] : bArr[i], 16));
        }
        byte[] bArr2 = bArr[bArr.length - 1];
        if (bArr2.length >= 16) {
            xor = Bytes.xorEnd(bArr2, compute);
        } else {
            xor = Bytes.xor(a.a(bArr2), a.b(compute));
        }
        return this.f11019a.compute(xor, 16);
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length >= 16) {
            Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
            byte[] bArr3 = (byte[]) copyOfRange.clone();
            bArr3[8] = (byte) (bArr3[8] & Byte.MAX_VALUE);
            bArr3[12] = (byte) (bArr3[12] & Byte.MAX_VALUE);
            engineFactory.init(2, new SecretKeySpec(this.b, AesKey.ALGORITHM), new IvParameterSpec(bArr3));
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 16, bArr.length);
            byte[] doFinal = engineFactory.doFinal(copyOfRange2);
            if (copyOfRange2.length == 0 && doFinal == null && SubtleUtil.isAndroid()) {
                doFinal = new byte[0];
            }
            if (Bytes.equal(copyOfRange, a(bArr2, doFinal))) {
                return doFinal;
            }
            throw new AEADBadTagException("Integrity check failed.");
        }
        throw new GeneralSecurityException("Ciphertext too short.");
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= 2147483631) {
            Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
            byte[] a2 = a(bArr2, bArr);
            byte[] bArr3 = (byte[]) a2.clone();
            bArr3[8] = (byte) (bArr3[8] & Byte.MAX_VALUE);
            bArr3[12] = (byte) (bArr3[12] & Byte.MAX_VALUE);
            engineFactory.init(1, new SecretKeySpec(this.b, AesKey.ALGORITHM), new IvParameterSpec(bArr3));
            return Bytes.concat(a2, engineFactory.doFinal(bArr));
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
