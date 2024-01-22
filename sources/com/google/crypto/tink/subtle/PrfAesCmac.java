package com.google.crypto.tink.subtle;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
@Immutable
/* loaded from: classes10.dex */
public final class PrfAesCmac implements Prf {

    /* renamed from: a  reason: collision with root package name */
    public final SecretKey f11037a;
    public byte[] b;
    public byte[] c;

    public PrfAesCmac(byte[] bArr) throws GeneralSecurityException {
        Validators.validateAesKeySize(bArr.length);
        this.f11037a = new SecretKeySpec(bArr, AesKey.ALGORITHM);
        a();
    }

    public static Cipher b() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
    }

    public final void a() throws GeneralSecurityException {
        Cipher b = b();
        b.init(1, this.f11037a);
        byte[] b2 = a.b(b.doFinal(new byte[16]));
        this.b = b2;
        this.c = a.b(b2);
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] bArr, int i) throws GeneralSecurityException {
        byte[] xor;
        if (i <= 16) {
            Cipher b = b();
            b.init(1, this.f11037a);
            int max = Math.max(1, (int) Math.ceil(bArr.length / 16.0d));
            if (max * 16 == bArr.length) {
                xor = Bytes.xor(bArr, (max - 1) * 16, this.b, 0, 16);
            } else {
                xor = Bytes.xor(a.a(Arrays.copyOfRange(bArr, (max - 1) * 16, bArr.length)), this.c);
            }
            byte[] bArr2 = new byte[16];
            for (int i2 = 0; i2 < max - 1; i2++) {
                bArr2 = b.doFinal(Bytes.xor(bArr2, 0, bArr, i2 * 16, 16));
            }
            return Arrays.copyOf(b.doFinal(Bytes.xor(xor, bArr2)), i);
        }
        throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
    }
}
