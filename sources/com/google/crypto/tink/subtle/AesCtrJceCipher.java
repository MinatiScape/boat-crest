package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes10.dex */
public final class AesCtrJceCipher implements IndCpaCipher {
    public static final ThreadLocal<Cipher> d = new a();

    /* renamed from: a  reason: collision with root package name */
    public final SecretKeySpec f11013a;
    public final int b;
    public final int c;

    /* loaded from: classes10.dex */
    public class a extends ThreadLocal<Cipher> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public AesCtrJceCipher(byte[] bArr, int i) throws GeneralSecurityException {
        Validators.validateAesKeySize(bArr.length);
        this.f11013a = new SecretKeySpec(bArr, AesKey.ALGORITHM);
        int blockSize = d.get().getBlockSize();
        this.c = blockSize;
        if (i >= 12 && i <= blockSize) {
            this.b = i;
            return;
        }
        throw new GeneralSecurityException("invalid IV size");
    }

    public final void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, byte[] bArr3, boolean z) throws GeneralSecurityException {
        Cipher cipher = d.get();
        byte[] bArr4 = new byte[this.c];
        System.arraycopy(bArr3, 0, bArr4, 0, this.b);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr4);
        if (z) {
            cipher.init(1, this.f11013a, ivParameterSpec);
        } else {
            cipher.init(2, this.f11013a, ivParameterSpec);
        }
        if (cipher.doFinal(bArr, i, i2, bArr2, i3) != i2) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.b;
        if (length >= i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            int length2 = bArr.length;
            int i2 = this.b;
            byte[] bArr3 = new byte[length2 - i2];
            a(bArr, i2, bArr.length - i2, bArr3, 0, bArr2, false);
            return bArr3;
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.b;
        if (length <= Integer.MAX_VALUE - i) {
            byte[] bArr2 = new byte[bArr.length + i];
            byte[] randBytes = Random.randBytes(i);
            System.arraycopy(randBytes, 0, bArr2, 0, this.b);
            a(bArr, 0, bArr.length, bArr2, this.b, randBytes, true);
            return bArr2;
        }
        throw new GeneralSecurityException("plaintext length can not exceed " + (Integer.MAX_VALUE - this.b));
    }
}
