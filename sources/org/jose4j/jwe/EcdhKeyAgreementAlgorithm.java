package org.jose4j.jwe;

import java.math.BigInteger;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.XECPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmAvailability;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwe.kdf.KdfUtil;
import org.jose4j.jwk.EcJwkGenerator;
import org.jose4j.jwk.OkpJwkGenerator;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.EcKeyUtil;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.keys.XDHKeyUtil;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UncheckedJoseException;
/* loaded from: classes13.dex */
public class EcdhKeyAgreementAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    public String e;

    public EcdhKeyAgreementAlgorithm() {
        this.e = "enc";
        setAlgorithmIdentifier(KeyManagementAlgorithmIdentifiers.ECDH_ES);
        setJavaAlgorithm("ECDH");
        setKeyType("EC");
        setKeyPersuasion(KeyPersuasion.ASYMMETRIC);
    }

    public final void a(ECKey eCKey) throws InvalidKeyException {
        if (EllipticCurves.SECP_256K1.equals(EllipticCurves.getName(eCKey.getParams().getCurve()))) {
            throw new InvalidKeyException("Use of the secp256k1 curve is not defined for ECDH-ES key agreement with JOSE.");
        }
    }

    public final void b(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws JoseException {
        EllipticCurve curve = eCPrivateKey.getParams().getCurve();
        ECPoint w = eCPublicKey.getW();
        BigInteger affineX = w.getAffineX();
        BigInteger affineY = w.getAffineY();
        BigInteger a2 = curve.getA();
        BigInteger b = curve.getB();
        BigInteger p = ((ECFieldFp) curve.getField()).getP();
        if (affineY.pow(2).mod(p).equals(affineX.pow(3).add(a2.multiply(affineX)).add(b).mod(p))) {
            return;
        }
        throw new InvalidKeyException("epk is invalid for " + EllipticCurves.getName(curve));
    }

    public final KeyAgreement c(PrivateKey privateKey, PublicKey publicKey, ProviderContext providerContext) throws JoseException {
        KeyAgreement e = e(providerContext.getSuppliedKeyProviderContext().getKeyAgreementProvider(), privateKey instanceof ECPrivateKey ? getJavaAlgorithm() : "XDH");
        try {
            e.init(privateKey);
            e.doPhase(publicKey, true);
            return e;
        } catch (java.security.InvalidKeyException e2) {
            throw new InvalidKeyException("Invalid Key for " + getJavaAlgorithm() + " key agreement - " + e2, e2);
        }
    }

    public final byte[] d(PrivateKey privateKey, PublicKey publicKey, ProviderContext providerContext) throws JoseException {
        return c(privateKey, publicKey, providerContext).generateSecret();
    }

    public final KeyAgreement e(String str, String str2) throws JoseException {
        try {
            return str == null ? KeyAgreement.getInstance(str2) : KeyAgreement.getInstance(str2, str);
        } catch (NoSuchAlgorithmException e) {
            throw new UncheckedJoseException("No " + str2 + " KeyAgreement available.", e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Cannot get " + str2 + " KeyAgreement with provider " + str, e2);
        }
    }

    public final byte[] f(ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) {
        return new KdfUtil(providerContext.getGeneralProviderContext().getMessageDigestProvider()).kdf(bArr, ByteUtil.bitLength(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength()), headers.getStringHeaderValue(this.e), headers.getStringHeaderValue(HeaderParameterNames.AGREEMENT_PARTY_U_INFO), headers.getStringHeaderValue(HeaderParameterNames.AGREEMENT_PARTY_V_INFO));
    }

    public ContentEncryptionKeys g(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, PublicJsonWebKey publicJsonWebKey, ProviderContext providerContext) throws JoseException {
        headers.setJwkHeaderValue(HeaderParameterNames.EPHEMERAL_PUBLIC_KEY, publicJsonWebKey);
        return new ContentEncryptionKeys(f(contentEncryptionKeyDescriptor, headers, d(publicJsonWebKey.getPrivateKey(), (PublicKey) key, providerContext), providerContext), null);
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return new EcKeyUtil().isAvailable() && AlgorithmAvailability.isAvailable("KeyAgreement", getJavaAlgorithm());
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public Key manageForDecrypt(CryptoPrimitive cryptoPrimitive, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        return new SecretKeySpec(f(contentEncryptionKeyDescriptor, headers, cryptoPrimitive.getKeyAgreement().generateSecret(), providerContext), contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm());
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        PublicJsonWebKey generateJwk;
        KeyValidationSupport.cekNotAllowed(bArr, getAlgorithmIdentifier());
        String keyPairGeneratorProvider = providerContext.getGeneralProviderContext().getKeyPairGeneratorProvider();
        SecureRandom secureRandom = providerContext.getSecureRandom();
        if (key instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) key;
            a(eCPublicKey);
            generateJwk = EcJwkGenerator.generateJwk(eCPublicKey.getParams(), keyPairGeneratorProvider, secureRandom);
        } else if (XDHKeyUtil.isXECPublicKey(key)) {
            generateJwk = OkpJwkGenerator.generateJwk(((XECPublicKey) key).getParams().getName(), keyPairGeneratorProvider, secureRandom);
        } else {
            throw new InvalidKeyException("Inappropriate key for ECDH: " + key);
        }
        return g(key, contentEncryptionKeyDescriptor, headers, generateJwk, providerContext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public CryptoPrimitive prepareForDecrypt(Key key, Headers headers, ProviderContext providerContext) throws JoseException {
        PublicKey publicKey = headers.getPublicJwkHeaderValue(HeaderParameterNames.EPHEMERAL_PUBLIC_KEY, providerContext.getGeneralProviderContext().getKeyFactoryProvider()).getPublicKey();
        PrivateKey privateKey = (PrivateKey) key;
        if (publicKey instanceof ECPublicKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey) key;
            a(eCPrivateKey);
            b((ECPublicKey) publicKey, eCPrivateKey);
        }
        return new CryptoPrimitive(c(privateKey, publicKey, providerContext));
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        if ((key instanceof ECPrivateKey) || XDHKeyUtil.isXECPrivateKey(key)) {
            return;
        }
        throw new InvalidKeyException("Decrypting with ECDH expects ECPrivateKey or XECPrivateKey but was given " + key);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        if ((key instanceof ECPublicKey) || XDHKeyUtil.isXECPublicKey(key)) {
            return;
        }
        throw new InvalidKeyException("Encrypting with ECDH expects ECPublicKey or XECPublicKey but was given " + key);
    }

    public EcdhKeyAgreementAlgorithm(String str) {
        this();
        this.e = str;
    }
}
