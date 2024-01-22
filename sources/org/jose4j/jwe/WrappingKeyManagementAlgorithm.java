package org.jose4j.jwe;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwx.Headers;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public abstract class WrappingKeyManagementAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    public AlgorithmParameterSpec e;
    public final Logger log = LoggerFactory.getLogger(getClass());
    public boolean useSuppliedKeyProviderContext = true;

    public WrappingKeyManagementAlgorithm(String str, String str2) {
        setJavaAlgorithm(str);
        setAlgorithmIdentifier(str2);
    }

    public final ProviderContext.Context a(ProviderContext providerContext) {
        return this.useSuppliedKeyProviderContext ? providerContext.getSuppliedKeyProviderContext() : providerContext.getGeneralProviderContext();
    }

    public void b(Cipher cipher, int i, Key key) throws InvalidAlgorithmParameterException, InvalidKeyException {
        AlgorithmParameterSpec algorithmParameterSpec = this.e;
        if (algorithmParameterSpec == null) {
            cipher.init(i, key);
        } else {
            cipher.init(i, key, algorithmParameterSpec);
        }
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public Key manageForDecrypt(CryptoPrimitive cryptoPrimitive, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        Cipher cipher = cryptoPrimitive.getCipher();
        String contentEncryptionKeyAlgorithm = contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm();
        try {
            if (a(providerContext).getKeyDecipherModeOverride() == ProviderContext.KeyDecipherMode.DECRYPT) {
                return new SecretKeySpec(cipher.doFinal(bArr), contentEncryptionKeyAlgorithm);
            }
            return cipher.unwrap(bArr, contentEncryptionKeyAlgorithm, 3);
        } catch (Exception e) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Key unwrap/decrypt failed. Substituting a randomly generated CEK and proceeding. {}", ExceptionHelp.toStringWithCausesAndAbbreviatedStack(e, JsonWebEncryption.class));
            }
            return new SecretKeySpec(ByteUtil.randomBytes(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength()), contentEncryptionKeyAlgorithm);
        }
    }

    public ContentEncryptionKeys manageForEnc(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, byte[] bArr, ProviderContext providerContext) throws JoseException {
        Cipher a2 = CipherUtil.a(getJavaAlgorithm(), a(providerContext).getCipherProvider());
        try {
            b(a2, 3, key);
            return new ContentEncryptionKeys(bArr, a2.wrap(new SecretKeySpec(bArr, contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm())));
        } catch (InvalidAlgorithmParameterException e) {
            e = e;
            throw new JoseException("Unable to encrypt (" + a2.getAlgorithm() + ") the Content Encryption Key: " + e, e);
        } catch (InvalidKeyException e2) {
            throw new org.jose4j.lang.InvalidKeyException("Unable to encrypt (" + a2.getAlgorithm() + ") the Content Encryption Key: " + e2, e2);
        } catch (IllegalBlockSizeException e3) {
            e = e3;
            throw new JoseException("Unable to encrypt (" + a2.getAlgorithm() + ") the Content Encryption Key: " + e, e);
        }
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        if (bArr == null) {
            bArr = ByteUtil.randomBytes(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength());
        }
        return manageForEnc(key, contentEncryptionKeyDescriptor, bArr, providerContext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public CryptoPrimitive prepareForDecrypt(Key key, Headers headers, ProviderContext providerContext) throws JoseException {
        ProviderContext.Context a2 = a(providerContext);
        Cipher a3 = CipherUtil.a(getJavaAlgorithm(), a2.getCipherProvider());
        try {
            b(a3, a2.getKeyDecipherModeOverride() == ProviderContext.KeyDecipherMode.DECRYPT ? 2 : 4, key);
            return new CryptoPrimitive(a3);
        } catch (InvalidAlgorithmParameterException e) {
            throw new JoseException("Unable to initialize cipher (" + a3.getAlgorithm() + ") for key unwrap/decrypt - " + e, e);
        } catch (InvalidKeyException e2) {
            throw new org.jose4j.lang.InvalidKeyException("Unable to initialize cipher (" + a3.getAlgorithm() + ") for key unwrap/decrypt - " + e2, e2);
        }
    }

    public void setAlgorithmParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        this.e = algorithmParameterSpec;
    }
}
