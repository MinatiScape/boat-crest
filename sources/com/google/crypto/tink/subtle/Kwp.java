package com.google.crypto.tink.subtle;

import com.google.crypto.tink.KeyWrap;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes10.dex */
public class Kwp implements KeyWrap {
    public static final byte[] b = {-90, 89, 89, -90};

    /* renamed from: a  reason: collision with root package name */
    public final SecretKey f11036a;

    public Kwp(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != 16 && bArr.length != 32) {
            throw new GeneralSecurityException("Unsupported key length");
        }
        this.f11036a = new SecretKeySpec(bArr, AesKey.ALGORITHM);
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr2.length > 8 && bArr2.length <= 2147483631 && bArr.length == 8) {
            int c = c(bArr2.length);
            byte[] bArr3 = new byte[c];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, 8, bArr2.length);
            int i = 1;
            int i2 = (c / 8) - 1;
            Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
            engineFactory.init(1, this.f11036a);
            byte[] bArr4 = new byte[16];
            System.arraycopy(bArr3, 0, bArr4, 0, 8);
            int i3 = 0;
            while (i3 < 6) {
                int i4 = 0;
                while (i4 < i2) {
                    int i5 = i4 + 1;
                    int i6 = i5 * 8;
                    System.arraycopy(bArr3, i6, bArr4, 8, 8);
                    engineFactory.doFinal(bArr4, 0, 16, bArr4);
                    int i7 = (i3 * i2) + i4 + i;
                    for (int i8 = 0; i8 < 4; i8++) {
                        int i9 = 7 - i8;
                        bArr4[i9] = (byte) (((byte) (i7 & 255)) ^ bArr4[i9]);
                        i7 >>>= 8;
                    }
                    System.arraycopy(bArr4, 8, bArr3, i6, 8);
                    i4 = i5;
                    i = 1;
                }
                i3++;
                i = 1;
            }
            System.arraycopy(bArr4, 0, bArr3, 0, 8);
            return bArr3;
        }
        throw new GeneralSecurityException("computeW called with invalid parameters");
    }

    public final byte[] b(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length >= 24 && bArr.length % 8 == 0) {
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            int length = (copyOf.length / 8) - 1;
            Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
            engineFactory.init(2, this.f11036a);
            byte[] bArr2 = new byte[16];
            System.arraycopy(copyOf, 0, bArr2, 0, 8);
            for (int i = 5; i >= 0; i--) {
                for (int i2 = length - 1; i2 >= 0; i2--) {
                    int i3 = (i2 + 1) * 8;
                    System.arraycopy(copyOf, i3, bArr2, 8, 8);
                    int i4 = (i * length) + i2 + 1;
                    for (int i5 = 0; i5 < 4; i5++) {
                        int i6 = 7 - i5;
                        bArr2[i6] = (byte) (bArr2[i6] ^ ((byte) (i4 & 255)));
                        i4 >>>= 8;
                    }
                    engineFactory.doFinal(bArr2, 0, 16, bArr2);
                    System.arraycopy(bArr2, 8, copyOf, i3, 8);
                }
            }
            System.arraycopy(bArr2, 0, copyOf, 0, 8);
            return copyOf;
        }
        throw new GeneralSecurityException("Incorrect data size");
    }

    public final int c(int i) {
        return i + (7 - ((i + 7) % 8)) + 8;
    }

    @Override // com.google.crypto.tink.KeyWrap
    public byte[] unwrap(byte[] bArr) throws GeneralSecurityException {
        int i;
        if (bArr.length >= c(16)) {
            if (bArr.length <= c(4096)) {
                if (bArr.length % 8 == 0) {
                    byte[] b2 = b(bArr);
                    boolean z = true;
                    boolean z2 = false;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 4) {
                            break;
                        }
                        if (b[i2] != b2[i2]) {
                            z = false;
                        }
                        i2++;
                    }
                    int i3 = 0;
                    for (i = 4; i < 8; i++) {
                        i3 = (i3 << 8) + (b2[i] & 255);
                    }
                    if (c(i3) == b2.length) {
                        for (int i4 = i3 + 8; i4 < b2.length; i4++) {
                            if (b2[i4] != 0) {
                                z = false;
                            }
                        }
                        z2 = z;
                    }
                    if (z2) {
                        return Arrays.copyOfRange(b2, 8, i3 + 8);
                    }
                    throw new BadPaddingException("Invalid padding");
                }
                throw new GeneralSecurityException("Wrapped key size must be a multiple of 8 bytes");
            }
            throw new GeneralSecurityException("Wrapped key size is too large");
        }
        throw new GeneralSecurityException("Wrapped key size is too small");
    }

    @Override // com.google.crypto.tink.KeyWrap
    public byte[] wrap(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length >= 16) {
            if (bArr.length <= 4096) {
                byte[] bArr2 = new byte[8];
                byte[] bArr3 = b;
                System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
                for (int i = 0; i < 4; i++) {
                    bArr2[i + 4] = (byte) ((bArr.length >> ((3 - i) * 8)) & 255);
                }
                return a(bArr2, bArr);
            }
            throw new GeneralSecurityException("Key size of key to wrap too large");
        }
        throw new GeneralSecurityException("Key size of key to wrap too small");
    }
}
