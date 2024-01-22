package org.jose4j.jwe;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class CipherUtil {
    public static Cipher a(String str, String str2) throws JoseException {
        try {
            return str2 == null ? Cipher.getInstance(str) : Cipher.getInstance(str, str2);
        } catch (NoSuchAlgorithmException e) {
            e = e;
            throw new JoseException(e.toString(), e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Unable to get a Cipher implementation of " + str + " using provider " + str2, e2);
        } catch (NoSuchPaddingException e3) {
            e = e3;
            throw new JoseException(e.toString(), e);
        }
    }
}
