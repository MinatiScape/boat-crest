package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes10.dex */
public final class Hkdf {
    public static byte[] computeEciesHkdfSymmetricKey(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, byte[] bArr4, int i) throws GeneralSecurityException {
        return computeHkdf(str, Bytes.concat(bArr, bArr2), bArr3, bArr4, i);
    }

    public static byte[] computeHkdf(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, int i) throws GeneralSecurityException {
        Mac engineFactory = EngineFactory.MAC.getInstance(str);
        if (i <= engineFactory.getMacLength() * 255) {
            if (bArr2 != null && bArr2.length != 0) {
                engineFactory.init(new SecretKeySpec(bArr2, str));
            } else {
                engineFactory.init(new SecretKeySpec(new byte[engineFactory.getMacLength()], str));
            }
            byte[] bArr4 = new byte[i];
            engineFactory.init(new SecretKeySpec(engineFactory.doFinal(bArr), str));
            byte[] bArr5 = new byte[0];
            int i2 = 1;
            int i3 = 0;
            while (true) {
                engineFactory.update(bArr5);
                engineFactory.update(bArr3);
                engineFactory.update((byte) i2);
                bArr5 = engineFactory.doFinal();
                if (bArr5.length + i3 < i) {
                    System.arraycopy(bArr5, 0, bArr4, i3, bArr5.length);
                    i3 += bArr5.length;
                    i2++;
                } else {
                    System.arraycopy(bArr5, 0, bArr4, i3, i - i3);
                    return bArr4;
                }
            }
        } else {
            throw new GeneralSecurityException("size too large");
        }
    }
}
