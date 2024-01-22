package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes10.dex */
public final class AesEaxJce implements Aead {
    public static final ThreadLocal<Cipher> e = new a();
    public static final ThreadLocal<Cipher> f = new b();

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11014a;
    public final byte[] b;
    public final SecretKeySpec c;
    public final int d;

    /* loaded from: classes10.dex */
    public class a extends ThreadLocal<Cipher> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/ECB/NOPADDING");
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b extends ThreadLocal<Cipher> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/CTR/NOPADDING");
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public AesEaxJce(byte[] bArr, int i) throws GeneralSecurityException {
        if (i != 12 && i != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.d = i;
        Validators.validateAesKeySize(bArr.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, AesKey.ALGORITHM);
        this.c = secretKeySpec;
        Cipher cipher = e.get();
        cipher.init(1, secretKeySpec);
        byte[] a2 = a(cipher.doFinal(new byte[16]));
        this.f11014a = a2;
        this.b = a(a2);
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        while (i < 15) {
            int i2 = i + 1;
            bArr2[i] = (byte) (((bArr[i] << 1) ^ ((bArr[i2] & 255) >>> 7)) & 255);
            i = i2;
        }
        bArr2[15] = (byte) ((bArr[15] << 1) ^ ((bArr[0] & 128) != 0 ? 135 : 0));
        return bArr2;
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    public final byte[] b(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(d(bArr2, this.f11014a));
        }
        byte[] doFinal = cipher.doFinal(bArr2);
        int i4 = 0;
        while (i3 - i4 > 16) {
            for (int i5 = 0; i5 < 16; i5++) {
                doFinal[i5] = (byte) (doFinal[i5] ^ bArr[(i2 + i4) + i5]);
            }
            doFinal = cipher.doFinal(doFinal);
            i4 += 16;
        }
        return cipher.doFinal(d(doFinal, c(Arrays.copyOfRange(bArr, i4 + i2, i2 + i3))));
    }

    public final byte[] c(byte[] bArr) {
        if (bArr.length == 16) {
            return d(bArr, this.f11014a);
        }
        byte[] copyOf = Arrays.copyOf(this.b, 16);
        for (int i = 0; i < bArr.length; i++) {
            copyOf[i] = (byte) (copyOf[i] ^ bArr[i]);
        }
        copyOf[bArr.length] = (byte) (copyOf[bArr.length] ^ 128);
        return copyOf;
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = (bArr.length - this.d) - 16;
        if (length >= 0) {
            Cipher cipher = e.get();
            cipher.init(1, this.c);
            byte[] b2 = b(cipher, 0, bArr, 0, this.d);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr3 = bArr2;
            byte[] b3 = b(cipher, 1, bArr3, 0, bArr3.length);
            byte[] b4 = b(cipher, 2, bArr, this.d, length);
            int length2 = bArr.length - 16;
            byte b5 = 0;
            for (int i = 0; i < 16; i++) {
                b5 = (byte) (b5 | (((bArr[length2 + i] ^ b3[i]) ^ b2[i]) ^ b4[i]));
            }
            if (b5 == 0) {
                Cipher cipher2 = f.get();
                cipher2.init(1, this.c, new IvParameterSpec(b2));
                return cipher2.doFinal(bArr, this.d, length);
            }
            throw new AEADBadTagException("tag mismatch");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.d;
        if (length <= (Integer.MAX_VALUE - i) - 16) {
            byte[] bArr3 = new byte[bArr.length + i + 16];
            byte[] randBytes = Random.randBytes(i);
            System.arraycopy(randBytes, 0, bArr3, 0, this.d);
            Cipher cipher = e.get();
            cipher.init(1, this.c);
            byte[] b2 = b(cipher, 0, randBytes, 0, randBytes.length);
            byte[] bArr4 = bArr2 == null ? new byte[0] : bArr2;
            byte[] b3 = b(cipher, 1, bArr4, 0, bArr4.length);
            Cipher cipher2 = f.get();
            cipher2.init(1, this.c, new IvParameterSpec(b2));
            cipher2.doFinal(bArr, 0, bArr.length, bArr3, this.d);
            byte[] b4 = b(cipher, 2, bArr3, this.d, bArr.length);
            int length2 = bArr.length + this.d;
            for (int i2 = 0; i2 < 16; i2++) {
                bArr3[length2 + i2] = (byte) ((b3[i2] ^ b2[i2]) ^ b4[i2]);
            }
            return bArr3;
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
