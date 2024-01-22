package org.bouncycastle.openssl.jcajce;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
/* loaded from: classes13.dex */
public class JcaPEMKeyConverter {
    public static final Map b;

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15206a = new DefaultJcaJceHelper();

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(X9ObjectIdentifiers.id_ecPublicKey, "ECDSA");
        hashMap.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        hashMap.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }

    public final KeyFactory a(AlgorithmIdentifier algorithmIdentifier) throws NoSuchAlgorithmException, NoSuchProviderException {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        String str = (String) b.get(algorithm);
        if (str == null) {
            str = algorithm.getId();
        }
        try {
            return this.f15206a.createKeyFactory(str);
        } catch (NoSuchAlgorithmException e) {
            if (str.equals("ECDSA")) {
                return this.f15206a.createKeyFactory("EC");
            }
            throw e;
        }
    }

    public KeyPair getKeyPair(PEMKeyPair pEMKeyPair) throws PEMException {
        try {
            KeyFactory a2 = a(pEMKeyPair.getPrivateKeyInfo().getPrivateKeyAlgorithm());
            return new KeyPair(a2.generatePublic(new X509EncodedKeySpec(pEMKeyPair.getPublicKeyInfo().getEncoded())), a2.generatePrivate(new PKCS8EncodedKeySpec(pEMKeyPair.getPrivateKeyInfo().getEncoded())));
        } catch (Exception e) {
            throw new PEMException("unable to convert key pair: " + e.getMessage(), e);
        }
    }

    public PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo) throws PEMException {
        try {
            return a(privateKeyInfo.getPrivateKeyAlgorithm()).generatePrivate(new PKCS8EncodedKeySpec(privateKeyInfo.getEncoded()));
        } catch (Exception e) {
            throw new PEMException("unable to convert key pair: " + e.getMessage(), e);
        }
    }

    public PublicKey getPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws PEMException {
        try {
            return a(subjectPublicKeyInfo.getAlgorithm()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (Exception e) {
            throw new PEMException("unable to convert key pair: " + e.getMessage(), e);
        }
    }

    public JcaPEMKeyConverter setProvider(String str) {
        this.f15206a = new NamedJcaJceHelper(str);
        return this;
    }

    public JcaPEMKeyConverter setProvider(Provider provider) {
        this.f15206a = new ProviderJcaJceHelper(provider);
        return this;
    }
}
