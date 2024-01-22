package org.jose4j.jws;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public abstract class BaseSignatureAlgorithm extends AlgorithmInfo implements JsonWebSignatureAlgorithm {
    public final Logger e = LoggerFactory.getLogger(getClass());
    public AlgorithmParameterSpec f;

    public BaseSignatureAlgorithm(String str, String str2, String str3) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm(str2);
        setKeyPersuasion(KeyPersuasion.ASYMMETRIC);
        setKeyType(str3);
    }

    public final void a(Key key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("Key cannot be null");
        }
    }

    public final String b(Key key) {
        String str;
        if (key == null) {
            str = "key is null";
        } else {
            str = "algorithm=" + key.getAlgorithm();
        }
        return "The given key (" + str + ") is not valid ";
    }

    public final Signature c(ProviderContext providerContext) throws JoseException {
        ProviderContext.Context suppliedKeyProviderContext = providerContext.getSuppliedKeyProviderContext();
        String signatureProvider = suppliedKeyProviderContext.getSignatureProvider();
        String javaAlgorithm = getJavaAlgorithm();
        ProviderContext.SignatureAlgorithmOverride signatureAlgorithmOverride = suppliedKeyProviderContext.getSignatureAlgorithmOverride();
        if (signatureAlgorithmOverride != null && signatureAlgorithmOverride.getAlgorithmName() != null) {
            javaAlgorithm = signatureAlgorithmOverride.getAlgorithmName();
        }
        try {
            Signature signature = signatureProvider == null ? Signature.getInstance(javaAlgorithm) : Signature.getInstance(javaAlgorithm, signatureProvider);
            AlgorithmParameterSpec algorithmParameterSpec = this.f;
            if (signatureAlgorithmOverride != null) {
                algorithmParameterSpec = signatureAlgorithmOverride.getAlgorithmParameterSpec();
            }
            if (algorithmParameterSpec != null) {
                try {
                    signature.setParameter(algorithmParameterSpec);
                } catch (UnsupportedOperationException e) {
                    if (this.e.isDebugEnabled()) {
                        Logger logger = this.e;
                        logger.debug("Unable to set algorithm parameter spec on Signature (java algorithm name: " + javaAlgorithm + ") so ignoring the UnsupportedOperationException and relying on the default parameters.", (Throwable) e);
                    }
                }
            }
            return signature;
        } catch (InvalidAlgorithmParameterException e2) {
            throw new JoseException("Invalid algorithm parameter (" + this.f + ") for: " + javaAlgorithm, e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new JoseException("Unable to get an implementation of algorithm name: " + javaAlgorithm, e3);
        } catch (NoSuchProviderException e4) {
            throw new JoseException("Unable to get an implementation of " + javaAlgorithm + " for provider " + signatureProvider, e4);
        }
    }

    public final void d(Signature signature, Key key, ProviderContext providerContext) throws InvalidKeyException {
        try {
            PrivateKey privateKey = (PrivateKey) key;
            SecureRandom secureRandom = providerContext.getSecureRandom();
            if (secureRandom == null) {
                signature.initSign(privateKey);
            } else {
                signature.initSign(privateKey, secureRandom);
            }
        } catch (java.security.InvalidKeyException e) {
            throw new InvalidKeyException(b(key) + "for " + getJavaAlgorithm(), e);
        }
    }

    public final void e(Signature signature, Key key) throws InvalidKeyException {
        try {
            signature.initVerify((PublicKey) key);
        } catch (java.security.InvalidKeyException e) {
            throw new InvalidKeyException(b(key) + "for " + getJavaAlgorithm(), e);
        }
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        try {
            return c(new ProviderContext()) != null;
        } catch (Exception e) {
            Logger logger = this.e;
            logger.debug(getAlgorithmIdentifier() + " via " + getJavaAlgorithm() + " is NOT available from the underlying JCE (" + ExceptionHelp.toStringWithCauses(e) + ").");
            return false;
        }
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public CryptoPrimitive prepareForSign(Key key, ProviderContext providerContext) throws JoseException {
        Signature c = c(providerContext);
        d(c, key, providerContext);
        return new CryptoPrimitive(c);
    }

    public void setAlgorithmParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        this.f = algorithmParameterSpec;
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public byte[] sign(CryptoPrimitive cryptoPrimitive, byte[] bArr) throws JoseException {
        Signature signature = cryptoPrimitive.getSignature();
        try {
            signature.update(bArr);
            return signature.sign();
        } catch (SignatureException e) {
            throw new JoseException("Problem creating signature.", e);
        }
    }

    public abstract void validatePrivateKey(PrivateKey privateKey) throws InvalidKeyException;

    public abstract void validatePublicKey(PublicKey publicKey) throws InvalidKeyException;

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public void validateSigningKey(Key key) throws InvalidKeyException {
        a(key);
        try {
            validatePrivateKey((PrivateKey) key);
        } catch (ClassCastException e) {
            throw new InvalidKeyException(b(key) + "(not a private key or is the wrong type of key) for " + getJavaAlgorithm() + " / " + getAlgorithmIdentifier() + HexStringBuilder.DEFAULT_SEPARATOR + e);
        }
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public void validateVerificationKey(Key key) throws InvalidKeyException {
        a(key);
        try {
            validatePublicKey((PublicKey) key);
        } catch (ClassCastException e) {
            throw new InvalidKeyException(b(key) + "(not a public key or is the wrong type of key) for " + getJavaAlgorithm() + MqttTopic.TOPIC_LEVEL_SEPARATOR + getAlgorithmIdentifier() + HexStringBuilder.DEFAULT_SEPARATOR + e);
        }
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public boolean verifySignature(byte[] bArr, Key key, byte[] bArr2, ProviderContext providerContext) throws JoseException {
        Signature c = c(providerContext);
        e(c, key);
        try {
            c.update(bArr2);
            return c.verify(bArr);
        } catch (SignatureException e) {
            if (this.e.isDebugEnabled()) {
                Logger logger = this.e;
                logger.debug("Problem verifying " + getAlgorithmIdentifier() + " signature: " + ExceptionHelp.toStringWithCauses(e));
                return false;
            }
            return false;
        }
    }
}
