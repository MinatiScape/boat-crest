package org.jose4j.jwe;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.GCMParameterSpec;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
/* loaded from: classes13.dex */
public class SimpleAeadCipher {
    public static final String GCM_TRANSFORMATION_NAME = "AES/GCM/NoPadding";

    /* renamed from: a  reason: collision with root package name */
    public String f15518a;
    public int b;

    /* loaded from: classes13.dex */
    public static class CipherOutput {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f15519a;
        public byte[] b;

        public byte[] getCiphertext() {
            return this.f15519a;
        }

        public byte[] getTag() {
            return this.b;
        }
    }

    public SimpleAeadCipher(String str, int i) {
        this.f15518a = str;
        this.b = i;
    }

    public final void a(Cipher cipher, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        cipher.updateAAD(bArr);
    }

    public byte[] decrypt(Key key, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, String str) throws JoseException {
        return decrypt(bArr2, bArr3, bArr4, getInitialisedCipher(key, bArr, 2, str));
    }

    public CipherOutput encrypt(Key key, byte[] bArr, byte[] bArr2, byte[] bArr3, String str) throws JoseException {
        Cipher initialisedCipher = getInitialisedCipher(key, bArr, 1, str);
        a(initialisedCipher, bArr3);
        try {
            byte[] doFinal = initialisedCipher.doFinal(bArr2);
            CipherOutput cipherOutput = new CipherOutput();
            int length = doFinal.length - this.b;
            cipherOutput.f15519a = ByteUtil.subArray(doFinal, 0, length);
            cipherOutput.b = ByteUtil.subArray(doFinal, length, this.b);
            return cipherOutput;
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new JoseException(e.toString(), e);
        }
    }

    public Cipher getInitialisedCipher(Key key, byte[] bArr, int i, String str) throws JoseException {
        Cipher a2 = CipherUtil.a(this.f15518a, str);
        try {
            a2.init(i, key, new GCMParameterSpec(ByteUtil.bitLength(this.b), bArr));
            return a2;
        } catch (InvalidAlgorithmParameterException e) {
            throw new JoseException(e.toString(), e);
        } catch (InvalidKeyException e2) {
            throw new JoseException("Invalid key for " + this.f15518a, e2);
        }
    }

    public boolean isAvailable(Logger logger, int i, int i2, String str) {
        if (CipherStrengthSupport.isAvailable(this.f15518a, i)) {
            byte[] bArr = {com.htsmart.wristband2.a.a.a.J1, 108, 97, 105, 110, 116, 101, 120, 116};
            byte[] bArr2 = {97, 97, 100};
            byte[] randomBytes = ByteUtil.randomBytes(i);
            try {
                encrypt(new AesKey(randomBytes), ByteUtil.randomBytes(i2), bArr, bArr2, null);
                return true;
            } catch (Throwable th) {
                logger.debug("{} is not available ({}).", str, ExceptionHelp.toStringWithCauses(th));
            }
        }
        return false;
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, Cipher cipher) throws JoseException {
        a(cipher, bArr3);
        try {
            return cipher.doFinal(ByteUtil.concat(bArr, bArr2));
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new JoseException(e.toString(), e);
        }
    }
}
