package com.mappls.sdk.services.hmac;

import android.util.Base64;
import java.nio.charset.Charset;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes8.dex */
public class CryptoServiceImpl implements CryptoService {
    @Override // com.mappls.sdk.services.hmac.CryptoService
    public String decryptMessageUsingAES(String str, String str2) {
        if (str2 != null && !str2.trim().isEmpty()) {
            if (str != null && !str.trim().isEmpty()) {
                try {
                    byte[] decode = Base64.decode(str, 2);
                    byte[] decode2 = Base64.decode(str2, 2);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(decode, 0, decode.length, AesKey.ALGORITHM);
                    Cipher cipher = Cipher.getInstance(AesKey.ALGORITHM);
                    cipher.init(2, secretKeySpec);
                    return new String(cipher.doFinal(decode2));
                } catch (Exception unused) {
                    return null;
                }
            }
            throw new IllegalArgumentException("The symmetricKey cannot be null or empty.");
        }
        throw new IllegalArgumentException("The encryptedMessage cannot be null or empty.");
    }

    @Override // com.mappls.sdk.services.hmac.CryptoService
    public String encryptAndEncodeUsingAES(String str, String str2) {
        if (str2 != null && !str2.trim().isEmpty()) {
            if (str != null && !str.trim().isEmpty()) {
                try {
                    byte[] decode = Base64.decode(str, 2);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(decode, 0, decode.length, AesKey.ALGORITHM);
                    Cipher cipher = Cipher.getInstance(AesKey.ALGORITHM);
                    cipher.init(1, secretKeySpec);
                    return Base64.encodeToString(cipher.doFinal(str2.getBytes(Charset.forName("UTF-8"))), 2);
                } catch (Exception unused) {
                    return null;
                }
            }
            throw new IllegalArgumentException("The symmetricKey cannot be null or empty.");
        }
        throw new IllegalArgumentException("The messageToEncrypt cannot be null or empty.");
    }
}
